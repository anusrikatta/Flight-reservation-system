package com.flight.dao;

import java.sql.*;
import com.flight.bean.ScheduleBean;
import com.flight.dao.DBConnection;  // make sure package matches

public class ScheduleDao {

    // Add Schedule
    public static String addSchedule(ScheduleBean sb) {
        String result = "Error";
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("INSERT INTO schedule VALUES (?,?,?,?)")) {
            
            ps.setString(1, sb.getScheduleid());
            ps.setString(2, sb.getFlightID());
            ps.setInt(3, sb.getTravelDuration());
            ps.setInt(4, sb.getAvailableDays());
            
            int i = ps.executeUpdate();
            result = (i == 1) ? "Success" : "Fail";
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }

    // Console-based display
    public static void displaySchedules() {
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM schedule");
             ResultSet rs = ps.executeQuery()) {

            System.out.printf("%-10s %-10s %-15s %-15s\n",
                    "ScheduleID", "FlightID", "TravelDuration", "AvailableDays");
            System.out.println("-----------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10s %-10s %-15d %-15d\n",
                        rs.getString("scheduleid"),
                        rs.getString("flightid"),
                        rs.getInt("travelduration"),
                        rs.getInt("availabledays"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GUI-friendly (returns string)
    public static String getAllSchedulesAsString() {
        StringBuilder sb = new StringBuilder();
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM schedule");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                sb.append("ScheduleID: ").append(rs.getString("scheduleid"))
                  .append(", FlightID: ").append(rs.getString("flightid"))
                  .append(", Duration: ").append(rs.getInt("travelduration")).append(" mins")
                  .append(", Available Days: ").append(rs.getInt("availabledays")).append("\n");
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
        return sb.length() > 0 ? sb.toString() : "No schedules found!";
    }

    // ✅ Delete Schedule
    public static String deleteSchedule(String scheduleId) {
        String result = "Error";
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("DELETE FROM schedule WHERE scheduleid=?")) {
            
            ps.setString(1, scheduleId);
            int i = ps.executeUpdate();
            result = (i == 1) ? "Success" : "Schedule Not Found";
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }
}
