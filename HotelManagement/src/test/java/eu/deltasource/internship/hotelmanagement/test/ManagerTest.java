package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Hotel;
import eu.deltasource.internship.hotelmanagement.core.Manager;
import eu.deltasource.internship.hotelmanagement.core.Room;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTest {
	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		// then
		assertThrows(IllegalArgumentException.class, () -> {
			// when
			Manager manager = new Manager(null);
		});
	}

	@Test
	public void setHotelShouldThrowExceptionWhenHotelIsNull() {
		// then
		assertThrows(IllegalArgumentException.class, () -> {
			// given
			Manager manager = new Manager("Manager");

			// when
			manager.setHotel(null);
		});
	}

	@Test
	public void roomShouldBeBookedAfterManagerBooksIt() {
		// given
		Room room = new Room(1);
		room.getCommodities().add(new Bed(1, 1));
		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(room);
		Manager manager = new Manager("Manager");
		manager.setHotel(hotel);
		LocalDate fromDate = LocalDate.of(2019, 7, 16);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// when
		manager.bookRoom(1, fromDate, toDate, 4, 1, "guest name", "001");

		// then
		assertThat(room.isBooked(fromDate, toDate), equalTo(true));
	}
}
