package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Booking;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingTest {
	@Test
	public void bookingWithFromDateAfterToDateShouldThrowException() {
		// Then
		assertThrows(IllegalArgumentException.class, () -> {
			// When
			Booking booking = new Booking(LocalDate.of(2019, 7, 25),
				LocalDate.of(2019, 7, 20), "guestName", "guestId");
		});
	}

	@Test
	public void updateBookingShouldUpdateBookingDatesCorrectly() {
		// Given
		Booking booking = new Booking(LocalDate.of(2019, 7, 20),
			LocalDate.of(2019, 7, 25), "guestName", "guestId");

		// When
		booking.updateBooking(LocalDate.of(2019, 7, 25),
			LocalDate.of(2019, 7, 30));

		// Then
		assertThat(booking.getFromDate(), equalTo(LocalDate.of(2019, 7, 25)));
		assertThat(booking.getToDate(), equalTo(LocalDate.of(2019, 7, 30)));
	}

	@Test
	public void updateBookingShouldUpdateGuestInformationCorrectly() {
		// Given
		Booking booking = new Booking(LocalDate.of(2019, 7, 20),
			LocalDate.of(2019, 7, 25), "guestName", "guestId");

		// When
		booking.updateBooking("name", "id");

		// Then
		assertThat(booking.getGuestName(), equalTo("name"));
		assertThat(booking.getGuestId(), equalTo("id"));
	}
}
