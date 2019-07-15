package core.Commodities;

public abstract class AbstractCommodity {
	private int inventoryNumber;

	public AbstractCommodity(int inventoryNumber) {
		this.setInventoryNumber(inventoryNumber);
	}

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
		return this.getInventoryNumber() == ((AbstractCommodity)obj).getInventoryNumber();
	}
}
