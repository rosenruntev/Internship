package tests;

import core.Hotel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HotelTests {

	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Hotel hotel = new Hotel(null);
		});
	}
}
