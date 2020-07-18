package com.aiko.apiolhovivo.util;

public class CalculateDistanceBetweenCoordinates {
	
	public static Double calculate (Double lat1, Double lng1, Double lat2, Double lng2) {
		double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    Double dist = earthRadius * c;

	    return dist;
	}
}
