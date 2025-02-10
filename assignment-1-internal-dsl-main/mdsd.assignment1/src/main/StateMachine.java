package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class StateMachine {

	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	private ArrayList<State> states = new ArrayList<State>();
	private int starting_state_id = -1;
	
	
	private State get_scoped_state() {
		int latest_state_id = states.size() - 1;
		return states.get(latest_state_id);
	}
	
	private Transition get_scoped_transition() {
		State latest_state = get_scoped_state();
		List<Transition> transitions = latest_state.getTransitions();
		int latest_transition_id = transitions.size() - 1;
		return transitions.get(latest_transition_id);
	}
	
	public Machine build() {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine state(String string) {
		// TODO Auto-generated method stub
		State new_state = new State(string);
		states.add(new_state);
		return this;
	}

	public StateMachine initial() {
		starting_state_id = states.size() - 1; // Get index of current element and set it as 
		return this;
	}

	public StateMachine when(String string) {
		State latest_state = get_scoped_state();
		Transition transition = new Transition();
		latest_state.addTransition(transition);
		int latest_state_id = states.size() - 1;
		states.set(latest_state_id, latest_state);
		return this;
	}

	public StateMachine to(String string) {
		Transition latest_transition = get_scoped_transition();
		latest_transition.setTarget(string);
		return this;
	}

	public StateMachine integer(String string) {
		variables.remove(string);
		variables.put(string, 0);
		return this;
	}

	public StateMachine set(String string, int i) {
		return null;
	}

	public StateMachine increment(String string) {
		return null;
	}

	public StateMachine decrement(String string) {
		return null;
	}

	public StateMachine ifEquals(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifLessThan(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
