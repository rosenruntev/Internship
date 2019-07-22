package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Hotel;
import eu.deltasource.internship.hotelmanagement.core.Manager;
import eu.deltasource.internship.hotelmanagement.core.Room;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;
import eu.deltasource.internship.hotelmanagement.core.commodities.BedType;
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
			new Manager(null);
		});
	}

	@Test
	public void assignHotelShouldThrowExceptionWhenHotelIsNull() {
		// then
		assertThrows(IllegalArgumentException.class, () -> {
			// given
			Manager manager = new Manager("Manager");

			// when
			manager.assignHotel(null);
		});
	}

	@Test
	public void roomShouldBeBookedAfterManagerBooksIt() {
		// given
		Room room = new Room(1);
		room.getCommodities().add(new Bed(1, BedType.SINGLE));

		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(room);

		Manager manager = new Manager("Manager");
		manager.assignHotel(hotel);

		LocalDate fromDate = LocalDate.of(2019, 7, 15);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// when
		manager.createBooking("1", fromDate, toDate, 1, "guestName");

		// then
		assertThat(room.isBooked(fromDate, toDate), equalTo(true));
	}
}
