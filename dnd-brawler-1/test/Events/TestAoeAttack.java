package Events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Actions.AoeAttackAction;
import Combatants.Combatant;
import Combatants.Statistics;


class TestAoeAttack {

	private static final AoeAttackAction AOE_10_DAM_ACTION = 
			new AoeAttackAction("My Aoe", 100, 30, "strength", false, 1, 10);
	
	private static final AoeAttackAction AOE_FULL_DAM_ACTION = 
			new AoeAttackAction("My Aoe", 100, 30, "strength", false, 6, 10);
	
	private static final AoeAttackAction AOE_HALF_DAM_ACTION = 
			new AoeAttackAction("My Aoe", 100, 0, "strength", true, 6, 10);

	protected Combatant combatant1;
	protected Combatant combatant2;
	protected Combatant combatant3;
	
	protected AoeAttackEvent testEvent;
	
	private Stack<Combatant> targetStack = new Stack<Combatant>();
	
	@BeforeEach
	void setUp() throws Exception {
		
		combatant1 = new Combatant(
				"testMan1", 
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
		
		combatant2 = new Combatant(
				"testMan2", 
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
				
		
		combatant3 = new Combatant(
				"testMan3", 
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
				
				
		this.targetStack.push(combatant1);
		this.targetStack.push(combatant2);
		this.targetStack.push(combatant3);
		
	}

	@Test
	void testDamageCorrectlyAdds() {
		testEvent = new AoeAttackEvent(AOE_10_DAM_ACTION, this.targetStack);
		
		testEvent.doActionToTargets();
		
		assertEquals(90, combatant1.getHealthPoints());
		assertEquals(90, combatant2.getHealthPoints());
		assertEquals(90, combatant3.getHealthPoints());
	}
	
	@Test
	void testHigherDamage() {
		testEvent = new AoeAttackEvent(AOE_FULL_DAM_ACTION, this.targetStack);
		
		testEvent.doActionToTargets();
		
		int remainingHealth = 100 - testEvent.getDamageRoll();
		
		assertEquals(remainingHealth, combatant1.getHealthPoints());
		assertEquals(remainingHealth, combatant2.getHealthPoints());
		assertEquals(remainingHealth, combatant3.getHealthPoints());
	}
	
	@Test
	void testHalfDamage() {
		testEvent = new AoeAttackEvent(AOE_HALF_DAM_ACTION, this.targetStack);
		
		testEvent.doActionToTargets();
		
		int remainingHealth = 100 - Math.round(testEvent.getDamageRoll()/2);
		
		assertEquals(remainingHealth, combatant1.getHealthPoints());
		assertEquals(remainingHealth, combatant2.getHealthPoints());
		assertEquals(remainingHealth, combatant3.getHealthPoints());
	}

}
