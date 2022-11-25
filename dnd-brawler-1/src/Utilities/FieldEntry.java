package Utilities;

public class FieldEntry {
	
	protected String name;
	protected String assignedTeam;
	protected int numberInCombat;
	
	
	public FieldEntry(String name, String assignedTeam, int numberInCombat) {
		super();
		this.name = name;
		this.assignedTeam = assignedTeam;
		this.numberInCombat = numberInCombat;
	}


	public String getName() {
		return name;
	}


	public String getAssignedTeam() {
		return assignedTeam;
	}


	public int getNumberInCombat() {
		return numberInCombat;
	}

	
}
