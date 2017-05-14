package org.location.controller

import org.location.service.StateService
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
class StateController {

	@Autowired
	StateService stateService

	@RequestMapping(value = "/state/states", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String states() {
		return JsonOutput.toJson(stateService.getStates())
	}

	@RequestMapping(value = "/state/state/{state}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String cities(@PathVariable String state) {
		return JsonOutput.toJson(stateService.findState(state))
	}
}
