package org.zipcode.model

class Location {
	String city
	String state
	String zip
	String areaCode
	String fips
	String county
	String timeZone
	String daylightSavingsTime
	String latitude
	String longitude
	
	Location(String[] elements) {
		city = elements[0]
		state = elements[1]
		zip = elements[2]
		areaCode = elements[3]
		fips = elements[4]
		county = elements[5]
		timeZone = elements[6]
		daylightSavingsTime = elements[7]
		latitude = elements[8]
		longitude = elements[9]
	}
}
