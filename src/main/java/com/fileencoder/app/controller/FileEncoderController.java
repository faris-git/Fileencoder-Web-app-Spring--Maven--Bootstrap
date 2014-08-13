package com.fileencoder.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fileencoder.app.encoder.Encoder;
import com.fileencoder.app.exception.FileEncoderException;
import com.fileencoder.app.model.FileEncoder;
import com.fileencoder.app.util.JsonError;

/**
 * The controller which is registered with spring
 * All the url is defined here
 * 
 * @author faris
 *
 */
@Controller
public class FileEncoderController {
	
	/**
	 * The default landing page redirection to index.jsp
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index", "fileEncoder", new FileEncoder(null, System.getProperty("user.dir")));
	}
	
	/**
	 * Submit the form for generating the encoded file
	 * @param model
	 * @param fileEncoder
	 * @param result
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers="Accept=*/*", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String create(Model model, FileEncoder fileEncoder, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				_log.error("Error:: " + error.getCode());
			}
			
			throw new FileEncoderException("The submitted form has errors!! Please fill the form..");
		}
		
		if (fileEncoder.getFile() == null 
				|| fileEncoder.getFile().getOriginalFilename().isEmpty())
			throw new FileEncoderException("Please choose a text file for encoding!!");
		
		if (fileEncoder.getEncodingAlgorithm().isEmpty())
			throw new FileEncoderException("Please select an algorithm!");
		
		if (fileEncoder.getPath().isEmpty())
			throw new FileEncoderException("Please choose a path of current system for saving the encoded file!");
		
		if (fileEncoder.getFile().getOriginalFilename().endsWith(".txt")) {
			fileEncoder.setPath(fileEncoder.getPath() 
					+ File.separator + fileEncoder.getFile().getOriginalFilename());
			
			_log.info("------------------------------------------------------");
			_log.info("******** File : " + fileEncoder.getFile().getOriginalFilename());
			_log.info("*** Algorithm : " + fileEncoder.getEncodingAlgorithm());
			_log.info("******** Path : " + fileEncoder.getPath());
			
			_log.info("-----------------------Encoding-----------------------");
			Encoder encoder = new Encoder(fileEncoder);
			encoder.doEncode();
			
			_log.info("----------- Successfully the file is encoded!! -------");
			
			ObjectMapper mapper = new ObjectMapper();
			
			FileEncoder _result = new FileEncoder(
					null, fileEncoder.getEncodingAlgorithm(), 
					fileEncoder.getPath());
			
			return mapper.writeValueAsString(_result);
		} else 
			throw new FileEncoderException("The selected file should be .txt file!!");
			
	}
	
	/**
	 * Download the encoded file
	 * @param path
	 * @param response
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadFile(@RequestParam("path") String path, HttpServletResponse response) {
		System.out.println("Th e path:: "+path);
		
		if (path.isEmpty())
			throw new FileEncoderException("Please mention the local path to download the file");
		
		try {
			File file = new File(path);
			InputStream is = new FileInputStream(file);
			
			response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            
            _log.info("----------Downloading the file which is encoded!------");
            
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
		} catch(IOException ioe) {
			throw new RuntimeException("IO error while writing to output..");
		}
	}
	
	@ExceptionHandler (Exception.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllExceptions(Exception ex) {
        return new JsonError(ex.getMessage()).asModelAndView();
    }
	
	private static final Logger _log = Logger.getLogger(FileEncoderController.class);
}
