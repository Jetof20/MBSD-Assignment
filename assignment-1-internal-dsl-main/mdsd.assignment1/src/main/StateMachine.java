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
	private int starting_state_id = 0;
	
	
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
		// Map each state's transitions to the other states o(N) complexity
		for (int s = 0; s < states.size();s++) {
			
			for (int t = 0; t < states.get(s).getTransitions().size();t++) {
				
				// Map the target_names set to an actual state reference o(N) complexity, instead of string 
				String target_name = states.get(s).getTransitions().get(t).getTargetString();
				
				for (int s_ = 0; s_ < states.size();s_++) {
					if (states.get(s_).getName() == target_name) {
						State state_to_map = states.get(s_);
						states.get(s).getTransitions().get(t).setTarget(state_to_map);
					}
				}
				
			}
		}
		
		Machine machine = new Machine(states,starting_state_id);
		
		return machine; 
	}

	public StateMachine state(String string) {
		State new_state = new State(string);
		states.add(new_state);
		return this;
	}

	public StateMachine initial() {
		this.starting_state_id = states.size() - 1; // Get index of current element and set it as 
		return this;
	}

	public StateMachine when(String string) {
		State latest_state = get_scoped_state();
		Transition transition = new Transition(string);
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
		Transition latest_transition = get_scoped_transition();
		latest_transition.createOperation(string,"SET", i);
		return this;
	}

	public StateMachine increment(String string) {
		Transition latest_transition = get_scoped_transition();
		latest_transition.createOperation(string,"INCREMENT", 1);
		return this;
	}

	public StateMachine decrement(String string) {
		Transition latest_transition = get_scoped_transition();
		latest_transition.createOperation(string,"DECREMENT", 1);
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		Transition latest_transition = get_scoped_transition();
		latest_transition.createConditional(string,"EQUAL", i);
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		Transition latest_transition = get_scoped_transition();
		latest_transition.createConditional(string,"GREAT", i);
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		Transition latest_transition = get_scoped_transition();
		latest_transition.createConditional(string,"LESS", i);
		return this;
	}

}
