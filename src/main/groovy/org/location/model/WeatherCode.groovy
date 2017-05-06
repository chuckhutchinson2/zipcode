package org.location.model

class WeatherCode {
	String 	weatherCode
	String	city
	String	state
	
	WeatherCode() {	
	}
	
	WeatherCode(String[] elements) {
		weatherCode = elements[0]
		city = elements[1]
		state = elements[2]
	}
}
