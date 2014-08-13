package com.fileencoder.app.encoder;

public class LowerCase implements EncodingAlgorithm {

	public LowerCase() {
	}
	
	public String encode(String input) {
		return input.toLowerCase();
	}
}
