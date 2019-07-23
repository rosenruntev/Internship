package eu.deltasource.internship.hotelmanagement.test;

import eu.deltasource.internship.hotelmanagement.core.Hotel;
import eu.deltasource.internship.hotelmanagement.core.Room;
import eu.deltasource.internship.hotelmanagement.core.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;
import eu.deltasource.internship.hotelmanagement.core.commodities.BedType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HotelTest {

	@Test
	public void setNameShouldThrowExceptionWhenNameIsNull() {
		// Then
		assertThrows(IllegalArgumentException.class, () -> {
			// When
			new Hotel(null);
		});
	}

	@Test
	public void setNameShouldThrowExceptionWhenNameIsEmpty() {
		// Then
		assertThrows(IllegalArgumentException.class, () -> {
			// When
			new Hotel("");
		});
	}

	@Test
	public void setRoomsToNullShouldThrowException() {
		// Then
		assertThrows(IllegalArgumentException.class, () -> {
			// When
			new Hotel("", null);
		});
	}

	@Test
	public void findAvailableRoomsShouldReturnAllOfTheAvailableRooms() {
		// Given
		Set<AbstractCommodity> firstRoomCommodities = new HashSet<>();
		Bed firstRoomBed = new Bed(BedType.SINGLE);
		firstRoomCommodities.add(firstRoomBed);

		Set<AbstractCommodity> secondRoomCommodities = new HashSet<>();
		Bed secondRoomBed = new Bed(BedType.DOUBLE);
		secondRoomCommodities.add(secondRoomBed);

		List<Room> rooms = new ArrayList<>();
		Room firstRoom = new Room(1, firstRoomCommodities);
		Room secondRoom = new Room(2, secondRoomCommodities);
		rooms.add(firstRoom);
		rooms.add(secondRoom);
		Hotel hotel = new Hotel("hotel", rooms);

		// When
		List<Room> availableRooms = hotel.findAvailableRooms(LocalDate.of(2019, 7, 20),
			LocalDate.of(2019, 7, 25), 2);

		// Then
		assertThat(availableRooms.size(), equalTo(1));
	}

	@Test
	public void getNextInventoryNumberShouldIncrease() {
		// When
		int firstNumber = Hotel.getNextInventoryNumber();
		int secondNumber = Hotel.getNextInventoryNumber();

		// Then
		assertThat(firstNumber, equalTo(firstNumber));
		assertThat(secondNumber, equalTo(secondNumber));
	}
}
