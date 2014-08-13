<!-- 
	File Encoder web app index page
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.fileencoder.app.util.FileEncoderConstants" %>

<html>
	<head>
	    <title>File Encoder App - Encrypt file</title>
	</head>
	<body>
	
		<h1>Encode your file!</h1>
		
		<form:form method="post" modelAttribute="fileEncoder" action="create" id="fileEncoderForm" cssClass="form-horizontal"
		           autocomplete="on" enctype="multipart/form-data">
		    <fieldset>
		        <legend>Enter Your Information</legend>
				
		        <div class="control-group">
		            <label class="control-label" for="field-file">Choose a file</label>
		
		            <div class="controls">
		                <form:input type="file" name="file" path="file" id="field-file" tabindex="1" maxlength="45" placeholder="File"/>
		                <form:errors path="file" cssClass="help-inline" element="span"/>
		            </div>
		        </div>
		       	
		        <div class="control-group">
		            <label class="control-label" for="field-encryptionAlgorithm">Select an Encoding Algorithm</label>
		            <div class="controls">
		                <form:select path="encodingAlgorithm" id="field-encodingAlgorithm" tabindex="2" maxlength="35">
		                	<form:option value="">Please select</form:option>
		                	<% for (String algorithm : FileEncoderConstants.ENCODING_ALGORITHMS) { %>
		                		<form:option value="<%= algorithm %>"><%= algorithm %></form:option>
		                	<% } %>
		                </form:select>
		                <form:errors path="encodingAlgorithm" cssClass="help-inline" element="span"/>
		            </div>
		        </div>
		       	
		        <div class="control-group">
		            <label class="control-label" for="field-path">Choose a path</label>
		            <div class="controls">
		                <form:input path="path" name="path" id="field-path" tabindex="3" />
		                <span class="help-block">Please give your local directory path for saving the encoded file.</span>
		            </div>
		        </div>
		        <div class="form-actions">
		            <button type="submit" class="btn btn-primary">Generate</button>
		            <button type="reset" class="btn">Reset</button>
		        </div>
		    </fieldset>
		</form:form>
	
	</body>
</html>
