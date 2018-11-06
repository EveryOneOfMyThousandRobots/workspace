package com.samrae.neuro;

import java.util.ArrayList;

public class Layers {
	private ArrayList<Layer> layers = new ArrayList<Layer>();
	
	public void add(Layer layer) {
		layers.add(layer);
	}
	
	public Layer get(int index) {
		return layers.get(index);
	}
	
	public int size() {
		return layers.size();
	}
	
	public Layer last() {
		return layers.get(layers.size() - 1);
		
	}
	
	public Layer first() {
		return layers.get(0);
	}

}
