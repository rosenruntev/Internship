package core;

public class Room {
	private int number;
	private boolean isBooked;

	public Room(int number) {
		this.setNumber(number);
		isBooked = false;
	}

	public Room(int number, boolean isBooked) {
		this(number);
		this.isBooked = isBooked;
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

	/**
	 * Returns {@code true} if the room is booked.
	 * @return {@code true} if the room is booked.
	 */
	public boolean isBooked() {
		return isBooked;
	}

	/**
	 * Books the room.
	 */
	public void bookTheRoom() {
		this.isBooked = true;
	}

	/**
	 * Clear booking of the room.
	 */
	public void clearRoomBooking() {
		this.isBooked = false;
	}
}
