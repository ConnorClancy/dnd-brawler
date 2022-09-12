package Utilities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Combatants.Combatant;
import Combatants.Statistics;
import Exceptions.CreationException;

class TestCombatRoster {
	
	protected static CombatRoster roster;
	protected static Combatant skeleton;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		roster = new CombatRoster();
		
		Statistics stats = new Statistics(
				0,
				2,
				2,
				-2,
				-1,
				-3
			);
		
		try {
			skeleton = new Combatant("Skeleton", 13, 13, 30, stats);
		} catch(CreationException e){
			e.getMessage();
		}
		
		roster.add(skeleton);
	}

	@Test
	void testGetSingleEntryInList() {
		
		assertTrue(skeleton == roster.getFirst());
		
		System.out.println(roster.getFirst().toString());
	
	}
	
	@Test
	void testLinkedList() {
		Statistics stats = new Statistics(
				0,
				2,
				2,
				-2,
				-1,
				-3
			);
		
		try {
			Combatant skeletonTwo = new Combatant("SkeletonTwo", 13, 13, 30, stats);
			
			roster.add(skeletonTwo);
		
			Combatant current = null;
			
			Iterator<Combatant> itr=roster.iterator();  
			while(itr.hasNext()){  
				current = itr.next();  
				System.out.println(current.toString());
			}  
			
			assertTrue(skeletonTwo == current);
			
			
		} catch(CreationException e){
			e.getMessage();
		}
	}

}
