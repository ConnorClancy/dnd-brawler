package Runner;

import Combatants.Combatant;
import Utilities.BrawlOutputter;
import Utilities.CombatRoster;

public class State {
	
	private static State state = null;
	
	protected CombatRoster roster = new CombatRoster();
	
	private State() {
		//idk what does yet
	}
	
	public static State getState() {
		if (state == null) {
			state = new State();
		}
		
		return state;
	}
	
	public void addCombatant(Combatant combatant) {
		roster.add(combatant);
		roster.increaseTeamCount(combatant.getTeam());
		
		BrawlOutputter.getBrawlOutputter().logEvent(combatant.toString() + " added to combat");
	}
	
	public CombatRoster getRoster() {
		return roster;
	}

	public void removeCombatant(Combatant target) {
		roster.remove(target);
		roster.reduceTeamCount(target.getTeam());
		
		BrawlOutputter.getBrawlOutputter().logEvent(target.toString() + " removed from combat");
	}
	
	public boolean opposingCombatantsRemain() {
		return roster.getBlueTeamCount() > 0 && roster.getRedTeamCount() > 0;
	}

	public int getCombatantCount() {
		return roster.getBlueTeamCount() + roster.getRedTeamCount();
	}

	public void resetRoster() {
		roster.clear();
		roster = new CombatRoster();
	}
	
}
