package Combatants;

public class Statistics {

	protected int strength;
	protected int dexterity;
	protected int con;
	protected int intelligence;
	protected int wis;
	protected int cha;
	
	private final int LOWER_BOUND = -5;
	private final int UPPER_BOUND = 10;
	
	
	public Statistics(int strength, int dexterity, int con, int intelligence, int wis, int cha) {
		this.strength = strength;
		this.dexterity = dexterity;
		this.con = con;
		this.intelligence = intelligence;
		this.wis = wis;
		this.cha = cha;
	}

	public boolean isValid() {
		boolean valid = true;
		
		if (this.strength < LOWER_BOUND || this.strength > UPPER_BOUND) {
			valid = false;
		}
		if (this.dexterity < LOWER_BOUND || this.dexterity > UPPER_BOUND) {
			valid = false;
		}
		if (this.con < LOWER_BOUND || this.con > UPPER_BOUND) {
			valid = false;
		}
		if (this.intelligence < LOWER_BOUND || this.intelligence > UPPER_BOUND) {
			valid = false;
		}
		if (this.wis < LOWER_BOUND || this.wis > UPPER_BOUND) {
			valid = false;
		}
		if (this.cha < LOWER_BOUND || this.cha > UPPER_BOUND) {
			valid = false;
		}
		
		return valid;
	}

	public int getStrength() {
		return strength;
	}
	public int getDexterity() {
		return dexterity;
	}
	public int getCon() {
		return con;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public int getWis() {
		return wis;
	}
	public int getCha() {
		return cha;
	}
	
}
