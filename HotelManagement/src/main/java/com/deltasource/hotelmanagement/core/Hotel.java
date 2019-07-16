package com.deltasource.hotelmanagement.core;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for creating a hotel with name and rooms
 */
public class Hotel {
	private String name;
	private List<Room> rooms;

	/**
	 * Constructs a hotel with given name
	 *
	 * @param name the name of the hotel
	 */
	public Hotel(String name) {
		setName(name);
		rooms = new ArrayList<>();
	}

	/**
	 * Constructs a hotel with given name and collection of rooms
	 *
	 * @param name  the name of the hotel
	 * @param rooms collection with the rooms of the hotel
	 */
	public Hotel(String name, ArrayList<Room> rooms) {
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
		if (name == null) {
			throw new IllegalArgumentException("Hotel name cannot be null.");
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("The name cannot be empty.");
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

	public void setRooms(ArrayList<Room> rooms) {
		if (rooms == null) {
			throw new IllegalArgumentException("Rooms cannot be null.");
		}

		this.rooms = rooms;
	}
}
