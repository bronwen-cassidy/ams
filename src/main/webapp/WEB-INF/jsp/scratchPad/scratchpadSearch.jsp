<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tilesx:useAttribute id="scratchpad_id" name="id"/>
<tilesx:useAttribute id="scratchpad_name" name="name"/>
<tilesx:useAttribute id="scratchpad_search_url" name="search_url"/> 
<tilesx:useAttribute id="parentAccordian" name="parentAccordian" ignore="true"/> 
<c:if test="${parentAccordian eq null}">
	<c:set var="parentAccordian" value="panel-group-scratchpad"/>
</c:if>

<div class="panel panel-default" data-scratchpad-url="${scratchpad_search_url}">	
	<div class="panel-heading scratchpad-header collapsed" data-toggle="collapse" data-parent="#${parentAccordian}" href="#${scratchpad_id}_accordian">
		<h4 class="panel-title">
			<spring:message code="${scratchpad_name}"/>
		</h4>
	</div>

	<div id="${scratchpad_id}_accordian" class="scratchpad-body panel-collapse collapse">
		<input id="${scratchpad_id}_sp_search_input"  type="search" name="assetSearch" class="form-control scratchpad-search" placeholder="search..."/>
		<ul class="panel-body list-group search-panel-body">
		</ul>
		<div class="scratchpad-pagination">
			<a class="prev disabled"><i class="fa fa-arrow-circle-left"></i> Prev 10</a>
			<a class="next">Next 10 <i class="fa fa-arrow-circle-right"></i></a>
		</div>
	</div>
</div>