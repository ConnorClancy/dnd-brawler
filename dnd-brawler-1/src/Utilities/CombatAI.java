package Utilities;

import java.util.Stack;

import Actions.Action;
import Actions.AoeAttackAction;
import Actions.AoeRechargeAction;
import Actions.MultiAction;
import Actions.RegenerationAction;
import Combatants.Combatant;
import Events.Event;
import Events.EventFactory;
import Exceptions.ActionNotExistException;
import Exceptions.EventTypeException;

public class CombatAI {
	
	
	public static Event determineAction(Combatant currentCombatant, CombatRoster roster) {
		
		//TODO add choosing logic
		Action chosenAction = null;
		
		try {
			if (currentCombatant.isMultiAttackAvailable()) {
				chosenAction = currentCombatant.getActionByType(MultiAction.class);
				System.out.println("multiattack found");
				
			} else if (currentCombatant.isAoeAttackAvailable()) {
				
				chosenAction = currentCombatant.getActionByType(AoeAttackAction.class);
				
				currentCombatant.setAoeAttackAvailable(false);
				
				System.out.println("Aoe found and available");
				
			} else {
				chosenAction = currentCombatant.getActions().get(0);
			}
		} catch (ActionNotExistException e) {
			e.printStackTrace();
			chosenAction = currentCombatant.getActions().get(0);
		}

		Stack<Combatant> targets = new Stack<Combatant>();

		 //TODO choosing logic
		for (Combatant c : roster.getOpponents(currentCombatant)) {
			targets.push(c);
		}

		EventFactory factory = EventFactory.getEventFactory();
		
		try {
			return factory.createEvent(chosenAction, targets);
		} catch (EventTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean hasPassiveAbility(Combatant currentCombatant) {
		return currentCombatant.getPassiveAbilityCount() != 0;
	}
	
public static Event determinePassiveAction(Combatant currentCombatant) {
		
		Action chosenAction = currentCombatant.getPassiveAbilities().get(0);
		
		Stack<Combatant> targets = new Stack<Combatant>();
		
		if (chosenAction instanceof RegenerationAction || chosenAction instanceof AoeRechargeAction) {
			targets.add(currentCombatant);
		}
		
		EventFactory factory = EventFactory.getEventFactory();
		
		try {
			return factory.createEvent(chosenAction, targets);
		} catch (EventTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
