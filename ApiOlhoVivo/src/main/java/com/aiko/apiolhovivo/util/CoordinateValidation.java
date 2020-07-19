package com.aiko.apiolhovivo.util;

public class CoordinateValidation {
	
	public static boolean validationLongitude(Double longitude) {
		if(longitude < -90 || longitude > 90 || longitude == null)
			return false;
		
		return true;
	}
	
	public static boolean validationLatitude (Double latitude) {
		if(latitude < -180 || latitude > 180 || latitude == null)
			return false;
		return true;
	}
	
	public static boolean validation(Double latitude, Double longitude) {
		
		if(validationLatitude(latitude) && validationLongitude(longitude))
			return true;
		return false;
		
		
	}
}
