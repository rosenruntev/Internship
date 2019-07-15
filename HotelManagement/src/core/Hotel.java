package core;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	private String name;
	private List<Room> rooms;

	public Hotel(String name) {
		this.setName(name);
		this.rooms = new ArrayList<>();
	}

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
	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
