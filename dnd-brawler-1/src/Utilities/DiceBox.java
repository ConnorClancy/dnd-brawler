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
	
	public static int rollActionDice(int diceSides) {
		switch(diceSides) {
		case 4:
			return DiceBox.rollDFour();
		case 6:
			return DiceBox.rollDSix();
		case 8:
			return DiceBox.rollDEight();
		case 10:
			return DiceBox.rollDTen();
		case 12:
			return DiceBox.rollDTwelve();
		default:
			return DiceBox.rollNSidedDice(diceSides);
			// >:(
			//better coding but less fun
		}
	}
	
}
