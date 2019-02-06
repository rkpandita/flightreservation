package com.raman.flightreservation.controllers;

import javax.persistence.EntityNotFoundException;

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

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {

		Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new EntityNotFoundException());
		modelMap.addAttribute("flight", flight);
		return "completeReservation";

	}

	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {

		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation successfully created with Id: " + reservation.getId());
		return "reservationConfirmation";

	}

}
