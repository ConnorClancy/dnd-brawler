package Events;

import java.util.ArrayList;
import java.util.Stack;

import Actions.Action;
import Combatants.Combatant;

public interface Event {
	
	public Action getAction();
	
	public Stack<Combatant> getTargetStack();
	
	public void doActionToTargets();

	boolean combatantsRemoved();

}
