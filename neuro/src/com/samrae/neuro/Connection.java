package com.samrae.neuro;

public class Connection {
	double weight, deltaweight;
	
	public Connection(double weight, double deltaWeight) {
		this.weight = weight;
		this.deltaweight = deltaWeight;
		
	}
	
	public Connection() {
		this(1,0);
	}

}
