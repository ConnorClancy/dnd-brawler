package Events;

import java.util.Stack;

import Actions.RegenerationAction;
import Combatants.Combatant;
import Utilities.BrawlOutputter;

public class RegenerationEvent implements Event {

	protected final boolean NOT_OVERHEAL = false;

	protected RegenerationAction action;
	protected Stack<Combatant> targetStack;

	public RegenerationEvent(RegenerationAction action, Stack<Combatant> targetStack) {
		this.action = action;
		this.targetStack = targetStack;
	}

	@Override
	public void doActionToTargets() {
		targetStack.get(0).increaseHealthPoints(
				action.getFlatAmount(), 
				NOT_OVERHEAL
			);
		
		BrawlOutputter.getBrawlOutputter().logEvent("Healed " + action.getFlatAmount() + "hp - new total: " + targetStack.get(0).getHealthPoints() + "hp");
	}

}
