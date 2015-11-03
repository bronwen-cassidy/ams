<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html class="no-js">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>DAS Acumen</title>

        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clip-one/main-responsive.css">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        
	<body>
	
	<div class="landing-screen">
	
		<div class="landing-container">
	
			<img alt="DAS Acumen" src="${pageContext.request.contextPath}/images/das_logo.png">
			
			<c:if test="${empty users}">
			   <b>There are no Users in the Database. Add one?</b>
			</c:if>
			
			<div class="panel panel-default">
			
				<div class="panel-heading">Users</div>
				
				<div class="panel-body">
	
					<div class="list-group">
					<c:forEach items="${users}" var="item">
						<div class="userField list-group-item">
						<form class="userField" method="post" action="../ucp/login">
							<input type="hidden" name="userName" value="${item.userName}"/>
							<input type="hidden" name="surname" value="${item.surname}"/>
							<input type="hidden" name="id" value="${item.id}"/>
							<button name="btnDelete" type="submit" class="splash-buttons btn btn-success pull-right ">Login With '${item.userName}'</button>
						</form>
						<strong>${item.userName}  - ${item.forenames}  ${item.surname}</strong></div>
					</c:forEach>
						<div class="list-group-item">
							Add User
							<form method="post" action="${pageContext.request.contextPath}/das/splash/adduser">
								<div class="form-group">
									<input class="form-control" type="text" name="userName" placeholder="Username"/>
								</div>
								<div class="form-group">
									<input class="form-control" type="text" name="forenames" placeholder="Forename"/>
									<input class="form-control" type="text" name="surname" placeholder="Surname"/>
								</div>
								<button name="btnAddUser" type="submit" class="btn btn-success">Create User</button>
							</form>
						</div>
						<div class="list-group-item clearfix"><a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/das/systemAdmin">System Admin</a></div>			
					</div>		
				
				</div>
			
			</div>
		
		</div>
	
	</div>

	</body>
</html>