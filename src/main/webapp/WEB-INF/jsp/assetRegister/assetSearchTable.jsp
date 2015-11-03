<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:if test="${empty assets}">
	<td colspan="9" class="no-assets-found">No assets found</td>	
</c:if>
<input type="hidden" class="total-results" value="${searchTotalCount}" />
<c:forEach items="${assets}" var="assets">
<tr>      
	<th class="checkbox-holder-size"><input type="checkbox" /></th>         
	<td class="col-sm-2"><a href="${pageContext.request.contextPath}/das/assetDetails?assetId=${assets.id}">${assets.assetNumber}</a></td>
	<td class="col-sm-2">${assets.name}</td>
	<td class="col-sm-2">${assets.custodian.name}</td>
	<td class="col-sm-2">${assets.supplier.name}</td>
	<td class="col-sm-2">${assets.criticalAsset}</td> 
	<td class="col-sm-2">${assets.lifeExpectancy}</td> 
</tr>                         
</c:forEach>	