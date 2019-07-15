package core.Commodities;

public class Bed extends AbstractCommodity {
	private int numberOfPeople;

	public Bed(int numberOfPeople) {
		this.setNumberOfPeople(numberOfPeople);
	}

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
