<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>

<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
//TODO: move to main.js file.
/*
 * Function called when user types password to call the check passwords mathc func.
 */
	function onLoad() {
		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpass").keyup(checkPasswordsMatch);
		
	}
	
	/*
	 * Function that will check that as a user types a password (or confirm password) of more than 3 chars it is equal to the
	 * confirm password and vice versa.
	 */
	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		
		if (password.length > 3 || confirm.length > 3) {
		
			if (password == confirmpass) {
				$("#matchpass").text("Passwords match");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");
			}
			else {
				$("#matchpass").text("Passwords do not match");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}
		}
	}

	$(document).ready(onLoad);
</script>

	<title>DAS Acumen New Account</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main-responsive.css">
      
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/clip-font/style.css">
      
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newTheme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/JLABlue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
	<body>
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
						<div class="col-sm-9 no-padding">
							<p class="pull-right login-header">
								Create New User Account
							</p>
						</div>
						<div class="col-sm-3 no-padding">
							<a class="pull-right system-admin-position" href="${pageContext.request.contextPath}/das/login">
								<i class="login-admin fa fa-angle-double-left"></i>
									Go Back
							</a>
						</div>
					</div>

					<div class="list-group">
						<div class="userField list-group-item">
							<form:form id="details" action="${pageContext.request.contextPath}/das/createAccount" method="POST" commandName="user">
								<c:if test="${not empty errors}">
									<form:errors title="Errors" path="*" cssClass="error"/> <!-- exception error handling! -->
								</c:if>
							
								<div class="input-group margin-bottom">
									<label for="userName" class="input-group-addon">
										<i class="fa fa-user"></i> 
									</label>
									<form:input type="text" class="form-control" id="userName" name="userName" path="userName" title="User" placeholder="Enter username..."/>
								</div>
								
								<div class="input-group margin-bottom">
									<label for="password" class="input-group-addon">
										<i class="login-lock fa fa-unlock-alt"></i> 
									</label>
									<form:input type="text"  class="form-control" id="password" name="password" path="password" title="Password" placeholder="Enter password..."/>
								</div>
								
								<div class="input-group">
									<label for="confirmpass" class="input-group-addon">
										<i class="login-lock fa fa-lock"></i> 
									</label>
									<form:input type="text" class="form-control" id="confirmpass" name="confirmPassword" path="confirmPassword" title="confirmPassword" placeholder="Confirm password..."/>
								</div>
								<div class="matchpass-holder">
									<div id="matchpass"></div>
								</div>
									<hr>
								<button name="btnCreate" type="submit" class="splash-buttons btn-block btn btn-success ">Create Account</button>
							</form:form>
						</div>		
					</div>
				</div>
			</div>
		</div>
	</body>
</html>