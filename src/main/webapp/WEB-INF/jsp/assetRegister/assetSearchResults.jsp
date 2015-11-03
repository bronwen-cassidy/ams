<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
  <input type="hidden" class="total-results" value="${searchTotalCount}" />
	<div class="clearfix">
		<div class="col-sm-3 pull-left no-padding">
		    <select class="asset-filter form-control">
		        <option disabled selected><spring:message code="placeholder.filterBy"/></option>
		        <option value="<spring:message code="assetRegister.asset.search.lifeExpectancy.ending"/>"><spring:message code="assetRegister.asset.search.lifeExpectancy.ending"/></option>
     		    <option value="<spring:message code="assetRegister.asset.search.lifeExpectancy.ceased"/>"><spring:message code="assetRegister.asset.search.lifeExpectancy.ceased"/></option>
		        <option value="<spring:message code="assetRegister.asset.search.critical"/>"><spring:message code="assetRegister.asset.search.critical"/></option>
		        <option value="<spring:message code="assetRegister.asset.search.createdBy"/>"><spring:message code="assetRegister.asset.search.createdBy"/></option>
		    </select>
		    
		</div>

		<div class="col-sm-6 pull-right no-padding">
		    <a href="${pageContext.request.contextPath}/das/assetController" class="btn btn-default pull-right"><spring:message code="assetRegister.asset.createNewAsset"/></a>
			<a href="${pageContext.request.contextPath}/das/assetSearch/full" class=" margin-right btn btn-default pull-right"><spring:message code="assetRegister.asset.search"/></a>
			<a href="${pageContext.request.contextPath}/das/assetAvailability/full" class=" margin-right btn btn-default pull-right">Asset Lease Order</a>
		</div>
	</div>
	<div class="clearfix margin-top asset-table">
	<table class=" table-header-bar context-opener table multi-table table-hover" data-type="ASSETS">
	
		<thead>
			<tr class="alternateRow">
				<th><input type="checkbox" /></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.assetNumber"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.name"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.custodian"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.supplier"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.criticalAsset"/></th>
				<th class="table-header-width"><spring:message code="assetRegister.asset.assetSearchResult.columnName.lifeExpectancy"/></th>		
			</tr>
		</thead>
		
	</table>
	<table class="table-container context-opener table multi-table table-hover scroll-content" data-type="ASSETS">
		<c:if test="${!empty assets}">
		
		<tbody>
			<c:forEach items="${assets}" var="asset">
			<tr class="bucketData" data-bucket-type="${asset.modelType}" data-bucket-id="${asset.id}" data-list-id="${entityList.id}" data-group-id="${group.id}" data-asset-id="${asset.id}" data-custodian-id="${asset.custodianId}" data-supplier-id="${asset.supplierId}"> 
				<th class="checkbox-holder-size"><input type="checkbox" /></th>   
				<td><a href="${pageContext.request.contextPath}/das/assetDetails?assetId=${asset.id}">${asset.assetNumber}</a></td>
				<td>${asset.name}</td>
				<td>${asset.custodian.name}</td> 
				<td>${asset.supplier.name}</td>
				<td>${asset.criticalAsset}</td> 
				<td>${asset.lifeExpectancy}</td> 
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