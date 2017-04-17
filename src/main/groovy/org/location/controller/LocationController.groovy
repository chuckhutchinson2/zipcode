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

import groovy.util.logging.Slf4j
import groovy.json.JsonOutput

@Slf4j
@RestController
@CrossOrigin(origins = "*")
class LocationController {

	@Autowired
	LocationService locationService

	@RequestMapping(value = "/states", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String states() {
		return JsonOutput.toJson(locationService.getStates())
	}
	
	@RequestMapping(value = "/locations/{zipCode}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String locations(@PathVariable String zipCode) {
		return JsonOutput.toJson(locationService.findZipCode(zipCode))
	}

	@RequestMapping(value = "/cities/{state}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String cities(@PathVariable String state) {
		return JsonOutput.toJson(locationService.findCities(state))
	}

	// /near/38.9896/77.4505/5
	@RequestMapping(value = "/near/{lat}/{lon}/{distance}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String near(@PathVariable String lat, @PathVariable String lon, @PathVariable Double distance) {
		log.info("lat " + lat + " lon " + lon + " distance " + distance)
		return JsonOutput.toJson(locationService.withinLatLon(lat, lon, distance))
	}
	
	@RequestMapping(value = "/near/{zipCode}/{distance}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String near(@PathVariable String zipCode,  @PathVariable Double distance) {
		def location = locationService.findZipCode(zipCode)
		
		return JsonOutput.toJson(locationService.within(location[0], distance))
	}
}
