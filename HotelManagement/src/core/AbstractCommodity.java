package core;

public abstract class AbstractCommodity {
	private String name;

	public abstract void prepare();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
