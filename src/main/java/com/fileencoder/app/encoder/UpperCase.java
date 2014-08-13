package com.fileencoder.app.encoder;

public class UpperCase implements EncodingAlgorithm {

	public UpperCase() {
	}
	
	public String encode(String input) {
		return input.toUpperCase();
	}
}
