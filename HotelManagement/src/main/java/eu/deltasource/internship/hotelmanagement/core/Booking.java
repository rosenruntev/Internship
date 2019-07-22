package eu.deltasource.internship.hotelmanagement.core;

import java.time.LocalDate;

/**
 * A class that represents a booking with dates, guest name and guest id
 */
public class Booking {
	private LocalDate fromDate;
	private LocalDate toDate;
	private String guestName;
	private String guestId;

	/**
	 * Constructs a booking with given dates, guest name and guest id
	 *
	 * @param fromDate  the date of accommodation
	 * @param toDate    the date of leaving
	 * @param guestName the name of the guest
	 * @param guestId   the id of the guest
	 */
	public Booking(LocalDate fromDate, LocalDate toDate, String guestName, String guestId) {
		setBookingDates(fromDate, toDate);
		setGuestInfo(guestName, guestId);
	}

	/**
	 * Returns the date of accommodation
	 *
	 * @return the date of accommodation
	 */
	public LocalDate getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the date of accommodation
	 *
	 * @param fromDate the date of accommodation
	 */
	private void setFromDate(LocalDate fromDate) {
		if (fromDate == null) {
			throw new IllegalArgumentException("From date cannot be null.");
		}

		this.fromDate = fromDate;
	}

	/**
	 * Returns the date of leaving
	 *
	 * @return the date of leaving
	 */
	public LocalDate getToDate() {
		return toDate;
	}

	/**
	 * Sets the date of leaving
	 *
	 * @param toDate the date of leaving
	 */
	private void setToDate(LocalDate toDate) {
		if (toDate == null) {
			throw new IllegalArgumentException("To date cannot be null.");
		}

		this.toDate = toDate;
	}

	/**
	 * Sets the date of accommodation and the date of leaving
	 *
	 * @param fromDate the date of accommodation
	 * @param toDate   the date of leaving
	 */
	private void setBookingDates(LocalDate fromDate, LocalDate toDate) {
		if (fromDate == null || toDate == null) {
			throw new IllegalArgumentException("Booking dates cannot be null.");
		}

		if (!fromDate.isBefore(toDate)) {
			if (fromDate.getDayOfMonth() != toDate.getDayOfMonth()) {
				throw new IllegalArgumentException("From date cannot be after to date");
			}
		}

		setFromDate(fromDate);
		setToDate(toDate);
	}

	/**
	 * Returns the guest's name
	 *
	 * @return the guest's name
	 */
	public String getGuestName() {
		return guestName;
	}

	/**
	 * Sets the guest's name
	 *
	 * @param guestName the name of the guest
	 */
	private void setGuestName(String guestName) {
		if (guestName == null || guestName.isEmpty()) {
			throw new IllegalArgumentException("Guest name cannot be null or empty.");
		}

		this.guestName = guestName;
	}

	/**
	 * Returns the guest's id
	 *
	 * @return the guest's id
	 */
	public String getGuestId() {
		return guestId;
	}

	/**
	 * Sets the guest's id
	 *
	 * @param guestId the id of the guest
	 */
	private void setGuestId(String guestId) {
		if (guestId == null || guestId.isEmpty()) {
			throw new IllegalArgumentException("Guest id cannot be null or empty.");
		}

		this.guestId = guestId;
	}

	private void setGuestInfo(String guestName, String guestId) {
		setGuestName(guestName);
		setGuestId(guestId);
	}

	public void updateBooking(LocalDate fromDate, LocalDate toDate) {
		setBookingDates(fromDate, toDate);
	}

	public void updateBooking(String guestName, String guestId) {
		setGuestInfo(guestName, guestId);
	}
}
