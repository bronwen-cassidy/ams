<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags" %>

<c:if test="${!empty assets}">
	<thead>
		<tr class="alternateRow">
		<th><input type="checkbox" onclick="checkCheckbox();"/></th>
			<th>Name</th>
			<th>Category</th>
			<th>Status</th>
		</tr>
	</thead>
		
	<tbody>
	
			 
		
		<c:forEach items="${assets}" var="assetAvailability">
		<tr class="bucketData" data-asset-id="${assetAvailability.id}" data-client-id="${clientId}" data-from-date="${fromDate}" data-to-date="${toDate}">
		<th class="checkbox-holder-size"><input type="checkbox"/></th> 
		<td>
				<strong>${assetAvailability.name}</strong> 
		</td>
		<td>${assetAvailability.category.name}</td>
		
		<c:choose>
			<c:when test="${not empty unavailable}">
				<td>Unavailable</td>
			</c:when>
			<c:otherwise>
				<td>Available</td>
			</c:otherwise>
		</c:choose>
			
		</tr>  
	          
		</c:forEach>	
	</tbody>

</c:if>
