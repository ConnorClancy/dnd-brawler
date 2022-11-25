package Runner;

import java.util.Iterator;
import java.util.logging.Logger;

import Combatants.Combatant;
import Events.Event;
import Exceptions.ValidationException;
import Utilities.CombatAI;
import Utilities.CombatRoster;
import Utilities.FieldEntry;
import Utilities.LayoutCreator;


public class GameRunner {
	
	protected static Logger log = Logger.getLogger("GameRunner");

	public static void main(String args[]) {
		
		LayoutCreator lc = new LayoutCreator(args);
		
		FieldEntry[] fieldLayout;
		
		try {
			fieldLayout = lc.generateFieldLayout();
		} catch (ValidationException e) {
			log.severe("Input not valid - brawl cancelled");
			return;
		}
		
		GameInitialiser gi = new GameInitialiser();
		
		if (gi.setField(fieldLayout)) {
			
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
							log.info("resetting iterator to " + currCombatant);
						}
					}
					
				}

			}
			
			System.out.println("----------------------");

			System.out.println("Winning Team: " + State.getState().getRoster().get(0).getTeam());

			
		}
		
		else {
			log.severe("Initialiser failed, game did not start");
			System.out.println("Exiting Early: report error");
		}
		
		System.out.println("----------------------");
		
		
		System.out.println("Brawl Brawled");
	}
}
