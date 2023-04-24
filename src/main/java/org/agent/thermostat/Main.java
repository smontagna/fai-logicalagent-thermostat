package org.agent.thermostat;

import alice.tuprolog.exceptions.NoSolutionException;

public class Main {
    public static void main(String[] args) throws NoSolutionException {
        final var agent = new ThermostatAgent(new Environment(18),20);
        final int iterations = 3;
        for (int i = 0; i < iterations; i++) {
            agent.reasoningCycle();
        }
    }
}
