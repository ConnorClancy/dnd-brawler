package Actions;

public class AoeAttackAction extends Action {

	protected int range;
	protected int dc;
	protected String saveType;
	protected boolean halfOnSuccess;
	protected int diceSides;
	protected int diceCount;
	
	public AoeAttackAction(
			String name, 
			int range, 
			int dc, 
			String saveType, 
			boolean halfOnSuccess, 
			int diceSides,
			int diceCount
		) {
		super(name);
		this.range = range;
		this.dc = dc;
		this.saveType = saveType;
		this.halfOnSuccess = halfOnSuccess;
		this.diceSides = diceSides;
		this.diceCount = diceCount;
	}

	public int getRange() {
		return range;
	}

	public int getDc() {
		return dc;
	}

	public String getSaveType() {
		return saveType;
	}

	public boolean isHalfOnSuccess() {
		return halfOnSuccess;
	}

	public int getDiceSides() {
		return diceSides;
	}

	public int getDiceCount() {
		return diceCount;
	}

}