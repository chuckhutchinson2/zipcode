package org.location.controller

import org.location.service.LocationService
import org.slf4j.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import groovy.json.JsonOutput
import groovy.util.logging.Slf4j

@Slf4j
@RestController
@CrossOrigin(origins = "*")
class LocationController {
	
	@Autowired
	LocationService locationService
	
	@RequestMapping(value = "/locations/{zipCode}", method=RequestMethod.GET, produces="application/json", consumes="application/json")
	public @ResponseBody String locations(@PathVariable String zipCode) {
		return locationService.findZipCode(zipCode)
	}
	
	@RequestMapping(value = "/cities/{state}", method=RequestMethod.GET, produces="application/json", consumes="application/json")
	public @ResponseBody String cities(@PathVariable String state) {
		return locationService.findCities(state)
	}

}
