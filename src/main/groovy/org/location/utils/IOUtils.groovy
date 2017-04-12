package org.location.utils


import org.springframework.core.io.ClassPathResource

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class IOUtils {

	static save(Object content, String filePath) {
		new File(filePath).write(new JsonBuilder(content).toPrettyString())
	}

	static Object load(String filePath) {
		return new JsonSlurper().parseText(new ClassPathResource(filePath).getInputStream().text)
	}
}
