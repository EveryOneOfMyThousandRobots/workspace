package com.samrae;

import java.util.HashMap;

public final class Config {
	private HashMap<String, String> config = new HashMap<String, String>();
	
	HashMap<String, String> getConfig() {
		return config;
	}
	
	void put(String key, String val) {
		config.put(key, val);
	}

}
