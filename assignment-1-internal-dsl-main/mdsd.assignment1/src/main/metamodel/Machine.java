package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Machine {

	private ArrayList<State> states = new ArrayList<State>();
	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	private State cur_state;
	private State initial_state;
	
	
	public List<State> getStates() {
		// TODO Auto-generated method stub
		return states;
	}

	public Machine(ArrayList<State> states, int initial_state_id, HashMap<String, Integer> variables) {
		this.variables = variables;
		if (states.size() != 0) {
			this.states = (ArrayList<State>) states;
			this.cur_state = this.states.get(initial_state_id);
			this.initial_state = this.cur_state;
		}
		
	}
	
	public HashMap<String, Integer> getVariables() {
		return variables;
	}
	
	public State getInitialState() {
		// TODO Auto-generated method stub
		return initial_state;
	}

	public State getState(String string) {
		// TODO Auto-generated method stub
		for (State s : states) {
			if (s.getName() == string) {
				return s;
			}
		}
		return null;
	}

	public int numberOfIntegers() {
		// TODO Auto-generated method stub
		return variables.size();
	}

	public boolean hasInteger(String string) {
		// TODO Auto-generated method stub
		return variables.containsKey(string);
	}
}

