package org.zipcode;

import static org.junit.Assert.*

import org.junit.Test
import org.zipcode.utils.IOUtils

import groovy.json.JsonOutput

class LocationParserTest {

	@Test
	public void findMarylandCities() {
		def locations = IOUtils.load("locations.json")
		
		def marylandCities = locations.findAll {it.state == 'MD'}

		IOUtils.save(marylandCities, "maryland.json")
	}
	
	@Test
	public void findZipCode20850() {
		def locations = IOUtils.load("locations.json")
		
		def zip20850 = locations.findAll {it.zip == '20850'}

		IOUtils.save(zip20850, "20850.json")
	}
}
