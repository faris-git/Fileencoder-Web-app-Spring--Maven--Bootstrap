package com.fileencoder.app.encoder;

public class Rotator implements EncodingAlgorithm {
	
	private int step;

	public Rotator(int step) {
		this.step = step;
	}

	public String encode(String input) {
		String result = "";
		for (char c : input.toCharArray()) {
			result += rotateChar(c);
		}
		return result;
	}
	
	private char rotateChar(char c) {
		if (c >= 'a' && c <= 'z') {
			return rotateCharAroundBasis(c, 'a', 26);
		}
        if (c >= 'A' && c <= 'Z') {
        	return rotateCharAroundBasis(c, 'A', 26);
        }
        return c;
	}
	
	private char rotateCharAroundBasis(char oldChar, char rotationBasis, int rotationWidth) {
		int oldDiffToBasis = oldChar - rotationBasis;
		int newDiffToBasis = (oldDiffToBasis + step) % rotationWidth;
		return (char) (rotationBasis + newDiffToBasis);
	}
}
