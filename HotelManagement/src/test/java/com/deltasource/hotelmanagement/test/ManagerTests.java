package tests;

import main.java.com.deltasource.hotelmanagement.core.Commodities.Bed;
import main.java.com.deltasource.hotelmanagement.core.Hotel;
import main.java.com.deltasource.hotelmanagement.core.Manager;
import main.java.com.deltasource.hotelmanagement.core.Room;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTests {

	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Manager manager = new Manager(null);
		});
	}

	@Test
	public void setHotelShouldThrowExceptionWhenHotelIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Manager manager = new Manager("Manager");

			manager.setHotel(null);
		});
	}

	@Test
	public void roomShouldBeBookedAfterManagerBooksIt() {
		Room room = new Room(1);
		Bed bed = new Bed(1, 1);
		room.getCommodities().add(bed);
		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(room);
		Manager manager = new Manager("Manager");
		manager.setHotel(hotel);

		manager.bookRoom(1, LocalDate.of(2019, 7, 15),
			LocalDate.of(2019, 7, 20), 5, 1, "guest", "000");

		assertEquals(true, room.isBooked(LocalDate.of(2019, 7, 15),
			LocalDate.of(2019, 7, 20), 5));
	}

	@Test
	public void bookRoomShouldThrowExceptionIfRoomIsAlreadyBooked() {
		/*
		Room room = new Room(1, true);
		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(room);
		Manager manager = new Manager("Manager");
		manager.setHotel(hotel);

		assertThrows(IllegalArgumentException.class, () -> {
			manager.bookRoom(1);
		});
		*/
	}
}
