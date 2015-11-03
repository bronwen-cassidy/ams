<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags" %>

<c:if test="${!empty availableAssets}">
	<thead>
		<tr class="alternateRow" data-scratchpad-trigger-id="client">
		<th><input type="checkbox"/></th>
			<th>Name</th>
			<th>Order</th>
		</tr>
	</thead>
		
	<tbody>
		<c:forEach items="${notAvailableAssets}" var="asset">
		<tr>
		<th class="checkbox-holder-size"><input type="checkbox"/></th> 
		
			<td>
				<a href="TODO - go to new proposal controller!">
					<strong>AVAILABLE!</strong> ${asset.assetNumber}
				</a>
			</td>

			<td>${asset.name}</td>
		</tr>  
	          
		</c:forEach>	
	</tbody>

</c:if>