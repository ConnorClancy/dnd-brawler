package Utilities;

import java.util.Random;

public class DiceBox {
	
	protected static Random dice = new Random();;
	
	public static int rollDFour() {
		return dice.nextInt(1, 5);
	}
	
	public static int rollDSix() {
		return dice.nextInt(1, 7);
	}
	
	public static int rollDEight() {
		return dice.nextInt(1, 9);
	}
	
	public static int rollDTen() {
		return dice.nextInt(1, 11);
	}
	
	public static int rollDTwelve() {
		return dice.nextInt(1, 13);
	}
	
	public static int rollDTwenty() {
		return dice.nextInt(1, 21);
	}
	
	public static int rollNSidedDice(int sides) {
		return dice.nextInt(1, sides+1);
	}
	
}
