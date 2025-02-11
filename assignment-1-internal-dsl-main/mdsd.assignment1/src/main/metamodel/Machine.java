package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Machine {

	private ArrayList<State> states = new ArrayList<State>();
	private int initial_state_id = 0;
	
	
	public List<State> getStates() {
		// TODO Auto-generated method stub
		return states;
	}

	public Machine(List<State> states, int initial_state_id) {
		this.states = (ArrayList<State>) states;
		this.initial_state_id = initial_state_id;
	}
	
	public State getInitialState() {
		// TODO Auto-generated method stub
		return null;
	}

	public State getState(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public int numberOfIntegers() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hasInteger(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}

