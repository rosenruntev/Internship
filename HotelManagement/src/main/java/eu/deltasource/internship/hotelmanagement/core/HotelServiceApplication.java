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
		firstRoom.addCommodity(new Bed(BedType.DOUBLE));
		firstRoom.addCommodity(new Shower());
		firstRoom.addCommodity(new Toilet());
		Room secondRoom = new Room(2);
		secondRoom.addCommodity(new Bed(BedType.SINGLE));
		secondRoom.addCommodity(new Bed(BedType.SINGLE));
		secondRoom.addCommodity(new Bed(BedType.SINGLE));
		secondRoom.addCommodity(new Shower());
		secondRoom.addCommodity(new Toilet());
		secondRoom.addCommodity(new Toilet());
		Room thirdRoom = new Room(3);
		thirdRoom.addCommodity(new Bed(BedType.SINGLE));
		thirdRoom.addCommodity(new Bed(BedType.SINGLE));
		thirdRoom.addCommodity(new Shower());
		thirdRoom.addCommodity(new Toilet());

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
			LocalDate.of(2019, 7, 23), 2, "guest2");
		manager.createBooking("3", LocalDate.of(2019, 7, 19),
			LocalDate.of(2019, 7, 21), 2, "guest3");
	}
}
