package Events;

import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import Actions.Action;
import Actions.MultiAction;
import Combatants.Combatant;
import Exceptions.EventTypeException;
import Runner.GameRunner;

public class MultiAttackEvent implements Event {

	protected MultiAction action;
	protected Stack<Combatant> sourceTargetStack;
	protected Stack<Combatant> copyTargetStack;
	
	Logger log;

	public MultiAttackEvent(MultiAction action, Stack<Combatant> targetStack) {
		this.action = action;
		this.sourceTargetStack = targetStack;
		log = Logger.getLogger(MultiAttackEvent.class.getName());
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
						log.log(Level.WARNING, "Event could not be created - " + e);
					}
				}
			}
		}

		);
	}

}
