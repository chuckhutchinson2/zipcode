package org.location.loader

import org.location.model.Location

class CSVLocationLoader {

	def loader (def filename) {
		def locations = [];
		new File(filename).eachLine { line ->
			
			locations << new Location(line.split(','))
		}
		
		return locations
	}
}
