package com.samrae.neuro;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		ArrayList<Integer> topology = new ArrayList<Integer>();

		topology.add(2);
		topology.add(3);
		topology.add(1);

		Net net = new Net(topology);

		double[] i1 = { 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1 };
		double[] i2 = { 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1 };
		double[] oo = { 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 };

		for (int a = 0; a < 100; a++) {
			for (int i = 0; i < i1.length; i++) {
				ArrayList<Double> input = new ArrayList<Double>();
				input.add(i1[i]);
				input.add(i2[i]);

				net.feedForward(input);
				ArrayList<Double> target = new ArrayList<Double>();
				target.add(oo[i]);
				net.backProp(target);
				ArrayList<Double> results = new ArrayList<Double>();
				net.getResults(results);
				System.out.println("[" + a + "][" + i + "] i: " + i1[i] + "," + i2[i] + " t: " + oo[i] + " results: " + results);
			}
		}

	}

}
