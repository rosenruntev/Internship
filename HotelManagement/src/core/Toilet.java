package core;

public class Toilet extends AbstractCommodity {
	@Override
	public void prepare() {
		System.out.println("the toilet is being cleaned");
	}
}
