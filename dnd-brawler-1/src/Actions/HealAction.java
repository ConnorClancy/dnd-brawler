/**
 * 
 */
package Actions;

/**
 * @author connorclancy
 *
 */
public class HealAction extends Action {
	
	protected boolean overHeal;
	protected int flatAmount;

	public HealAction(String name, int diceSides, int diceCount, int targetCount, int repeats,
			int bonus, boolean overHeal, int flatAmount) {
		super(name);

		this.overHeal = overHeal;
		this.flatAmount = flatAmount;

	}

	public boolean isOverHeal() {
		return overHeal;
	}

	public int getFlatAmount() {
		return flatAmount;
	}

}
