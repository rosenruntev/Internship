package eu.deltasource.internship.hotelmanagement.core.commodities;

/**
 * A class for creating a shower commodity with inventory number
 */
public class Shower extends AbstractCommodity {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prepare() {
		System.out.println("the shower is being cleaned");
	}
}
