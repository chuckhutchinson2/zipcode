package org.location.service

import org.location.utils.IOUtils
import org.springframework.stereotype.Service

import groovy.json.JsonBuilder

@Service
class WeatherService {

	def weatherCodes
	
	def print(def data) {
		println (new JsonBuilder(data).toPrettyString())
	}
	
	WeatherService () {
		weatherCodes = IOUtils.load("weather-code.json")
		
		// print weatherCodes
	}
	
	def getWeatherCode(def city, def state) {
		return weatherCodes.findAll { it -> (it.state == state && it.city == city)  }.collect {it}.collect {it.weatherCode}.find {true}
	}
}
