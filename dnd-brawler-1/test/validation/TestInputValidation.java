package validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestInputValidation {

	@Test
	void testValidation() {
		String[] goodInput1 = new String[] {"Goblin", "blue", "5"};
		String[] goodInput2 = new String[] {"Troll", "red", "10"};
		
		String[] badInputName1 = new String[] {"Gobl1n", "blue", "5"};
		String[] badInputName2 = new String[] {"</script = blahblahblah>", "blue", "5"};
		
		String[] badInputTeam = new String[] {"Goblin", "green", "5"};
		
		String[] badInputCountHigh = new String[] {"Goblin", "blue", "2000"};
		String[] badInputCountLow = new String[] {"Goblin", "blue", "-3"};
		
		assertTrue(validation.inputValidator.validate(goodInput1));
		
		assertTrue(validation.inputValidator.validate(goodInput2));
		
		assertFalse(validation.inputValidator.validate(badInputName1));
		
		assertFalse(validation.inputValidator.validate(badInputName2));
		
		assertFalse(validation.inputValidator.validate(badInputTeam));
		
		assertFalse(validation.inputValidator.validate(badInputCountHigh));
		
		assertFalse(validation.inputValidator.validate(badInputCountLow));
	
	}

}
