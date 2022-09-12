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

import Combatants.Combatant;
import Combatants.Statistics;
import Exceptions.CreationException;

/**
 * @author Connor Clancy
 *
 */
class TestCombatant {
	
	protected Combatant combatant;

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
			combatant = new Combatant("Skeleton", 13, 13, 30, validStats);
		});

		CreationException exception = assertThrows(CreationException.class, () -> {
			combatant = new Combatant("Skeleton", 13, 13, 30, invalidStats);
		});
		
		assertEquals("Stats given are invalid", exception.getMessage());
		
	}

}
