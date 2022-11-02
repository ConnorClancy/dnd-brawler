package Runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Actions.AttackAction;
import Actions.RegenerationAction;
import Combatants.Combatant;
import Combatants.CombatantSorter;
import Combatants.Statistics;
import Exceptions.CreationException;
import Utilities.DiceBox;


public class GameInitialiser {

	/*
	 * Class creates the following
	 * singleton State object
	 * Combatants 
	 * Created Actions and assigns to combatants
	 * Future idea: env variables - things like light/no light, under water, per turn damage etc
	 * 
	 */
	
	//constructor
	
	//create combatants on each side
	
	//create STATE and "add" combatants
	//roll initiative and re-order combatants
	
	//Combatants class = stats and numbers
	//CombatantRepitour class = actions logic etc?
	
	
	public GameInitialiser() {
		
	}
	
	/*
	 * Initializes combatants
	 * rolls initiative
	 * adds to state
	 */
	public boolean setField() {
		
		Path dirName = Path.of("/Users/connorclancy/dev/dnd-brawler/dnd-brawler-1/sampleJsonCombatants");
		
		ArrayList<Combatant> field = new ArrayList<Combatant>();
		
		try {
			
			Files.walk(dirName).filter(Files::isRegularFile).forEach(path -> {
	            System.out.println(path);
	           
	            try {
	    			String str = Files.readString(path);

	    			JSONObject combatantJson = new JSONObject(str);

    				JSONObject stats = combatantJson.getJSONObject("statistics");
    				
    				
	    			int m = combatantJson.getInt("amountOfCreatureInCombat");
	    			
					for (int i = 1; i <= m; i++) {
    				
	    				Combatant A = new Combatant(
	    						combatantJson.getString("name"),
	    						combatantJson.getInt("healthPoints"),
	    						combatantJson.getInt("armourClass"),
	    						combatantJson.getInt("speed"),
	    						new Statistics(
	    								stats.getInt("strength"),
	    								stats.getInt("dexterity"),
	    								stats.getInt("constitusion"),
	    								stats.getInt("intelligence"),
	    								stats.getInt("wisdom"),
	    								stats.getInt("charisma")
	    								)
	    						);
	    				
	    				A.setTeam(combatantJson.getString("team"));
	    				
	    				//get all abilities and parse into respective sets
	    				JSONObject abilities = combatantJson.getJSONObject("abilities");
	    					    	
	    				//get turn actions first
	    				JSONArray actions = abilities.getJSONArray("actions");
	    				
	
	    				for (int j = 0; j < actions.length(); j++) {
	    					JSONObject jo = actions.getJSONObject(j);
	
	    					switch (jo.getString("type")) {
	    					case "attack":
	    						A.addAction(
	    								new AttackAction(
	    										jo.getString("name"), 
	    										jo.getInt("diceSides"),
	    										jo.getInt("diceCount"),
	    										jo.getInt("targetCount"),
	    										jo.getInt("repeats"),
	    										jo.getInt("toHitBonus"),
	    										jo.getInt("damageBonus")
	    										)
	    								);
	    						break;
	    					default:
	    						throw new CreationException("Action not recognised");
	    					}
	    					
	    				}
	    				
	    				if(abilities.has("passives")) {
	    					
	    					System.out.println("Passive abilities found");

	    					JSONArray passiveAbilities = abilities.getJSONArray("passives");
		    				
		    				
		    				for (int j = 0; j < passiveAbilities.length(); j++) {
		    					JSONObject jo = passiveAbilities.getJSONObject(j);
		    					
		    					switch (jo.getString("type")) {
		    					case "Regeneration":
		    						A.addPassiveAbility(
		    								new RegenerationAction(jo.getString("name"), 0, 0, 0, 0, 0, jo.getInt("flatAmount")));
		    						break;
		    					default:
		    						throw new CreationException("Passive Ability not recognised");
		    					}
		    					
		    				}
	    				} else {
	    					System.out.println("No passive abilities found");
	    				}
	    				

						System.out.println(A.toString());
						A.updateName(combatantJson.getString("name") + "-" + i);
						field.add(A);
					}
		            
				} catch (IOException | JSONException | CreationException e) {
					System.out.println(e.getMessage());
				}
	        });
			
			State state = State.getState();

			int initiative = 0;

			for (Combatant c : field) {
				initiative = rollInitiative(c);
				System.out.println(c.toString() + " - intiative:" + initiative);
				c.setInitiative(initiative);
			}

			field.sort(new CombatantSorter());

			for (Combatant c : field) {
				state.addCombatant(c);
			}

		} catch (IOException exeption) {
			System.out.println(exeption.getMessage());
		}

		if (State.getState().getRoster().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	private int rollInitiative(Combatant c) {
		return DiceBox.rollDTwenty() + c.statistics().getDexterity();
	}
	
}
