package tests;

import core.Hotel;
import core.Manager;
import core.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HotelTests {

	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Hotel hotel = new Hotel(null);
		});
	}

	@Test
	public void hotelShouldNotHaveAvailableRoomsIfAllRoomsAreBooked() {
		Room firstRoom = new Room(1);
		Room secondRoom = new Room(2);
		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(firstRoom);
		hotel.getRooms().add(secondRoom);
		Manager manager = new Manager("Manager", hotel);

		manager.bookRoom(1);
		manager.bookRoom(2);

		assertEquals(0, manager.getAvailableRooms().size());
	}
}
