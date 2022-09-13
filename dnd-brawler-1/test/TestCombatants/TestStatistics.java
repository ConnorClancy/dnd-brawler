package TestCombatants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Combatants.Statistics;


class TestStatistics {
	
	protected Statistics validStats;
	protected Statistics invalidStats;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		validStats = new Statistics(
				0,
				2,
				2,
				-2,
				-1,
				-3
			);
		
		invalidStats = new Statistics(
				100,
				-90,
				2,
				-2,
				-1,
				-11
			);
	}

	@Test
	void testIsValid() {
		assertTrue(validStats.isValid());
		
		assertFalse(invalidStats.isValid());
		
		
	}

}
