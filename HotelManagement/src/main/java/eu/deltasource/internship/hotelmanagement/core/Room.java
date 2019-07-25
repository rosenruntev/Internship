package eu.deltasource.internship.hotelmanagement.core;

import eu.deltasource.internship.hotelmanagement.core.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagement.core.commodities.Bed;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

		this.commodities = new HashSet<>();
		for (AbstractCommodity commodity : commodities) {
			addCommodity(commodity);
		}
	}

	/**
	 * Adds commodity to room commodities.
	 *
	 * @param commodity the commodity to add
	 * @return the inventory number of the commodity
	 */
	public int addCommodity(AbstractCommodity commodity) {
		if (commodity == null) {
			throw new IllegalArgumentException("Commodity cannot be null.");
		}

		commodities.add(commodity);
		int inventoryNumber = Hotel.getNextInventoryNumber();
		commodity.setInventoryNumber(inventoryNumber);

		return inventoryNumber;
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
	 * Returns booked dates for interval given by the customer.
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return list with booked dates in interval
	 */
	private ArrayList<LocalDate> getBookedDates(LocalDate fromDate, LocalDate toDate) {
		ArrayList<LocalDate> bookedDays = new ArrayList<>();
		for (Booking booking : bookings) {
			LocalDate bookingFromDate = booking.getFromDate();
			LocalDate bookingToDate = booking.getToDate();
			if (!(bookingToDate.isBefore(fromDate) || bookingFromDate.isAfter(toDate))) {
				while (!bookingFromDate.equals(bookingToDate)) {
					bookedDays.add(bookingFromDate);
					bookingFromDate = bookingFromDate.plusDays(1);
				}

				bookedDays.add(bookingFromDate);
			}
		}

		return bookedDays;
	}

	/**
	 * Returns available dates for interval given by the customer
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return list with available dates in interval
	 */
	private ArrayList<LocalDate> getAvailableDates(LocalDate fromDate, LocalDate toDate) {
		ArrayList<LocalDate> bookedDays = getBookedDates(fromDate, toDate);
		ArrayList<LocalDate> availableDays = new ArrayList<>();
		while (!fromDate.equals(toDate)) {
			if (!bookedDays.contains(fromDate)) {
				availableDays.add(fromDate);
			}

			fromDate = fromDate.plusDays(1);
		}

		if (!bookedDays.contains(fromDate)) {
			availableDays.add(fromDate);
		}
		return availableDays;
	}

	/**
	 * Finds available dates for interval given by the customer
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 * @return list of date arrays with available dates in interval
	 */
	public ArrayList<LocalDate[]> findAvailableDatesForInterval(LocalDate fromDate, LocalDate toDate) {
		validateDates(fromDate, toDate);
		ArrayList<LocalDate> availableDays = getAvailableDates(fromDate, toDate);
		if (availableDays.size() == 0) {
			return new ArrayList<LocalDate[]>();
		}

		ArrayList<LocalDate[]> availableDates = new ArrayList<>();
		int daysInARow = 1;
		int startingIndex = 0;
		for (int i = 1; i < availableDays.size(); i++) {
			LocalDate currentDate = availableDays.get(i);
			LocalDate previousDate = availableDays.get(i - 1);
			if (currentDate.getDayOfMonth() == previousDate.plusDays(1).getDayOfMonth()) {
				daysInARow++;
			} else {
				LocalDate from = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(),
					availableDays.get(startingIndex).getDayOfMonth());
				LocalDate to = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(),
					availableDays.get(startingIndex + daysInARow - 1).getDayOfMonth());
				LocalDate[] availableInterval = {from, to};
				availableDates.add(availableInterval);

				startingIndex = i;
				daysInARow = 1;
			}
		}

		return availableDates;
	}
}
