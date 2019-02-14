package com.raman.flightreservation.controllers;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.raman.flightreservation.dto.ReservationRequest;
import com.raman.flightreservation.entities.Flight;
import com.raman.flightreservation.entities.Reservation;
import com.raman.flightreservation.repository.FlightRepository;
import com.raman.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;

	@RequestMapping(value = "/showCompleteReservation", method = RequestMethod.GET)
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {

		LOGGER.info("Method showCompleteReservation() invoked with Flight Id: {} ", flightId);
		Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new EntityNotFoundException());
		modelMap.addAttribute("flight", flight);
		LOGGER.info("Flight is: {} ", flight);
		return "completeReservation";

	}

	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {

		LOGGER.info("Method completeReservation() invoked with: {}", request);
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation successfully created with Id: " + reservation.getId());
		return "reservationConfirmation";

	}

}
