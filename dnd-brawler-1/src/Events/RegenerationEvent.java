package Events;

import java.util.Stack;

import Actions.Action;
import Actions.RegenerationAction;
import Combatants.Combatant;

public class RegenerationEvent implements Event {

	boolean NOT_OVERHEAL = false;

	protected RegenerationAction action;
	protected Stack<Combatant> targetStack;

	public RegenerationEvent(RegenerationAction action, Stack<Combatant> targetStack) {
		this.action = action;
		this.targetStack = targetStack;
	}

	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stack<Combatant> getTargetStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doActionToTargets() {
		targetStack.get(0).increaseHealthPoints(
				action.getFlatAmount(), 
				NOT_OVERHEAL
			);
		
		System.out.println("Healed " + action.getFlatAmount() + "hp - new total: " + targetStack.get(0).getHealthPoints() + "hp");
	}

	@Override
	public boolean combatantsRemoved() {
		// TODO Auto-generated method stub
		return false;
	}

}
