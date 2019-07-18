package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Hotel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HotelTest {

	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		// then
		assertThrows(IllegalArgumentException.class, () -> {
			// when
			new Hotel(null);
		});
	}

	@Test
	public void setNameShouldThrowExceptionWhenNameIsEmpty() {
		// then
		assertThrows(IllegalArgumentException.class, () -> {
			// when
			new Hotel("");
		});
	}

	@Test
	public void setRoomsToNullShouldThrowException() {
		// then
		assertThrows(IllegalArgumentException.class, () -> {
			// when
			new Hotel("", null);
		});
	}
}
