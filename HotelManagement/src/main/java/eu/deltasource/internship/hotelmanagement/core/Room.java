package eu.deltasource.internship.hotelmanagement.core;

import eu.deltasource.internship.hotelmanagement.core.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	 *
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
	 *
	 * @param number      the number of the room
	 * @param commodities set of room commodities
	 */
	public Room(int number, Set<AbstractCommodity> commodities) {
		this(number);
		setCommodities(commodities);
	}

	/**
	 * Returns the number of the room.
	 *
	 * @return the number of the room
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number of the room.
	 *
	 * @param number the number of the room
	 */
	private void setNumber(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException("Room number cannot be a negative number or zero.");
		}

		this.number = number;
	}

	public List<LocalDateTime> getMaintenanceDates() {
		return maintenanceDates;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public Set<AbstractCommodity> getCommodities() {
		return commodities;
	}

	private void setCommodities(Set<AbstractCommodity> commodities) {
		if (commodities == null || commodities.size() == 0) {
			throw new IllegalArgumentException("Commodities set cannot be null or empty.");
		}

		this.commodities = new HashSet<>(commodities);
	}

	/**
	 * Adds commodity to room commodities.
	 *
	 * @param commodity the commodity to add
	 */
	public void addCommodity(AbstractCommodity commodity) {
		if (commodity == null) {
			throw new IllegalArgumentException("Commodity cannot be null.");
		}

		commodities.add(commodity);
		commodity.setInventoryNumber(Hotel.getNextInventoryNumber());
	}

	/**
	 * Returns true if the room is booked for that period otherwise false
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return true if the room is booked otherwise false
	 */
	public boolean isBooked(LocalDate fromDate, LocalDate toDate) {
		validateDates(fromDate, toDate);
		for (Booking booking : bookings) {
			if (!(booking.getToDate().isBefore(fromDate) || booking.getFromDate().isAfter(toDate))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Prepares the room commodities
	 */
	public void prepareRoomCommodities() {
		if (commodities.size() == 0) {
			throw new IllegalArgumentException("Commodities set is empty.");
		}

		for (AbstractCommodity commodity : commodities) {
			commodity.prepare();
		}

		maintenanceDates.add(LocalDateTime.now());
	}

	/**
	 * Returns the capacity of beds in the room
	 *
	 * @return the capacity of beds in the room
	 */
	public int getBedsCapacity() {
		int capacity = 0;
		for (AbstractCommodity commodity : commodities) {
			if (commodity instanceof Bed) {
				capacity += ((Bed) commodity).getType().getCapacity();
			}
		}

		return capacity;
	}

	/**
	 * Creates a booking with accommodation and leaving dates, capacity, guest name and guest id
	 *
	 * @param fromDate  the date of accommodation
	 * @param toDate    the date of leaving
	 * @param capacity  the room beds capacity
	 * @param guestName the name of the guest
	 * @param guestId   the id of the guest
	 */
	public void createBooking(LocalDate fromDate, LocalDate toDate, int capacity, String guestName, String guestId) {
		validateDates(fromDate, toDate);
		if (guestName == null || guestId == null) {
			throw new IllegalArgumentException("Guest name or guest id cannot be null.");
		}

		if (capacity < 1) {
			throw new IllegalArgumentException("Room beds capacity cannot be a negative number or zero.");
		} else if (capacity > getBedsCapacity()) {
			throw new IllegalArgumentException("Capacity cannot be bigger than the room beds capacity.");
		}

		if (isBooked(fromDate, toDate)) {
			System.out.println("This room is already booked for that period.");
		} else {
			Booking booking = new Booking(fromDate, toDate, guestName, guestId);
			bookings.add(booking);
			System.out.printf("Successfully created %s's booking from %s to %s\n", guestName, fromDate.toString(), toDate.toString());
		}
	}

	/**
	 * Removes booking by date of accommodation and leaving
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return true if the booking was removed otherwise false
	 */
	public boolean removeBooking(LocalDate fromDate, LocalDate toDate) {
		validateDates(fromDate, toDate);
		for (Booking booking : bookings) {
			if (booking.getFromDate().isEqual(fromDate) && booking.getToDate().isEqual(toDate)) {
				bookings.remove(booking);
				return true;
			}
		}

		return false;
	}

	private void validateDates(LocalDate fromDate, LocalDate toDate) {
		if (fromDate == null || toDate == null) {
			throw new IllegalArgumentException("From and to dates cannot be null");
		} else if (!fromDate.isBefore(toDate)) {
			if (fromDate.getDayOfMonth() != toDate.getDayOfMonth()) {
				throw new IllegalArgumentException("From date cannot be after to date");
			}
		}
	}

	/**
	 * Finds booked days for interval given by the customer.
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return list with booked days in interval
	 */
	private ArrayList<String> findBookedDays(LocalDate fromDate, LocalDate toDate) {
		ArrayList<String> bookedDays = new ArrayList<>();
		for (Booking booking : bookings) {
			LocalDate bookingFromDate = booking.getFromDate();
			LocalDate bookingToDate = booking.getToDate();
			int bookingFromDateMonth = bookingFromDate.getMonthValue();
			if (bookingFromDateMonth == fromDate.getMonthValue() ||
				bookingToDate.getMonthValue() == fromDate.getMonthValue() ||
				bookingFromDateMonth == toDate.getMonthValue() ||
				bookingToDate.getMonthValue() == toDate.getMonthValue()) {
				while (!bookingFromDate.equals(bookingToDate)) {
					bookedDays.add(bookingFromDate.getDayOfMonth() + "." + bookingFromDateMonth);
					bookingFromDate = bookingFromDate.plusDays(1);
				}

				// Adds the day of leaving to booked days
				bookedDays.add(bookingFromDate.getDayOfMonth() + "." + bookingFromDateMonth);
			}
		}

		return bookedDays;
	}

	/**
	 * Finds available days for interval given by the customer
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return list with available days in interval
	 */
	private ArrayList<String> findAvailableDays(LocalDate fromDate, LocalDate toDate) {
		ArrayList<String> bookedDays = findBookedDays(fromDate, toDate);
		ArrayList<String> availableDays = new ArrayList<>();
		int fromDateDay = fromDate.getDayOfMonth();
		int fromDateMonth = fromDate.getMonthValue();
		int toDateDay = toDate.getDayOfMonth();
		int toDateMonth = toDate.getMonthValue();

		// Check if booking is for one day
		if (fromDateDay == toDateDay && fromDateMonth == toDateMonth) {
			if (!bookedDays.contains(fromDateDay + "." + fromDateMonth)) {
				availableDays.add(fromDateDay + "." + fromDateMonth);
			}

			return availableDays;
		}

		while (!fromDate.equals(toDate)) {
			if (!bookedDays.contains(fromDateDay + "." + fromDateMonth)) {
				availableDays.add(fromDateDay + "." + fromDateMonth);
			}

			fromDate = fromDate.plusDays(1);
			fromDateDay = fromDate.getDayOfMonth();
			fromDateMonth = fromDate.getMonthValue();
		}

		// Adds the last day
		availableDays.add(fromDateDay + "." + fromDateMonth);

		return availableDays;
	}

	/**
	 * Finds available dates for interval given by the customer
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return list with available dates in interval
	 */
	private ArrayList<String> findAvailableDatesForInterval(LocalDate fromDate, LocalDate toDate) {
		ArrayList<String> availableDays = findAvailableDays(fromDate, toDate);

		ArrayList<String> availableDates = new ArrayList<>();
		if (availableDays.size() == 0) {
			return availableDates;
		}

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

		return availableDates;
	}

	/**
	 * Finds available dates for interval and size given by the customer
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @param capacity the room beds capacity
	 * @return list with available dates for interval and size
	 */
	public ArrayList<String> findAvailableDatesForIntervalAndSize(LocalDate fromDate, LocalDate toDate, int capacity) {
		validateDates(fromDate, toDate);
		if (capacity < 1) {
			throw new IllegalArgumentException("Room beds capacity cannot be a negative number or zero.");
		} else if (capacity > getBedsCapacity()) {
			throw new IllegalArgumentException("Capacity cannot be bigger than the room beds capacity.");
		}

		ArrayList<String> availableDates = findAvailableDatesForInterval(fromDate, toDate);
		ArrayList<String> availableDateForIntervalAndSize = new ArrayList<>();
		if (availableDates.size() == 0) {
			return availableDateForIntervalAndSize;
		}

		for (String availableDate : availableDates) {
			String[] splittedDates = availableDate.split(" to ");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
			LocalDate bookingFromDate = LocalDate.parse(splittedDates[0], formatter);
			LocalDate bookingToDate = LocalDate.parse(splittedDates[1], formatter);

			availableDateForIntervalAndSize.add(availableDate);
		}

		return availableDateForIntervalAndSize;
	}
}
