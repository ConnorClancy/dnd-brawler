/**
 * 
 */
package TestCombatants;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Actions.Action;
import Actions.AttackAction;
import Actions.DamageDie;
import Combatants.Combatant;
import Combatants.Statistics;
import Exceptions.CreationException;

/**
 * @author Connor Clancy
 *
 */
class TestCombatant {
	
	protected Combatant combatant;
	
	private static final String[] RESTISTANCES = {};
	private static final String[] VULNERABILITIES = {};

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Statistics validStats = new Statistics(
				0,
				2,
				2,
				-2,
				-1,
				-3
			);
		
		try {
			combatant = new Combatant("Skeleton", 13, 13, 30, validStats, RESTISTANCES, VULNERABILITIES);
		} catch (CreationException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testConstructor() {

		Statistics validStats = new Statistics(
				0,
				2,
				2,
				-2,
				-1,
				-3
			);
		
		Statistics invalidStats = new Statistics(
				11,
				-7,
				2,
				-2,
				-1,
				-3
			);
		
		assertDoesNotThrow(() -> {
			combatant = new Combatant("Skeleton", 13, 13, 30, validStats, RESTISTANCES, VULNERABILITIES);
		});

		CreationException exception = assertThrows(CreationException.class, () -> {
			combatant = new Combatant("Skeleton", 13, 13, 30, invalidStats, RESTISTANCES, VULNERABILITIES);
		});
		
		assertEquals("Stats given are invalid", exception.getMessage());
		
	}
	
	@Test
	void testAddAction() {
		
		DamageDie[] dice1 = 
			{new DamageDie(1, 1, 0, "type")};
		
		AttackAction A = new AttackAction("test", 1, 1, 1, dice1);
		
		combatant.addAction(A);
		
		assertEquals(
				A.getDamageDice()[0].getDamageBonus(), 
				((AttackAction) combatant.getActions().get(0)).getDamageDice()[0].getDamageBonus()
				);
		assertEquals(
				A.getDamageDice()[0].getDiceCount(), 
				((AttackAction) combatant.getActions().get(0)).getDamageDice()[0].getDiceCount()
				);
		assertEquals(
				A.getRepeats(), 
				((AttackAction) combatant.getActions().get(0)).getRepeats()
				);
	
	}
	
	@Test
	void testOverHeal() {
		
		int startingHealth = combatant.getHealthPoints();
		
		combatant.reduceHealthPoints(5);
		
		combatant.increaseHealthPoints(3, false);
		
		assertEquals(startingHealth - 2, combatant.getHealthPoints());
		
		combatant.increaseHealthPoints(2000, false);
		
		assertEquals(startingHealth, combatant.getHealthPoints());
		
		combatant.increaseHealthPoints(2000, true);
		
		assertEquals(startingHealth + 2000, combatant.getHealthPoints());
		
	}

}
