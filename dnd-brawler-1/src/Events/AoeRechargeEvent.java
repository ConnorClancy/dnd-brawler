package Events;

import java.util.Stack;

import Actions.AoeRechargeAction;
import Combatants.Combatant;
import Utilities.BrawlOutputter;
import Utilities.DiceBox;

public class AoeRechargeEvent implements Event {

	protected AoeRechargeAction action;
	protected Stack<Combatant> targetStack;

	public AoeRechargeEvent(AoeRechargeAction action, Stack<Combatant> targetStack) {
		this.action = action;
		this.targetStack = targetStack;
	}

	@Override
	public void doActionToTargets() {

		if (!targetStack.peek().isAoeAttackAvailable()) {

			int rechargeRoll = DiceBox.rollActionDice(action.getRechargeDieSides());

			BrawlOutputter.getBrawlOutputter().logEvent(targetStack.peek() + " rolls " + rechargeRoll + " to recharge its AOE - " + action.getSuccessBoundLower() + "needed");

			targetStack.pop().setAoeAttackAvailable(
					rechargeRoll >= action.getSuccessBoundLower() && 
					rechargeRoll <= action.getSuccessBoundUpper()
				);
		}
	}

}
