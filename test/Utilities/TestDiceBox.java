package Utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDiceBox {

	@Test
	void testDiceRoll20() {
		int resultBuckets[] = new int[40];
		int result = -1;
		
		for (int i = 0; i < 1000; i++) {
			result = DiceBox.rollDTwenty();
			resultBuckets[result]++;
		}
		
		for (int i = 1; i < (resultBuckets.length); i++) {
			System.out.println("[" + i + "]: " + resultBuckets[i]);
			if (i <= 20) {
				assertTrue(resultBuckets[i] > 0, "value below 20 not appearing");
			}
			else {
				assertTrue(resultBuckets[i] == 0, "value above 20 appearing");
			}
		}

	}

	@Test
	void testDiceRoll4() {
		int resultBuckets[] = new int[8];
		int result = -1;
		
		for (int i = 0; i < 1000; i++) {
			result = DiceBox.rollDFour();
			resultBuckets[result]++;
		}
		
		for (int i = 1; i < (resultBuckets.length); i++) {
			System.out.println("[" + i + "]: " + resultBuckets[i]);
			if (i <= 4) {
				assertTrue(resultBuckets[i] > 0, "value below 20 not appearing");
			}
			else {
				assertTrue(resultBuckets[i] == 0, "value above 20 appearing");
			}
			
		}

	}

}
