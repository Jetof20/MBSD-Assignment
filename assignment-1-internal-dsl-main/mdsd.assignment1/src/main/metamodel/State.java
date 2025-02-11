package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {
	
	private String name;
	private List<Transition> transitions;
	
	
	public State(String name) {
		this.name = name;
		this.transitions = new ArrayList<Transition>();
	}

	public String getName() {
		return name;
	}
	
	public void addTransition(Transition transition) {
		transitions.add(transition);
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public Transition getTransitionByEvent(String string) {
		for (Transition t : transitions) {
			if (t.getName() == string) {
				return t;
			}
		}
		return null;
	}
}
