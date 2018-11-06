package com.samrae.neuro2;

import java.util.Random;
import java.util.Arrays;

public class MLP {
	
	public static class MLPLayer {
		double[] output;
		double[] input;
		double[] weights;
		double[] dweights;
		boolean isSigmoid = true;
		
		public MLPLayer (int inputSize, int outputSize, Random r) {
			output = new double[outputSize];
			input = new double[inputSize + 1];
			weights = new double[(1 + inputSize ) * outputSize];
			dweights = new double[weights.length];
			initWeights(r);
			
		}
		
		public void setIsSigmod(boolean isSigmoid) {
			this.isSigmoid = isSigmoid;
		}
		
		public void initWeights(Random r) {
			for (int i = 0; i <weights.length; i += 1) {
				weights[i] = (r.nextDouble() - 0.5) * 4;
			}
		}
		
		public double[] run(double[] in) {
			System.arraycopy(in, 0, input, 0, in.length);
			input[input.length - 1] = 1;
			int offs = 0;
			Arrays.fill(output, 0);
			
			for (int i = 0; i < output.length; i += 1) {
				for (int j = 0; j < input.length; j += 1) {
					output[i] += weights[offs + j] * input[j];
				}
				if (isSigmoid) {
					output[i] = (1 / (1 + Math.exp(-output[i])));
				}
				offs += input.length;
			}
			
			return Arrays.copyOf(output, output.length);
		}
		
		public double[] train(double[] error, double learningRate, double momentum) {
			int offs = 0;
			double[] nextError = new double[input.length];
			for (int i = 0; i < output.length; i++) {
				double d = error[i];
				if (isSigmoid) {
					d *= output[i] * (1 - output[i]);
				}
				
				for (int j = 0; j < input.length; j++) {
					int idx = offs + j;
					nextError[j] += weights[idx] * d;
					double dw = input[j] * d * learningRate;
					weights[idx] += dweights[idx] * momentum * dw;
					dweights[idx] = dw;
				}
				offs += input.length;
				
			}
			return nextError;
			
		}
		
	}
	
	MLPLayer[] layers;
	
	public MLP(int inputSize, int[] layersSize) {
		layers = new MLPLayer[layersSize.length];
		
		Random r= new Random(1234);
		for (int i = 0; i < layersSize.length; i++) {
			int insize = i == 0 ? inputSize : layersSize[i - 1];
			layers[i] = new MLPLayer(insize, layersSize[i], r);
		}
	}
	
	public MLPLayer getLayer(int idx) {
		return layers[idx];
	}
	
	public double[] run(double[] input) {
		double [] actIn = input;
		for (int i = 0; i < layers.length; i++) {
			actIn = layers[i].run(actIn);
		}
		return actIn;
	}
	
	public void train(double[] input, double[] targetOutput, double learningRate, double momentum) {
		double[] calcOut = run(input);
		double[] error = new double[calcOut.length];
		
		for (int i = 0; i < error.length; i++) {
			error[i] = targetOutput[i] - calcOut[i];
			
		}
		
		for (int i = layers.length - 1; i >= 0; i--) {
			error = layers[i].train(error, learningRate, momentum);
			
		}
	}

	public static void main(String[] args) {
		double[][] train = {{0,0}, {0,1}, {1,0}, {1}}; 
		double[][] res = {{0}, {1}, {1}, {0}};
		
		MLP mlp = new MLP(2, new int[] {2,1});
		mlp.getLayer(1).setIsSigmod(false);
		Random r = new Random();
		int en = 500;
		for (int e = 0; e < en; e++) {
			for (int i = 0; i < res.length; i++) {
				int idx = r.nextInt(res.length);
				mlp.train(train[idx], res[idx], 0.3,  0.6);
				
			}
			
			if ((e + 1) % 100 == 0) {
				System.out.println();
				for (int i = 0; i < res.length; i++) {
					double[] t = train[i];
					System.out.printf("%d epoch\n", e + 1);
					System.out.printf("%.1f, %.1f -- > %.3f\n", t[0], t[1], mlp.run(t)[0]);
					
				}
			}
			
		}

	}

}
