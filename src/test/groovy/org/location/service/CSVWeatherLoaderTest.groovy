package org.location.service

import org.location.loader.CSVWeatherLoader
import org.location.utils.IOUtils

import groovy.json.JsonOutput
import spock.lang.*


class CSVLoaderTest extends Specification {

	def test() {
		
		when:
		
			CSVWeatherLoader loader = new CSVWeatherLoader()
		
			def locations = loader.loader("weather-code.csv")
			
			//IOUtils.save (locations, "weather-code.json")
		
		then:
			def json = JsonOutput.toJson(locations)
		
			println JsonOutput.prettyPrint(json)
			
			1 == 1
	}
}