package org.location.utils


import org.springframework.core.io.ClassPathResource

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import java.net.URL
import java.net.URLConnection

class IOUtils {

	static save(Object content, String filePath) {
		new File(filePath).write(new JsonBuilder(content).toPrettyString())
	}
	
	static Object loadUrl(String urlPath) {
		URL url = null
		InputStream iStream = null
		URLConnection connection = null
		try {
			url = new URL(urlPath);
			
			connection = url.openConnection()
			
			connection.setUseCaches(false)
			connection.setDefaultUseCaches(false)

			iStream = connection.getInputStream()
			
			new JsonSlurper().parseText(iStream.text)
		} finally {
			if (iStream != null) {
				iStream.close()
			}
		}
	}

	static Object load(String filePath) {
		return new JsonSlurper().parseText(new ClassPathResource(filePath).getInputStream().text)
	}
}
