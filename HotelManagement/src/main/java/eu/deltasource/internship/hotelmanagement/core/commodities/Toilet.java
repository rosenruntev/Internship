package eu.deltasource.internship.hotelmanagement.core.commodities;

/**
 * A class for creating a toilet commodity with inventory number
 */
public class Toilet extends AbstractCommodity {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prepare() {
		System.out.println("the toilet is being cleaned");
	}
}
