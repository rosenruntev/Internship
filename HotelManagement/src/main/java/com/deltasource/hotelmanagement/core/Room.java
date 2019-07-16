package com.deltasource.hotelmanagement.core;

import com.deltasource.hotelmanagement.core.commodities.AbstractCommodity;
import com.deltasource.hotelmanagement.core.commodities.Bed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * A class for creating a room with number, set of commodities, set of maintenance dates and set of bookings
 */
public class Room {
	private int number;
	private Set<AbstractCommodity> commodities;
	private List<LocalDateTime> maintenanceDates;
	private List<Booking> bookings;

	/**
	 * Constructs a room with number
	 * @param number the number of the room
	 */
	public Room(int number) {
		setNumber(number);
		commodities = new HashSet<>();
		maintenanceDates = new ArrayList<>();
		bookings = new ArrayList<>();
	}

	/**
	 * Constructs a room with given number and set of commodities
	 * @param number the number of the room
	 * @param commodities set of room commodities
	 */
	public Room(int number, Set<AbstractCommodity> commodities) {
		this(number);
		this.commodities = commodities;
	}

	/**
	 * Returns the number of the room.
	 * @return the number of the room
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number of the room.
	 * @param number the number of the room
	 */
	private void setNumber(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Room number cannot be negative.");
		}

		this.number = number;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	/**
	 * Returns {@code true} if the room is booked otherwise {@code false}
	 * @return {@code true} if the room is booked otherwise {@code false}
	 */
	public boolean isBooked(LocalDate fromDate, LocalDate toDate, int size) {
		ArrayList<String> availableDates = findAvailableDatesForIntervalAndSize(fromDate, toDate, size);
		String date = fromDate.getYear() + "-" + fromDate.getMonthValue() + "-" + fromDate.getDayOfMonth() + " to " +
			toDate.getYear() + "-" + toDate.getMonthValue() + "-" + toDate.getDayOfMonth();
		if (availableDates == null) {
			return false;
		}

		return true;
	}

	public Set<AbstractCommodity> getCommodities() {
		return commodities;
	}

	/**
	 * Prepares the room commodities
	 */
	public void prepareRoomCommodities() {
		for(AbstractCommodity commodity : commodities) {
			commodity.prepare();
		}

		maintenanceDates.add(LocalDateTime.now());
	}

	/**
	 * Returns the number of beds in the room
	 * @return the number of beds in the room
	 */
	public int getNumberOfBeds() {
		int numberOfBeds = 0;
		for (AbstractCommodity commodity : commodities) {
			if (commodity instanceof Bed) {
				numberOfBeds++;
			}
		}

		return numberOfBeds;
	}

	/**
	 * Creates booking with accommodation and leaving dates, size, guest name and guest id
	 * @param fromDate the date of accommodation
	 * @param toDate the date of leaving
	 * @param size the booking period
	 * @param guestName the name of the guest
	 * @param guestId the id of the guest
	 */
	public void createBooking(LocalDate fromDate, LocalDate toDate, int size, String guestName, String guestId) {
		ArrayList<String> availableDates = findAvailableDatesForIntervalAndSize(fromDate, toDate, size);
		String date = fromDate.getYear() + "-" + fromDate.getMonthValue() + "-" + fromDate.getDayOfMonth() + " to " +
			toDate.getYear() + "-" + toDate.getMonthValue() + "-" + toDate.getDayOfMonth();
		if (availableDates == null) {
			System.out.println("There are not available dates for this interval and size.");
		} else if (availableDates.contains(date)) {
			Booking booking = new Booking(fromDate, toDate, guestName, guestId);
			bookings.add(booking);
		} else {
			System.out.println("Available dates in this interval:");
			for (String availableDate : availableDates) {
				System.out.println(availableDate);
			}
		}
	}

	/**
	 * Removes booking by date of accommodation and leaving
	 * @param fromDate the date of accommodation
	 * @param toDate the date of leaving
	 */
	public void removeBooking(LocalDate fromDate, LocalDate toDate) {
		for (Booking booking : bookings) {
			if (booking.getFromDate().isEqual(fromDate) && booking.getToDate().isEqual(toDate)) {
				bookings.remove(booking);
			}
		}
	}

