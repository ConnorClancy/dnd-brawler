package Utilities;

import java.util.Random;
import java.util.Stack;

import Actions.Action;
import Actions.AoeAttackAction;
import Actions.AoeRechargeAction;
import Actions.AttackAction;
import Actions.MultiAction;
import Actions.RegenerationAction;
import Combatants.Combatant;
import Events.Event;
import Events.EventFactory;
import Exceptions.ActionNotExistException;
import Exceptions.EventTypeException;
import Runner.State;

public class CombatAI {
	
	
	public static Event determineAction(Combatant currentCombatant, CombatRoster roster) {
		
		//TODO add choosing logic
		Action chosenAction = null;
		
		try {
			if (currentCombatant.isMultiAttackAvailable()) {
				chosenAction = currentCombatant.getActionByType(MultiAction.class);
				System.out.println("multiattack found");
				
			} else if (currentCombatant.isAoeAttackAvailable()) {
				
				chosenAction = currentCombatant.getActionByType(AoeAttackAction.class);
				
				currentCombatant.setAoeAttackAvailable(false);
				
				System.out.println("Aoe found and available");
				
			} else {
				chosenAction = currentCombatant.getActionByType(AttackAction.class);
			}
		} catch (ActionNotExistException e) {
			e.printStackTrace();
			chosenAction = currentCombatant.getActions().get(0);
		}

		Stack<Combatant> targets = new Stack<Combatant>();

		if (chosenAction instanceof AoeAttackAction) {
			
			targets = calculateAoeTargetStack(currentCombatant);
			
		} else {
			for (Combatant c : roster.getOpponents(currentCombatant)) {
				targets.push(c);
			}
		}

		EventFactory factory = EventFactory.getEventFactory();
		
		try {
			return factory.createEvent(chosenAction, targets);
		} catch (EventTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean hasPassiveAbility(Combatant currentCombatant) {
		return currentCombatant.getPassiveAbilityCount() != 0;
	}
	
	public static Event determinePassiveAction(Combatant currentCombatant) {
		
		Action chosenAction = currentCombatant.getPassiveAbilities().get(0);
		
		Stack<Combatant> targets = new Stack<Combatant>();
		
		if (chosenAction instanceof RegenerationAction || chosenAction instanceof AoeRechargeAction) {
			targets.add(currentCombatant);
		}
		
		if (chosenAction instanceof AoeAttackAction) {
			targets = calculateAoeTargetStack(currentCombatant);
		}
		
		EventFactory factory = EventFactory.getEventFactory();
		
		try {
			return factory.createEvent(chosenAction, targets);
		} catch (EventTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	protected static Stack<Combatant> calculateAoeTargetStack(Combatant currentCombatant) {
		
		Stack<Combatant> stack = new Stack<Combatant>();
		//represents the number of quarters of opponents targeted by AOE, i.e 1 implies 25%, 2 implies 50%
		int[] aoeHitDistribution = new int[] {1, 1, 1, 2, 2, 2, 2, 3, 3, 4};
		
		Random selector = new Random();
		int selectedIndex = selector.nextInt(0, aoeHitDistribution.length);
		int selectedDistribution = aoeHitDistribution[selectedIndex];
		
		int numOpponents = State.getState().getRoster().getOpponentCount(currentCombatant);
		int targetCount;
		
		if (numOpponents <= 4) {				
			targetCount = Math.min(numOpponents, selectedDistribution);
		} else {
			//divide opponent count by 4, multiply by distribution, round down if needed
			double n = (numOpponents/4.0) * selectedDistribution;
			targetCount = (int) Math.floor(n);
		}
		
		System.out.println("targets selected: " + targetCount);
		
		int targetsAdded = 0;
		for (Combatant c : State.getState().getRoster().getOpponents(currentCombatant)) {
			stack.push(c);
			targetsAdded++;
			
			if (targetsAdded >= targetCount) {
				break;
			}
		}
		
		return stack;
	}

}
