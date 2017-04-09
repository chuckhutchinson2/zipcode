package org.zipcode;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.zipcode.service.LocationService

import groovy.json.JsonBuilder

class LocationServiceTest {
	
	def locationService 
	
	public print(def content) {
		println (new JsonBuilder(content).toPrettyString())
	}
	
	@Before
	public void setup() {
		locationService = new LocationService()
	}
	
	@Test
	public void findAll() {
		def rockville = locationService.findAll("{ it -> it.state == 'MD' & it.city == 'Rockville' }")
		
		print rockville
	}

	@Test
	public void findMarylandCities() {
		def marylandCities = locationService.findCities('MD')

		print marylandCities
	}
	
	@Test
	public void findZipCode20850() {
		def zip20850 = locationService.findZipCode('20850')
		
		print zip20850
	}
	
	@Test
	public void within() {
		def zip20850 = locationService.findZipCode('20850')
		
		def marylandCities = locationService.findCities('MD')
		
		def within5MilesOf20850 = locationService.within(marylandCities, zip20850[0], 3.0)
		
		print within5MilesOf20850

	}
}
