package org.zipcode;

import static org.junit.Assert.*

import org.junit.Test
import org.zipcode.service.LocationService
import org.zipcode.utils.IOUtils

import groovy.json.JsonBuilder

class LocationServiceTest {
	
	def locationService 
	
	public print(def content) {
		println (new JsonBuilder(content).toPrettyString())
	}

	@Test
	public void findMarylandCities() {
		locationService = new LocationService()
		
		def marylandCities = locationService.findCities('MD')

		print marylandCities
	}
	
	@Test
	public void findZipCode20850() {
		locationService = new LocationService()
		
		def zip20850 = locationService.findZipCode('20850')
		
		print zip20850
	}
}
