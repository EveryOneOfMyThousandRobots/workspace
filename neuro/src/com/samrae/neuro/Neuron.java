package com.samrae.neuro;

import java.util.ArrayList;


public class Neuron {
	private double output;
	private final int myIndex;
	private static double alpha = 0.5;
	private static double eta = 0.15;
	
	
	
	
	ArrayList<Connection> connections = new ArrayList<Connection>();
	private double gradient;
	Neuron(int numOutputs, int myIndex) {
		
		System.out.println("Made a Neuron! outputs: " + numOutputs + " myIndex" + myIndex);
		this.myIndex = myIndex;
		for (int i = 0; i <= numOutputs; i++ ) {
			double rand = Math.random();
			Connection con = new Connection(rand, 0);
			
			
			connections.add(con);
			System.out.println("Made a connection " + con.weight + ", " + con.deltaweight);
		}
	}
	public void setOutputVal(Double output) {
		// TODO Auto-generated method stub
		this.output = output;
	}
	
	public double getOutputValue() {
		return output;
		
	}
	public void feedForward(Layer prevLayer) {
		// TODO Auto-generated method stub
		
		double sum = 0;
		for (int n = 0; n < prevLayer.size(); n++) {
			//System.out.println("connections:" + connections.size() + " myIndex : " + myIndex);
			double w = prevLayer.get(n).connections.get(myIndex).weight;
			sum += prevLayer.get(n).getOutputValue() * w;
		}
		
		output = transferFunction(sum);
		
	}
	
	public static double transferFunction(double sum) {
		
		return Math.tanh(sum);
	}
	
	private double transFuncDerivative(double x) {
		return 1.0 - (x * x);
	}
	public void calcOutputGradients(Double targetVal) {
		
		double delta = targetVal - output;
		gradient = delta * Neuron.transferFunction(output);
		
	}
	public void calcHiddenGradients(final Layer nextLayer) {
		double dow = sumDOW(nextLayer);
		gradient = dow * Neuron.transferFunction(dow);
		
	}
	private double sumDOW(Layer nextLayer) {
		double sum = 0;
		
		for (int n = 0; n <nextLayer.size() - 1; n += 1) {
			sum += connections.get(n).weight * nextLayer.get(n).gradient;
		}
		return sum;
	}
	public void updateInputWeights(Layer prevLayer) {
		//weights to be updated
		
		for (int n = 0; n < prevLayer.size(); n += 1) {
			Neuron neuron = prevLayer.get(n);
			
			double oldDelta = neuron.connections.get(myIndex).deltaweight;
			
			double newDelta =
					//individual input
					eta 
					* neuron.getOutputValue()
					* gradient
					* alpha
					* oldDelta
					;
			
			neuron.connections.get(myIndex).deltaweight = newDelta;
			neuron.connections.get(myIndex).weight += newDelta;
		}
		
		
		
	}

}
