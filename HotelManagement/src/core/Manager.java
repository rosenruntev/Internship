package core;

import java.time.LocalDate;
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
	public void bookRoom(int roomNumber, LocalDate fromDate, LocalDate toDate, int size, String guestName, String guestId) {
		ArrayList<Room> rooms = this.hotel.getRooms();
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getNumber() == roomNumber) {
				rooms.get(i).createBooking(fromDate, toDate, size, guestName, guestId);
			}
		}
	}

	/**
	 * Returns the available rooms of the hotel.
	 * @return ArrayList with the available rooms of the hotel
	 */
	public void findAvailableDatesForIntervalAndSizeForRooms(LocalDate fromDate, LocalDate toDate, int size) {
		ArrayList<Room> rooms = this.hotel.getRooms();
		for (int i = 0; i < rooms.size(); i++) {
			ArrayList<String> availableDates = rooms.get(i).findAvailableDatesForIntervalAndSize(fromDate, toDate, size);
			for (String availableDate : availableDates) {
				System.out.println(availableDate);
			}
		}
	}
}
