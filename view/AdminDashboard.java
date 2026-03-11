package com.flight.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.flight.bean.FlightBean;
import com.flight.bean.PassengerBean;
import com.flight.bean.ReservationBean;
import com.flight.bean.ScheduleBean;
import com.flight.dao.AdminDao;
import com.flight.dao.PassengerDao;
import com.flight.dao.ReservationDao;
import com.flight.dao.ScheduleDao;

public class AdminDashboard extends JFrame {

    public AdminDashboard(String adminName) {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Top welcome label
        JLabel welcomeLabel = new JLabel("Welcome " + adminName, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton addFlightBtn = new JButton("Add Flight");
        JButton viewFlightsBtn = new JButton("View All Flights");
        JButton deleteFlightBtn = new JButton("Delete Flight");
        JButton viewPassengersBtn = new JButton("View Passengers");
        JButton viewReservationsBtn = new JButton("View Reservations");
        JButton viewSchedulesBtn = new JButton("View Schedules");
        JButton addScheduleBtn = new JButton("Add Schedule");
        JButton deleteScheduleBtn = new JButton("Delete Schedule");
        JButton logoutBtn = new JButton("Logout");

        panel.add(addFlightBtn);
        panel.add(viewFlightsBtn);
        panel.add(deleteFlightBtn);
        panel.add(viewPassengersBtn);
        panel.add(viewReservationsBtn);
        panel.add(viewSchedulesBtn);
        panel.add(addScheduleBtn);
        panel.add(deleteScheduleBtn);
        panel.add(logoutBtn);

        add(panel, BorderLayout.CENTER);

        // -------- Button Actions --------

        // Add Flight
     // Add Flight
        addFlightBtn.addActionListener(e -> {
            try {
                JTextField flightIdField = new JTextField();
                JTextField flightNameField = new JTextField();
                JTextField seatCapField = new JTextField();
                JTextField reserveCapField = new JTextField();

                Object[] message = {
                    "Flight ID:", flightIdField,
                    "Flight Name:", flightNameField,
                    "Seating Capacity:", seatCapField,
                    "Reservation Capacity:", reserveCapField
                };

                int option = JOptionPane.showConfirmDialog(
                        null, message, "Add Flight", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String flightId = flightIdField.getText();
                    String flightName = flightNameField.getText();
                    int seatCap = Integer.parseInt(seatCapField.getText());
                    int reserveCap = Integer.parseInt(reserveCapField.getText());

                    FlightBean flight = new FlightBean();
                    flight.setFlightId(flightId);
                    flight.setFlightName(flightName);
                    flight.setSeatingCapacity(seatCap);
                    flight.setReservationCapacity(reserveCap);

                    AdminDao dao = new AdminDao();
                    String result = dao.addFlight(flight);

                    JOptionPane.showMessageDialog(null,
                            result.equals("Success") ? "Flight added successfully!" : result);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });


        // View Flights
        viewFlightsBtn.addActionListener(e -> {
            try {
                AdminDao dao = new AdminDao();
                List<FlightBean> flights = dao.getAllFlights();
                if (flights.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No flights found.");
                    return;
                }
                StringBuilder sb = new StringBuilder("Flights:\n");
                for (FlightBean f : flights) {
                    sb.append(f.getFlightId()).append(" - ")
                      .append(f.getFlightName()).append(" - ")
                      .append(f.getSeatingCapacity()).append(" seats, ")
                      .append(f.getReservationCapacity()).append(" reserved\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Delete Flight
        deleteFlightBtn.addActionListener(e -> {
            String flightId = JOptionPane.showInputDialog("Enter Flight ID to delete:");
            if (flightId != null && !flightId.trim().isEmpty()) {
                String result = AdminDao.deleteFlight(flightId);
                JOptionPane.showMessageDialog(null, result.equals("Success") ?
                        "Flight deleted successfully!" : "Flight not found or could not be deleted.");
            }
        });

        // View Passengers
        viewPassengersBtn.addActionListener(e -> {
            try {
                PassengerDao dao = new PassengerDao();
                List<PassengerBean> passengers = dao.getAllPassengers();
                if (passengers.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No passengers found.");
                    return;
                }
                StringBuilder sb = new StringBuilder("Passengers:\n");
                for (PassengerBean p : passengers) {
                    sb.append(p.getPassengerId()).append(" - ")
                      .append(p.getName()).append(" - ")
                      .append(p.getAge()).append(" years, ")
                      .append(p.getGender()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // View Reservations
        viewReservationsBtn.addActionListener(e -> {
            try {
                ReservationDao dao = new ReservationDao();
                List<ReservationBean> reservations = dao.getAllReservations();
                if (reservations.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No reservations found.");
                    return;
                }
                StringBuilder sb = new StringBuilder("Reservations:\n");
                for (ReservationBean r : reservations) {
                    sb.append("ResID: ").append(r.getReservationId())
                      .append(", Passenger: ").append(r.getPassengerId())
                      .append(", Flight: ").append(r.getFlightId())
                      .append(", Schedule: ").append(r.getScheduleId())
                      .append(", Route: ").append(r.getRouteID())
                      .append(", Seat: ").append(r.getSeatNo())
                      .append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // View Schedules
        viewSchedulesBtn.addActionListener(e -> {
            try {
                String schedules = ScheduleDao.getAllSchedulesAsString();
                JOptionPane.showMessageDialog(null, schedules);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Add Schedule
        addScheduleBtn.addActionListener(e -> {
            try {
                String scheduleId = JOptionPane.showInputDialog("Enter Schedule ID:");
                String flightId = JOptionPane.showInputDialog("Enter Flight ID:");
                String durationStr = JOptionPane.showInputDialog("Enter Travel Duration (mins):");
                String daysStr = JOptionPane.showInputDialog("Enter Available Days (1-7):");

                if (scheduleId != null && flightId != null && durationStr != null && daysStr != null) {
                    ScheduleBean sb = new ScheduleBean();
                    sb.setScheduleid(scheduleId);
                    sb.setFlightID(flightId);
                    sb.setTravelDuration(Integer.parseInt(durationStr));
                    sb.setAvailableDays(Integer.parseInt(daysStr));

                    String result = ScheduleDao.addSchedule(sb);
                    JOptionPane.showMessageDialog(null, result.equals("Success") ? 
                        "Schedule added successfully!" : result);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Delete Schedule
        deleteScheduleBtn.addActionListener(e -> {
            String scheduleId = JOptionPane.showInputDialog("Enter Schedule ID to delete:");
            if (scheduleId != null && !scheduleId.trim().isEmpty()) {
                String result = ScheduleDao.deleteSchedule(scheduleId);
                JOptionPane.showMessageDialog(null, result.equals("Success") ?
                        "Schedule deleted successfully!" : result);
            }
        });

        // Logout
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginPage().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard("Admin").setVisible(true));
    }
}
