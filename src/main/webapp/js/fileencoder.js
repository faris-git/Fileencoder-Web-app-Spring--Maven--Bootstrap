/**
 * This is custom js file used for FileEncoderApp
 * @ author: Faris Abdulla
 */
$(document).ready(function() {
	
	// prepare Options before submitting 
	var options = { 
	    target: '#fileEncoderForm', 
	    url: $("#fileEncoderForm").attr( "action"),
	    beforeSubmit: function() {
	    	$('.alert').remove();
	    },
	    success: function(data) {
	    	var successDiv = $("<p class='alert alert-success'>Successfully encoded the given file..</p> " +
	    			"<p class='alert' id='download-link'>Please click here to download the encoded file: " +
	    			"<a href='/fileencoder/download?path="+data.path+"'>Download</a></p>" +
	    					"<p><a href='/fileencoder/'>&laquo; Back</a></p>");
	    	$('#fileEncoderForm').before(successDiv);
	    }, 
	    error: function(exception) {
	    	var errorMessage = JSON.parse(exception.responseText);
	    	$('#fileEncoderForm').before("<p class='alert alert-error'>"+errorMessage.error+"</p>");
	    }
	}; 
	 
	// submit the form 
	$('#fileEncoderForm').ajaxForm(options);
	
});