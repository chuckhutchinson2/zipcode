package org.location.loader

import org.location.model.WeatherCode

class CSVWeatherLoader {

	def loader (def filename) {
		def locations = [];
		new File(filename).eachLine { line ->
			
			locations << new WeatherCode(line.split(','))
		}
		
		return locations
	}
}
