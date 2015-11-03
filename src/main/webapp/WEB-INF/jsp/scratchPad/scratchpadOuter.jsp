<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<tilesx:useAttribute id="sps" name="sps"/>
<div class="scratchpad" id="scratchpad">

	<p>Scratchpad</p>
	
	<div class="panel-group panel-group-scratchpad" id="panel-group-scratchpad">
	
		<div class="scratchpad-toggle"></div>

		<c:forEach var="sp" items="${sps}">
			<tiles:insertAttribute value="${sp}" flush="true" />
		</c:forEach>
	
	</div>
	
</div>