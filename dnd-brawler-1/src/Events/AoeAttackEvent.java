package Events;

import java.util.Stack;

import Actions.AoeAttackAction;
import Combatants.Combatant;
import Runner.State;
import Utilities.BrawlOutputter;
import Utilities.DiceBox;

public class AoeAttackEvent implements Event {

	protected AoeAttackAction action;
	protected Stack<Combatant> targetStack;
	protected int damageRoll;

	public AoeAttackEvent(AoeAttackAction action, Stack<Combatant> targetStack) {
		this.action = action;
		this.targetStack = targetStack;
	}

	@Override
	public void doActionToTargets() {

		damageRoll = 0;

		for (int diceRolled = 0; diceRolled < action.getDiceCount(); diceRolled++) {
			damageRoll += DiceBox.rollActionDice(action.getDiceSides());
		}

		while (!targetStack.isEmpty()) {
			Combatant currentTarget = targetStack.pop();

			// current target makes save
			int save = DiceBox.rollDTwenty() + currentTarget.getStatBonus(action.getSaveType());

			if (save < action.getDc()) {
				BrawlOutputter.getBrawlOutputter().logEvent(action.getName() + " hits " + currentTarget + "(rolled " + save
						+ ") with full damage: " + damageRoll);
				currentTarget.reduceHealthPoints(damageRoll, action.getDamageType());
			} else {
				int halfDamage = Math.round(damageRoll) / 2;
				BrawlOutputter.getBrawlOutputter().logEvent(action.getName() + " hits " + currentTarget + "(rolled " + save
						+ ") with half damage: " + halfDamage);
				currentTarget.reduceHealthPoints(halfDamage, action.getDamageType());
			}

			if (currentTarget.getHealthPoints() <= 0) {
				State.getState().removeCombatant(currentTarget);
			}

		}

	}

	// testing helper method
	public int getDamageRoll() {
		return damageRoll;
	}

}
