package Events;

import java.util.Stack;

import Actions.AoeRechargeAction;
import Combatants.Combatant;
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

			System.out.println(targetStack.peek() + " rolled " + rechargeRoll + " to recharge its AOE");

			targetStack.pop().setAoeAttackAvailable(
					rechargeRoll >= action.getSuccessBoundLower() && 
					rechargeRoll <= action.getSuccessBoundUpper()
				);
		}
	}

}
