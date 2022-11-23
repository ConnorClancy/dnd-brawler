package Combatants;

import java.util.ArrayList;

import Actions.Action;
import Exceptions.ActionNotExistException;
import Exceptions.CreationException;

public class Combatant {
	
	protected String name;
	protected int healthPoints;
	final protected int maximumHealthPoints;
	protected int ac;
	protected int speed;
	protected Statistics statistics;
	protected String team = "";
	protected boolean multiAttackAvailable = false;
	protected boolean aoeAttackAvailable = false;
	
	protected int initiative;
	
	protected ArrayList<Action> actions;
	protected ArrayList<Action> passiveAbilities;
	
	final protected String[] damageResitances;
	final protected String[] damageVulnerabilities;

	public Combatant(String name, int healthPoints, int ac, int speed, Statistics statistics, String[] restistances, String[] vulnerabilities)  throws CreationException {
		if(statistics.isValid())
		{
			this.name = name;
			this.healthPoints = healthPoints;
			this.ac = ac;
			this.speed = speed;
			this.statistics = statistics;
			
			this.maximumHealthPoints = this.healthPoints;
			this.actions = new ArrayList<Action>();
			this.passiveAbilities = new ArrayList<Action>();
		} else {
			throw new CreationException("Stats given are invalid");
		}
		this.damageResitances = restistances;
		this.damageVulnerabilities = vulnerabilities;
	}

	public int getHealthPoints() {
		return healthPoints;
	}
	
	public void reduceHealthPoints(int damageAmount) {
		this.healthPoints -= damageAmount;
	}
	
	public void reduceHealthPoints(int damageAmount, String incomingDamageType) {
		
		for (String resistedType : damageResitances) {
			if (incomingDamageType.equals(resistedType)) {
				damageAmount = (int) Math.floor(damageAmount/2.0);
				System.out.println("resisted damage");
				break;
			}
		}
		
		for (String vulnerableType : damageVulnerabilities) {
			if (incomingDamageType.equals(vulnerableType)) {
				damageAmount = damageAmount * 2;
				System.out.println("vulnerable!");
				break;
			}
		}
			
		
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
		return this.name + " (hp:" + this.healthPoints + ")";
	}
	
	public ArrayList<Action> getActions() {
		return actions;
	}
	
	public Action getAction(String nameOfActionNeeded) throws ActionNotExistException {
		for (Action currentAction : this.actions) {
			if(currentAction.getName().equals(nameOfActionNeeded)) {
				return currentAction;
			}
		}	
		throw new ActionNotExistException("Action not found - '" + nameOfActionNeeded + "' does not exist");
	}
	
	public <T> Action getActionByType(Class<T> type) throws ActionNotExistException {
		for (Action currentAction : this.actions) {
			if(type.isInstance(currentAction)) {
				return currentAction;
			}
		}	
		throw new ActionNotExistException("Action not found - '" + type + "' does not exist");
	}

	public void addAction(Action action) {
		this.actions.add(action);
	}
	
	public ArrayList<Action> getPassiveAbilities() {
		return passiveAbilities;
	}
	
	public int getPassiveAbilityCount() {
		return passiveAbilities.size();
	}

	public void addPassiveAbility(Action action) {
		passiveAbilities.add(action);
	}

	public void updateName(String newName) {
		this.name = newName;
		
	}

	public boolean isMultiAttackAvailable() {
		return multiAttackAvailable;
	}

	public void setMultiAttackAvailable(boolean hasMultiAttack) {
		this.multiAttackAvailable = hasMultiAttack;
	}

	public boolean isAoeAttackAvailable() {
		return aoeAttackAvailable;
	}
	
	public void setAoeAttackAvailable(boolean hasAoeAttack) {
		this.aoeAttackAvailable = hasAoeAttack;
	}

	public int getStatBonus(String saveType) {
		
		switch (saveType) {
		
		case "strength":
			return statistics.getStrength();
		case "dexterity":
			return statistics.getDexterity();
		case "constitusion":
			return statistics.getCon();
		case "intelligence":
			return statistics.getIntelligence();
		case "wisdom":
			return statistics.getWis();
		case "charisma":
			return statistics.getCha();
		default:
			return 0;		
		}
		
	}
	
	public void addAbility(Action action, String actionSetType) {
		switch(actionSetType) {
		
		case "actions" :
			actions.add(action);
			break;
			
		case "passives" :
			passiveAbilities.add(action);
			break;
			
		}	
	}
	
}
