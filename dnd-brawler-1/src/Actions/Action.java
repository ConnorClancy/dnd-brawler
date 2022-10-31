package Actions;

public class Action {
	
	protected String type;
	protected int diceSides;
	protected int diceCount;
	protected int targetCount;
	protected int repeats;
	protected int damageBonus;
	
	
	public Action
	(
			String type, 
			int diceSides, 
			int diceCount, 
			int targetCount, 
			int repeats, 
			int damageBonus
	) {
		this.type = type;
		this.diceSides = diceSides;
		this.diceCount = diceCount;
		this.targetCount = targetCount;
		this.repeats = repeats;
		this.damageBonus = damageBonus;
	}

	public String getType() {
		return type;
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
