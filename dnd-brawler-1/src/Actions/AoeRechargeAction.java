package Actions;

public class AoeRechargeAction extends Action {
	
	protected int rechargeDieSides;
	protected int successBoundLower;
	protected int successBoundUpper;

	public AoeRechargeAction(String name, int rechargeDieSides, int successBoundLower, int successBoundUpper) {
		super(name);
		// TODO Auto-generated constructor stub
		this.rechargeDieSides = rechargeDieSides;
		this.successBoundLower = successBoundLower;
		this.successBoundUpper = successBoundUpper;
			
	}

	public int getRechargeDieSides() {
		return rechargeDieSides;
	}

	public int getSuccessBoundLower() {
		return successBoundLower;
	}

	public int getSuccessBoundUpper() {
		return successBoundUpper;
	}

}
