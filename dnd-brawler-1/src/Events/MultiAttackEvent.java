package Events;

import java.util.Map;
import java.util.Stack;

import Actions.Action;
import Actions.MultiAction;
import Combatants.Combatant;

public class MultiAttackEvent implements Event {
	
	protected MultiAction action;
	protected Stack<Combatant> targetStack;
	protected Combatant attacker;

	public MultiAttackEvent(MultiAction action, Stack<Combatant> targetStack, Combatant attacker) {
		this.action = action;
		this.targetStack = targetStack;
		this.attacker = attacker;
	}

	@Override
	public void doActionToTargets() {
		
		//gets multiAction mapping
		Map<String, Integer> attackSequence = action.getMultiActionMapping();
		
		//iterates over
		attackSequence.forEach(
	            (actionName, repeatCount)
	                -> {
	                	for (int i = 1; i <= repeatCount; i++) {
	                		System.out.println(actionName + ": " + i + "/" + repeatCount);
	                	}
	                }
				
				);
		
		
		//calls event factory for all internal events
	}

}
