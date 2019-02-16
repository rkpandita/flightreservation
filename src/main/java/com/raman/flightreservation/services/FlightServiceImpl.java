package com.raman.flightreservation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raman.flightreservation.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository repository;
	
	@Override
	public List<Object[]> findOperatingAirlines() {
		return repository.findOperatingAirlines();
	}
	
}
