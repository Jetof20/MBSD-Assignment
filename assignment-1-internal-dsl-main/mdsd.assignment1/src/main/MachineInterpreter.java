package main;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {
	private State current_state = null;
	private Machine machine = null;
	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	
    public void run(Machine m) {
        // TODO Auto-generated method stub
    	machine = m;
    	current_state = machine.getInitialState();
    	variables = m.getVariables();
    }

    public State getCurrentState() {
        // TODO Auto-generated method stub
        return current_state;
    }

    public void processEvent(String event_name) {
    	List<Transition> transitions = current_state.getTransitions();
    	
    	for (int t = 0; t < transitions.size();t++) { // Perform every event
    		Transition transition = transitions.get(t);
    		if (transition.getName() == event_name) { // if event matches
    			
    			System.out.println("\nTriggered: "+transition.getName()+" in state "+current_state.getName());
    			
    			if (transition.isConditional()) { // Check if there is a condition for executing the switch
    				String variable_name_to_compare = (String)transition.getConditionVariableName();
    				int variable_value_to_compare = (int)transition.getConditionComparedValue();
    				int current_value_of_compared_var = variables.get(variable_name_to_compare);
    				
    				if (transition.isConditional("EQUAL") && variable_value_to_compare == current_value_of_compared_var) {
    					System.out.println("Condition: PASSED "+variable_value_to_compare+" == "+current_value_of_compared_var);
    				}
    				else if (transition.isConditional("EQUAL")) {
    					System.out.println("Condition: FAILED "+variable_value_to_compare+" == "+current_value_of_compared_var);
    					continue;
    				}
    				else if (transition.isConditional("GREATER") && variable_value_to_compare < current_value_of_compared_var) {
    					System.out.println("Condition: PASSED "+variable_value_to_compare+" < "+current_value_of_compared_var);
    				}
    				else if (transition.isConditional("GREATER")) {
    					System.out.println("Condition: FAILED "+variable_value_to_compare+" < "+current_value_of_compared_var);
    					continue;
    				}
    				else if (transition.isConditional("LESS") && variable_value_to_compare > current_value_of_compared_var) {
    					System.out.println("Condition: PASSED "+variable_value_to_compare+" > "+current_value_of_compared_var);
    				}
    				else if (transition.isConditional("LESS") && variable_value_to_compare > current_value_of_compared_var) {
    					System.out.println("Condition: FAILED "+variable_value_to_compare+" > "+current_value_of_compared_var);
    					continue;
    				} else {
    					System.out.println("Condition: FAILED CRITICALLY");
    					continue;
    				}
  
    			} else {
    				System.out.println("Condition: None");
    			}
    			
    			if (transition.hasOperation()) { // Check if there is any mathematical operators to be performed
    				String variable_to_modify = (String)transition.getVariableToModify();
					int old_value = variables.get(variable_to_modify);
							
    				if (transition.hasOperation("SET")) {
    					variables.replace(variable_to_modify, transition.getOperationValue());
    					System.out.println("Set: "+variable_to_modify+" to "+transition.getOperationValue());
    				}
    				else if (transition.hasOperation("INCREMENT")) {
    					variables.replace(variable_to_modify, old_value + transition.getOperationValue());
    					System.out.println("Set: "+variable_to_modify+" to "+(old_value + transition.getOperationValue()));
    				}
    				else if (transition.hasOperation("DECREMENT")) {
    					variables.replace(variable_to_modify, old_value - transition.getOperationValue());
    					System.out.println("Set: "+variable_to_modify+" to "+(old_value - transition.getOperationValue()));
    				}  				
    			}
    			
    			current_state = transition.getTarget();
    			System.out.println("new state "+current_state.getName());
    			return;
    		}
    	}
    }

    public int getInteger(String string) {
    	return variables.get(string);
    }

}
