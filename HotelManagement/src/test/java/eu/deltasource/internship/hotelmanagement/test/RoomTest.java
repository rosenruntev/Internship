package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Booking;
import eu.deltasource.internship.hotelmanagement.core.Room;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {
	@Test
	public void setNumberShouldThrowExceptionWhenSettingNegativeRoomNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			Room room = new Room(-1);
		});
	}

	@Test
	public void roomShouldBeBookedAfterBookingIt() {
		// given
		Room room = new Room(1);
		room.getCommodities().add(new Bed(1, 1));
		LocalDate fromDate = LocalDate.of(2019, 7, 16);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// when
		room.createBooking(fromDate, toDate, 4, "guest name", "001");

		// then
		assertThat(room.isBooked(fromDate, toDate), equalTo(true));
	}

	@Test
	public void roomShouldBeFreeIfThereIsNotBookingForThatPeriod() {
		// given
		Room room = new Room(1);
		room.getCommodities().add(new Bed(1, 1));
		LocalDate fromDate = LocalDate.of(2019, 7, 16);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// then
		assertThat(room.isBooked(fromDate, toDate), equalTo(false));
	}

	@Test
	public void createBookingShouldCreateNewBooking() {
		// Given
		Room room = new Room(1);
		LocalDate fromDate = LocalDate.of(2019, 7, 16);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// When
		room.createBooking(fromDate, toDate, 4, "guest name", "001");

		// Then
		Booking booking = room.getBookings().get(0);
		assertTrue(booking.getFromDate().equals(fromDate));
		assertTrue(booking.getToDate().equals(toDate));
	}

	@Test
	public void createBookingShouldBeAbleToCreateABookingForADay() {
		// Given
		Room room = new Room(1);
		LocalDate fromDate = LocalDate.of(2019, 7, 20);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// When
		room.createBooking(fromDate, toDate, 1, "guest name", "001");

		// Then
		Booking booking = room.getBookings().get(0);
		assertTrue(booking.getFromDate().equals(fromDate));
		assertTrue(booking.getToDate().equals(toDate));
	}

	@Test
	public void removeBookingShouldBeAbleToRemoveBooking() {
		// Given
		Room room = new Room(1);
		LocalDate fromDate = LocalDate.of(2019, 7, 20);
		LocalDate toDate = LocalDate.of(2019, 7, 20);
		room.createBooking(fromDate, toDate, 1, "guest name", "001");

		// Then
		assertTrue(room.removeBooking(fromDate, toDate));
	}
}
