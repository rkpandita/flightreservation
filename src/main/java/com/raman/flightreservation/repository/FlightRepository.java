package com.raman.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raman.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
