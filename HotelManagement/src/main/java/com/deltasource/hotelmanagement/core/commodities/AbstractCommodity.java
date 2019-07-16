package com.deltasource.hotelmanagement.core.commodities;

/**
 * An abstract class that defines commodity with inventory number
 */
public abstract class AbstractCommodity {
	private int inventoryNumber;

	/**
	 * Constructor to set inventory number to commodity
	 *
	 * @param inventoryNumber the inventory number of commodity
	 */
	public AbstractCommodity(int inventoryNumber) {
		this.setInventoryNumber(inventoryNumber);
	}

	/**
	 * Prepares the commodity for customer
	 */
	public abstract void prepare();

	public int getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(int inventoryNumber) {
		if (inventoryNumber < 0) {
			throw new IllegalArgumentException("Inventory number cannot be negative.");
		}

		this.inventoryNumber = inventoryNumber;
	}

	@Override
	public int hashCode() {
		return inventoryNumber;
	}

	@Override
	public boolean equals(Object obj) {
		return getInventoryNumber() == ((AbstractCommodity) obj).getInventoryNumber();
	}
}
