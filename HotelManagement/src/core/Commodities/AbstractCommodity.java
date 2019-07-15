package core.Commodities;

public abstract class AbstractCommodity {
	private int inventoryNumber;

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
}
