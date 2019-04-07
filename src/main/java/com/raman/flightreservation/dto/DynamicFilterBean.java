package com.raman.flightreservation.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("DynamicFB")
public class DynamicFilterBean {

	private String name;
	private String email;
	private String password;

	public DynamicFilterBean(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
