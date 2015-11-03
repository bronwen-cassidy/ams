<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tilesx:useAttribute id="scratchpad_id" name="id"/>
<tilesx:useAttribute id="scratchpad_name" name="name"/>
<tilesx:useAttribute id="scratchpad_url" name="scratchpad_url"/> 
<tilesx:useAttribute id="parentAccordian" name="parentAccordian" ignore="true"/> 
<c:if test="${parentAccordian eq null}">
	<c:set var="parentAccordian" value="panel-group-scratchpad"/>
</c:if>
<div class="panel panel-default" data-scratchpad-url="${scratchpad_url}">	
	<div class="panel-heading scratchpad-header collapsed" data-toggle="collapse" data-parent="#${parentAccordian}" href="#${scratchpad_id}_accordian">
		<h4 class="panel-title">
			<spring:message code="${scratchpad_name}"/>
		</h4>
	</div>
	

	<div id="${scratchpad_id}_accordian" class="scratchpad-body panel-collapse collapse">
		<ul class="panel-body search-panel-body">
			
		</ul>
	</div>
</div>