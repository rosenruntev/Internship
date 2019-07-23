package eu.deltasource.internship.hotelmanagement.core.commodities;

/**
 * An abstract class that defines commodity with inventory number
 */
public abstract class AbstractCommodity {
	private int inventoryNumber;

	/**
	 * Prepares the commodity for the customer
	 */
	public abstract void prepare();

	public int getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(int inventoryNumber) {
		if (inventoryNumber <= 0) {
			throw new IllegalArgumentException("Inventory number cannot be negative or zero.");
		}

		this.inventoryNumber = inventoryNumber;
	}

	@Override
	public int hashCode() {
		return inventoryNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractCommodity) {
			return getInventoryNumber() == ((AbstractCommodity) obj).getInventoryNumber();
		}

		return false;
	}
}
