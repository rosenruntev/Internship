package core;

import java.util.ArrayList;

public class Manager {
	private String name;
	private Hotel hotel;

	public Manager(String name) {
		this.setName(name);
	}

	public Manager(String name, Hotel hotel) {
		this(name);
		this.setHotel(hotel);
	}

	/**
	 * Returns the name of the manager.
	 * @return the name of the manager
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the manager.
	 * @param name the name to be set
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Manager name cannot be null.");
		}

		this.name = name;
	}

	/**
	 * Returns the hotel of the manager.
	 * @return the hotel of the manager
	 */
	public Hotel getHotel() {
		return this.hotel;
	}

	/**
	 * Sets the hotel of the manager.
	 * @param hotel the hotel to be set
	 */
	public void setHotel(Hotel hotel) {
		if (hotel == null) {
			throw new IllegalArgumentException("Hotel cannot be null.");
		}

		this.hotel = hotel;
	}

	/**
	 * Books a room by room number.
	 * @param roomNumber the number of the room to be booked
	 */
	public void bookRoom(int roomNumber) {
		ArrayList<Room> rooms = this.hotel.getRooms();
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getNumber() == roomNumber) {
				if (rooms.get(i).isBooked()) {
					throw new IllegalArgumentException("Room is already booked");
				} else {
					rooms.get(i).bookTheRoom();
				}
			}
		}
	}

	/**
	 * Returns the available rooms of the hotel.
	 * @return ArrayList with the available rooms of the hotel
	 */
	public ArrayList<Room> getAvailableRooms() {
		ArrayList<Room> rooms = this.hotel.getRooms();
		ArrayList<Room> availableRooms = new ArrayList<>();
		for (int i = 0; i < rooms.size(); i++) {
			if (!rooms.get(i).isBooked()) {
				availableRooms.add(rooms.get(i));
			}
		}

		return availableRooms;
	}
}
