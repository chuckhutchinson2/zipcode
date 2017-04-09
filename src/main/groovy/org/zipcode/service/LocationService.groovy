package org.zipcode.service

import org.zipcode.utils.IOUtils

class LocationService {
	
	def locations
	
	LocationService () {
		locations = IOUtils.load("locations.json")
	}
	
	def findAll(def key, def operator, def value) {
		def query = sprintf('{ it -> it.%s %s \'%s\' }', key, operator, value)
		
		return findAll (query)
	}
	
	def findAll( def query ) {
		def sh = new GroovyShell()
		def closure = sh.evaluate(query)
		
		return locations.findAll ( closure )
	}
 	
	def findZipCode (def zipCode) {
		return findAll ('zip', '==', zipCode)
	}
	
	def findCities (def stateName) {
		return findAll ('state', '==', stateName)
	}

}
