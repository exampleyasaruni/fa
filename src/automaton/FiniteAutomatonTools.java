package automaton;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FiniteAutomatonTools {
	/* Input/Output */
	public FiniteAutomaton fromJson(String s) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		return g.fromJson(s, FiniteAutomaton.class);
	}

	public FiniteAutomaton fromJson(File f) throws IOException {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		FileReader fr = new FileReader(f);
		FiniteAutomaton fa = g.fromJson(fr, FiniteAutomaton.class);
		fr.close();
		return fa;
	}

	public String toJson(FiniteAutomaton fa) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		return g.toJson(fa);
	}

	public void toJson(File f, FiniteAutomaton fa) throws IOException {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fw = new FileWriter(f);
		g.toJson(fa, fw);
		fw.close();
	}

	/*------------------- FUNCTIONS -------------------*/
	public boolean isDeterministic(FiniteAutomaton fa) {
		boolean bool = true; int ct = 0;
		if(fa.getFinalStates().isEmpty() == true | fa.getInitialState().length() == 0) { // check if fa has an initial state/final states
			bool = false;
		}
		for (String state : fa.getStates()) { // check if fa has proper transitions for being deterministic  
			Set<Transition> tras = fa.getTransitions();
			for (Iterator<Transition> iter = tras.iterator(); iter.hasNext();) {
				Transition tra = iter.next();
				if(tra.getFrom().equals(state)) {
					for (String input : fa.getInputs()) {
						if(tra.getInput().equals(input)) {
							ct++;
						}	
					}
				}
			}
			if(ct != fa.getInputs().size()) {
				bool = false;
			}
			ct = 0;
		}
		for (Transition tra: fa.getTransitions()) { // check if any transition's To is not in States set
			if(!(fa.getStates().contains(tra.getTo()))) {
				bool = false;
			}
		}
		return bool;
	}

	public boolean containsEpsilonTransition(FiniteAutomaton fa) {
		boolean bool = false;
		String e = "<epsilon>";
		if(fa.getInputs().contains(e)) { // check if input set has epsilon
			bool = true;
		}
		for(Transition tra : fa.getTransitions()) { // check if any transition has epsilon as input
			if(tra.getInput().equals(e)) {
				bool = true;
			}
		}
		return bool;
	}

	public boolean isMember(FiniteAutomaton fa, String s) {
		boolean bool = true;



		// String e = "<epsilon>"; int ct = 0;
		//		String currentState = fa.getInitialState();

		//		for(int i = 0; i < s.length(); i++) {
		//			for(Transition tra : fa.getTransitions()) { 
		//				if(tra.getFrom().equals(currentState)) {
		//					if(tra.getInput().equals(e)) {
		//						currentState = tra.getTo();
		//					}
		//					if(tra.getInput().equals(s.substring(i, i+1))) {
		//						currentState = tra.getTo(); ct++;
		//					}
		//				}	
		//			}
		//		}	


		//		for(Transition tra : fa.getTransitions()) { // initial state case
		//			if(tra.getInput().equals(e)) {
		//				currentState = tra.getTo(); ctrl = 1;
		//			}else {
		//				if(ctrl == 1) {
		//					if(tra.getFrom().equals(currentState) && tra.getInput().equals(s.substring(0, 1))) {
		//						currentState = tra.getTo();
		//					}	
		//				}else{
		//					if(tra.getFrom().equals(fa.getInitialState()) && tra.getInput().equals(s.substring(0, 1))) {
		//						currentState = tra.getTo();
		//					}	
		//				}
		//			}
		//		}
		//		for(int i = 1; i < s.length(); i++) { // other transitions (includes epsilon transitions)
		//			for(Transition tra : fa.getTransitions()) {
		//				if(tra.getFrom().equals(currentState) &&  tra.getInput().equals(e)) {
		//					currentState = tra.getTo();
		//				}
		//				if(tra.getFrom().equals(currentState) && tra.getInput().equals(s.substring(i, i+1))) {
		//					currentState = tra.getTo();
		//				}
		//			}
		//		}	

		//		if(!(fa.getFinalStates().contains(currentState)) | ct != s.length()) { // check if automaton ends in a final state
		//			bool = false;
		//		}
		return bool;
	}

	public FiniteAutomaton difference(FiniteAutomaton dfa1, FiniteAutomaton dfa2) {
		FiniteAutomaton pa = productAutomaton(dfa1, dfa2);
		for (String pfstate : pa.getStates()) { // add dfa1's final states to pa
			for (String fstate : dfa1.getFinalStates()) {
				if(pfstate.contains(fstate)) {
					pa.addFinalState(pfstate);
				}
			}	
		}

		Iterator<String> iter = dfa2.getFinalStates().iterator();
		while(iter.hasNext()) { // remove dfa2's final states from pa
			String fstate2 = iter.next();
			Iterator<String> iter2 = pa.getFinalStates().iterator();
			while(iter2.hasNext()) {
				String pfstate = iter2.next();
				if(pfstate.contains(fstate2)) {
					iter2.remove();
				}
			}
		}
		return pa;
	}

	public FiniteAutomaton union(FiniteAutomaton dfa1, FiniteAutomaton dfa2) {
		FiniteAutomaton pa = productAutomaton(dfa1, dfa2);
		for (String pfstate : pa.getStates()) { // add dfa1's final states to pa
			for (String fstate : dfa1.getFinalStates()) {
				if(pfstate.contains(fstate)) {
					pa.addFinalState(pfstate);
				}
			}	
		}
		for (String pfstate : pa.getStates()) { // add dfa2's final states to pa
			for (String fstate : dfa2.getFinalStates()) {
				if(pfstate.contains(fstate)) {
					pa.addFinalState(pfstate);
				}
			}	
		}
		return pa;
	}	

	public FiniteAutomaton productAutomaton(FiniteAutomaton dfa1, FiniteAutomaton dfa2) { // intersectionAutomaton at the same time
		FiniteAutomaton pa = new FiniteAutomaton();
		if(dfa1.getInputs().equals(dfa2.getInputs())) {
			pa.addInputs(dfa1.getInputs()); // set inputs if dfas have same inputs
		}else {
			System.out.println("DFAS' INPUT SETS CANNOT BE DIFFERENT!");
			return pa;
		}
		String ispa = dfa1.getInitialState().concat(dfa2.getInitialState()); // construct initial state 
		pa.setInitialState(ispa); 
		for (String sdfa1 : dfa1.getStates()) { // construct product automaton's states
			for (String sdfa2 : dfa2.getStates()) {
				String pstate = sdfa1.concat(sdfa2);
				pa.addState(pstate);
			}
		}	
		for (String fsdfa1: dfa1.getFinalStates()) { // detect final states 
			for (String fsdfa2 : dfa2.getFinalStates()) {
				String fpstate = fsdfa1.concat(fsdfa2);
				pa.addFinalState(fpstate);
			}
		}
		for (Transition tra: dfa1.getTransitions()) { // construct transitions
			for(Transition tra2: dfa2.getTransitions()) {
				if(tra.getInput().equals(tra2.getInput())) {
					String pFrom = tra.getFrom().concat(tra2.getFrom()); 
					String pTo = tra.getTo().concat(tra2.getTo());
					pa.addTransition(pFrom, tra.getInput(), pTo); 
				}
			}
		}
		return pa;
	}

	public String[][] getTransitionTable(FiniteAutomaton dfa){
		int statenum = dfa.getStates().size();
		int inputnum = dfa.getInputs().size();
		String[][] table = new String[statenum+1][inputnum+1]; 
		int i = 1, j;
		for (String state : dfa.getStates()) {
			table[i][0] = state; i++;
		}
		i = 1;	
		for (String input : dfa.getInputs()) {
			table[0][i] = input; i++;
		}
		for(j = 1; j < inputnum + 1; j++) {
			for(i = 1; i < statenum + 1; i++) {
				for (Transition tra : dfa.getTransitions()) {
					if(tra.getFrom().equals(table[i][0]) && tra.getInput().equals(table[0][j])) {
						table[i][j] = tra.getTo(); 
					}
				}
			}
		}
		return table;
	}

	public FiniteAutomaton minimize_TableFilling(FiniteAutomaton dfa) {
		int statenum = dfa.getStates().size(); 	int i, j = statenum;
		String[][] table = getTransitionTable(dfa); // get table of transitions from dfa
		String[][] marktable = new String[statenum][statenum];
		List<String> states = new ArrayList<>(dfa.getStates());
		for(i = 1; i < statenum; i++) { // create marking table
			marktable[0][i] = states.get(j-1); j--;
			marktable[i][0] = states.get(i-1); 
		}
//		System.out.println(Arrays.deepToString(table));
		int limit = statenum, limit2, ct;
		for(i = 1; i < statenum; i++) { // Mark {p,q} if p is in F and q is not or vice versa.
			for(j = 1; j < limit; j++) {
				ct = 0;
				if(dfa.getFinalStates().contains(marktable[i][0])) {
					ct++;
				}
				if(dfa.getFinalStates().contains(marktable[0][j])) {
					ct++;
				}
				if(ct == 1) {
					marktable[i][j] = "X";
				}
			}
			limit--;
		}		
		String fs = null, ss = null; int ctrl = 1;
		while(ctrl == 1) { // Mark {p,q} s.t. {tra(p,a), tra(q,a)} is marked
			ctrl = 0;
			limit = statenum;
			for(i = 1; i < statenum; i++) {
				for(j = 1; j < limit; j++) {
					if(marktable[i][j] != "X") {
						for(int k = 1; k < dfa.getInputs().size()+1; k++) {
							for(int l = 1; l < statenum+1; l++) {
								if(table[l][0] == marktable[i][0]) {
									fs = table[l][k];
								}
								if(table[l][0] == marktable[0][j]) {
									ss = table[l][k];
								}
							}
							limit2 = statenum;
							for(int m = 1; m < statenum; m++) {
								for(int n = 1; n < limit2; n++) {
									if((marktable[m][0] == fs) && (marktable[0][n] == ss)) {
										if(marktable[m][n] == "X") {
											marktable[i][j] = "X"; ctrl = 1;
										}
									}
								}
								limit2--;
							}	
						}
					}
				}
				limit--;
			}
		}
//		System.out.println(Arrays.deepToString(marktable));
		limit = statenum;
		ArrayList<String[]> rstates = new ArrayList<String[]>(); String[] strarr = new String[2];
		for(i = 1; i < statenum; i++) {
			for(j = 1; j < limit; j++) {
				if(marktable[i][j] != "X") {
					strarr[0] = marktable[i][0];
					strarr[1] = marktable[0][j];
					rstates.add(strarr); 

				}
			}
			limit--;
		}
//		for (String[] arr : rstates) {
//			System.out.println(Arrays.toString(arr));
//		}
		String[] newstates = new String[rstates.size()];
		for(i = 0; i < rstates.size(); i++) {
			String ns = "ns" + i;
			newstates[i] = ns;
		}
//		System.out.println(Arrays.toString(newstates));
		for(int k = 0; k < rstates.size(); k++) { // scan transition table
			for(i = 1; i < statenum+1; i++) {
				for(j = 0; j < dfa.getInputs().size()+1; j++) {
					if(table[i][j] == rstates.get(k)[0]) {
						table[i][j] = newstates[k];
					}
					if(table[i][j] == rstates.get(k)[1]) {
						table[i][j] = newstates[k];
					}
				}
			}	
		}
//		System.out.println(Arrays.deepToString(table));
		for(i = 0; i < statenum+1; i++) { // eliminate reducable states 
			for(j = i+1; j < statenum+1; j++) {
				if(Arrays.equals(table[i], table[j])) {
					for (int k = 0; k < dfa.getInputs().size()+1; k++) {
						table[j][k] = "";
					}
				}
			}
		}
//		System.out.println(Arrays.deepToString(table));	
		String minitialState = dfa.getInitialState();
		Set<String> minputs = new LinkedHashSet<>();
		Set<String> mstates = new LinkedHashSet<>();
		Set<String> mfinalStates = new LinkedHashSet<>();
		Set<Transition> mtra = new LinkedHashSet<>();
		for (String input : dfa.getInputs()) {
			minputs.add(input);
		}
		for (String fstates : dfa.getFinalStates()) {
			mfinalStates.add(fstates);
		}
		for(i = 1; i < statenum+1; i++) {
			if(table[i][0] != "") {
				mstates.add(table[i][0]);
			}
		}	
		for(i = 1; i < statenum+1; i++) {
			for(j = 1; j < dfa.getInputs().size()+1; j++) {
				if(table[i][0] != "") {
					mtra.add(new Transition(table[i][0],table[0][j],table[i][j])); 
				}
			}
		}
		FiniteAutomaton mindfa = new FiniteAutomaton(mstates, minputs, minitialState, mfinalStates, mtra); // construct minimized DFA
		return mindfa;
	}
}







