package com.samrae.neuro;

import java.util.ArrayList;

public class Net {
	int numLayers;
	private double error;
	//ArrayList<Layer> layers = new ArrayList<Layer>();
	Layers layers = new Layers();
	Net(ArrayList<Integer>topology) {
		numLayers = topology.size();
		
		
		
		for (int l = 0; l < topology.size(); l++) {
			Layer layer = new Layer();
			layers.add(layer);
			
			int numOutputs  = 0;
			if (l == topology.size() - 1) {
				numOutputs = 0;
			} else {
				numOutputs = topology.get(l + 1);
			}
			
			for (int n = 0 ; n <= topology.get(l); n++) {
				Neuron neuron = new Neuron(numOutputs, n);
				layer.add(neuron);
				
			}
			layers.last().last().setOutputVal(1.0);
		}
	}
	
	public void feedForward(ArrayList<Double> input) {
		//assert(input.size() == layers.size() - 1);
		
		System.out.println(" inputsize :" + input.size() + " layers: " + (layers.get(0).size() - 1));
		
		for (int i = 0; i < input.size(); i++) {
			layers.get(0).get(i).setOutputVal(input.get(i));
			
		}
		
		for (int layerIndex = 1; layerIndex < layers.size(); layerIndex += 1 ) {
			Layer prevLayer = layers.get(layerIndex - 1);
			Layer layer = layers.get(layerIndex);
			
			for (int n = 0; n < layer.size() - 1 ; n += 1) {
				layer.get(n).feedForward(prevLayer);
			}
			
		}
		
	}
	
	public void backProp(ArrayList<Double> target) {
		//calc overall net error RMS
		Layer outputLayer = layers.last();
		
		error = 0.0;
		for (int n = 0; n < outputLayer.size() - 1; n += 1) {
			double delta = target.get(n) - outputLayer.get(n).getOutputValue();
			error += (delta * delta);
		}
		
		error /= outputLayer.size() - 1;
		error = Math.sqrt(error);
		//calc output layer gradients
		for (int n = 0; n < outputLayer.size() - 1; n += 1) {
			outputLayer.get(n).calcOutputGradients(target.get(n));
			
		}
		//calc gradients on hidden layers
		for (int layerIndex = layers.size() - 2; layerIndex > 0; layerIndex -= 1) {
			Layer hiddenLayer = layers.get(layerIndex);
			Layer nextLayer = layers.get(layerIndex + 1);
			
			for (int n = 0; n < hiddenLayer.size(); n += 1 ) {
				Neuron neuron = hiddenLayer.get(n);
				neuron.calcHiddenGradients(nextLayer);
			}
		}
		//For all layers from outputs to first hidden layer
		//update connection weights
		
		for (int layerIndex = layers.size() - 1; layerIndex > 0; layerIndex -= 1) {
			Layer layer = layers.get(layerIndex);
			Layer prevLayer = layers.get(layerIndex - 1);
			
			for (int n = 0; n < layer.size(); n += 1 ) {
				layer.get(n).updateInputWeights(prevLayer);
			}
		}
		
		
		
	}
	
	public void getResults(ArrayList<Double> results) {
		results.clear();
		
		for (int n = 0; n < layers.last().size() - 1; n += 1) {
			results.add(layers.last().get(n).getOutputValue());
			
		}
		
		
	}

}
