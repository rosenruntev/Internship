package eu.deltasource.internship.hotelmanagement.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A class for creating a manager with name and hotel
 */
public class Manager {
	private String name;
	private Hotel hotel;

	/**
	 * Constructs a manager with given name
	 *
	 * @param name the name of the manager
	 */
	public Manager(String name) {
		setName(name);
	}

	/**
	 * Constructs a manager with given name and hotel
	 *
	 * @param name  the name of the manager
	 * @param hotel the hotel of the manager
	 */
	public Manager(String name, Hotel hotel) {
		this(name);
		assignHotel(hotel);
	}

	/**
	 * Returns the name of the manager.
	 *
	 * @return the name of the manager
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the manager.
	 *
	 * @param name the name to be set
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Manager name cannot be null.");
		}

		this.name = name;
	}

	/**
	 * Returns the assigned hotel
	 *
	 * @return the assigned hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * Assigns hotel
	 *
	 * @param hotel the hotel to be assigned
	 */
	public void assignHotel(Hotel hotel) {
		if (hotel == null) {
			throw new IllegalArgumentException("Hotel cannot be null.");
		}

		this.hotel = hotel;
	}

	/**
	 * Books a room by guest id, accommodation and leaving dates, capacity and guest name
	 *
	 * @param guestId   the id of the guest
	 * @param fromDate  the date of accommodation
	 * @param toDate    the date of leaving
	 * @param capacity  the room beds capacity
	 * @param guestName the name of the guest
	 * @return the booked room number
	 */
	public int createBooking(String guestId, LocalDate fromDate, LocalDate toDate, int capacity, String guestName) {
		List<Room> hotelRooms = hotel.getRooms();
		int roomNumber = 0;
		for (Room room : hotelRooms) {
			if (!room.isBooked(fromDate, toDate)) {
				room.createBooking(fromDate, toDate, capacity, guestName, guestId);
				roomNumber = room.getNumber();
				break;
			}
		}

		return roomNumber;
	}

	/**
	 * Finds the available rooms of the hotel for period and capacity
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @param capacity the room beds capacity
	 */
	public LinkedHashMap<Integer, ArrayList<LocalDate[]>> findAvailableRoomsForInterval(LocalDate fromDate, LocalDate toDate, int capacity) {
		List<Room> rooms = hotel.getRooms();
		LinkedHashMap<Integer, ArrayList<LocalDate[]>> roomsWithAvailableDatesForInterval = new LinkedHashMap<>();
		for (Room currentRoom : rooms) {
			if (currentRoom.getBedsCapacity() == capacity) {
				ArrayList<LocalDate[]> availableDates = currentRoom.findAvailableDatesForInterval(fromDate, toDate);
				if (!availableDates.isEmpty()) {
					roomsWithAvailableDatesForInterval.put(currentRoom.getNumber(), availableDates);
				}
			}
		}

		return roomsWithAvailableDatesForInterval;
	}
}
