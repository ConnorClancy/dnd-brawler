package Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultiAction extends Action{
	
	protected Map<String, Integer> nameMap;
	protected Map<Action, Integer> actionMap;
	protected ArrayList<Action> actionList;

	public MultiAction(String name) {
		super(name);
		this.nameMap = new HashMap<String, Integer>();
		this.actionMap = new HashMap<Action, Integer>();
		
	}
	
	public void loadNameMap(String attackName, int numberOfRepeats) {
		nameMap.put(attackName, numberOfRepeats);
	}
	
	public void loadActionMap(Action attack, int numberOfRepeats) {
		actionMap.put(attack, numberOfRepeats);
	}
	
	public Map<String, Integer> getMultiActionNameMapping() {
		return nameMap;
	}
	
	public Map<Action, Integer> getMultiActionMapping() {
		return actionMap;
	}

}
