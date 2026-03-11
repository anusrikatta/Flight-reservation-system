package com.flight.bean;

public class ReservationBean {
	 private String reservationId;
	    private String passengerId;
	    private String flightId;
	    private String scheduleId;
	    private String RouteID;
	    private String SeatNo;
		public String getReservationId() {
			return reservationId;
		}
		public void setReservationId(String reservationId) {
			this.reservationId = reservationId;
		}
		public String getPassengerId() {
			return passengerId;
		}
		public void setPassengerId(String passengerId) {
			this.passengerId = passengerId;
		}
		public String getFlightId() {
			return flightId;
		}
		public void setFlightId(String flightId) {
			this.flightId = flightId;
		}
		public String getScheduleId() {
			return scheduleId;
		}
		public void setScheduleId(String scheduleId) {
			this.scheduleId = scheduleId;
		}
		public String getRouteID() {
			return RouteID;
		}
		public void setRouteID(String routeID) {
			RouteID = routeID;
		}
		public String getSeatNo() {
			return SeatNo;
		}
		public void setSeatNo(String seatNo) {
			SeatNo = seatNo;
		}
		
}
