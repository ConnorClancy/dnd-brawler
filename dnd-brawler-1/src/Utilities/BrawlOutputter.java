package Utilities;

import org.json.JSONObject;

public class BrawlOutputter {

	//Singleton outputter. We want one of these tracking all the events taking place during the brawl.
	private static BrawlOutputter brawlOutputter = null;
	
	JSONObject brawlLogParent;
	JSONObject brawlLogWinner;
	JSONObject brawlLogPlayByPlay;
	long brawlLogRunTime;
	String playByPlay;
	JSONObject round = null;
	
		
	private BrawlOutputter() {
		brawlLogParent = new JSONObject();
		brawlLogWinner = new JSONObject();
		brawlLogPlayByPlay = new JSONObject();
		brawlLogRunTime = 0;
		playByPlay = "";
	}
	
	public static BrawlOutputter getBrawlOutputter() {
		if (brawlOutputter == null) {
			brawlOutputter = new BrawlOutputter();
		}
		
		return brawlOutputter;
	}
	
	public JSONObject getBrawlOutput() {
		brawlLogParent.put("winner", brawlLogWinner);
		
		brawlLogPlayByPlay.put("log", playByPlay);
		
		brawlLogParent.put("play-by-play", brawlLogPlayByPlay);
		
		brawlLogParent.put("runtime", brawlLogRunTime);
		
		return brawlLogParent;
		
	}
	
	public String getBrawlOutputAsString() {
		
		String outputBuilder = "";
		
		outputBuilder += 
				"winner: " + brawlLogWinner.getString("teamName") +
				"winning team: " + brawlLogWinner.getString("teamStatus") +
				"play by play: " + playByPlay;
		
		return outputBuilder;
	}
	
	
	public void logWinner(String teamName, String teamStatus) {
		brawlLogWinner.put("teamName", teamName + "\n");
		
		String[] teamStatuses = teamStatus.split(", ");
		String formattedLog = "";
		for (String status : teamStatuses) {
			
			if (status.endsWith("]")) {
				status = status.substring(0, status.length()-1);
			}
			if (status.startsWith("[")) {
				status = status.substring(1, status.length());
			}
			formattedLog += status + "\n";
			
		}
		
		brawlLogWinner.put("teamStatus", formattedLog);
	}
	
	
	public void logEvent(String event) {
		playByPlay += event + "\n";
	}
	
	public void logNewRound(int roundCount) {
		
		String roundFormated = 
						"--------------\n" +
						"Round: " + roundCount + "\n"  + 
						"--------------\n";
						
		
		playByPlay += roundFormated;
		
	}

	public void logRunTime(long runTime) {
		this.brawlLogRunTime = runTime;
		
	}

	public void clearLog() {
		brawlLogParent = new JSONObject();
		brawlLogWinner = new JSONObject();
		brawlLogPlayByPlay = new JSONObject();
		playByPlay = "";
		brawlLogRunTime = 0;
		
	}
	
	
}
