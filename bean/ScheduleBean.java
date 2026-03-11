package com.flight.bean;

public class ScheduleBean {
private String Scheduleid;
private String FlightID;
private int TravelDuration;
private int AvailableDays;
public String getScheduleid() {
	return Scheduleid;
}
public void setScheduleid(String scheduleid) {
	Scheduleid = scheduleid;
}
public String getFlightID() {
	return FlightID;
}
public void setFlightID(String flightID) {
	FlightID = flightID;
}
public int getTravelDuration() {
	return TravelDuration;
}
public void setTravelDuration(int travelDuration) {
	TravelDuration = travelDuration;
}
public int getAvailableDays() {
	return AvailableDays;
}
public void setAvailableDays(int availableDays) {
	AvailableDays = availableDays;
}


}
