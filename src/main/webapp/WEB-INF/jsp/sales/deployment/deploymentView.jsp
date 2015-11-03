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


	<form:form action="${pageContext.request.contextPath}/das/deployment/deploymentConfirm" modelAttribute="assetProposal" role="form" method="post">

		<div class="clearfix margin-top asset-table">
			<table class="table-header-bar context-opener table multi-table table-hover" data-type="ASSETS">
				<thead class="col-sm-12">
					<tr>
						<th class="col-sm-3" style="text-align:center; width:25vw;"><spring:message code="sales.assetDeployment.assetName" /></th>
						<th class="col-sm-3" style="text-align:center; width:25vw;"><spring:message code="sales.assetDeployment.location" /></th>
						<th class="col-sm-3" style="text-align:center; width:25vw;"><spring:message code="sales.assetDeployment.customer" /></th>
						<th class="col-sm-3" style="text-align:center; width:25vw;"><spring:message code="sales.assetDeployment.deliveryDate" /></th>
					</tr>
		
				</thead>
		
			</table>
			
			<table class="table-container context-opener table multi-table table-hover scroll-content" data-type="ASSETPROPOSAL">
				<c:if test="${!empty assetProposal}"> 
					<tbody>
							<tr style="vh:10"> 
								<td class="col-sm-3" style="text-align:center;width:25vw; ">${assetProposal.asset.name}</td>
								<td class="col-sm-3" style="text-align:center;width:25vw; overflow:auto;">${assetProposal.address.addressLine1} ${assetProposal.address.addressLine2} <br> ${assetProposal.address.addressLine3}<br> ${assetProposal.address.city}<br> ${assetProposal.address.zipPostCode} <br>${assetProposal.address.country} </td>
								<td class="col-sm-3" style="text-align:center;width:25vw;">${assetProposal.company.name}</td>
								<td class="col-sm-3" style="text-align:center;width:25vw;"><div>
									<label for="DATE_ASSET_DEPLOYED"><spring:message
											code="sales.assetDeployment.dateDeployed" /></label>
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-calendar"></i>
										</span>
										<form:input id="DATE_ASSET_DEPLOYED" path="assetSchedule.dateCollectedDeployed"
											name="DATE_ASSET_DEPLOYED"
											class="form-control date-picker hasDatepicker" type="text" />
									</div>
								</div></td>
							</tr> 
					                   
					</tbody>
				</c:if>
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
   
   <form:form  action="${pageContext.request.contextPath}/das/deployment/deploymentCollection" modelAttribute="assetProposal" role="form" method="post">
   		<div class="clearfix margin-top asset-table">
			<table class=" table-header-bar context-opener table multi-table table-hover" data-type="ASSETS">
				<thead>
					<tr class="alternateRow">
						<th><input type="checkbox" /></th>
						<th class="col-sm-3" style="text-align:center; width:33vw;"><spring:message code="sales.assetDeployment.assetName" /></th>
						<th class="col-sm-3" style="text-align:center; width:33vw;"><spring:message code="sales.assetDeployment.customer" /></th>
						<th class="col-sm-3" style="text-align:center; width:33vw;"><spring:message code="sales.assetDeployment.collectionDate" /></th>
					</tr>
				</thead>
			</table>
			
			<table class="table-container context-opener table multi-table table-hover scroll-content" data-type="ASSETPROPOSAL">
				<c:if test="${!empty assetProposal}">
					<tbody>
							<tr> 
								<th class="checkbox-holder-size"><input type="checkbox" /></th>   
								<td class="col-sm-3" style="text-align:center; width:25vw;">${assetProposal.asset.name}</td>
								<td class="col-sm-3" style="text-align:center; width:25vw;">${assetProposal.company.name}</td>
								<td class="col-sm-3" style="text-align:center; width:25vw;"><div>
									<label for="DATE_ASSET_COLLECTED"><spring:message
											code="sales.assetDeployment.collectionDate" /></label>
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-calendar"></i>
										</span>
										<form:input id="DATE_ASSET_COLLECTED" path="assetSchedule.dateCollectedDeployed"
											name="DATE_ASSET_COLLECTED"
											class="form-control date-picker hasDatepicker" type="text" />
									</div>
								</div></td>
							</tr>  
						    
					</tbody>
				</c:if>
				
			</table>
		
			<div class=" pull-right">
				<input class="btn no-radius btn-success margin-top" type="submit" value="Collected">	
			</div>
		
		</div>
	</form:form>
</div>