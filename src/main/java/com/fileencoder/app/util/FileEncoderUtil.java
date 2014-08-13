package com.fileencoder.app.util;

import java.util.ArrayList;
import java.util.List;

import com.fileencoder.app.encoder.EncodingAlgorithm;
import com.fileencoder.app.encoder.LowerCase;
import com.fileencoder.app.encoder.Replacer;
import com.fileencoder.app.encoder.Reverser;
import com.fileencoder.app.encoder.Rotator;
import com.fileencoder.app.encoder.UpperCase;
import com.fileencoder.app.exception.FileEncoderException;

/**
 * FileEncoderUtil: The class used for different operation like encoding, file
 * handling
 * 
 * @author faris
 * 
 */
public class FileEncoderUtil {
	
	/**
	 * From Encodingclasses
	 * @return
	 * @throws FileEncoderException
	 */
	public List<EncodingAlgorithm> getAlgorithms() throws FileEncoderException {
		List<EncodingAlgorithm> algorithms = new ArrayList<EncodingAlgorithm>();
		
		for(String param : FileEncoderConstants.ENCODING_ALGORITHMS) {
			algorithms.add(findRightAlgorithm(param));
		}
		
		return algorithms;
	}
	
	/**
	 * From Encodingclasses
	 * @param param
	 * @return
	 * @throws FileEncoderException
	 */
	public static EncodingAlgorithm findRightAlgorithm(String param) throws FileEncoderException {
		if (param.equals("low")) 
			return new LowerCase();
		if (param.equals("up"))
			return new UpperCase();
		if (param.equals("rev"))
			return new Reverser();
		if (param.startsWith("rot") )
			return parseRotatorParam(param);
		if (param.length() == 3 && param.charAt(1)=='=') 
			return new Replacer(param.charAt(0), param.charAt(2));

		throw new FileEncoderException("Unknown parameter: " + param);
	}
	
	/**
	 * From Encoding classes
	 * @param param
	 * @return
	 * @throws FileEncoderException
	 */
	private static Rotator parseRotatorParam(String param) throws FileEncoderException {
		String rotStepAsString = param.substring(3);
		try {
			return new Rotator(Integer.parseInt(rotStepAsString));
		} 
		catch (NumberFormatException ex) {
			throw new FileEncoderException("Unknown parameter: " + param);
		}
	}
	
}
