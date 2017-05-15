package org.location.controller

import org.location.service.PlaceService
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
class PlaceController {

	@Autowired
	PlaceService placeService

	@RequestMapping(value = "/places", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String places() {
		return JsonOutput.toJson(placeService.getPlaces())
	}

	@RequestMapping(value = "/place/{place}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String place(@PathVariable String place) {
		return JsonOutput.toJson(placeService.findPlace(place))
	}
}
