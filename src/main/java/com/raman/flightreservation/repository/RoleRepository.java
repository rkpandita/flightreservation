package com.raman.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raman.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
