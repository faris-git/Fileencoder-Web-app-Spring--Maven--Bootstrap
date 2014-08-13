package com.fileencoder.app.encoder;

public class Replacer implements EncodingAlgorithm {

	private char oldChar;
	private char newChar;	


	public Replacer(char oldChar, char newChar) {
		this.oldChar = oldChar;
		this.newChar = newChar;
	}

	public String encode(String input) {
		return input.replace(oldChar, newChar);
	}
}
