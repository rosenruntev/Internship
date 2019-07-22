package eu.deltasource.internship.hotelmanagement.core;

import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;
import eu.deltasource.internship.hotelmanagement.core.commodities.BedType;
import eu.deltasource.internship.hotelmanagement.core.commodities.Shower;
import eu.deltasource.internship.hotelmanagement.core.commodities.Toilet;

import java.time.LocalDate;

public class HotelServiceApplication {
	public static void main(String[] args) {
		// Creating manager
		Manager manager = new Manager("Manager");

		// Creating rooms and adding commodities
		Room firstRoom = new Room(1);
		firstRoom.getCommodities().add(new Bed(1, BedType.DOUBLE));
		firstRoom.getCommodities().add(new Shower(2));
		firstRoom.getCommodities().add(new Toilet(3));
		Room secondRoom = new Room(2);
		secondRoom.getCommodities().add(new Bed(1, BedType.SINGLE));
		secondRoom.getCommodities().add(new Bed(2, BedType.SINGLE));
		secondRoom.getCommodities().add(new Bed(3, BedType.SINGLE));
		secondRoom.getCommodities().add(new Shower(4));
		secondRoom.getCommodities().add(new Toilet(5));
		secondRoom.getCommodities().add(new Toilet(6));
		Room thirdRoom = new Room(3);
		thirdRoom.getCommodities().add(new Bed(1, BedType.SINGLE));
		thirdRoom.getCommodities().add(new Bed(2, BedType.SINGLE));
		thirdRoom.getCommodities().add(new Shower(3));
		thirdRoom.getCommodities().add(new Toilet(4));

		// Adding rooms to the hotel
		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(firstRoom);
		hotel.getRooms().add(secondRoom);
		hotel.getRooms().add(thirdRoom);
		// Setting manager's hotel
		manager.assignHotel(hotel);

		// Creating bookings
		manager.createBooking("1", LocalDate.of(2019, 7, 20),
			LocalDate.of(2019, 7, 25), 1, "guest1");
		manager.createBooking("2", LocalDate.of(2019, 7, 22),
			LocalDate.of(2019, 7, 22), 2, "guest2");
		manager.createBooking("3", LocalDate.of(2019, 7, 19),
			LocalDate.of(2019, 7, 21), 2, "guest3");
	}
}
