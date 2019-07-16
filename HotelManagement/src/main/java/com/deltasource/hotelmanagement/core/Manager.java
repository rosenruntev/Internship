package com.deltasource.hotelmanagement.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for creating a manager with name and hotel
 */
public class Manager {
	private String name;
	private Hotel hotel;

	/**
	 * Constructs a manager with given name
	 * @param name the name of the manager
	 */
	public Manager(String name) {
		setName(name);
	}

	/**
	 * Constructs a manager with given name and hotel
	 * @param name the name of the manager
	 * @param hotel the hotel of the manager
	 */
	public Manager(String name, Hotel hotel) {
		this(name);
		setHotel(hotel);
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
		return hotel;
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
	 * Books a room by room number, accommodation and leaving dates, size, number of beds, guest name and guest id
	 * @param roomNumber the number of the room to be booked
	 * @param fromDate the date of accommodation
	 * @param toDate the date of leaving
	 * @param size the booking period
	 * @param numberOfBeds the number of beds
	 * @param guestName the name of the guest
	 * @param guestId the id of the guest
	 */
	public void bookRoom(int roomNumber, LocalDate fromDate, LocalDate toDate, int size, int numberOfBeds, String guestName, String guestId) {
		List<Room> rooms = hotel.getRooms();
		if (fromDate == null) {
			throw new IllegalArgumentException("From date cannot be null");
		} else if (toDate == null) {
			throw new IllegalArgumentException("To date cannot be null");
		} else if (guestName == null) {
			throw new IllegalArgumentException("Guest name cannot be null");
		} else if (guestId == null) {
			throw new IllegalArgumentException("Guest id cannot be null");
		}

		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getNumber() == roomNumber) {
				if (rooms.get(i).getNumberOfBeds() < numberOfBeds) {
					throw new IllegalArgumentException("Not enough beds in the room.");
				}

				rooms.get(i).createBooking(fromDate, toDate, size, guestName, guestId);
			}
		}
	}

	/**
	 * Returns the available rooms of the hotel.
	 * @return ArrayList with the available rooms of the hotel
	 */
	public void findAvailableDatesForIntervalAndSizeForRooms(LocalDate fromDate, LocalDate toDate, int size, int numberOfBeds) {
		List<Room> rooms = hotel.getRooms();
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getNumberOfBeds() >= numberOfBeds) {
				ArrayList<String> availableDates = rooms.get(i).findAvailableDatesForIntervalAndSize(fromDate, toDate, size);
				for (String availableDate : availableDates) {
					System.out.println(availableDate);
				}
			}
		}
	}
}
