package Actions;

public class AttackAction extends Action {
	
	protected int toHitBonus;
	protected int targetCount;
	protected int repeats;
	protected DamageDie damageDice[];

	public AttackAction(String name, int targetCount, int repeats, int toHitBonus, DamageDie damageDice[]) {
		super(name);
		this.targetCount = targetCount;
		this.repeats = repeats;
		this.toHitBonus = toHitBonus;
		this.damageDice = damageDice;
	}
	

	public int getToHitBonus() {
		return toHitBonus;
	}


	public int getTargetCount() {
		return targetCount;
	}


	public int getRepeats() {
		return repeats;
	}
	
	public DamageDie[] getDamageDice() {
		return damageDice;
	}

}
