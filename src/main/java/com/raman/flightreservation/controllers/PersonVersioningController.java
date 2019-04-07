package com.raman.flightreservation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raman.flightreservation.dto.Name;
import com.raman.flightreservation.dto.PersonV1;
import com.raman.flightreservation.dto.PersonV2;

@RestController
public class PersonVersioningController {

	// URI Versioning
	@GetMapping("/person/v1")
	public PersonV1 personV1() {
		return new PersonV1("Raman");
	}

	@GetMapping("/person/v2")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Raman", "Pandita"));
	}
	
	// Params Versioning
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Raman");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Raman", "Pandita"));
	}
	
	// Headers Versioning
	@GetMapping(value = "/person/header", headers = "API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Raman");
	}

	@GetMapping(value = "/person/header", headers = "API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Raman", "Pandita"));
	}
	
	// Media Type / Accept Header / Produces Versioning
	@GetMapping(value = "/person/produces", produces = "application/appV1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Raman");
	}

	@GetMapping(value = "/person/produces", produces = "application/appV2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Raman", "Pandita"));
	}
	
	
	
	
}
