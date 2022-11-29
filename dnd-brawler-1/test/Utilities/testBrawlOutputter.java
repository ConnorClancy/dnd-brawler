package Utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testBrawlOutputter {
	
	BrawlOutputter myBrawlOutputter; 

	@BeforeEach
	void setUp() throws Exception {
		myBrawlOutputter = BrawlOutputter.getBrawlOutputter();
	}

	@Test
	void testFormatCorrect() {
		JSONObject output = myBrawlOutputter.getBrawlOutput();
		
		String correctEntries = "winner|play-by-play";
		
		Iterator<String> outputItr = output.keys();
		
		while(outputItr.hasNext()) {
			
			String keyName = outputItr.next();
		
			assertTrue(correctEntries.contains(keyName));
		}
	
	}
	
	@Test
	void testAddLogEventRightOrder() {
		String expected = "log 1\nlog 2\nlog 3\n";
		
		
		myBrawlOutputter.logEvent("log 1");
		myBrawlOutputter.logEvent("log 2");
		myBrawlOutputter.logEvent("log 3");
		
		
		JSONObject output = myBrawlOutputter.getBrawlOutput();
		
		JSONObject playByPlay = output.getJSONObject("play-by-play");
		
		String actual = playByPlay.getString("log");
		
		assertEquals(actual, expected);
		
	}
			
	@Test
	void testAddWinner() {
		String expectedStatus = "Behir-2 (hp:168)\nBalor-1 (hp:262)\nBehir-1 (hp:168)\n";		
		
		myBrawlOutputter.logWinner("blue", "[Behir-2 (hp:168), Balor-1 (hp:262), Behir-1 (hp:168)]");
		
		
		JSONObject output = myBrawlOutputter.getBrawlOutput();
		
		JSONObject winnerBlock = output.getJSONObject("winner");
		
		String actual = winnerBlock.getString("teamStatus");
				
		assertEquals(actual, expectedStatus);
		
	}

}
