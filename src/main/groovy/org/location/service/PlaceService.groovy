package org.location.service

import org.location.model.Location
import org.location.utils.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.util.logging.Slf4j

@Slf4j
@Service
class PlaceService {
	static def places
	
	PlaceService() {
		places = IOUtils.load("place-coordinates.json")
		
		new Timer().schedule({
			try {
				log.info("loading place-coordinates.json")
				def updatedPlaces =  IOUtils.loadUrl("https://raw.githubusercontent.com/chuckhutchinson2/zipcode/master/src/main/resources/place-coordinates.json")
				log.info("loaded place-coordinates.json")
				setPlaces(updatedPlaces);
			} catch (RuntimeException e) {
				log.error("error loading place-coordinates.json", e)
			}
		} as TimerTask, 600000, 300000)
	}
	
	def setPlaces(newPlaces) {
		places = newPlaces
	}
	
	def getPlaces() {
		return places.collect {it -> it.code} as SortedSet
	}
	
	def findAll(def key, def operator, def value) {
		def query = sprintf('{ it -> it.%s %s \'%s\' }', key, operator, value)
		
		return findAll (query)
	}
	
	def findAll( def query ) {
		def closure = new GroovyShell().evaluate(query)
		
		return places.findAll ( closure )
	}

	def findPlace (def placeCode) {
		return findAll ('code', '==', placeCode)
	}
}
