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

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/clip-font/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newTheme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/JLABlue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main-responsive.css">
	
	<title>DAS Acumen</title>

	<body onload='document.f.j_username.focus();'>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${pageContext.request.contextPath}/das/splash/">
						DasAcumen
					</a>
				</div>
			</div>
		</div>
		<div class="login-landing-screen">
			<div class="login-headers">
				<h1 class="login-header-text">Asset Optimisation Solutions</h1>
			</div>
			<div class="login-landing-container">
				<div class="panel pad-top panel-default login-holder">
					<p class="logout-text no-margin">
						Account Created Successfully.
					</p>
					<hr>
					<c:if test="${param.error !=null}">
						<p class="error">
							Creation of account failed.
						</p>
					</c:if>
					<div class="list-group">
						<div class="no-border userField list-group-item">
							<p>
								<a class="btn btn-success btn-block" href="<c:url value="/das/newAccount"/>">
									Create Another Account
								</a>
							</p>
							<p>
								<a class="btn btn-danger btn-block" href="<c:url value="/das/login"/>">
									Return To Login Screen
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
	</body>
</html>