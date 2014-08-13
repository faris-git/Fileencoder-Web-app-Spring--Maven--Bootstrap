package com.fileencoder.app.encoder;


public class Reverser implements EncodingAlgorithm {

	public Reverser() {	}

	public String encode(String input) {
		return new StringBuffer(input).reverse().toString();
	}

}
