package eu.deltasource.internship.hotelmanagement.core.commodities;

/**
 * Enumeration that represents the different types of beds
 */
public enum BedType {
	SINGLE(1),
	DOUBLE(2),
	KINGSIZE(2);

	private int capacity;

	/**
	 * Sets the capacity(count of people) of the bed
	 *
	 * @param capacity the capacity of bed
	 */
	BedType(int capacity) {
		setCapacity(capacity);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Bed capacity cannot be a negative number");
		}

		this.capacity = capacity;
	}
}
