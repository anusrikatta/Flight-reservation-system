# Flight Reservation System (SkyBooker)

## Overview
SkyBooker is a Java-based desktop application designed for managing flight reservations. It provides an intuitive Graphical User Interface (GUI) built with Java Swing and connects to a MySQL database using JDBC. The system features role-based access control, allowing both Admins and Users to log in and interact with the application.

## Features
- **Role-Based Login System**: Separate login access for `Admin` and `User`.
- **Database Integration**: Seamless connection to a MySQL database for storing and retrieving user and flight data.
- **Admin Dashboard**: Specialized interface for administrators to manage flights.
- **User Dashboard**: Dedicated interface for regular users to view flights and manage their bookings.

## Technologies Used
- **Language**: Java
- **GUI Framework**: Java Swing / AWT
- **Database**: MySQL
- **Database Connector**: MySQL Connector/J (8.4.0)

## Project Structure
- `view/`: Contains the UI components (`LoginPage.java`, `AdminDashboard.java`, `UserDashboard.java`, `Main.java`).
- `dao/`: Data Access Object layer for handling database connections.
- `bean/`: Contains Java beans/models representing database entities (e.g., `Flight.java`).

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/anusrikatta/Flight-reservation-system.git
   ```

2. **Database Configuration**
   - Ensure you have MySQL Server installed and running.
   - Set up the database and required tables such as `users` and `flights`.
   - Update the database credentials (URL, username, password) in your Data Access Object (`DBConnection`) to match your local MySQL setup.

3. **Add Dependencies**
   - Ensure `mysql-connector-j-8.4.0.jar` is included in your project's classpath/build path.

4. **Run the Application**
   - To start the application, compile and run the main entry point, such as `LoginPage.java` or `Main.java` located in the `com.flight.view` package.
