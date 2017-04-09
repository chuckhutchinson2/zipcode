package org.zipcode.loader

import org.zipcode.model.Location

class CSVLoader {

	def loader (def filename) {
		def locations = [];
		new File(filename).eachLine { line ->
			
			locations << new Location(line.split(','))
		}
		
		return locations
	}
}
