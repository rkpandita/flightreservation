package com.raman.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raman.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
