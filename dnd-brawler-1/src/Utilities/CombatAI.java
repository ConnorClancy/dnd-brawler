package Utilities;

import java.util.Stack;
import Actions.Action;
import Actions.AttackAction;
import Combatants.Combatant;
import Events.Event;
import Events.EventFactory;
import Exceptions.EventTypeException;

public class CombatAI {
	
	
	public static Event determineAction(Combatant currentCombatant, CombatRoster roster) {
		Action chosenAction = new AttackAction("Attack", 6, 2, 1, 1, 4, 2);

		Stack<Combatant> targets = new Stack<Combatant>();

		/*
		 * TODO choosing logic
		 */

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

}
