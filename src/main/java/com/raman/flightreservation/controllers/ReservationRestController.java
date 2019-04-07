package com.raman.flightreservation.controllers;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raman.flightreservation.dto.ReservationUpdateRequest;
import com.raman.flightreservation.entities.Reservation;
import com.raman.flightreservation.repository.ReservationRepository;

@RestController
public class ReservationRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	ReservationRepository reservationRepository;

	// @GetMapping(value = "/reservations/{id}") -- Value attribute is an alias for path
	@GetMapping(path = "/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for {} ", id);
		return reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	
	@PutMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() for {} ", request);
		Reservation reservation = reservationRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
		reservation.setNoOfBags(request.getNoOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Reservation");
		return reservationRepository.save(reservation);
	}

}
