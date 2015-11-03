<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags" %>

<div class="tab-content">

	<label for="deploy" class="panel-title">
   		<spring:message code="sales.assetDeployment.deploy" />
   </label>
   <hr class="smallHr">


	<form:form>
		<div class="clearfix margin-top asset-table">
			<table class=" table-header-bar context-opener table multi-table table-hover" data-type="ASSETS">
				<thead>
					<tr class="alternateRow">
						<th><input type="checkbox" /></th>
						<th style="width:25%;"><spring:message code="sales.assetDeployment.assetName" /></th>
						<th style="width:25%;"><spring:message code="sales.assetDeployment.location" /></th>
						<th style="width:25%;"><spring:message code="sales.assetDeployment.customer" /></th>
						<th style="width:25%;"><spring:message code="sales.assetDeployment.deliveryDate" /></th>
					</tr>
		
				</thead>
		
			</table>
			
			<table class="table-container context-opener table multi-table table-hover scroll-content" data-type="ASSETPROPOSAL">
				<c:if test="${!empty proposalList}"> 
					<tbody>
							<tr> 
								<th class="checkbox-holder-size"><input type="checkbox" /></th>   
								<td style="width:25%;">${assetProposal.asset.name}</td>
								<td style="width:25%;">${assetProposal.address}</td>
								<td style="width:25%;">${assetProposal.person.forenames} ${assetProposal.person.surname}</td>
								<td style="width:25%;"></td>
							</tr> 
					</tbody>
				</c:if>
				<tfoot>
					<tr>
						<td colspan="9" class="load-more-assets text-center">
							<span class="caret pull-left"></span> 
								Load more results 
							<span class="caret pull-right"></span>
						</td>
					</tr>
				</tfoot>
			</table>
			<div class=" pull-right">
				<input class="btn no-radius btn-success margin-top" type="submit" value="Deploy">	
			</div>
		</div>
	</form:form>	

	
	<label for="collection" class="panel-title margin-top">
   		<spring:message code="sales.assetDeployment.collection" />
   </label>
   <hr class="smallHr">
   
   <form:form>
   		<div class="clearfix margin-top asset-table">
			<table class=" table-header-bar context-opener table multi-table table-hover" data-type="ASSETS">
				<thead>
					<tr class="alternateRow">
						<th><input type="checkbox" /></th>
						<th style="width:33%;"><spring:message code="sales.assetDeployment.assetName" /></th>
						<th style="width:33%;"><spring:message code="sales.assetDeployment.customer" /></th>
						<th style="width:33%;"><spring:message code="sales.assetDeployment.collectionDate" /></th>
					</tr>
				</thead>
			</table>
			
			<table class="table-container context-opener table multi-table table-hover scroll-content" data-type="ASSETPROPOSAL">
				<c:if test="${!empty proposalList}">
					<tbody>
							<tr> 
								<th class="checkbox-holder-size"><input type="checkbox" /></th>   
								<td style="width:33%;">${deploymentProposal.asset.name}</td>
								<td style="width:33%;">${deploymentProposal.person.forenames} ${deploymentProposal.person.surname}</td>
								<td style="width:33%;"></td>
							</tr>  
					</tbody>
				</c:if>
				<tfoot>
					<tr>
						<td colspan="9" class="load-more-assets text-center">
							<span class="caret pull-left"></span> 
								Load more results 
							<span class="caret pull-right"></span>
						</td>
					</tr>
				</tfoot>
			</table>
		
			<div class=" pull-right">
				<input class="btn no-radius btn-success margin-top" type="submit" value="Collected">	
			</div>
		
		</div>
	</form:form>
</div>