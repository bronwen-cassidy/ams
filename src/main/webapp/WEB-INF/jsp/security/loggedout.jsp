<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html class="no-js">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main-responsive.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/clip-font/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newTheme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/JLABlue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
	<title>Aspect</title>

	<body onload='document.f.j_username.focus();'>
	
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/das/splash/">
					Aspect
				</a>
			</div>
		</div>
	</div>
	<div class="login-landing-screen">
	
		<div class="login-headers">
			<h1 class="login-header-text">
				Asset Optimisation Solutions
			</h1>
		</div>
	
		<div class="login-landing-container">
			<div class="panel panel-default login-holder">
				<div class="list-group">
					<div class="no-border logout-text userField list-group-item">
					Successfully logged out.
					<hr>
						<p>
							<a class="margin-top btn btn-success btn-block" href="${pageContext.request.contextPath}/das/splash/">
								Return To Login Page
							</a>
						</p>
					</div>		
				</div>
			</div>
		</div>
	</div>
	
	<!-- jQuery -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/js/vendor/jquery/jquery-1.10.1.min.js"><\/script>');</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery/jquery-ui-1.10.4.custom.js"></script>
		
	<!-- Plugins -->
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/js/vendor/bootstrap/bootstrap.min.js"></script>
	
	</body>
</html>