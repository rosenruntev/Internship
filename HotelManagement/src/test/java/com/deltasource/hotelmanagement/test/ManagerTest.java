package com.deltasource.hotelmanagement.test;

import com.deltasource.hotelmanagement.core.Manager;
import org.junit.jupiter.api.Test;

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
}
