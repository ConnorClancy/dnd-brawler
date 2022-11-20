package Actions;

public class DamageDie {

	protected int sides;
	protected int count;
	protected int damageBonus;
	protected String damageType;
	
	
	public DamageDie(int sides, int count, int damageBonus, String damageType) {
		this.sides = sides;
		this.count = count;
		this.damageBonus = damageBonus;
		this.damageType = damageType;
	}


	public int getSides() {
		return sides;
	}


	public int getDiceCount() {
		return count;
	}


	public int getDamageBonus() {
		return damageBonus;
	}


	public String getDamageType() {
		return damageType;
	}
	
}
