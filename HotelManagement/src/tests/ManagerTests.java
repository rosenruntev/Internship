package tests;

import core.Hotel;
import core.Manager;
import core.Room;
import org.junit.jupiter.api.Test;

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
		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(room);
		Manager manager = new Manager("Manager");
		manager.setHotel(hotel);

		manager.bookRoom(1);

		assertEquals(true, room.isBooked());
	}

	@Test
	public void bookRoomShouldThrowExceptionIfRoomIsAlreadyBooked() {
		Room room = new Room(1, true);
		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(room);
		Manager manager = new Manager("Manager");
		manager.setHotel(hotel);

		assertThrows(IllegalArgumentException.class, () -> {
			manager.bookRoom(1);
		});
	}
}
