package Utilities;

import java.util.LinkedList;
import Combatants.Combatant;

public class CombatRoster extends LinkedList<Combatant> {
	
	protected int blueTeamCount;
	protected int redTeamCount;
	
	public CombatRoster() {
		blueTeamCount = 0;
		redTeamCount = 0;
	}
	
	public LinkedList<Combatant> getOpponents(Combatant asker) {
		
		LinkedList<Combatant> opponentList = new LinkedList<Combatant>();
		
		Combatant current = null;
		
		for (int i = 0; i < this.size(); i++) {
			current = this.get(i);
			
			if(!current.getTeam().equals(asker.getTeam())) {
				opponentList.add(current);
			}
			
		}
		
		return opponentList;
	}
	
	public LinkedList<Combatant> getAllies(Combatant asker) {

		LinkedList<Combatant> allyList = new LinkedList<Combatant>();
		
		Combatant current = null;
		
		for (int i = 0; i < this.size(); i++) {
			current = this.get(i);
			
			if(current.getTeam().equals(asker.getTeam())) {
				allyList.add(current);
			}
			
		}
		
		return allyList;
	}

	public int getBlueTeamCount() {
		return blueTeamCount;
	}

	public int getRedTeamCount() {
		return redTeamCount;
	}
	
	public int reduceTeamCount(String team) {
		
		if ((team.equals("blue"))) {
			blueTeamCount--;
			return blueTeamCount;
		}
		
		if (team.equals("red")) {
			redTeamCount--;
			return redTeamCount;
		}
		
		return -1;
	}

	public int increaseTeamCount(String team) {
		if ((team.equals("blue"))) {
			blueTeamCount++;
			return blueTeamCount;
		}
		
		if (team.equals("red")) {
			redTeamCount++;
			return redTeamCount;
		}
		
		return -1;
	}
	

}
