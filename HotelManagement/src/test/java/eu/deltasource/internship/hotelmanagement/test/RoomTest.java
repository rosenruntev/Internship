package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Booking;
import eu.deltasource.internship.hotelmanagement.core.Room;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;
import eu.deltasource.internship.hotelmanagement.core.commodities.BedType;
import eu.deltasource.internship.hotelmanagement.core.commodities.Shower;
import eu.deltasource.internship.hotelmanagement.core.commodities.Toilet;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {
	@Test
	public void setNumberShouldThrowExceptionWhenSettingNegativeRoomNumber() {
		// Then
		assertThrows(IllegalArgumentException.class, () -> {
			// When
			new Room(-1);
		});
	}

	@Test
	public void addCommodityShouldSetCorrectInventoryNumber() {
		// Given
		Room room = new Room(1);
		Bed bed = new Bed(BedType.SINGLE);
		Shower shower = new Shower();
		Toilet toilet = new Toilet();

		// When
		int bedInventoryNumber = room.addCommodity(bed);
		int showerInventoryNumber = room.addCommodity(shower);
		int toiletInventoryNumber = room.addCommodity(toilet);

		// Then
		assertThat(bed.getInventoryNumber(), equalTo(bedInventoryNumber));
		assertThat(shower.getInventoryNumber(), equalTo(showerInventoryNumber));
		assertThat(toilet.getInventoryNumber(), equalTo(toiletInventoryNumber));
	}

	@Test
	public void roomShouldBeBookedForPeriodAfterBookingIt() {
		// Given
		Room room = new Room(1);
		room.addCommodity(new Bed(BedType.SINGLE));
		LocalDate fromDate = LocalDate.of(2019, 7, 16);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// When
		room.createBooking(fromDate, toDate, 1, "guest name", "001");

		// Then
		assertThat(room.isBooked(fromDate, toDate), equalTo(true));
	}

	@Test
	public void roomShouldBeFreeIfThereIsNotBookingForThatPeriod() {
		// Given
		Room room = new Room(1);
		room.getCommodities().add(new Bed(BedType.SINGLE));
		LocalDate fromDate = LocalDate.of(2019, 7, 16);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// Then
		assertThat(room.isBooked(fromDate, toDate), equalTo(false));
	}

	@Test
	public void getBedsCapacityShouldReturnCorrectCapacity() {
		// Given
		Room room = new Room(1);

		// When
		room.addCommodity(new Bed(BedType.SINGLE));
		room.addCommodity(new Bed(BedType.DOUBLE));
		room.addCommodity(new Bed(BedType.KINGSIZE));

		// Then
		assertThat(room.getBedsCapacity(), equalTo(5));
	}


	@Test
	public void createBookingShouldCreateNewBooking() {
		// Given
		Room room = new Room(1);
		room.addCommodity(new Bed(BedType.SINGLE));
		LocalDate fromDate = LocalDate.of(2019, 7, 16);
		LocalDate toDate = LocalDate.of(2019, 7, 20);

		// When
		room.createBooking(fromDate, toDate, 1, "guest name", "001");

		// Then
		Booking booking = room.getBookings().get(0);
		assertTrue(booking.getFromDate().equals(fromDate));
		assertTrue(booking.getToDate().equals(toDate));
	}

	@Test
	public void createBookingShouldBeAbleToCreateABookingForADay() {
		// Given
		Room room = new Room(1);
		room.addCommodity(new Bed(BedType.SINGLE));
		LocalDate date = LocalDate.of(2019, 7, 20);

		// When
		room.createBooking(date, date, 1, "guest name", "001");

		// Then
		Booking booking = room.getBookings().get(0);
		assertTrue(booking.getFromDate().equals(date));
		assertTrue(booking.getToDate().equals(date));
	}

	@Test
	public void removeBookingShouldBeAbleToRemoveBooking() {
		// Given
		Room room = new Room(1);
		room.addCommodity(new Bed(BedType.SINGLE));
		LocalDate fromDate = LocalDate.of(2019, 7, 20);
		LocalDate toDate = LocalDate.of(2019, 7, 25);
		room.createBooking(fromDate, toDate, 1, "guest name", "001");

		// Then
		assertTrue(room.removeBooking(fromDate, toDate));
	}
}
