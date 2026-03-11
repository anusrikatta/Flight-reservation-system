package com.flight.view;


	import javax.swing.JOptionPane;



	import com.flight.bean.FlightBean;
	public class Flight {


	public static FlightBean add()

	{

	String flightid=JOptionPane.showInputDialog("Enter Flight ID");

	String flightname=JOptionPane.showInputDialog("Enter Flight Name");

	int seatingcapacity=Integer.parseInt(JOptionPane.showInputDialog("Enter Seating Capacity"));

	int reservationcapacity=Integer.parseInt(JOptionPane.showInputDialog("Enter Reservation Capacity"));

	FlightBean fb=new FlightBean();

	fb.setFlightId(flightid);

	fb.setFlightName(flightname);

	fb.setSeatingCapacity(seatingcapacity);

	fb.setReservationCapacity(reservationcapacity);

	return fb;

	}





	}

