package com.flight.dao;

import java.sql.*;
import com.flight.bean.RouteBean;
import com.flight.dao.DBConnection;  // make sure package is correct

public class RouteDao {

    // Add Route
    public static String addRoute(RouteBean rb) {
        String result = "Error";
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("INSERT INTO route VALUES (?,?,?,?,?)")) {
            
            ps.setString(1, rb.getRouteId());
            ps.setString(2, rb.getFlightId());
            ps.setString(3, rb.getSource());
            ps.setString(4, rb.getDestination());
            ps.setInt(5, rb.getDistance());
            
            int i = ps.executeUpdate();
            result = (i == 1) ? "Success" : "Fail";
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }

    // View All Routes
    public static String getAllRoutesAsString() {
        StringBuilder sb = new StringBuilder("RouteID   FlightID   Source → Destination   Distance\n");
        sb.append("-------------------------------------------------------\n");
        
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM route");
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                sb.append(rs.getString("routeid")).append("   ")
                  .append(rs.getString("flightid")).append("   ")
                  .append(rs.getString("source")).append(" → ")
                  .append(rs.getString("destination")).append("   ")
                  .append(rs.getInt("distance")).append(" km\n");
            }
        } catch (Exception e) {
            sb.append("Error: ").append(e.getMessage());
        }
        return sb.toString();
    }

    // ✅ Delete Route
    public static String deleteRoute(String routeId) {
        String result = "Error";
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("DELETE FROM route WHERE routeid = ?")) {
            
            ps.setString(1, routeId);
            int i = ps.executeUpdate();
            result = (i == 1) ? "Success" : "Route Not Found";
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }
}
