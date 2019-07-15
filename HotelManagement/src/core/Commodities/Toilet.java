package core.Commodities;

public class Toilet extends AbstractCommodity {

	public Toilet(int inventoryNumber) {
		super(inventoryNumber);
	}

	@Override
	public void prepare() {
		System.out.println("the toilet is being cleaned");
	}
}
