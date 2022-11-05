package Actions;

public class AttackAction extends Action {
	
	protected int toHitBonus;
	protected int diceSides;
	protected int diceCount;
	protected int targetCount;
	protected int repeats;
	protected int damageBonus;

	public AttackAction(String name, int diceSides, int diceCount, int targetCount, int repeats, int toHitBonus,
			int damageBonus) {
		super(name);
		this.diceSides = diceSides;
		this.diceCount = diceCount;
		this.targetCount = targetCount;
		this.repeats = repeats;
		this.damageBonus = damageBonus;
		this.toHitBonus = toHitBonus;
	}
	

	public int getToHitBonus() {
		return toHitBonus;
	}

	public int getDiceSides() {
		return diceSides;
	}


	public int getDiceCount() {
		return diceCount;
	}


	public int getTargetCount() {
		return targetCount;
	}


	public int getRepeats() {
		return repeats;
	}


	public int getDamageBonus() {
		return damageBonus;
	}

}
