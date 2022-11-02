package Actions;

public class Action {
	
	protected String name;
	protected int diceSides;
	protected int diceCount;
	protected int targetCount;
	protected int repeats;
	protected int damageBonus;
	
	
	public Action
	(
			String name, 
			int diceSides, 
			int diceCount, 
			int targetCount, 
			int repeats, 
			int damageBonus
	) {
		this.name = name;
		this.diceSides = diceSides;
		this.diceCount = diceCount;
		this.targetCount = targetCount;
		this.repeats = repeats;
		this.damageBonus = damageBonus;
	}

	public String getName() {
		return name;
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
