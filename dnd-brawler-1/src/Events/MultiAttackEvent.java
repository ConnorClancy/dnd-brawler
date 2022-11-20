package Events;

import java.util.Map;
import java.util.Stack;

import Actions.Action;
import Actions.MultiAction;
import Combatants.Combatant;
import Exceptions.EventTypeException;

public class MultiAttackEvent implements Event {

	protected MultiAction action;
	protected Stack<Combatant> sourceTargetStack;
	protected Stack<Combatant> copyTargetStack;

	public MultiAttackEvent(MultiAction action, Stack<Combatant> targetStack) {
		this.action = action;
		this.sourceTargetStack = targetStack;
	}

	@Override
	public void doActionToTargets() {

		Map<Action, Integer> attackSequence = action.getMultiActionMapping();

		EventFactory factory = EventFactory.getEventFactory();

		attackSequence.forEach((action, repeatCount) -> {
			for (int i = 1; i <= repeatCount; i++) {
				System.out.println(action.getName() + ": " + i + "/" + repeatCount);
				
				if (!sourceTargetStack.isEmpty()) {

					this.copyTargetStack = (Stack<Combatant>) this.sourceTargetStack.clone();

					try {
						
						factory.createEvent(action, copyTargetStack).doActionToTargets();
						
						if (sourceTargetStack.peek().getHealthPoints() <= 0) {
							sourceTargetStack.pop();
						}
						
					} catch (EventTypeException e) {
						e.printStackTrace();
					}
				}
			}
		}

		);
	}

}
