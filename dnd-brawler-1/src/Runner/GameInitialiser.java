package Runner;

import java.util.Arrays;

import Actions.Action;
import Actions.AttackAction;
import Combatants.Combatant;
import Combatants.CombatantSorter;
import Combatants.Statistics;
import Exceptions.CreationException;
import Utilities.DiceBox;

public class GameInitialiser {

	/*
	 * Class creates the following
	 * singleton State object
	 * Combatants (for now manually, later maybe from JSON)
	 * Created Actions and assigns to combatants
	 * Future idea: env variables - things like light/no light, under water, per turn damage etc
	 * 
	 */
	
	//constructor
	
	//create combatants on each side
	
	//create STATE and "add" combatants
	//roll initiative and re-order combatants
	
	//Combatants class = stats and numbers
	//CombatantRepitour class = actions logic etc?
	
	
	public GameInitialiser(/*pass in combatants in future?*/) {
		
	}
	
	/*
	 * Initializes combatants
	 * rolls initiative
	 * adds to state
	 */
	public boolean setField() {
				
		Statistics aStats = new Statistics(
				0,
				2,
				2,
				-2,
				-1,
				-3
			);
		
		Statistics bStats = new Statistics(
				1,
				2,
				0,
				-5,
				-3,
				-5
			);
		
		try {
			
			Combatant A = new Combatant("Skeleton", 13, 13, 30, aStats);
			Combatant B = new Combatant("Flying Sword", 17, 17, 50, bStats);
			
			//	super(type, diceSides, diceCount, targetCount, repeats, toHitBonus, damageBonus);
			
			AttackAction skeletonAttack = new AttackAction("attack", 6, 1, 1, 1, 4, 2);
			AttackAction swordAttack = new AttackAction("attack", 8, 1, 1, 1, 3, 1);
			
			A.addAction(skeletonAttack);
			B.addAction(swordAttack);
			
			A.setTeam("red");
			B.setTeam("blue");
			
			Combatant[] field = {A, B};
			
			State state = State.getState();
			
			int initiative = 0;
			
			for (Combatant c : field) {
				initiative = rollInitiative(c);
				System.out.println(c.toString() + " - intiative:" + initiative);
				c.setInitiative(initiative);
			}
			
			Arrays.sort(field, new CombatantSorter());
			
			for (Combatant c : field) {
				state.addCombatant(c);
			}
			
			
		} catch (CreationException C) {
			System.out.println(C.getMessage());
		}
		
		if (State.getState().getRoster().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	private int rollInitiative(Combatant c) {
		return DiceBox.rollDTwenty() + c.statistics().getDexterity();
	}
	
}
