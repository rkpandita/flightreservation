package com.raman.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raman.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
