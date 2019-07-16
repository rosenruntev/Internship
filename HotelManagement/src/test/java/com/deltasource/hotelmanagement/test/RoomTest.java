package com.deltasource.hotelmanagement.test;

import com.deltasource.hotelmanagement.core.Hotel;
import com.deltasource.hotelmanagement.core.Manager;
import com.deltasource.hotelmanagement.core.Room;
import com.deltasource.hotelmanagement.core.commodities.Bed;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		assertThat(room.isBooked(fromDate, toDate, 4), equalTo(true));
	}
}
