package Runner;

import java.util.Iterator;

import Combatants.Combatant;
import Events.Event;
import Utilities.CombatAI;
import Utilities.CombatRoster;

public class GameRunner {

	public static void main(String args[]) {
		
		GameInitialiser gi = new GameInitialiser();
		
		if (gi.setField()) {
			
			CombatRoster roster = State.getState().getRoster();
			
			System.out.println(roster.toString());
			
			Iterator<Combatant> turnItr = roster.iterator();
			
			Combatant currCombatant = null;
			Event event = null;
			
			int roundCounter = 0;
			//Round Loop
			while (State.getState().opposingCombatantsRemain()) {
				
				System.out.println("Round :" + ++roundCounter);
				System.out.println("----------------------");
				
				turnItr = roster.listIterator();

				// Turn Loop
				while (turnItr.hasNext() && State.getState().opposingCombatantsRemain()) {
					
					int numCombatantsTopOfTurn = State.getState().getCombatantCount();
					
					currCombatant = turnItr.next();
					
					System.out.println("Turn: " + currCombatant.toString());

					roster = State.getState().getRoster();

					// top of turn affects e.g regen
					if (CombatAI.hasPassiveAbility(currCombatant)) {
						CombatAI.determinePassiveAction(currCombatant).doActionToTargets();
					}

					// take actions
					event = CombatAI.determineAction(currCombatant, roster);

					event.doActionToTargets();
					
					if(State.getState().getCombatantCount() < numCombatantsTopOfTurn) {
						//reset iterator to avoid concurrent manipulation exception
						turnItr = roster.listIterator();
						while (turnItr.hasNext() && turnItr.next() != currCombatant) {
							System.out.println("resetting iterator to " + currCombatant);
						}
					}
					
				}

			}
			
			System.out.println("----------------------");

			System.out.println("Winning Team: " + State.getState().getRoster().get(0).getTeam());

			
		}
		
		else {
			//TODO throw some custom exception
			System.out.println("Fuckin Error with setField m8");
		}
		
		System.out.println("----------------------");
		
		
		System.out.println("Brawl Brawled");
	}
}
