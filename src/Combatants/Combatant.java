package Combatants;

import Exceptions.CreationException;

public class Combatant {
	
	protected String name;
	protected int healthPoints;
	final protected int maximumHealthPoints;
	protected int ac;
	protected int speed;
	protected Statistics statistics;
	//TODO add teams logic
	protected String team = "";
	
	protected int initiative;
	
	public Combatant(String name, int healthPoints, int ac, int speed, Statistics statistics)  throws CreationException {
		if(statistics.isValid())
		{
			this.name = name;
			this.healthPoints = healthPoints;
			this.ac = ac;
			this.speed = speed;
			this.statistics = statistics;
			
			this.maximumHealthPoints = this.healthPoints;
		} else {
			throw new CreationException("Stats given are invalid");
		}
	}

	public int getHealthPoints() {
		return healthPoints;
	}
	
	public void reduceHealthPoints(int damageAmount) {
		this.healthPoints -= damageAmount;
	}
	
	public void increaseHealthPoints(int increaseAmount, boolean overheal) {
		this.healthPoints += increaseAmount;
		
		if (
			this.healthPoints > this.maximumHealthPoints && 
			!overheal) 
		{
			this.healthPoints = this.maximumHealthPoints;
		}
	}


	public int getAc() {
		return ac;
	}


	public int getSpeed() {
		return speed;
	}
	
	public Statistics statistics() {
		return this.statistics;
	}
	
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String toString() {
		return "Name: " + this.name;
	}
	
}
