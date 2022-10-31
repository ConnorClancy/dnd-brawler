package Actions;

public class RegenerationAction extends Action {

	protected int flatAmount;
	
	public RegenerationAction(String type, int diceSides, int diceCount, int targetCount, int repeats,
			int damageBonus, int flatAmount) {
		super(type, diceSides, diceCount, targetCount, repeats, damageBonus);

		this.flatAmount = flatAmount;
	}

	
	public int getFlatAmount() {
		return flatAmount;
	}

}
