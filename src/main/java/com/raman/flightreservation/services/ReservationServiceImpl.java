package com.raman.flightreservation.services;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Value("${com.raman.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Value("${com.raman.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;
	
	@Value("${com.raman.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;
	
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
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		// Logic to Make Payment via Payment Gateway
		/*
		 * request.getCardNumber(); 
		 * request.getNameOnTheCard(); 
		 * request.getExpiryDate();
		 * request.getSecurityCode();
		 */
		
		LOGGER.info("Inside bookFlight()");

		Long flightId = request.getFlightId();
		LOGGER.info("Fetching flight with Flight Id: {}", flightId);
		Flight flight = flightRepository.findById(flightId).orElseThrow(EntityNotFoundException::new);

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Saving passeger: {}", passenger);

		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving reservation: {}", reservation);

		Reservation savedReservation = reservationRepository.save(reservation);
		 
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		
		LOGGER.info("Generating the Itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), EMAIL_SUBJECT, EMAIL_BODY, filePath);

		return savedReservation;

	}

}
