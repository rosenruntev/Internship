package com.deltasource.hotelmanagement.test;

import com.deltasource.hotelmanagement.core.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTests {

	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Manager manager = new Manager(null);
		});
	}

	@Test
	public void setHotelShouldThrowExceptionWhenHotelIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Manager manager = new Manager("Manager");

			manager.setHotel(null);
		});
	}
}
