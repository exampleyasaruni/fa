import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import automaton.*;

public class Main {

	public static void main(String[] args) throws IOException {
		FiniteAutomatonTools tools = new FiniteAutomatonTools();
		boolean bool = false;

		/* isDeterministic test FA1 */
		Set<String> states1 = new LinkedHashSet<>(); // Example DFA (FiniteAutomaton - 21. Slide)
		states1.add("p0"); states1.add("p1"); states1.add("p2");
		states1.add("p3"); states1.add("p4"); states1.add("p5");
		states1.add("p6"); states1.add("p7");
		Set<String> inputs1 = new LinkedHashSet<>();
		inputs1.add("a"); inputs1.add("b");
		String initialState1 = "p0";
		Set<String> finalStates1 = new LinkedHashSet<>(); finalStates1.add("p7");
		Set<Transition> tra1 = new LinkedHashSet<>();
		tra1.add(new Transition("p0","a","p1")); tra1.add(new Transition("p0","b","p4"));
		tra1.add(new Transition("p1","a","p2")); tra1.add(new Transition("p1","b","p4"));
		tra1.add(new Transition("p2","a","p2")); tra1.add(new Transition("p2","b","p3"));
		tra1.add(new Transition("p3","a","p2")); tra1.add(new Transition("p3","b","p7"));
		tra1.add(new Transition("p4","a","p1")); tra1.add(new Transition("p4","b","p5"));
		tra1.add(new Transition("p5","a","p6")); tra1.add(new Transition("p5","b","p5"));
		tra1.add(new Transition("p6","a","p7")); tra1.add(new Transition("p6","b","p5"));
		tra1.add(new Transition("p7","a","p7")); tra1.add(new Transition("p7","b","p7"));
		FiniteAutomaton fa1 = new FiniteAutomaton(states1, inputs1, initialState1, finalStates1, tra1);
		bool = tools.isDeterministic(fa1);
		System.out.println("isDeterministic(FA1): " + bool);
		String FA1_json = tools.toJson(fa1);
		FiniteAutomaton FA1_fromjson = tools.fromJson(FA1_json);
		System.out.println("FA1");
		System.out.println(FA1_fromjson.toString());
		tools.toJson(new File("FA1_json"), fa1);
		
		
		/* isDeterministic test FA2 */
		Set<String> states2 = new LinkedHashSet<>();
		states2.add("q0"); states2.add("q1"); states2.add("q2"); 
		states2.add("q3"); states2.add("q4"); states2.add("q5");
		Set<String> inputs2 = new LinkedHashSet<>();
		inputs2.add("a"); inputs2.add("b");
		String initialState2 = "q0";
		Set<String> finalStates2 = new LinkedHashSet<>(); finalStates2.add("q5");
		Set<Transition> tra2 = new LinkedHashSet<>();
		tra2.add(new Transition("q0","a","q2")); tra2.add(new Transition("q0","b","q1"));
		tra2.add(new Transition("q1","a","q3")); tra2.add(new Transition("q1","b","q0")); 
		tra2.add(new Transition("q2","a","q4")); tra2.add(new Transition("q2","b","q3"));
		tra2.add(new Transition("q3","a","q5")); tra2.add(new Transition("q3","b","q2"));
		tra2.add(new Transition("q4","a","q4")); tra2.add(new Transition("q4","b","q5"));
		tra2.add(new Transition("q5","a","q5")); tra2.add(new Transition("q5","b","q4"));
		FiniteAutomaton fa2 = new FiniteAutomaton(states2, inputs2, initialState2, finalStates2, tra2);
		bool = tools.isDeterministic(fa2);
		System.out.println("isDeterministic(FA2): " + bool);
		String FA2_json = tools.toJson(fa2);
		FiniteAutomaton FA2_fromjson = tools.fromJson(FA2_json);
		System.out.println("FA2");
		System.out.println(FA2_fromjson.toString());
		tools.toJson(new File("FA2_json"), fa2);
		
		
		/* isDeterministic test FA3 */
		Set<String> states3 = new LinkedHashSet<>();
		states3.add("q0"); states3.add("q1"); states3.add("q2"); 
		states3.add("q3"); states3.add("q4");
		Set<String> inputs3 = new LinkedHashSet<>();
		inputs3.add("a"); inputs3.add("b");
		String initialState3 = "q0";
		Set<String> finalStates3 = new LinkedHashSet<>(); finalStates3.add("q4");
		Set<Transition> tra3 = new LinkedHashSet<>();
		tra3.add(new Transition("q0","a","q0")); tra3.add(new Transition("q0","b","q0"));
		tra3.add(new Transition("q0","b","q1")); tra3.add(new Transition("q1","a","q2")); 
		tra3.add(new Transition("q1","b","q2")); tra3.add(new Transition("q2","a","q3"));
		tra3.add(new Transition("q2","b","q3")); tra3.add(new Transition("q3","a","q4"));
		FiniteAutomaton fa3 = new FiniteAutomaton(states3, inputs3, initialState3, finalStates3, tra3);
		bool = tools.isDeterministic(fa3);
		System.out.println("isDeterministic(FA3): " + bool);
		bool = tools.containsEpsilonTransition(fa3);
		System.out.println("containsEpsilonTransition(FA3): " + bool);
		String FA3_json = tools.toJson(fa3);
		FiniteAutomaton FA3_fromjson = tools.fromJson(FA3_json);
		System.out.println("FA3");
		System.out.println(FA3_fromjson.toString());
		tools.toJson(new File("FA3_json"), fa3);
		
		
		/* containsEpsilonTransition test FA4 */
		Set<String> states4 = new LinkedHashSet<>();
		states4.add("q0"); states4.add("q1"); states4.add("q2");  
		states4.add("q3"); states4.add("q4"); states4.add("q5");
		Set<String> inputs4 = new LinkedHashSet<>();
		inputs4.add("0"); inputs4.add("1"); inputs4.add("2");
		String initialState4 = "q0";
		Set<String> finalStates4 = new LinkedHashSet<>(); finalStates4.add("q4"); finalStates4.add("q5");
		Set<Transition> tra4 = new LinkedHashSet<>();
		tra4.add(new Transition("q0","0","q0")); tra4.add(new Transition("q0","<epsilon>","q1"));
		tra4.add(new Transition("q1","2","q1")); tra4.add(new Transition("q1","<epsilon>","q2")); 
		tra4.add(new Transition("q1","0","q2")); tra4.add(new Transition("q1","2","q0")); 
		tra4.add(new Transition("q1","1","q5")); tra4.add(new Transition("q2","0","q2"));
		tra4.add(new Transition("q2","1","q4")); tra4.add(new Transition("q4","0","q1"));
		tra4.add(new Transition("q5","1","q0")); tra4.add(new Transition("q1","2","q0"));
		FiniteAutomaton fa4 = new FiniteAutomaton(states4, inputs4, initialState4, finalStates4, tra4);
		bool = tools.containsEpsilonTransition(fa4);
		System.out.println("containsEpsilonTransition(FA4): " + bool);	
		String FA4_json = tools.toJson(fa4);
		FiniteAutomaton FA4_fromjson = tools.fromJson(FA4_json);
		System.out.println("FA4");
		System.out.println(FA4_fromjson.toString());
		tools.toJson(new File("FA4_json"), fa4);
		
		/* isMember test FA5 */
		Set<String> states5 = new LinkedHashSet<>();
		states5.add("q0"); states5.add("q1"); states5.add("q2");  
		states5.add("q3"); states5.add("q4"); 
		Set<String> inputs5 = new LinkedHashSet<>();
		inputs5.add("a"); inputs5.add("b"); inputs5.add("c");
		String initialState5 = "q0";
		Set<String> finalStates5 = new LinkedHashSet<>(); finalStates5.add("q3");
		Set<Transition> tra5 = new LinkedHashSet<>();
		tra5.add(new Transition("q0","a","q1")); tra5.add(new Transition("q1","<epsilon>","q2"));  
		tra5.add(new Transition("q0","b","q4")); tra5.add(new Transition("q1","<epsilon>","q4")); 
		tra5.add(new Transition("q2","b","q4")); tra5.add(new Transition("q3","a","q2"));
		tra5.add(new Transition("q3","c","q1")); tra5.add(new Transition("q4","c","q3"));
		FiniteAutomaton fa5 = new FiniteAutomaton(states5, inputs5, initialState5, finalStates5, tra5);
		bool = tools.containsEpsilonTransition(fa4);
		System.out.println("isMember(FA5): " + bool);	
		String FA5_json = tools.toJson(fa4);
		FiniteAutomaton FA5_fromjson = tools.fromJson(FA5_json);
		System.out.println("FA5");
		System.out.println(FA5_fromjson.toString());
		tools.toJson(new File("FA5_json"), fa5);
		bool = tools.isMember(fa5, "01010010101");
		System.out.println("isMember(FA5): " + bool);
	
		/* productAutomaton, union, difference test */
		if(tools.isDeterministic(fa1) && tools.isDeterministic(fa2)) { // both FAs should be deterministic
			System.out.println("INTERSECTION AUTOMATON");
			FiniteAutomaton intseca = tools.productAutomaton(fa1, fa2); // product automaton is already returns intersection automaton
			String intseca_json = tools.toJson(intseca);
			FiniteAutomaton intseca_fromjson = tools.fromJson(intseca_json);
			System.out.println(intseca_fromjson.toString());
			tools.toJson(new File("FA1FA2_INTER_json"), intseca);
			
			System.out.println("UNION AUTOMATON");		
			FiniteAutomaton uniona = tools.union(fa1, fa2);
			String uniona_json = tools.toJson(uniona);
			FiniteAutomaton uniona_fromjson = tools.fromJson(uniona_json);
			System.out.println(uniona_fromjson.toString());
			tools.toJson(new File("FA1FA2_UNI_json"), uniona);
			
			System.out.println("DIFFERENCE AUTOMATON");		
			FiniteAutomaton diffa = tools.difference(fa1, fa2);
			String diffa_json = tools.toJson(diffa);
			FiniteAutomaton diffa_fromjson = tools.fromJson(diffa_json);
			System.out.println(diffa_fromjson.toString());
			tools.toJson(new File("FA1FA2_DIFF_json"), diffa);
		}			
		
		
		/* minimize_TableFilling test DFA1 */
		Set<String> mstates1 = new LinkedHashSet<>();
		mstates1.add("q0"); mstates1.add("q1"); mstates1.add("q2");
		mstates1.add("q3"); mstates1.add("q4"); mstates1.add("q5");
		Set<String> minputs1 = new LinkedHashSet<>();
		minputs1.add("0"); minputs1.add("1");
		String minitialState1 = "q0";
		Set<String> mfinalStates1 = new LinkedHashSet<>();
		mfinalStates1.add("q2"); mfinalStates1.add("q3"); mfinalStates1.add("q4");
		Set<Transition> mtra1 = new LinkedHashSet<>();
		mtra1.add(new Transition("q0","0","q1")); mtra1.add(new Transition("q0","1","q2"));
		mtra1.add(new Transition("q1","0","q0")); mtra1.add(new Transition("q1","1","q3"));
		mtra1.add(new Transition("q2","0","q4")); mtra1.add(new Transition("q2","1","q5"));
		mtra1.add(new Transition("q3","0","q4")); mtra1.add(new Transition("q3","1","q5"));
		mtra1.add(new Transition("q4","0","q4")); mtra1.add(new Transition("q4","1","q5"));
		mtra1.add(new Transition("q5","0","q5")); mtra1.add(new Transition("q5","1","q5"));
		FiniteAutomaton reducableDFA1 = new FiniteAutomaton(mstates1, minputs1, minitialState1, mfinalStates1, mtra1);
		FiniteAutomaton minimizedDFA1 = tools.minimize_TableFilling(reducableDFA1);
		String mDFA1_json = tools.toJson(minimizedDFA1);
		FiniteAutomaton mDFA1_fromjson = tools.fromJson(mDFA1_json);
		System.out.println("mDFA1");
		System.out.println(mDFA1_fromjson.toString());
		tools.toJson(new File("mDFA1_json"), minimizedDFA1);
		
		/* minimize_TableFilling test DFA2 */
		Set<String> mstates2 = new LinkedHashSet<>(); // Example DFA in slides (rs1 - 43)
		mstates2.add("A"); mstates2.add("B"); mstates2.add("C");
		mstates2.add("D"); mstates2.add("E"); mstates2.add("F"); mstates2.add("G");
		Set<String> minputs2 = new LinkedHashSet<>();
		minputs2.add("r"); minputs2.add("b");
		String minitialState2 = "A";
		Set<String> mfinalStates2 = new LinkedHashSet<>();
		mfinalStates2.add("F"); mfinalStates2.add("G");
		Set<Transition> mtra2 = new LinkedHashSet<>();
		mtra2.add(new Transition("A","r","B")); 
		mtra2.add(new Transition("A","b","C"));
		mtra2.add(new Transition("B","r","D"));
		mtra2.add(new Transition("B","b","E"));
		mtra2.add(new Transition("C","r","D"));
		mtra2.add(new Transition("C","b","F"));
		mtra2.add(new Transition("D","r","D"));
		mtra2.add(new Transition("D","b","G"));
		mtra2.add(new Transition("E","r","D"));
		mtra2.add(new Transition("E","b","G"));
		mtra2.add(new Transition("F","r","D"));
		mtra2.add(new Transition("F","b","C"));
		mtra2.add(new Transition("G","r","D"));
		mtra2.add(new Transition("G","b","G"));
		FiniteAutomaton reducableDFA2 = new FiniteAutomaton(mstates2, minputs2, minitialState2, mfinalStates2, mtra2);
		FiniteAutomaton minimizedDFA2 = tools.minimize_TableFilling(reducableDFA2);
		String mDFA2_json = tools.toJson(minimizedDFA2);
		FiniteAutomaton mDFA2_fromjson = tools.fromJson(mDFA2_json);
		System.out.println("mDFA2");
		System.out.println(mDFA2_fromjson.toString());
		tools.toJson(new File("mDFA2_json"), minimizedDFA2);
	}

}
