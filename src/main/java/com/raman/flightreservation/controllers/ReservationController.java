package com.raman.flightreservation.controllers;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.raman.flightreservation.entities.Flight;
import com.raman.flightreservation.repository.FlightRepository;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {

		Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new EntityNotFoundException());
		modelMap.addAttribute("flight", flight);
		return "login/completeReservation";

	}

}
