package Events;

import java.util.Stack;

import Actions.AttackAction;
import Actions.DamageDie;
import Combatants.Combatant;
import Utilities.DiceBox;
import Runner.State;

public class AttackEvent implements Event {
	
	protected AttackAction action;
	protected Stack<Combatant> targetStack;

	public AttackEvent(AttackAction action, Stack<Combatant> targetStack) {
		this.action = action;
		this.targetStack = targetStack;
	}

	@Override
	public void doActionToTargets() {
		// make the attack boi

		int attackRoll;
		int damageRoll;

		Combatant currentTarget = this.targetStack.pop();

		for (int attackCounter = 0; attackCounter < this.action.getRepeats(); attackCounter++) {

			attackRoll = 0;
			
			attackRoll = DiceBox.rollDTwenty() + this.action.getToHitBonus();
			
			System.out.println("Rolls " + attackRoll + " to hit with its " + action.getName());

			if (attackRoll >= currentTarget.getAc()) {
				
				// do damage to target and check they aren't dead, if dead and targets remain
				// then switch target, else end event
				 
				for (DamageDie damageDie : action.getDamageDice()) {

					damageRoll = 0;

					for (int rolledDice = 0; rolledDice < damageDie.getDiceCount(); rolledDice++) {
						damageRoll += DiceBox.rollActionDice(damageDie.getSides());
					}

					damageRoll += damageDie.getDamageBonus();

					System.out.println("Hits " + currentTarget.toString() + " for " +
							damageRoll + " points of " + damageDie.getDamageType() + " damage");

					currentTarget.reduceHealthPoints(damageRoll, damageDie.getDamageType());
				}
				
				if (currentTarget.getHealthPoints() <= 0) {
					State.getState().removeCombatant(currentTarget);
										
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

}
