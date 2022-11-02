package Actions;

public class AttackAction extends Action {
	
	protected int toHitBonus;

	public AttackAction(String type, int diceSides, int diceCount, int targetCount, int repeats, int toHitBonus,
			int damageBonus) {
		super(type, diceSides, diceCount, targetCount, repeats, damageBonus);
		
		this.toHitBonus = toHitBonus;
	}
	

	public int getToHitBonus() {
		return toHitBonus;
	}


}
