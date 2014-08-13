package com.fileencoder.app.encoder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fileencoder.app.exception.FileEncoderException;
import com.fileencoder.app.model.FileEncoder;
import com.fileencoder.app.util.FileEncoderUtil;

public class Encoder {

	private List<EncodingAlgorithm> algorithms;
	BufferedReader reader;
	PrintWriter printer;

	/**
	 * Constructor
	 * @param in
	 * @param out
	 * @param algorithms
	 */
	public Encoder(InputStream in, OutputStream out,
			List<EncodingAlgorithm> algorithms) {
		this.reader = new BufferedReader(new InputStreamReader(in));
		this.printer = new PrintWriter(new OutputStreamWriter(out));
		this.algorithms = algorithms;
	}

	/**
	 * Constructor
	 * @param in
	 * @param out
	 * @param algorithm
	 */
	public Encoder(InputStream in, OutputStream out, EncodingAlgorithm algorithm) {
		this(in, out, createSingleAlgorithmList(algorithm));
	}

	/**
	 * Constructor 
	 * @param readFile
	 * @param writeFile
	 * @param algorithm
	 */
	public Encoder(String readFile, String writeFile, EncodingAlgorithm algorithm) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(readFile)));
			this.printer = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(writeFile)));
			this.algorithms = createSingleAlgorithmList(algorithm);

		} catch (FileNotFoundException e) {
			_log.error("File not found!!", e);
			throw new FileEncoderException("The file not found!!");
		}
	}

	/**
	 * Constructor with FileEncoder object
	 * @param fileEncoder
	 */
	public Encoder(FileEncoder fileEncoder) {

		try {
			this.reader = new BufferedReader(new InputStreamReader(fileEncoder.getFile().getInputStream()));
			this.printer = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(fileEncoder.getPath())));
			this.algorithms = createSingleAlgorithmList(
					FileEncoderUtil.findRightAlgorithm(fileEncoder.getEncodingAlgorithm()));
			
		} catch (FileNotFoundException e) {
			_log.error("File not found!!", e);
			throw new FileEncoderException("The file not found!!");
		} catch (IOException ioe) {
			_log.error("Input Output Exception !", ioe);
			throw new FileEncoderException("The Input output exception!!");
		}

	}
	
	private static List<EncodingAlgorithm> createSingleAlgorithmList(
			EncodingAlgorithm algorithm) {
		List<EncodingAlgorithm> algorithms = new ArrayList<EncodingAlgorithm>(1);
		algorithms.add(algorithm);
		return algorithms;
	}

	public void doEncode() throws IOException {
		while (true) {
			String line = reader.readLine();
			if (line == null)
				return;
			printer.println(encodeLine(line));
			printer.flush();
		}
	}

	private String encodeLine(String line) {
		String output = line;
		
		for (EncodingAlgorithm algorithm : algorithms) {
			output = algorithm.encode(output);
		}
		return output;
	}

	private Logger _log = Logger.getLogger(Encoder.class);
}
