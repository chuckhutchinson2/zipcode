package org.location.service

import org.location.model.Location
import org.location.utils.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaceService {
	def places
	
	PlaceService() {
		places = IOUtils.load("state-coordinates.json")
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
