package com.deltasource.hotelmanagement.core.commodities;

/**
 * A class for creating a bed commodity with inventory number and number of people
 */
public class Bed extends AbstractCommodity {
	private int numberOfPeople;

	/**
	 * Constructs a bed with given inventory number and number of people
	 * @param inventoryNumber the id of the bed in the inventory
	 * @param numberOfPeople the bed size
	 */
	public Bed(int inventoryNumber, int numberOfPeople) {
		super(inventoryNumber);
		setNumberOfPeople(numberOfPeople);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prepare() {
		System.out.println("Bed sheets are being replaced");
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		if (numberOfPeople < 1) {
			throw new IllegalArgumentException("Number of people cannot be less than 1.");
		}

		this.numberOfPeople = numberOfPeople;
	}
}
