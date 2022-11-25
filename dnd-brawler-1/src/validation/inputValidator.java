package validation;

public class inputValidator {

	public static boolean validate(String[] inputList) {
		
		boolean valid = true;
		
		if (inputList.length != 3) {
			valid = false;
		}
			
		if (!inputList[0].matches("[a-zA-Z]+")) {
			valid = false;
		}
		
		if (!inputList[1].equals("blue") && !inputList[1].equals("red")) {
			valid = false;
		}
		
		int count = Integer.parseInt(inputList[2]);
		
		if (count < 1 || count > 1000) {
			valid = false;
		}

		return valid;
		
	}
	
}
