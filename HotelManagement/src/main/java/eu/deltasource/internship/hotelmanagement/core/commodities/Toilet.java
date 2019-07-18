package eu.deltasource.internship.hotelmanagement.core.commodities;

/**
 * A class for creating a toilet commodity with inventory number
 */
public class Toilet extends AbstractCommodity {

	/**
	 * Constructs a toilet commodity with given inventory number
	 *
	 * @param inventoryNumber the id of the toilet in inventory
	 */
	public Toilet(int inventoryNumber) {
		super(inventoryNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prepare() {
		System.out.println("the toilet is being cleaned");
	}
}
