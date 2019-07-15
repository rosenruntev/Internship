package tests;

import main.java.com.deltasource.hotelmanagement.core.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTests {

	@Test
	public void setNumberShouldThrowExceptionWhenSettingNegativeRoomNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			Room room = new Room(-1);
		});
	}

	@Test
	public void bookingABookedRoomShouldThrowException() {
		/*
		assertThrows(IllegalArgumentException.class, () -> {
			Room room = new Room(1, true);
			room.bookTheRoom();
		});

		 */
	}

	@Test
	public void clearBookingFromNotBookedRoomShouldThrowException() {
		/*
		assertThrows(IllegalArgumentException.class, () -> {
			Room room = new Room(1, false);
			room.clearRoomBooking();
		});

		 */
	}
}
