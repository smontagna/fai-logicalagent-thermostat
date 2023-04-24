package org.agent.thermostat;

public class Environment {
	
	private double temperature;
	
	public Environment(final double initialT) {
		this.temperature = initialT;
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
	public void increaseTemperature() {
		this.temperature++;
	}
	
	public void decreaseTemperature() {
		this.temperature--;
	}
}
