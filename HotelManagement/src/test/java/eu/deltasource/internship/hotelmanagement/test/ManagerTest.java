package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Hotel;
import eu.deltasource.internship.hotelmanagement.core.Manager;
import eu.deltasource.internship.hotelmanagement.core.Room;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;
import eu.deltasource.internship.hotelmanagement.core.commodities.BedType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTest {
	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		// Then
		assertThrows(IllegalArgumentException.class, () -> {
			// When
			new Manager(null);
		});
	}

	@Test
	public void assignHotelShouldThrowExceptionWhenHotelIsNull() {
		// Then
		assertThrows(IllegalArgumentException.class, () -> {
			// Given
			Manager manager = new Manager("Manager");

			// When
			manager.assignHotel(null);
		});
	}

	@Test
	public void roomShouldBeBookedAfterManagerBooksIt() {
		// Given
		Room room = new Room(1);
		room.getCommodities().add(new Bed(BedType.SINGLE));

		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(room);

		Manager manager = new Manager("Manager");
		manager.assignHotel(hotel);

		LocalDate fromDate = LocalDate.of(2019, 7, 15);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// When
		manager.createBooking("1", fromDate, toDate, 1, "guestName");

		// Then
		assertThat(room.isBooked(fromDate, toDate), equalTo(true));
	}

	@Test
	public void findAvailableRoomsForIntervalShouldReturnAllAvailableRooms() {
		// Given
		Room firstRoom = new Room(1);
		firstRoom.addCommodity(new Bed(BedType.SINGLE));
		firstRoom.createBooking(LocalDate.of(2019, 7, 15),
			LocalDate.of(2019, 7, 20), 1, "guest name", "guest id");

		Room secondRoom = new Room(2);
		secondRoom.addCommodity(new Bed(BedType.SINGLE));

		Room thirdRoom = new Room(3);
		thirdRoom.addCommodity(new Bed(BedType.SINGLE));

		Hotel hotel = new Hotel("hotel");
		hotel.getRooms().add(firstRoom);
		hotel.getRooms().add(secondRoom);
		hotel.getRooms().add(thirdRoom);

		Manager manager = new Manager("manager");
		manager.assignHotel(hotel);

		// When
		LinkedHashMap<Integer, ArrayList<LocalDate[]>> availableRooms = manager
			.findAvailableRoomsForInterval(LocalDate.of(2019, 7, 15),
				LocalDate.of(2019, 7, 20), 1);

		// Then
		assertEquals(false, availableRooms.containsKey(1));
		assertEquals(true, availableRooms.containsKey(2));
		assertEquals(true, availableRooms.containsKey(3));
	}
}
