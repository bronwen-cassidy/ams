<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<input type="hidden" class="total-results" value="${searchTotalCount}" />
	<div class="clearfix">
		<div class="col-sm-3 pull-left no-padding">
		    <select class="sales-filter form-control">
		        <option disabled selected><spring:message code="placeholder.filterBy"/></option>
		       	<option value="<spring:message code="sales.landingScreen.search.pending"/>"><spring:message code="sales.landingScreen.search.pending"/></option>
		    	<option value="<spring:message code="sales.landingScreen.search.sent"/>"><spring:message code="sales.landingScreen.search.sent"/></option>		
		        <option value="<spring:message code="sales.landingScreen.search.accepted"/>"><spring:message code="sales.landingScreen.search.accepted"/></option>
		        <option value="<spring:message code="sales.landingScreen.search.declined"/>"><spring:message code="sales.landingScreen.search.declined"/></option>
		        <option value="<spring:message code="sales.landingScreen.search.deployed"/>"><spring:message code="sales.landingScreen.search.deployed"/></option>					        
		    	<option value="<spring:message code="sales.landingScreen.search.collection"/>"><spring:message code="sales.landingScreen.search.collection"/></option>		
		    </select>
		</div>

		<div class="col-sm-6 pull-right no-padding">
		    <a href="${pageContext.request.contextPath}/das/assetAvailability/full" class="margin-right btn btn-default margin-right-twentyone pull-right">
		    	Create Lease Order
		    </a>
		</div>
	</div>
	<div class="clearfix margin-top sales-table">
	<table class=" table-header-bar context-opener table multi-table table-hover" data-type="ASSET_PROPOSAL">
		
		<thead>
			<tr class="alternateRow">
				<th class="checkbox-holder-size" style="width:0.1%"><input type="checkbox"/></th>
				<th style="width:18%;"><spring:message code="sales.landingScreen.orderReference" /></th>
				<th style="width:16%;"><spring:message code="sales.landingScreen.assetName" /></th>
				<th style="width:16%;"><spring:message code="sales.landingScreen.client" /></th>
				<th style="width:15%;"><spring:message code="sales.landingScreen.telephone" /></th>
				<th style="width:29%;"><spring:message code="sales.landingScreen.email" /></th>
				<th style="width:10%; padding-right:10px;"><spring:message code="sales.landingScreen.status" /></th>
			</tr>
		
		</thead>
		
		</table>
		<table class="table-container context-opener table multi-table table-hover scroll-content" data-type="ASSET_PROPOSAL">
			<c:if test="${!empty proposalList}">
			
				<tbody>
					<c:forEach items="${proposalList}" var="assetProposal">
						<tr> 
							<th class="checkbox-holder-size"><input type="checkbox" /></th>   
							<td style="width:18%;"><a href="${pageContext.request.contextPath}/das/proposal/proposalDetails?proposalId=${assetProposal.id}">${assetProposal.id}</a></td>
							<td style="width:16%;">${assetProposal.asset.name}</td>
							<td style="width:16%;">${assetProposal.company.name}</td>
							<td style="width:15%;">${assetProposal.contactParty.partyContactDetails.partyTelephoneNumber.telephoneNumber.telNo}</td>
							<td style="width:29%;">${assetProposal.contactParty.partyContactDetails.partyEmail.email.emailAddress}</td>
							<td class="no-padding" style="width:10%; text-align:center; line-height: 2;">
								<label class="status-box"
									<c:choose>
										<c:when test="${assetProposal.currentStatus == 'Pending'}">
											style="background-color:#D5D65A;"
										</c:when>
										<c:when test="${assetProposal.currentStatus == 'Accepted'}">
											style="background-color:#61DC7C;"
										</c:when>
										<c:when test="${assetProposal.currentStatus == 'Declined'}">
											style="background-color:#E96464;"
										</c:when>
										<c:when test="${assetProposal.currentStatus == 'Deployed'}">
											style="background-color:#007ABD;"
										</c:when>
										<c:otherwise>
											style="background-color:#C199F0;"
										</c:otherwise>
									</c:choose>
								>${assetProposal.currentStatus}
							</label></td>
						</tr>  
					                    
					</c:forEach>
				</tbody>
			</c:if>
				<tfoot>
					<tr>
						<td colspan="9" class="load-more-sales text-center">
							<span class="caret pull-left"></span> 
								Load more results 
							<span class="caret pull-right"></span>
						</td>
					</tr>
				</tfoot>
		 
		</table>
	</div>
</div>
