package com.samrae.neuro3;

import java.text.*;

public class TestNeuralNetwork {
	public static void main(String args[]) {
		double xorInput[][] = { {1,0}, {1,1}, {0,1}, {0,0}, {0.1, 0.5}};

		double xorIdeal[][] = { { 0.5 }, { 1.0 }, { 0.5}, { 0.0 }, {0.3}};

		System.out.println("Learn:");

		Network network = new Network(2, 20, 1, 0.7, 0.9);

		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMinimumFractionDigits(4);

		for (int i = 0; i < 100000; i++) {
			for (int j = 0; j < xorInput.length; j++) {
				network.computeOutputs(xorInput[j]);
				network.calcError(xorIdeal[j]);
				network.learn();
			}
			if (i % 1000 == 0) {
				System.out.println("Trial #" + i + ",Error:" + percentFormat.format(network.getError(xorInput.length)));
			}
		}

		System.out.println("Recall:");

		for (int i = 0; i < xorInput.length; i++) {

			for (int j = 0; j < xorInput[0].length; j++) {
				System.out.print(xorInput[i][j] + ":");
			}

			double out[] = network.computeOutputs(xorInput[i]);
			System.out.printf("=%.3f\n", out[0]);
		}
	}
}