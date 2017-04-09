package org.zipcode.service

import org.zipcode.utils.IOUtils

import org.zipcode.loader.CSVLoader

import groovy.json.JsonOutput
import spock.lang.*

class CSVLoaderTest extends Specification {

	def test() {
		
		when:
		
			CSVLoader loader = new CSVLoader()
		
			def locations = loader.loader("locations.csv")
					
		then:
			def json = JsonOutput.toJson(locations)
		
			println JsonOutput.prettyPrint(json)
			
			1 == 1
	}
}
