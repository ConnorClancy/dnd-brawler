package Events;

import java.util.Stack;

import Actions.Action;
import Combatants.Combatant;
import Runner.State;
import Utilities.DiceBox;

public class AttackEvent implements Event {
	
	protected Action action;
	protected Stack<Combatant> targetStack;
	protected boolean combatantsRemoved;

	public AttackEvent(Action action, Stack<Combatant> targetStack) {
		this.action = action;
		this.targetStack = targetStack;
		combatantsRemoved = false;
	}

	@Override
	public Action getAction() {
		return action;
	}

	@Override
	public Stack<Combatant> getTargetStack() {
		return targetStack;
	}

	@Override
	public void doActionToTargets() {
		// make the attack boi

		int attackRoll;
		int damageRoll;

		Combatant currentTarget = this.targetStack.pop();

		for (int attackCounter = 0; attackCounter < this.action.getRepeats(); attackCounter++) {

			attackRoll = 0;
			damageRoll = 0;

			attackRoll = DiceBox.rollDTwenty() + this.action.getToHitBonus();

			for (int rolledDamageDice = 0; rolledDamageDice < this.action.getDiceCount(); rolledDamageDice++) {
				damageRoll += rollActionDice(this.action.getDiceSides());
			}

			damageRoll += this.action.getDamageBonus();
			
			System.out.println("Rolls " + attackRoll + " to hit");

			// do damage to target and check they aren't dead, if dead and targets remain
			// then switch target, else end turn

			if (attackRoll >= currentTarget.getAc()) {
				
				System.out.println("Hits " + currentTarget.toString() + " for " + damageRoll + " points of damage");

				currentTarget.reduceHealthPoints(damageRoll);
				
				if (currentTarget.getHealthPoints() <= 0) {
					State.getState().removeCombatant(currentTarget);
					
					combatantsRemoved = true;
					
					if (this.targetStack.isEmpty()) {
						return;
					} else {
						currentTarget = this.targetStack.pop();
					}
				}
			} else {
				System.out.println("Attack missed");
			}
			
			

		}
	}

	private int rollActionDice(int diceSides) {
		switch(diceSides) {
		case 4:
			return DiceBox.rollDFour();
		case 6:
			return DiceBox.rollDSix();
		case 8:
			return DiceBox.rollDEight();
		case 10:
			return DiceBox.rollDTen();
		case 12:
			return DiceBox.rollDTwelve();
		default:
			return DiceBox.rollNSidedDice(diceSides);
			// >:(
			//better coding but less fun
		}
	}

	@Override
	public boolean combatantsRemoved() {
		return combatantsRemoved;
	}

}
