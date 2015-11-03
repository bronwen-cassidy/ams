<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
<input type="hidden" class="total-results" value="${searchTotalCount}" />
	<div class="clearfix">
		<div class="col-sm-6 pull-right no-padding">
		    <a href="${pageContext.request.contextPath}/das/assetController" class="btn btn-default pull-right"><spring:message code="assetRegister.asset.createNewAsset"/></a>
			<a href="${pageContext.request.contextPath}/das/assetSearch/full" class="btn btn-default pull-right margin-right"><spring:message code="assetRegister.asset.search"/></a>
		</div>
	</div>
	
	<div class="clearfix margin-top asset-table">
	<table class="table-container table-header-bar context-opener table multi-table table-hover" data-type="ASSETS">
	
		<thead>
			<tr class="alternateRow">
				<th><input type="checkbox" /></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.assetNumber"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.name"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.custodian"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.supplier"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.criticalAsset"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.lifeExpectancy"/></th>
				<th></th>
			</tr>
		</thead>
		
		<c:if test="${!empty draftList}">
		<tbody class="scroll-content">
			<c:forEach items="${draftList}" var="draft">
			<tr class="bucketData" data-bucket-type="${draft.modelType}" data-bucket-id="${draft.draftId}" data-draft-id="${draft.draftId}" >       
				<th class="checkbox-holder-size"><input type="checkbox" /></th>   
				<td>
					<a href="${pageContext.request.contextPath}/das/assetController?draftId=${draft.draftId}">
						<c:choose>
							<c:when test="${not empty draft.assetNumber}">${draft.assetNumber}</c:when>
							<c:otherwise>draft asset</c:otherwise>
						</c:choose>
						${draft.assetNumber}
					</a>
				</td>
				<td>${draft.name}</td>
				<td>${draft.custodian.name}</td> 
				<td>${draft.supplier.name}</td>
				<td>${draft.criticalAsset}</td> 
				<td>${draft.lifeExpectancy}</td>
			</tr>
			</c:forEach>	
		</tbody>
		
	  	<tfoot>
			<tr>
				<td colspan="9" class="load-more-assets text-center">
					<span class="caret pull-left"></span> 
					Load more results 
					<span class="caret pull-right"></span>
				</td>
			</tr>	
		</tfoot>
		</c:if>
		
	</table>
	</div>

</div>