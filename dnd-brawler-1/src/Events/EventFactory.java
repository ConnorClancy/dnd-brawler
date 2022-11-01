package Events;

import java.util.Stack;

import Actions.Action;
import Actions.AttackAction;
import Actions.RegenerationAction;
import Combatants.Combatant;
import Exceptions.EventTypeException;

/*
 * Singleton class, as we only need one of these factories pumpin' out Events
 */
public class EventFactory {

	private static EventFactory eventFactory = null;
		
	private EventFactory() {}
	
	public static EventFactory getEventFactory() {
		if (eventFactory == null) {
			eventFactory = new EventFactory();
		}
		
		return eventFactory;
	}
	
	public Event createEvent(Action action, Stack<Combatant> targetStack) throws EventTypeException {		
		if (action instanceof AttackAction ) {
			return new AttackEvent((AttackAction)action, targetStack);
		} else if (action instanceof RegenerationAction ) {
			return new RegenerationEvent((RegenerationAction)action, targetStack);
		}
		else {
			throw new EventTypeException("Action type not recognised");
		}
	}
}
