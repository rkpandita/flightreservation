package com.raman.flightreservation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.raman.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query("FROM Flight WHERE departureCity=:departureCity AND arrivalCity=:arrivalCity AND dateOfDeparture=:dateOfDeparture")
	//@Query("FROM Flight")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to,
					 @Param("dateOfDeparture") Date departureDate);

	@Query("SELECT operatingAirlines, COUNT(operatingAirlines) FROM Flight GROUP BY operatingAirlines")
	List<Object[]> findOperatingAirlines();

}
