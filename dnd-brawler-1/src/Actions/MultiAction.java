package Actions;

import java.util.HashMap;
import java.util.Map;

public class MultiAction extends Action{
	
	protected Map<String, Integer> map;

	public MultiAction(String name) {
		super(name);
		map = new HashMap<String, Integer>();
	}
	
	public void load(String attackName, int numberOfRepeats) {
		map.put(attackName, numberOfRepeats);
	}
	
	public Map<String, Integer> getMultiActionMapping() {
		return map;
	}

}
