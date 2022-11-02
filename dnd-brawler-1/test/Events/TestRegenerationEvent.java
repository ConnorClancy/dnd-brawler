package Events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Actions.RegenerationAction;
import Combatants.Combatant;
import Combatants.Statistics;

class TestRegenerationEvent {
	
	private static final RegenerationAction RegenerationAction = 
			new RegenerationAction("Regen", 0, 0, 0, 0, 0, 10);
	
	protected Combatant combatant;
	
	protected RegenerationEvent testEvent;

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
		
		Stack<Combatant> targetStack = new Stack<Combatant>();
		
		targetStack.push(combatant);
		
		testEvent = new RegenerationEvent(RegenerationAction, targetStack);
	}

	@Test
	void testRegenHealsTarget() {
		combatant.reduceHealthPoints(20);
		
		testEvent.doActionToTargets();
		
		assertEquals(combatant.getHealthPoints(), 90);
		
	}
	
	@Test
	void testRegenNotOverheal() {
		combatant.reduceHealthPoints(5);
		
		testEvent.doActionToTargets();
		
		assertEquals(combatant.getHealthPoints(), 100);
		
	}

}
