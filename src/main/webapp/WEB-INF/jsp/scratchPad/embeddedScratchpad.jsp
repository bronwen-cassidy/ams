<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tilesx:useAttribute id="scratchpad_id" name="id"/>
<tilesx:useAttribute id="scratchpad_name" name="name"/>

<div class="panel panel-default multi-level">
	<div class="panel-heading scratchpad-header collapsed" data-toggle="collapse" data-parent="#panel-group-scratchpad" href="#${scratchpad_id}_accordian">
		<h4 class="panel-title">
			<spring:message code="${scratchpad_name}"/>
		</h4>
	</div>

	<div id="${scratchpad_id}_accordian" class="scratchpad-body panel-collapse collapse">
	<tilesx:useAttribute id="innerSPs" name="innerScratchpads"/> 
		<c:forEach items="${innerSPs}" var="innerSP">
			<tiles:insertAttribute value="${innerSP}">
				<tiles:putAttribute name="parentAccordian" value="${scratchpad_id}_accordian"/>
			</tiles:insertAttribute>
		</c:forEach>
	</div>
</div>
