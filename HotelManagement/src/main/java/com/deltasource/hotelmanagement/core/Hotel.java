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
	 * Constructs a hotel with provided name
	 * @param name the name of the hotel
	 */
	public Hotel(String name) {
		setName(name);
		rooms = new ArrayList<>();
	}

	/**
	 * Constructs a hotel with provided name and collection of rooms
	 * @param name the name of the hotel
	 * @param rooms collection with the rooms of the hotel
	 */
	public Hotel(String name, ArrayList<Room> rooms) {
		this(name);
		this.rooms = rooms;
	}

	/**
	 * Returns the name of the hotel.
	 * @return the name of the hotel
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the hotel.
	 * @param name the name to be set
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Hotel name cannot be null.");
		}

		this.name = name;
	}

	/**
	 * Returns the rooms of the hotel.
	 * @return ArrayList with the rooms of the hotel
	 */
	public List<Room> getRooms() {
		return rooms;
	}
}
