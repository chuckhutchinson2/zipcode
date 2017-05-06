package org.location.service;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.location.service.WeatherService

import groovy.json.JsonBuilder

class WeatherServiceTest {
	
	def weatherService 
	
	public print(def content) {
		// println (new JsonBuilder(content).toPrettyString())
	}
	
	@Before
	public void setup() {
		weatherService = new WeatherService()
	}
	

	@Test
	public void findRockville() {
		def rockvillesCode = weatherService.getWeatherCode('Rockville', 'MD')

		print rockvillesCode
	}
	
}
