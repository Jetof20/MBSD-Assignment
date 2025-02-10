package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.metamodel.Machine;
import main.metamodel.State;

public class StateMachine {

	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	private ArrayList<State> states = new ArrayList<State>();
	
	
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
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine when(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine to(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine integer(String string) {
		variables.remove(string);
		variables.put(string, 0);
		return this;
	}

	public StateMachine set(String string, int i) {
		variables.remove(string);
		variables.put(string, i);
		return this;
	}

	public StateMachine increment(String string) {
		int new_var = variables.get(string);
		variables.remove(string);
		variables.put(string, new_var + 1);
		return this;
	}

	public StateMachine decrement(String string) {
		int new_var = variables.get(string);
		variables.remove(string);
		variables.put(string, new_var - 1);
		return this;
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
