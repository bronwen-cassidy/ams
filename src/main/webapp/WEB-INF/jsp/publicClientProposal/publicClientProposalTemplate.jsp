<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/clip-one/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/clip-one/main-responsive.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/iCheck/skins/all.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/datepicker/css/datepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-colorpicker/css/bootstrap-colorpicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-fileupload/bootstrap-fileupload.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-timepicker/css/bootstrap-timepicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-modal/css/bootstrap-modal.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-modal/css/bootstrap-modal-bs3patch.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fonts/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fonts/clip-font/style.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/newTheme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/JLABlue.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

<!-- jQuery -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="${pageContext.request.contextPath}/js/vendor/jquery/jquery-1.10.1.min.js"><\/script>');
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/vendor/jquery/jquery-ui-1.10.4.custom.js"></script>

<title></title>
</head>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">

		<div class="navbar-header">
			<a class="navbar-brand"> Aspect </a>

		</div>
	</div>
</div>

<tiles:insertAttribute name="body" />

<script>
	var base_url = "${pageContext.request.contextPath}";
</script>
<!-- Bootstrap -->
<script
	src="${pageContext.request.contextPath}/js/vendor/bootstrap/bootstrap.min.js"></script>
<!-- Clip One -->
<script src="${pageContext.request.contextPath}/js/clip-one/main.js"></script>
<script src="${pageContext.request.contextPath}/js/clip-one/scripts.js"></script>
<!-- Plugins -->
<script
	src="${pageContext.request.contextPath}/js/plugins/iCheck/jquery.icheck.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/flotcharts/jquery.flot.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/flotcharts/jquery.flot.resize.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/flotcharts/jquery.flot.pie.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-daterangepicker/moment.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-daterangepicker/daterangepicker.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-modal/js/bootstrap-modal.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap/bootstrap-modal/js/bootstrap-modalmanager.js"></script>
<!-- Scripts -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/charts.js"></script>
<script src="${pageContext.request.contextPath}/js/scratchpad.js"></script>
<script
	src="${pageContext.request.contextPath}/js/assetregisterlandingscreen.js"></script>
<script src="${pageContext.request.contextPath}/js/tenures/tenures.js"></script>
<script src="${pageContext.request.contextPath}/js/sales.js"></script>
</html>