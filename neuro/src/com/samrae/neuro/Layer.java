package com.samrae.neuro;

import java.util.ArrayList;

public class Layer {
	private ArrayList<Neuron> neurons = new ArrayList<Neuron>();
	
	
	public void add(Neuron n) {
		neurons.add(n);
	}
	
	public void remove(int index) {
		neurons.remove(index);
	}
	
	public void clear() {
		neurons.clear();
	}
	
	public Neuron get(int index) {
		return neurons.get(index);
	}
	
	public int size() {
		return neurons.size();
	}
	
	public Layer() {
		System.out.println("Made a Layer!");
	}
	
	public ArrayList<Neuron> getNeurons() {
		return neurons;
	}
	
	public Neuron first() {
		return neurons.get(0);
	}
	
	public Neuron last() {
		return neurons.get(neurons.size() - 1);
	}



}
