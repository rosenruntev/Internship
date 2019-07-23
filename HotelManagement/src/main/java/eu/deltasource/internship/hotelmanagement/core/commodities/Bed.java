package eu.deltasource.internship.hotelmanagement.core.commodities;

/**
 * A class for creating a bed commodity with inventory number and bed type
 */
public class Bed extends AbstractCommodity {
	private BedType type;

	/**
	 * Constructs a bed with given bed type
	 *
	 * @param type the bed type
	 */
	public Bed(BedType type) {
		setType(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prepare() {
		System.out.println("Bed sheets are being replaced");
	}

	public BedType getType() {
		return type;
	}

	private void setType(BedType type) {
		this.type = type;
	}
}
