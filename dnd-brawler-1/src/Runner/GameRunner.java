package Runner;

import java.util.Iterator;

import Combatants.Combatant;
import Events.Event;
import Utilities.CombatAI;
import Utilities.CombatRoster;
import Utilities.DiceBox;

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
				while (turnItr.hasNext()) {
					
					currCombatant = turnItr.next();
					
					System.out.println("Turn: " + currCombatant.toString());

					roster = State.getState().getRoster();

					// top of turn affects e.g regen

					// take actions
					event = CombatAI.determineAction(currCombatant, roster);

					event.doActionToTargets();
				
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
