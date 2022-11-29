package Runner;

import java.util.Iterator;
import java.util.logging.Logger;

import Combatants.Combatant;
import Events.Event;
import Exceptions.ValidationException;
import Utilities.BrawlOutputter;
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
		
		BrawlOutputter brawlOutputter = BrawlOutputter.getBrawlOutputter();
		
		GameInitialiser gi = new GameInitialiser();
		
		if (gi.setField(fieldLayout)) {
			
			CombatRoster roster = State.getState().getRoster();
			
			log.info(roster.toString());
			
			Iterator<Combatant> turnItr = roster.iterator();
			
			Combatant currCombatant = null;
			Event event = null;
			
			int roundCounter = 0;
			//Round Loop
			while (State.getState().opposingCombatantsRemain()) {				
				
				brawlOutputter.logNewRound(++roundCounter);
				
				turnItr = roster.listIterator();

				// Turn Loop
				while (turnItr.hasNext() && State.getState().opposingCombatantsRemain()) {
					
					int numCombatantsTopOfTurn = State.getState().getCombatantCount();
					
					currCombatant = turnItr.next();
					
					brawlOutputter.logEvent("Turn: " + currCombatant.toString());

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
			
			brawlOutputter.logWinner(
					State.getState().getRoster().get(0).getTeam(),
					roster.toString()
				);
			
		}
		
		else {
			log.severe("Initialiser failed, game did not start");
			brawlOutputter.logEvent("Exiting Early: report error");
		}
		
		brawlOutputter.logEvent("----------------------");
		
		brawlOutputter.logEvent("Brawl Brawled");
		
		System.out.print(brawlOutputter.getBrawlOutputAsString());
	}
}
