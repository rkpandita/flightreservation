package com.raman.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raman.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
