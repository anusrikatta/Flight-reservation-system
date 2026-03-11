package com.flight.bean;

public class UserBean {
	private int FlightID;
	private String FlightName;
	private String SeatingCapacity;
	private String ReservationCapacity;
	public int getFlightID() {
		return FlightID;
	}
	public void setFlightID(int flightID) {
		FlightID = flightID;
	}
	public String getFlightName() {
		return FlightName;
	}
	public void setFlightName(String flightName) {
		FlightName = flightName;
	}
	public String getSeatingCapacity() {
		return SeatingCapacity;
	}
	public void setSeatingCapacity(String seatingCapacity) {
		SeatingCapacity = seatingCapacity;
	}
	public String getReservationCapacity() {
		return ReservationCapacity;
	}
	public void setReservationCapacity(String reservationCapacity) {
		ReservationCapacity = reservationCapacity;
	}
	
}
