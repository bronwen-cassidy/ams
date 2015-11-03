<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">

	<div class="form-panel panel panel-default">
		<div class="panel-heading">
			<i class="fa fa-square-o"></i>
			<tilesx:useAttribute id="searchTitle" name="screenTitle" />
			<spring:message code="${searchTitle}"/>
		</div>
		<div class="panel-body inner-scroll">
			<div class="tabbable panel-tabs panel-body">
			    <tiles:insertAttribute name="searchContent"/>
		    </div>
	    </div>
    </div>
</div>