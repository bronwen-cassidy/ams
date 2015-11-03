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

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/clip-font/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newTheme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/JLABlue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
	<!-- jQuery -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/js/vendor/jquery/jquery-1.10.1.min.js"><\/script>');</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery/jquery-ui-1.10.4.custom.js"></script>
		

<title>Aspect</title>

<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main-responsive.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/newTheme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/JLABlue.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

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
				<div class="login-holder-header">
					<%-- <c:if test="${empty user}">
						<b class="no-user-error">There are no Users in the Database. Add one?</b>
					</c:if> --%>
					<a class="pull-right system-admin-position" href="${pageContext.request.contextPath}/das/systemAdmin">
						System Admin<i class="login-admin fa fa-cogs"></i>
					</a>
				</div>

				<c:if test="${param.error !=null}">
					<p class="error text-center">Login failed check that your user name and
						password are correct</p>
				</c:if>

				<div class="list-group">
					<div class="userField list-group-item">
						<form class="userField" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
							<div class="input-group margin-bottom">
								<label for="j_username" class="input-group-addon">
									<i class="fa fa-user"></i> 
								</label>
								<input id="j_username" type="text" class="form-control" name="j_username" title="User" placeholder="Username..." />
							</div>
							<div class="input-group">
								<label for="j_password" class="input-group-addon">
									<i class="login-lock fa fa-lock"></i>
								</label>
								<input id="j_password" type="text" class="form-control" name="j_password" title="Password" placeholder="Password..." />
							</div>
							<p class="red-error text-center">
								<input class="login-checkbox-margin-right" type="checkbox" name="_spring_security_remember_me" checked="checked" title="Remember Me">
								Keep me logged in.
							</p>
							<button name="btnLogin" type="submit" class="btn-block btn btn-success">Login</button>
						</form>
						<div class="max-width">
							<a class="margin-top text-center" href="<c:url value="/das/newAccount"/>">
								Create New Account
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
  	<!-- Plugins -->
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    
	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/js/vendor/bootstrap/bootstrap.min.js"></script>
	</body>
</html>