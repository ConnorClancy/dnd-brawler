package Utilities;

import java.util.ArrayList;

import Exceptions.ValidationException;
import validation.inputValidator;

public class LayoutCreator {
	
	protected String[] combatInputs;
	protected FieldEntry[] fieldLayout = new FieldEntry[]{};

	public LayoutCreator(String[] combatInputs) {
		this.combatInputs = combatInputs;
	}
	
	public FieldEntry[] generateFieldLayout() throws ValidationException {
		
		if (combatInputs.length%3 != 0) {
			throw new ValidationException("Front end input did not pass valid");	
		}
		
		ArrayList<FieldEntry> layoutList = new ArrayList<FieldEntry>();
		
		for (int index = 0; index < combatInputs.length; index+=3) {
			//call validation class
			boolean isValid = inputValidator.validate(
					new String[] {
						combatInputs[index], 
						combatInputs[index + 1], 
						combatInputs[index + 2]
					}
				);
			
			//check if valid, if no pass back empty fieldLayout
			if (!isValid) {
				throw new ValidationException("Front end input did not pass valid");				
			} else {
				//create fieldEntry objects from data inputed
				layoutList.add(
						new FieldEntry(
							combatInputs[index], 
							combatInputs[index + 1], 
							Integer.parseInt(combatInputs[index + 2])
						)
					);
			}
		}
		
		//pass back array of all triplets in field
		return fieldLayout = layoutList.toArray(fieldLayout);
	}

}
