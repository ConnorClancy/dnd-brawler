package Runner;

import Combatants.Combatant;
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
		
		System.out.println(combatant.toString() + " added to combat");
	}
	
	public CombatRoster getRoster() {
		return roster;
	}

	public void removeCombatant(Combatant target) {
		roster.remove(target);
		roster.reduceTeamCount(target.getTeam());
		
		System.out.println(target.toString() + " removed from combat");
	}
	
	public boolean opposingCombatantsRemain() {
		return roster.getBlueTeamCount() > 0 && roster.getRedTeamCount() > 0;
	}
	
}
