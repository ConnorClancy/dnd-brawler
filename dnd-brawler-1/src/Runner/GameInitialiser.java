package Runner;

import static Utilities.ActionDirectory.AOE_RECHARGE_TYPE;
import static Utilities.ActionDirectory.AOE_TYPE;
import static Utilities.ActionDirectory.ATTACK_TYPE;
import static Utilities.ActionDirectory.MULTI_ATTACK_TYPE;
import static Utilities.ActionDirectory.REGENERATION_TYPE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Actions.AoeAttackAction;
import Actions.AoeRechargeAction;
import Actions.AttackAction;
import Actions.DamageDie;
import Actions.MultiAction;
import Actions.RegenerationAction;
import Combatants.Combatant;
import Combatants.CombatantSorter;
import Combatants.Statistics;
import Exceptions.ActionNotExistException;
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

    				String[] restiancesArr = {};
    				if (combatantJson.has("restistances")) {
    					JSONArray resistancesInput = combatantJson.getJSONArray("restistances");
    					restiancesArr = resistancesInput.toList().toArray(restiancesArr);
    				}
    				
    				String[] vulnerabilitiesArr = {};
    				if (combatantJson.has("vulnerabilities")) {
    					JSONArray vulnInput = combatantJson.getJSONArray("vulnerabilities");
    					vulnerabilitiesArr = vulnInput.toList().toArray(vulnerabilitiesArr);
    				}
    				
    				
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
	    								),
	    						restiancesArr,
	    						vulnerabilitiesArr
	    						);
	    				
	    				A.setTeam(combatantJson.getString("team"));
	    				
	    				//get all abilities and parse into respective sets
	    				JSONObject abilities = combatantJson.getJSONObject("abilities");
	    					    	    				
	    				Iterator<String> abilityIterator = abilities.keys();
	    				
	    				while(abilityIterator.hasNext()) {
	    					
	    					String actionSetName = abilityIterator.next();
	    					
	    					JSONArray actionSet = abilities.getJSONArray(actionSetName);
	    					
	    					System.out.println("current action set: " + actionSetName);
	    					
	    					
	    					for (int actionIndex = 0; actionIndex < actionSet.length(); actionIndex++) {
	    						
	    						JSONObject currAction = actionSet.getJSONObject(actionIndex);
	    						
	    						System.out.println("current action: " + currAction.getString("name"));
	    						
	    						switch (currAction.getString("type")) {
	    						case ATTACK_TYPE:
		    						
		    						JSONArray diceData = currAction.getJSONArray("dice");
		    						
		    						ArrayList<DamageDie> diceArrL = new ArrayList<DamageDie>();
		    						
		    						for (int diceCount = 0; diceCount < diceData.length(); diceCount++) {
		    							JSONObject dieData = diceData.getJSONObject(diceCount);
		    							diceArrL.add(new DamageDie(
		    									dieData.getInt("sides"), 
		    									dieData.getInt("count"), 
		    									dieData.getInt("damageBonus"), 
		    									dieData.getString("damageType")
		    									)
		    								);
		    						}
		    						
		    						DamageDie diceArr[] = new DamageDie[diceArrL.size()];
		    						
		    						A.addAbility(
		    								new AttackAction(
		    										currAction.getString("name"), 	    										
		    										currAction.getInt("targetCount"),
		    										currAction.getInt("repeats"),
		    										currAction.getInt("toHitBonus"),
		    										diceArrL.toArray(diceArr)
		    										),
		    								actionSetName
		    								);
		    						break;
		    					case MULTI_ATTACK_TYPE :
		    						/*
		    						 * Record names and repeat counts of each attack in multiAttack. 
		    						 * After action parse loop provide MultiAction object with instantiated Actions
		    						 * 
		    						 */
		    						MultiAction multiAttack = new MultiAction(currAction.getString("name"));
		    						
		    						JSONObject jsonAttackSequence = currAction.getJSONObject("sequence");
		    							    						
		    						for (String attackName : jsonAttackSequence.keySet()) {
										multiAttack.loadNameMap(attackName, jsonAttackSequence.getInt(attackName));
									}

		    						A.addAbility(multiAttack, actionSetName);
		    						A.setMultiAttackAvailable(true);
		    						
		    						break;
		    					case AOE_TYPE:
		    						A.addAbility(
		    							new AoeAttackAction(
	    									currAction.getString("name"), 
	    									currAction.getInt("range"), 
	    									currAction.getInt("dc"), 
	    									currAction.getString("saveType"), 
	    									currAction.getBoolean("halfOnSuccess"), 
	    									currAction.getInt("diceSides"),
	    									currAction.getInt("diceCount"),
	    									currAction.getString("damageType")
										),
		    							actionSetName
									);
		    						A.setAoeAttackAvailable(true);
		    						break;	    	
		    					case REGENERATION_TYPE:
		    						A.addAbility(
		    								new RegenerationAction(
		    										currAction.getString("name"), 
		    										currAction.getInt("flatAmount")
		    									),
		    								actionSetName
		    								);
		    						break;
		    					case AOE_RECHARGE_TYPE:
		    						A.addAbility(
		    								new AoeRechargeAction(
		    										currAction.getString("name"), 
		    										currAction.getInt("rechargeDieSides"),
		    										currAction.getInt("successBoundLower"),
		    										currAction.getInt("successBoundUpper")
		    									),
		    								actionSetName
		    								);
		    						break;
		    					default:
		    						throw new CreationException("Action not recognised");	
		    					}	
	    					}	
	    					
	    					if (A.isMultiAttackAvailable()) {
		    					//get multiAction mapping
		    					MultiAction multiAttack = (MultiAction)A.getAction("multiattack");
		    					
		    					Map<String, Integer> attackSequence = multiAttack.getMultiActionNameMapping();
		    					
		    					//iterates over mapping and adds actions as appropriate
		    					for (String attackName : attackSequence.keySet()) {
		    						multiAttack.loadActionMap(
		    								A.getAction(attackName), 
		    								attackSequence.get(attackName)
		    								);
		    					}
		    				}
	    				}
	    				

						System.out.println(A.toString());
						A.updateName(combatantJson.getString("name") + "-" + i);
						field.add(A);
					}
		            
				} catch (IOException | JSONException | CreationException | ActionNotExistException e) {
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