	/**
	 * Finds booked days for interval given by the customer
	 * @param fromDate the date of accommodation
	 * @param toDate the date of leaving
	 * @return list with booked days in interval
	 */
	private ArrayList<String> findBookedDays(LocalDate fromDate, LocalDate toDate) {
		ArrayList<String> bookedDays = new ArrayList<>();
		for (Booking booking : bookings) {
			if (booking.getFromDate().getMonthValue() == fromDate.getMonthValue() ||
				booking.getToDate().getMonthValue() == fromDate.getMonthValue() ||
				booking.getFromDate().getMonthValue() == toDate.getMonthValue() ||
				booking.getToDate().getMonthValue() == toDate.getMonthValue()) {
				int daysInMonthOfBookingFromDate = booking.getFromDate().lengthOfMonth();
				int day = booking.getFromDate().getDayOfMonth();
				int month = booking.getFromDate().getMonthValue();
				while (true) {
					if (month == booking.getToDate().getMonthValue() && day == booking.getToDate().getDayOfMonth()) {
						bookedDays.add(day + "." + month);
						break;
					}

					if (booking.getFromDate().getMonthValue() != booking.getToDate().getMonthValue()) {
						if (day == daysInMonthOfBookingFromDate) {
							bookedDays.add(day + "." + month);
							day = 1;
							month++;
						}
					}

					bookedDays.add(day + "." + month);
					day++;
				}
			}
		}

		return bookedDays;
	}

	/**
	 * Finds available days for interval given by the customer
	 * @param fromDate the date of accommodation
	 * @param toDate the date of leaving
	 * @return list with available days in interval
	 */
	private ArrayList<String> findAvailableDays(LocalDate fromDate, LocalDate toDate) {
		ArrayList<String> bookedDays = findBookedDays(fromDate, toDate);
		ArrayList<String> availableDays = new ArrayList<>();
		int day = fromDate.getDayOfMonth();
		int month = fromDate.getMonthValue();
		while (true) {
			if (day == toDate.getDayOfMonth() && month == toDate.getMonthValue()) {
				if (!bookedDays.contains(day + "." + month)) {
					availableDays.add(day + "." + month);
				}

				break;
			}

			if (fromDate.getMonthValue() != toDate.getMonthValue()) {
				if (day == fromDate.lengthOfMonth()) {
					if (!bookedDays.contains(day + "." + month)) {
						availableDays.add(day + "." + month);
					}

					day = 1;
					month++;
				}
			}

			if (!bookedDays.contains(day + "." + month)) {
				availableDays.add(day + "." + month);
			}

			day++;
		}

		return availableDays;
	}

	/**
	 * Finds available dates for interval given by the customer
	 * @param fromDate the date of accommodation
	 * @param toDate the date of leaving
	 * @return list with available dates in interval
	 */
	private ArrayList<String> findAvailableDatesForInterval(LocalDate fromDate, LocalDate toDate) {
		ArrayList<String> availableDays = findAvailableDays(fromDate, toDate);

		ArrayList<String> availableDates = new ArrayList<>();
		if (availableDays.size() == 0) {
			return null;
		} else {
			int elementIndex = 1;
			int daysInARow = 1;
			int startingIndex = 0;
			int month = fromDate.getMonthValue();
			while (true) {
				if (elementIndex == availableDays.size()) {
					availableDates.add(fromDate.getYear() + "-" + fromDate.getMonthValue() + "-" +
						availableDays.get(startingIndex).split("\\.")[0] + " to " + fromDate.getYear() + "-" +
						month + "-" + availableDays.get(startingIndex + daysInARow - 1).split("\\.")[0]);
					break;
				}

				int currentElementDay = Integer.parseInt(availableDays.get(elementIndex).split("\\.")[0]);
				int previousElementDay = Integer.parseInt(availableDays.get(elementIndex - 1).split("\\.")[0]);
				if (currentElementDay == previousElementDay + 1) {
					daysInARow++;
				} else if (previousElementDay == 30 && currentElementDay == 1 || previousElementDay == 31 && currentElementDay == 1) {
					daysInARow++;
					month++;
				} else {
					availableDates.add(fromDate.getYear() + "-" + fromDate.getMonthValue() + "-" +
						availableDays.get(startingIndex).split("\\.")[0] + " to " + fromDate.getYear() + "-" +
						month + "-" + availableDays.get(startingIndex + daysInARow - 1).split("\\.")[0]);
					startingIndex = elementIndex;
					daysInARow = 1;
				}

				elementIndex++;
			}
		}

		return availableDates;
	}

	/**
	 * Finds available dates for interval and size given by the customer
	 * @param fromDate the date of accommodation
	 * @param toDate the date of leaving
	 * @param size the booking period
	 * @return list with available dates for interval and size
	 */
	public ArrayList<String> findAvailableDatesForIntervalAndSize(LocalDate fromDate, LocalDate toDate, int size) {
		ArrayList<String> availableDates = findAvailableDatesForInterval(fromDate, toDate);

		ArrayList<String> availableDateForIntervalAndSize = new ArrayList<>();
		for (String availableDate : availableDates) {
			String[] splittedDates = availableDate.split(" to ");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
			LocalDate bookingFromDate = LocalDate.parse(splittedDates[0], formatter);
			LocalDate bookingToDate = LocalDate.parse(splittedDates[1], formatter);

			if (ChronoUnit.DAYS.between(bookingFromDate, bookingToDate) >= (size - 1)) {
				availableDateForIntervalAndSize.add(availableDate);
			}
		}

		return availableDateForIntervalAndSize;
	}
}
