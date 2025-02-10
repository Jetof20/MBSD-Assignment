package main.metamodel;

import java.util.List;

public class State {
	
	private String name;
	private List<Transition> transitions;
	
	
	public State(String name) {
		this.name = name;
	}

	public Object getName() {
		return name;
	}
	
	public void addTransition(Transition transition) {
		transitions.add(transition);
	}

	public List<Transition> getTransitions() {
		// TODO Auto-generated method stub
		return transitions;
	}

	public Transition getTransitionByEvent(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
