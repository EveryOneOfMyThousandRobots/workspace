package com.samrae;

import java.util.HashMap;

public final class ProcJava {
	public static double tanh(double x) {
		return Math.tanh(x);
	}
	
	public static double tan(double x) {
		return Math.tan(x);
	}
	
	public static double sin(double x) {
		return Math.sin(x);
	}
	
	public static double sinh(double x) {
		return Math.sinh(x);
	}
	
	public static double cos(double x) {
		return Math.cos(x);
	}
	
	public static double cosh(double x) {
		return Math.cosh(x);
	}
	
	public static HashMap<String,String> addtoHashMap(String key, String value, HashMap<String, String> map) {
		map.put(key, value);
		return map;
	}
	
	
}
