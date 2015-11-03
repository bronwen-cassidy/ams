<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>DAS Acumen - Print</title>

        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome/css/font-awesome.min.css">
	
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/print.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
	<body>
		<div class="print-container" id="print">
			
			<tiles:insertAttribute name="printContents"/>
		</div>          
	</body>
</html>