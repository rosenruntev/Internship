package core.Commodities;

public class Shower extends AbstractCommodity {

	public Shower(int inventoryNumber) {
		super(inventoryNumber);
	}

	@Override
	public void prepare() {
		System.out.println("the shower is being cleaned");
	}
}
