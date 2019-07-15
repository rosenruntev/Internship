package com.deltasource.hotelmanagement.core;

import java.time.LocalDate;

public class Booking {
	private LocalDate fromDate;
	private LocalDate toDate;
	private String guestName;
	private String guestId;

	public Booking(LocalDate fromDate, LocalDate toDate, String guestName, String guestId) {
		setBookingDates(fromDate, toDate);
		this.setGuestName(guestName);
		this.setGuestId(guestId);
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	private void setFromDate(LocalDate fromDate) {
		if (fromDate == null) {
			throw new IllegalArgumentException("From date cannot be null.");
		}

		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	private void setToDate(LocalDate toDate) {
		if (toDate == null) {
			throw new IllegalArgumentException("To date cannot be null.");
		}

		this.toDate = toDate;
	}

	public void setBookingDates(LocalDate fromDate, LocalDate toDate) {
		if (!fromDate.isBefore(toDate)) {
			if (fromDate.getDayOfMonth() != toDate.getDayOfMonth()) {
				throw new IllegalArgumentException("From date cannot be after to date");
			}
		}

		setFromDate(fromDate);
		setToDate(toDate);
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		if (guestName == null) {
			throw new IllegalArgumentException("Guest name cannot be null.");
		}

		this.guestName = guestName;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		if (guestId == null) {
			throw new IllegalArgumentException("Guest id cannot be null.");
		}

		this.guestId = guestId;
	}

	public void changeReservationDates(LocalDate fromDate, LocalDate toDate) {
		this.setFromDate(fromDate);
		this.setToDate(toDate);
	}
}
