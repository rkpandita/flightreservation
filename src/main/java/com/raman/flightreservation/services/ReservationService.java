package com.raman.flightreservation.services;

import com.raman.flightreservation.dto.ReservationRequest;
import com.raman.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
