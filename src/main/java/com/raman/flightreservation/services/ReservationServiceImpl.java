package com.raman.flightreservation.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raman.flightreservation.dto.ReservationRequest;
import com.raman.flightreservation.entities.Flight;
import com.raman.flightreservation.entities.Passenger;
import com.raman.flightreservation.entities.Reservation;
import com.raman.flightreservation.repository.FlightRepository;
import com.raman.flightreservation.repository.PassengerRepository;
import com.raman.flightreservation.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Reservation bookFlight(ReservationRequest request) {

		// Logic to Make Payment via Payment Gateway
		/*
		 * request.getCardNumber(); 
		 * request.getNameOnTheCard(); 
		 * request.getExpiryDate();
		 * request.getSecurityCode();
		 */

		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new EntityNotFoundException());

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());

		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		return reservationRepository.save(reservation);

	}

}
