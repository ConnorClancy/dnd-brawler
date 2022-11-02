package Utilities;

import java.util.Stack;
import Actions.Action;
import Actions.AttackAction;
import Actions.RegenerationAction;
import Combatants.Combatant;
import Events.Event;
import Events.EventFactory;
import Events.RegenerationEvent;
import Exceptions.EventTypeException;

public class CombatAI {
	
	
	public static Event determineAction(Combatant currentCombatant, CombatRoster roster) {
		
		//TODO add choosing logic
		Action chosenAction = currentCombatant.getActions().get(0);

		Stack<Combatant> targets = new Stack<Combatant>();

		 //TODO choosing logic
		targets.push(roster.getOpponents(currentCombatant).getFirst());

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
		
		if (chosenAction instanceof RegenerationAction) {
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
