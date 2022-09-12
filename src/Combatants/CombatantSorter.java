package Combatants;

import java.util.Comparator;

public class CombatantSorter implements Comparator<Combatant>{

	@Override
	public int compare(Combatant o1, Combatant o2) {
		return o2.initiative - o1.initiative;
	}

}
