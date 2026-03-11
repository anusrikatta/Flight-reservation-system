package com.flight.dao;

import java.sql.*;
import com.flight.bean.FlightBean;

public class UserDao {
    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vasavi", "root", "root");
            System.out.println("Connected successfully...");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver not found: " + cnf);
        } catch (SQLException sql) {
            System.out.println("SQL Error: " + sql);
        }
        return con;
    }

    public static FlightBean selectFlight(String flightId) {
        FlightBean fb = null;
        try (Connection con = getCon()) {
            String query = "SELECT * FROM flights WHERE FlightID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, flightId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fb = new FlightBean();
                fb.setFlightId(rs.getString("FlightID"));
                fb.setFlightName(rs.getString("FlightName"));
                fb.setSeatingCapacity(rs.getInt("SeatingCapacity"));
                fb.setReservationCapacity(rs.getInt("ReservationCapacity"));
            }
        } catch (SQLException sql) {
            System.out.println("SQL Error in selectFlight: " + sql);
        }
        return fb;
    }
}
