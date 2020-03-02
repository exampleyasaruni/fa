package automaton;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A FiniteAutomaton can be a DFA, an NFA or an e-NFA. 
 *
 */
public class FiniteAutomaton {
	private Set<String> states;
	private Set<String> inputs;
	private String initialState;
	private Set<String> finalStates;
	private Set<Transition> transitions;

	public FiniteAutomaton() {
		//Nonnull LinkedHashSet elements o1 and o2 are considered to be the same iff
		// (1) o1.hashCode() == o2.hashCode()
		// (2) o1.equals(o2) == true
		this.states = new LinkedHashSet<String>();
		this.inputs = new LinkedHashSet<String>();
		this.initialState = null;
		this.finalStates = new LinkedHashSet<String>();
		this.transitions = new LinkedHashSet<Transition>();
	}

	public FiniteAutomaton(Set<String> states, Set<String> inputs, String initialState, Set<String> finalStates, Set<Transition> transitions) {
		this.states = states;
		this.inputs = inputs;
		this.initialState = initialState;
		this.finalStates = finalStates;
		this.transitions = transitions;
	}

	public Set<String> getStates() {
		return states;
	}

	public void setStates(Set<String> states) {
		this.states = states;
	}

	public Set<String> getInputs() {
		return inputs;
	}

	public void setInputs(Set<String> inputs) {
		this.inputs = inputs;
	}

	public String getInitialState() {
		return initialState;
	}

	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}

	public Set<String> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(Set<String> finalStates) {
		this.finalStates = finalStates;
	}

	public Set<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}

	public boolean addState(String state) {
		return this.states.add(state);
	}

	public boolean removeState(String state) {
		return this.states.remove(state);
	}

	public boolean addStates(Set<String> states) {
		return this.states.addAll(states);
	}

	public boolean removeStates(Set<String> states) {
		return this.states.removeAll(states);
	}

	public boolean addInput(String input) {
		return this.inputs.add(input);
	}

	public boolean removeInput(String input) {
		return this.inputs.remove(input);
	}

	public boolean addInputs(Set<String> inputs) {
		return this.inputs.addAll(inputs);
	}

	public boolean removeInputs(Set<String> inputs) {
		return this.inputs.removeAll(inputs);
	}
	
	public boolean addFinalState(String state) {
		return this.finalStates.add(state);
	}

	public boolean removeFinalState(String state) {
		return this.finalStates.remove(state);
	}

	public boolean addFinalStates(Set<String> states) {
		return this.finalStates.addAll(states);
	}

	public boolean removeFinalStates(Set<String> states) {
		return this.finalStates.removeAll(states);
	}

	public boolean addTransition(Transition transition) {
		return this.transitions.add(transition);
	}

	public boolean removeTransition(Transition transition) {
		return this.transitions.remove(transition);
	}

	public boolean addTransition(String from, String input, String to) {
		return this.transitions.add(new Transition(from, input, to));
	}

	public boolean removeTransition(String from, String input, String to) {
		return this.transitions.remove(new Transition(from, input, to));
	}

	public boolean addTransitions(Set<Transition> transitions) {
		return this.transitions.addAll(transitions);
	}

	public boolean removeTransitions(Set<Transition> t) {
		return this.transitions.removeAll(t);
	}

	@Override
	public String toString() {
		return "FiniteAutomaton {" + 
				"\nStates: " + states.toString() + 
				"\nInputs: " + inputs.toString() + 
				"\nInitialState: " + initialState + 
				"\nFinalStates: " + finalStates.toString() + 
				"\nTransitions: " + transitions.toString() + 
				"\n}";
	}
}
