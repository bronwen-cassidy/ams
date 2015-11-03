<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<div class="tab-content">
		<div class="clearfix margin-top asset-table">
			<table class=" table-header-bar context-opener table multi-table table-hover asset-table" data-type="ASSETS">
				<thead>
					<tr class="alternateRow">
						<th><input type="checkbox" /></th>
						<th style="width:20%;"><spring:message code="sales.landingScreen.proposalId" /></th>
						<th style="width:20%;"><spring:message code="sales.landingScreen.client" /></th>
						<th style="width:20%;"><spring:message code="finance.invoice.totalToBill" /></th>
						<th style="width:20%;"><spring:message code="sales.createproposal.dateFrom" /></th>
						<th style="width:20%;"><spring:message code="sales.createproposal.dateTo" /></th>
					</tr>
				
				</thead>
			</table>
			<table class="table-container context-opener table multi-table table-hover scroll-content" data-type="ASSETPROPOSAL">
				<c:if test="${!empty proposalList}">
					<tbody>
						<c:forEach items="${proposalList}" var="assetProposal">
							<tr> 
								<th class="checkbox-holder-size"><input type="checkbox" /></th>   
								<td style="width:20%;"><a href="${pageContext.request.contextPath}/das/invoice/invoiceDetails?proposalId=${assetProposal.id}">${assetProposal.id}</a></td>
								<td style="width:20%;">${assetProposal.company.name}</td>
								<%--
						 		TODO: for now this links to standard price but needs to link to proposed price which is currently in
						 		leaseOutExtras and there might not always be leaseOutOutExtras so this problem must be resolved first.
						 	 	--%> 
								<td style="width:20%;">£ ${assetProposal.asset.leaseOut.leaseCharge}</td>
								<%-- MAYBE PUT VAT HERE TOO --%>
								<td style="width:20%;">${assetProposal.assetSchedule.leaseCommences}</td>
								<td style="width:20%;">${assetProposal.assetSchedule.leaseExpires}</td>
							</tr>  
						                    
						</c:forEach>
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
		</div>
		
	</div>	
</div>