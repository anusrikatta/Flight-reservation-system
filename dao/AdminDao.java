package com.flight.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.flight.bean.FlightBean;

public class AdminDao {

    public static Connection con = UserDao.getCon();
    public static PreparedStatement ps;
    public static ResultSet rs;

    // Add Flight (placeholder)
    public static String addFlight(FlightBean fb) {
        String str = "Error";
        try {
            ps = con.prepareStatement("INSERT INTO flight VALUES(?,?,?,?)");
            ps.setString(1, fb.getFlightId());
            ps.setString(2, fb.getFlightName());
            ps.setInt(3, fb.getSeatingCapacity());
            ps.setInt(4, fb.getReservationCapacity());

            int i = ps.executeUpdate();
            if (i == 1) return "Success";
            else return "Fail";
        } catch (SQLException sql) {
            System.out.println(sql);
            return str;
        }
    }

    // Select by Flight ID
    public static FlightBean selectByFlightId(String flightId) {
        FlightBean fb = null;
        try {
            ps = con.prepareStatement("SELECT * FROM flight WHERE flightid=?");
            ps.setString(1, flightId);
            rs = ps.executeQuery();

            if (rs.next()) {
                fb = new FlightBean();
                fb.setFlightId(rs.getString("flightid"));
                fb.setFlightName(rs.getString("flightname"));
                fb.setSeatingCapacity(rs.getInt("seatingcapacity"));
                fb.setReservationCapacity(rs.getInt("reservationcapacity"));
            }
        } catch (SQLException sql) {
            System.out.println(sql);
        }
        return fb;
    }

    // Delete Flight
    public static String deleteFlight(String flightId) {
        String str = "Error";
        try {
            ps = con.prepareStatement("DELETE FROM flight WHERE flightid=?");
            ps.setString(1, flightId);
            int i = ps.executeUpdate();
            if (i == 1) str = "Success";
            else str = "Fail";
        } catch (SQLException sql) {
            System.out.println(sql);
        }
        return str;
    }

    // Get All Flights
    public static List<FlightBean> getAllFlights() {
        List<FlightBean> flights = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM flight");
            rs = ps.executeQuery();
            while (rs.next()) {
                FlightBean f = new FlightBean();
                f.setFlightId(rs.getString("flightid"));
                f.setFlightName(rs.getString("flightname"));
                f.setSeatingCapacity(rs.getInt("seatingcapacity"));
                f.setReservationCapacity(rs.getInt("reservationcapacity"));
                flights.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flights;
    }
}
