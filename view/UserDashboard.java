package com.flight.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.flight.bean.FlightBean;
import com.flight.bean.ReservationBean;
import com.flight.dao.AdminDao;
import com.flight.dao.ReservationDao;

public class UserDashboard extends JFrame {

    public UserDashboard(String userName) {  // <-- pass username
        setTitle("User Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // --------- Welcome Label ---------
        JLabel welcomeLabel = new JLabel("Welcome " + userName, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBounds(100, 10, 300, 30);
        add(welcomeLabel);

        // --------- Buttons ---------
        JButton searchFlightBtn = new JButton("Search Flight");
        searchFlightBtn.setBounds(150, 60, 200, 30);
        add(searchFlightBtn);

        JButton bookTicketBtn = new JButton("Book Ticket");
        bookTicketBtn.setBounds(150, 110, 200, 30);
        add(bookTicketBtn);

        JButton myBookingsBtn = new JButton("My Bookings");
        myBookingsBtn.setBounds(150, 160, 200, 30);
        add(myBookingsBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(150, 210, 200, 30);
        add(logoutBtn);

        // -------------------- BUTTON ACTIONS --------------------

        // Search Flight
        searchFlightBtn.addActionListener(e -> {
            String flightId = JOptionPane.showInputDialog("Enter Flight ID to Search");
            if (flightId != null && !flightId.trim().isEmpty()) {
                FlightBean fb = AdminDao.selectByFlightId(flightId);
                if (fb != null && fb.getFlightId() != null) {
                    JOptionPane.showMessageDialog(null,
                            "Flight ID: " + fb.getFlightId() +
                            "\nName: " + fb.getFlightName() +
                            "\nSeating Capacity: " + fb.getSeatingCapacity() +
                            "\nReservation Capacity: " + fb.getReservationCapacity()
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "Flight not found!");
                }
            }
        });

        // Book Ticket
        bookTicketBtn.addActionListener(e -> {
            try {
                String reservationId = JOptionPane.showInputDialog("Enter Reservation ID");
                String passengerId = JOptionPane.showInputDialog("Enter Passenger ID");
                String flightId = JOptionPane.showInputDialog("Enter Flight ID");
                String scheduleId = JOptionPane.showInputDialog("Enter Schedule ID");
                String routeId = JOptionPane.showInputDialog("Enter Route ID");
                String seatNo = JOptionPane.showInputDialog("Enter Seat No");

                if (reservationId != null && passengerId != null && flightId != null
                        && scheduleId != null && routeId != null && seatNo != null) {

                    ReservationBean reservation = new ReservationBean();
                    reservation.setReservationId(reservationId);
                    reservation.setPassengerId(passengerId);
                    reservation.setFlightId(flightId);
                    reservation.setScheduleId(scheduleId);
                    reservation.setRouteID(routeId);
                    reservation.setSeatNo(seatNo);

                    ReservationDao dao = new ReservationDao();
                    String msg = dao.bookTicket(reservation);
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // My Bookings
        myBookingsBtn.addActionListener(e -> {
            String passengerId = JOptionPane.showInputDialog("Enter Passenger ID");
            if (passengerId != null && !passengerId.trim().isEmpty()) {
                ReservationDao dao = new ReservationDao();
                List<ReservationBean> bookingsList = dao.getBookingsByPassenger(passengerId);

                if (bookingsList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No bookings found for Passenger ID: " + passengerId);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (ReservationBean b : bookingsList) {
                        sb.append("Reservation ID: ").append(b.getReservationId())
                                .append("\nFlight: ").append(b.getFlightId())
                                .append("\nSchedule: ").append(b.getScheduleId())
                                .append("\nRoute: ").append(b.getRouteID())
                                .append("\nSeat: ").append(b.getSeatNo())
                                .append("\n----------------------\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
            }
        });

        // Logout
        logoutBtn.addActionListener(e -> {
            dispose(); // close dashboard
            new LoginPage().setVisible(true); // back to login
        });
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserDashboard("User").setVisible(true));
    }
}
