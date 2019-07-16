package com.deltasource.hotelmanagement.core.commodities;

/**
 * A class for creating a shower commodity with inventory number
 */
public class Shower extends AbstractCommodity {

	/**
	 * Constructs a shower with given inventory number
	 * @param inventoryNumber the id of the shower in inventory
	 */
	public Shower(int inventoryNumber) {
		super(inventoryNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prepare() {
		System.out.println("the shower is being cleaned");
	}
}
