<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 
 File encoder webapp decorator default file for the tiles framework sitemesh
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html lang="en">
	<head>
	    
	    <meta charset="utf-8">
		
	    <title><decorator:title/></title>
		
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/bootstrap/2.2.2/css/bootstrap.min.css" media="all"/>
	
	    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	    <!--[if lt IE 9]>
	    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	    <![endif]-->
	
	    <decorator:head/>
	
	    <style>
	        body {
	            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
	        }
	    </style>
	</head>
	<body>
	
		<div class="navbar navbar-inverse navbar-fixed-top">
		    <div class="navbar-inner">
		        <div class="container">
		            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		            </a>
		            <a class="brand" href="#"> File Encoder App</a>
		
		            <div class="nav-collapse collapse">
		                <ul class="nav">
		                    <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
		                </ul>
		            </div>
		            <!--/.nav-collapse -->
		        </div>
		    </div>
		</div>
	
		<div class="container">
		
		    <c:if test="${page_error != null }">
		        <div class="alert alert-error">
		            <button type="button" class="close" data-dismiss="alert">&times;</button>
		            <h4>Error!</h4>
		                ${page_error}
		        </div>
		    </c:if>
		
		    <decorator:body/>
		
		    <footer>
				<p style="font-size:10px;color:'#010101';">Developed by farisabdulla@gmail.com</p>
		    </footer>
		</div>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/2.2.2/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileencoder.js"></script>
		
	</body>
</html>