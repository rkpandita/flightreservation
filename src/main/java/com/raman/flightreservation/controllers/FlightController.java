package com.raman.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.raman.flightreservation.entities.Flight;
import com.raman.flightreservation.repository.FlightRepository;
import com.raman.flightreservation.services.FlightService;
import com.raman.flightreservation.util.ReportUtil;

@Controller
public class FlightController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	FlightService service;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	private ServletContext context;

	@RequestMapping(value = "findFlights", method = RequestMethod.GET)
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate,
			ModelMap modelMap) {

		LOGGER.info("Inside findFlights(): from {} to {}, Departure Date is {} ", from, to, departureDate);
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("flights", flights);
		LOGGER.info("Flights available: {}", flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}
	
	@RequestMapping(value = "generateReport", method = RequestMethod.GET)
	public String generateReport() {
		List<Object[]> data = service.findOperatingAirlines();
		String path = context.getRealPath("/");
		reportUtil.generatePieChart(path, data);
		return "airlinesReport";
	}

}
