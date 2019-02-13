package com.raman.flightreservation.util;

public interface EmailUtil {

	void sendItinerary(String toAddress, String subject, String body, String filePath);
	
}
