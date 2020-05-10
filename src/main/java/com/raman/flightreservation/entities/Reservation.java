package com.raman.flightreservation.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

@Entity
//@ApiModel(description = "Reservation Info")
public class Reservation extends AbstractEntity {

	//@ApiModelProperty(notes = "Tells if the passenger has Checked In or not")
	private Boolean checkedIn;
	private int noOfBags;
	@OneToOne
	private Passenger passenger;
	@OneToOne
	private Flight flight;

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public int getNoOfBags() {
		return noOfBags;
	}

	public void setNoOfBags(int noOfBags) {
		this.noOfBags = noOfBags;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "Reservation [checkedIn=" + checkedIn + ", noOfBags=" + noOfBags + ", passenger=" + passenger
				+ ", flight=" + flight + "]";
	}
	
}
