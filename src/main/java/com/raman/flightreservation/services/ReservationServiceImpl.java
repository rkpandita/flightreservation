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
import com.raman.flightreservation.util.EmailUtil;
import com.raman.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
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

		Reservation savedReservation = reservationRepository.save(reservation);
		 
		String filePath = "C:/Users/a236534/Desktop/reservation" + savedReservation.getId() + ".pdf";
		String subject = "Itinerary details for " + savedReservation.getPassenger().getFirstName();
		String body = "Congratulations !! Your tickets are booked. " + "Please find your Itinerary attached. "
				+ "Thanks for flying with us :) ";
		
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		emailUtil.sendItinerary(passenger.getEmail(), subject, body, filePath);

		return savedReservation;

	}

}
