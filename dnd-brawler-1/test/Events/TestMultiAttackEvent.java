package Events;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Actions.AttackAction;
import Actions.MultiAction;
import Combatants.Combatant;
import Combatants.Statistics;
import Exceptions.CreationException;

class TestMultiAttackEvent {

	private static final MultiAction MULTI_ACTION = 
			new MultiAction("multiattack");
	
	private static final AttackAction ACTION_1 = 
			new AttackAction("attack-1", 1, 5, 1, 1, 10, 20);
	
	private static final AttackAction ACTION_2 = 
			new AttackAction("attack-2", 1, 1, 1, 1, 10, 0);

	protected Combatant combatant;
	
	protected MultiAttackEvent testEvent;

	private Stack<Combatant> targetStack = new Stack<Combatant>();

	@BeforeEach
	void setUp() throws Exception {
		
		combatant = new Combatant(
				"testMan", 
				100, 
				10, 
				30, 
				new Statistics(
						0, 
						0, 
						0, 
						0, 
						0, 
						0
						)
				);
				
		this.targetStack.push(combatant);
		
		MULTI_ACTION.loadActionMap(ACTION_1, 2);
		
		MULTI_ACTION.loadActionMap(ACTION_2, 20);
		
		testEvent = new MultiAttackEvent(MULTI_ACTION, this.targetStack);
		
	}

	@Test
	void testAllAttacksFunction() {
		
		testEvent.doActionToTargets();
		
		assertTrue(combatant.getHealthPoints() == 30);
		
	}
	
	@Test
	void testZeroRepeatCount() {
		
		 MultiAction testZeroRepeatsAction = new MultiAction("multiattack");
		 
		 testZeroRepeatsAction.loadActionMap(ACTION_1, 0);
			
		 testZeroRepeatsAction.loadActionMap(ACTION_2, 0);
		 
		 new MultiAttackEvent(testZeroRepeatsAction, this.targetStack).doActionToTargets();
		 
		 assertTrue(combatant.getHealthPoints() == 100);
	}
	
	@Test
	void testTargetKilledByFirstAttackSet() {
		try {
			Combatant combatant_1 = new Combatant(
					"testMan-1", 
					20, 
					10, 
					30, 
					new Statistics(
							0, 
							0, 
							0, 
							0, 
							0, 
							0
							)
					);
			
			Combatant combatant_2 = new Combatant(
					"testMan-2", 
					100, 
					10, 
					30, 
					new Statistics(
							0, 
							0, 
							0, 
							0, 
							0, 
							0
							)
					);
			
			Stack<Combatant> testTargetStack = new Stack<Combatant>();
			
			testTargetStack.push(combatant_2);
			testTargetStack.push(combatant_1);
			
			new MultiAttackEvent(MULTI_ACTION, testTargetStack).doActionToTargets();
			
			assertTrue(testTargetStack.size() == 1);
			
			
		} catch (CreationException e) {
			System.out.println(e.getMessage());
		}	
		
	}

}
