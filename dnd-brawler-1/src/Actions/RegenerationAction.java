package Actions;

public class RegenerationAction extends Action {

	protected int flatAmount;
	
	public RegenerationAction(String name, int flatAmount) {
		super(name);
		this.flatAmount = flatAmount;
	}

	
	public int getFlatAmount() {
		return flatAmount;
	}

}
