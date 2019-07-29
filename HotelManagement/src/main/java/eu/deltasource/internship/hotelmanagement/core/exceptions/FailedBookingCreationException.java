package eu.deltasource.internship.hotelmanagement.core.exceptions;

/**
 * Thrown to indicate that the booking creation failed
 */
public class FailedBookingCreationException extends RuntimeException {
	/**
	 * Constructs a FailedBookingCreationException without detailed message
	 */
	public FailedBookingCreationException() {
	}

	/**
	 * Constructs a FailedBookingCreationException with a detailed message
	 *
	 * @param message the message of exception
	 */
	public FailedBookingCreationException(String message) {
		super(message);
	}
}
