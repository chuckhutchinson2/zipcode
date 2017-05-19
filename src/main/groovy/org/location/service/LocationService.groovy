package org.location.service

import org.location.model.Location
import org.location.utils.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.util.logging.Slf4j
import groovy.util.logging.Slf4j

@Slf4j
@Service
class LocationService {
	def locations
	

	LocationService() {
		locations = IOUtils.load("locations.json")
		
		new Timer().schedule({
			try {
				log.info("loading locations.json")
				def updatedLocations =  IOUtils.loadUrl("https://raw.githubusercontent.com/chuckhutchinson2/zipcode/master/src/main/resources/locations.json")
				log.info("loaded locations.json")
				setLocations(updatedLocations);
			} catch (RuntimeException e) {
				log.error("error loading locations", e)
			}
		} as TimerTask, 600000, 300000)
	}
	
	def setLocations(updatedLocations) {
		locations = updatedLocations
	}
	
	def getStates() {
		return locations.collect {it -> it.state} as SortedSet
	}
	
	def findAll(def key, def operator, def value) {
		def query = sprintf('{ it -> it.%s %s \'%s\' }', key, operator, value)
		
		return findAll (query)
	}
	
	def findAll( def query ) {
		def closure = new GroovyShell().evaluate(query)
		
		return locations.findAll ( closure )
	}
 	
	def findZipCode (def zipCode) {
		return findAll ('zip', '==', zipCode)
	}
	
	def findCities (def stateName) {
		return findAll ('state', '==', stateName)
	}
	
	def withinLatLon(def lat, def lng, def distance)
	{
		def location = new Location()
		location.latitude = lat
		location.longitude = lng
		
		return within(location, distance)	
	}
	
	def within(def location, def distance) {
		return within(locations, location, distance)
	}
	
	def within(def places, def location, def distance) {
		return places.findAll { it -> (it.distance = distanceBetween (location, it)) <= distance }
	}

	def distanceBetween(def loc1, def loc2) {
		return distance(new Double(loc1.latitude), new Double(loc1.longitude), new Double(loc2.latitude), new Double(loc2.longitude), "N")
	}
	
	def distance(def lat1, def lon1, def lat2, def lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
