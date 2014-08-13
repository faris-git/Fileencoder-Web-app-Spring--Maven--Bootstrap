package com.fileencoder.app.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * The FileEncoder object with all the fields used in this app
 * 
 * @author faris
 *
 */
public class FileEncoder {
	
	private CommonsMultipartFile file;
	private String encodingAlgorithm;
	private String path;
	
	/**
	 * Constructor with all fields
	 * @param file
	 * @param encodingAlgorithm
	 * @param path
	 */
	public FileEncoder(CommonsMultipartFile file, String encodingAlgorithm, String path) {
		this.file = file;
		this.encodingAlgorithm = encodingAlgorithm;
		this.path = path;
	}
	
	/**
	 * Constructor for algorithm and path
	 * @param encodingAlgorithm
	 * @param path
	 */
	public FileEncoder(String encodingAlgorithm, String path) {
		this.encodingAlgorithm = encodingAlgorithm;
		this.path = path;
	}
	
	/**
	 * Default constructor
	 */
	public FileEncoder() {}
	
	/**
	 * @return the file
	 */
	public CommonsMultipartFile getFile() {
		return file;
	}
	
	/**
	 * @param file the file to set
	 */
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	/**
	 * @return the encryptionAlgorithm
	 */
	public String getEncodingAlgorithm() {
		return encodingAlgorithm;
	}
	
	/**
	 * @param encodingAlgorithm the encodingAlgorithm to set
	 */
	public void setEncodingAlgorithm(String encodingAlgorithm) {
		this.encodingAlgorithm = encodingAlgorithm;
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
}
