package eu.deltasource.internship.hotelmanagement.core.exceptions;

/**
 * Thrown to indicate that there are not available rooms
 */
public class NoRoomsAvailableException extends RuntimeException {
	/**
	 * Constructs a NoRoomsAvailableException without detailed message
	 */
	public NoRoomsAvailableException() {
	}

	/**
	 * Constructs a NoRoomsAvailableException with a detailed message
	 *
	 * @param message the message of exception
	 */
	public NoRoomsAvailableException(String message) {
		super(message);
	}
}
