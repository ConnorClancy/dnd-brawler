package Runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Actions.AttackAction;
import Combatants.Combatant;
import Combatants.CombatantSorter;
import Combatants.Statistics;
import Exceptions.CreationException;
import Utilities.DiceBox;


public class GameInitialiser {

	/*
	 * Class creates the following
	 * singleton State object
	 * Combatants (for now manually, later maybe from JSON)
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
	    				
	    				JSONArray actions = combatantJson.getJSONArray("actions");
	
	    				for (int j = 0; j < actions.length(); j++) {
	    					JSONObject jo = actions.getJSONObject(j);
	
	    					switch (jo.getString("type")) {
	    					case "Melee Weapon Attack":
	    						A.addAction(
	    								new AttackAction(
	    										"attack", 
	    										jo.getInt("diceSides"),
	    										jo.getInt("diceCount"),
	    										jo.getInt("targetCount"),
	    										jo.getInt("repeats"),
	    										jo.getInt("toHitBonus"),
	    										jo.getInt("damageBonus")
	    										)
	    								);
	    						break;
	    					case "Ranged Weapon Attack":
	    						A.addAction(
	    								new AttackAction(
	    										"attack", 
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
