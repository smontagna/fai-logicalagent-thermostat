package org.agent.thermostat;

import java.util.Optional;

import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.exceptions.NoSolutionException;

public abstract class Agent {
	
	protected final Prolog prolog;
	
	public Agent() {
		this.prolog = new Prolog();
		prolog.addTheory(baseKnowledge());
	}
	
    public abstract Theory baseKnowledge();
    
    public abstract void sense();

    public Optional<Term> plan() {
    	try {
    	 Term t = Term.createTerm("next_action(X)");
    	 final SolveInfo solution = prolog.solve(t);
         if(solution.isSuccess()) {
        	 return Optional.of(solution.getSolution().getTerm());
         	} else {
         		return Optional.empty();
         	}
         } catch(Exception ex) {
        	 return Optional.empty();
         }
    }
    
    public abstract Term goal();
    
    public abstract void act(Term action);

    public final void reasoningCycle() throws NoSolutionException {
        this.sense(); 
        if (this.plan().isPresent()) {
        	Term action = this.plan().get();
        	this.act(action);
        } else {
        	System.out.println("no actions are selected");
        } 
    }
    
}
