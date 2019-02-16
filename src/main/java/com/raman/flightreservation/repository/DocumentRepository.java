package com.raman.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raman.flightreservation.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
