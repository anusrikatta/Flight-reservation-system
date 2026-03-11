package com.flight.dao;

import com.flight.bean.ReservationBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    // Book a ticket
    public String bookTicket(ReservationBean reservation) {
        String msg = "Booking Failed!";
        String query = "INSERT INTO reservation (ReservationID, PassengerID, FlightID, ScheduleID, RouteID, SeatNo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, reservation.getReservationId()); // could auto-generate
            ps.setString(2, reservation.getPassengerId());
            ps.setString(3, reservation.getFlightId());
            ps.setString(4, reservation.getScheduleId());
            ps.setString(5, reservation.getRouteID());
            ps.setInt(6, Integer.parseInt(reservation.getSeatNo()));

            int i = ps.executeUpdate();
            if (i > 0) msg = "Booking Successful!";

        } catch (Exception e) {
            msg = "Error: " + e.getMessage();
        }
        return msg;
    }

    // Get bookings for a specific passenger
    public List<ReservationBean> getBookingsByPassenger(String passengerId) {
        List<ReservationBean> bookings = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE PassengerID=?";
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, passengerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReservationBean r = new ReservationBean();
                r.setReservationId(rs.getString("ReservationID"));
                r.setPassengerId(rs.getString("PassengerID"));
                r.setFlightId(rs.getString("FlightID"));
                r.setScheduleId(rs.getString("ScheduleID"));
                r.setRouteID(rs.getString("RouteID"));
                r.setSeatNo(String.valueOf(rs.getInt("SeatNo")));
                bookings.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Get all reservations (for AdminDashboard)
    public List<ReservationBean> getAllReservations() {
        List<ReservationBean> list = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ReservationBean r = new ReservationBean();
                r.setReservationId(rs.getString("ReservationID"));
                r.setPassengerId(rs.getString("PassengerID"));
                r.setFlightId(rs.getString("FlightID"));
                r.setScheduleId(rs.getString("ScheduleID"));
                r.setRouteID(rs.getString("RouteID"));
                r.setSeatNo(String.valueOf(rs.getInt("SeatNo")));
                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
