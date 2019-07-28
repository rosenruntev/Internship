package eu.deltasource.internship.hotelmanagement.core;

import eu.deltasource.internship.hotelmanagement.core.exceptions.NoRoomsAvailableException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for creating a hotel with name and rooms
 */
public class Hotel {
	private String name;
	private List<Room> rooms;
	private static int inventoryNumber;

	/**
	 * Constructs a hotel with given name
	 *
	 * @param name the name of the hotel
	 */
	public Hotel(String name) {
		setName(name);
		rooms = new ArrayList<>();
		inventoryNumber = 0;
	}

	/**
	 * Constructs a hotel with given name and collection of rooms
	 *
	 * @param name  the name of the hotel
	 * @param rooms collection with the rooms of the hotel
	 */
	public Hotel(String name, List<Room> rooms) {
		this(name);
		setRooms(rooms);
	}

	/**
	 * Returns the name of the hotel.
	 *
	 * @return the name of the hotel
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the hotel.
	 *
	 * @param name the name to be set
	 */
	private void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Hotel name cannot be null or empty.");
		}

		this.name = name;
	}

	/**
	 * Returns the rooms of the hotel.
	 *
	 * @return ArrayList with the rooms of the hotel
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	private void setRooms(List<Room> rooms) {
		if (rooms == null || rooms.size() == 0) {
			throw new IllegalArgumentException("Rooms cannot be null or empty.");
		}

		this.rooms = new ArrayList<>(rooms);
	}

	/**
	 * Returns id of the commodity
	 *
	 * @return id of the commodity
	 */
	public static int getNextInventoryNumber() {
		inventoryNumber++;
		return inventoryNumber;
	}

	/**
	 * Finds available rooms for given interval and capacity
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @param capacity the room beds capacity
	 * @return a list with available rooms
	 */
	public List<Room> findAvailableRooms(LocalDate fromDate, LocalDate toDate, int capacity) {
		List<Room> rooms = getRooms();
		List<Room> availableRooms = new ArrayList<>();
		for (Room currentRoom : rooms) {
			if (currentRoom.getBedsCapacity() == capacity) {
				if (!currentRoom.isBooked(fromDate, toDate)) {
					availableRooms.add(currentRoom);
				}
			}
		}

		if (availableRooms.size() == 0) {
			throw new NoRoomsAvailableException("No available rooms for that interval.");
		}

		return availableRooms;
	}
}
