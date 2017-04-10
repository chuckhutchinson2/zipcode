package org.location.controller


import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin(origins = "*")
public class HealthController {
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public @ResponseBody String health() {
		return "healthy"
	}
}