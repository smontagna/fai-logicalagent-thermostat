package org.agent.thermostat;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Collectors;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;

public class ThermostatAgent extends Agent{
    private final int targetTemp;
    private final Environment env;

    public ThermostatAgent(final Environment e, final int targetT) {
        super();
        this.env = e;
        this.targetTemp = targetT;
        Term term = Term.createTerm("assert("+this.goal()+")");
		prolog.solve(term);
    }
    
    private String agentProgram() throws IOException {
    	Scanner scanner = new Scanner(this.getClass().getResourceAsStream("thermostat.pl"));
    	
    	String s = new String();

    	while(scanner.hasNextLine()){
    	    s = s + scanner.nextLine() + "\n";
    	}
    	
    	return s;
    }

	@Override
	public Theory baseKnowledge() {
		try{
			return Theory.parseWithStandardOperators(this.agentProgram());
		}catch(IOException ex) {
			return Theory.empty();
		}
	}
	
	public Term goal () {
		return Term.createTerm("target_temperature("+this.targetTemp+")");
	}

	@Override
	public void sense() {
		prolog.solve(Term.createTerm("retract(current_temperature(Y))"));
		Term t = Term.createTerm("assert(current_temperature("+this.env.getTemperature()+"))");
		prolog.solve(t);
		System.out.println(prolog.getTheory());
	}

	@Override
	public void act(Term action) {
		System.out.println(action);
		String actionToDo = ((Struct)action).getArg(0).getTerm().toString();
		if (actionToDo.equals("heating")) {
			this.env.increaseTemperature();;
		} else if (actionToDo.equals("cooling")) {
			this.env.decreaseTemperature();
		} else {
			System.out.println("unknown actions: "+ action);
		}
		
	}

}
