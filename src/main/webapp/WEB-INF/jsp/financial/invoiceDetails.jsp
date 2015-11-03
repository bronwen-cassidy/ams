<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags" %>

<div class="container">
<div class="tab-content">

	<form:form action="${pageContext.request.contextPath}/das/proposal/confirm" modelAttribute="assetProposal" id="proposalForm" class="form-horizontal" role="form" method="post">
	
	<div class="inner-scroll">
	
		<c:forEach begin="1" end="${timePeriod}" varStatus="loopVar">
			<div class="panel-body">
				<div class="form-group">
					<span class="panel-title">
			   			${nameOfTimePeriod} ${loopVar.index}
			   		</span>
			   		<hr class="smallHr">
					<div class="sales-container">
					<div class="col-sm-6" style="float:right;">	
						<div class="col-sm-6">
					          	<label for="proposalStatus"><spring:message code="sales.landingScreen.status"/>:&nbsp;</label>
					          	<select id="proposalStatus" name="proposalStatus" class="form-control">
				                	<option></option>
				                	<option>Accepted</option>
				                	<option>Pending</option>
				                	<option>Cancelled</option>
				                </select>
					     </div>
					</div> 
					<div class="col-sm-6" style="float:left;">    
					        
						<div class="clearfix">
					        <div class="col-sm-10">
					          	<span><spring:message code="sales.createproposal.customerName" /></span>
					          	<strong>${assetProposal.company.name}</strong>
					        </div>
					    </div>
					    
				    	<div class="clearfix">
					        <div class="col-sm-6">
					          	<span><spring:message code="sales.createproposal.addressLine1" /></span>
					          	<strong>${assetProposal.address.addressLine1}</strong>
					        </div>
					        
					  	</div>
					  	
					  	<div class="clearfix">
					  		<div class="col-sm-6">
							  	<span><spring:message code="sales.createproposal.postcode" /></span> 
						        <strong>${assetProposal.address.zipPostCode}</strong>
						 	</div> 
					  	</div>
					  	
					  	<div class="clearfix">
					  		<div class="col-sm-6">
					          	<span><spring:message code="sales.createproposal.email" /></span> 
					          	<strong>${assetProposal.email.emailAddress}</strong>
					        </div> 
					  	</div>
				  
				   		<div class="clearfix">
				        	<div class="col-sm-6">
				          		<span><spring:message code="sales.createproposal.telephone" /></span> 
				          		<strong>${assetProposal.telephone.telNo}</strong>
				        	</div> 
				    	</div>
				    	
				    	<div class="clearfix">
				   			<div class="col-sm-8">
							  	<span><spring:message code="assetRegister.asset.financeTab.vatCode"/>:&nbsp;</span> 
						        <das:userdata var="vatCode" userDataId="${assetProposal.asset.leaseOut.vatCodeId}"/>
						        <strong>${vatCode.name}</strong>
						 	</div> 
				    	</div>
				    	
				    	<div class="clearfix">
				   			<div class="col-sm-6">
							  	<span><spring:message code="sales.createproposal.cost" /></span> 
						        <strong>${assetProposal.asset.leaseOut.leaseCharge}</strong>
						 	</div>
				    	</div>
				    	
				    	<div class="clearfix">
				    		<%--
						 	TODO: for now this links to standard price but needs to link to proposed price which is currently in
						 	leaseOutExtras and there might not always be leaseOutOutExtras so this problem must be resolved first.
						 	 --%> 
						 	<div class="col-sm-6">
							  	<span><spring:message code="finance.invoice.totalToBill"/>:&nbsp;</span>
						       <strong>${assetProposal.totalProposedCost}</strong>
						 	</div>
				    	</div>
				    	
				    	<div class="clearfix">
				    		<div class="col-sm-6">
				    			<span><spring:message code="finance.invoice.extraCosts"/>:&nbsp;</span><br> 
				    	 		<c:forEach var="extra" items="${assetProposal.assetProposalExtras}">
				    	 			<%-- TODO Need the link from Proposal Extra to Lease out extra to get the extra name (user data) See AssetProposalExtras --%>
						        	<strong>${extra.costOfExtras}</strong><br>
						        </c:forEach>
							</div>
						</div> 
					</div>	       
					</div>
				</div>
			</div>
		</c:forEach>
	</div>	
	</form:form>
</div>
</div>
		