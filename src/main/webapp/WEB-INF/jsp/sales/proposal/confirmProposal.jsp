<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags" %>


<div class="tab-content">

	<form:form action="${pageContext.request.contextPath}/das/proposal/confirm" modelAttribute="assetProposal" id="proposalForm" class="form-horizontal" role="form" method="post">
		
	<!-- Customer Information -->	
	<div class="panel-body">
		<div class="form-group">
			<label for="cusotmerInfo" class="panel-title">
	   			<spring:message code="sales.createproposal.customerInfo" />
	   		</label>
	   		<hr class="smallHr">
			<div class="sales-container">
				<div class="clearfix">
			        <div class="col-sm-4">
			          	<label for="clientName" class=""><spring:message code="sales.landingScreen.client" /></label>
			          	<strong>${assetProposal.company.name}</strong>
			        </div>
			    </div>
			     
		    	<div class="clearfix">
			        <div class="col-sm-3">
			          	<label for="addressLine1" class=""><spring:message code="sales.createproposal.addressLine1" /></label>
			          	<strong>${assetProposal.contactParty.partyContactDetails.partyAddress.address.addressLine1}</strong>
			        </div>
			        <div class="col-sm-3">
			          	<label for="email" class=""><spring:message code="sales.createproposal.email" /></label> 
			          	<strong>${assetProposal.contactParty.partyContactDetails.partyEmail.email.emailAddress}</strong>
			        </div> 
			  	</div>
		  
		   		<div class="clearfix">
			        <div class="col-sm-3">
				    	<label for="addressLine2" class=""><spring:message code="sales.createproposal.addressLine2" /></label> 
				        <strong>${assetProposal.contactParty.partyContactDetails.partyAddress.address.addressLine2}</strong>
			        </div> 
		        	<div class="col-sm-3">
		          		<label for="telephone block" class=""><spring:message code="sales.createproposal.telephone" /></label> 
		          		<strong>${assetProposal.contactParty.partyContactDetails.partyTelephoneNumber.telephoneNumber.telNo}</strong>
		        	</div> 
		    	</div>
		    	
		    	<div class="clearfix">
			        <div class="col-sm-3">
			          	<label for="city" class=""><spring:message code="sales.createproposal.city" /></label> 
			         	<strong>${assetProposal.contactParty.partyContactDetails.partyAddress.address.city}</strong>
			        </div>
		    	</div>
		    	
			    <div class="clearfix">
				  	<div class="col-sm-3">
					  	<label for="postcode" class=""><spring:message code="sales.createproposal.postcode" /></label> 
				        <strong>${assetProposal.contactParty.partyContactDetails.partyAddress.address.zipPostCode}</strong>
				 	</div> 
				</div>
				
				<div class="clearfix">
			 		<div class="col-sm-4">
			  			<label for="country" class=""><spring:message code="sales.createproposal.country" /></label> 
			          	<strong>${assetProposal.contactParty.partyContactDetails.partyAddress.address.country}</strong>
			  		</div>
		  		</div>
			</div>
		</div>
	</div>
		
	
	<!-- Equipment Information -->
	<div class="panel-body">	
	  	<div class="form-group">
	  		<label for="equipmentInfo" class="panel-title">
	   			<spring:message code="sales.createproposal.equipmentInfo" />
	   		</label>
	   		<hr class="smallHr">
	  		<div class="sales-container">
	  			<div class="clearfix">
			   		<div class="col-sm-3">
				   		<label for="equipment" class=""><spring:message code="sales.createproposal.equipment" /></label> 
			         	<strong>${assetProposal.asset.name}</strong>
			   		</div>
		   		
			   		<div class="col-sm-3">
				   		<label for="quantity" class=""><spring:message code="sales.createproposal.quantity" /></label> 
				          <strong>${1}</strong> <!-- @TODO At the moment only 1 available, this will be changed when more made available -->
			   		</div>
			   	</div>
			   	
			   	<div class="clearfix">
			   		<div class="col-sm-3">
						<label for="dateFrom"><spring:message code="sales.createproposal.dateFrom"/></label>
						<strong><spring:eval expression="assetProposal.assetSchedule.leaseCommences"/></strong>
					</div>
				
					<div class="col-sm-3">
						<label for="dateTo"><spring:message code="sales.createproposal.dateTo"/></label>
						<strong><spring:eval expression="assetProposal.assetSchedule.leaseExpires"/></strong>
					</div>
		   		</div>
		   		
		   		<div class="clearfix">
			  		<div class="col-sm-3">
						<label for="chargePeriod"><spring:message code="assetRegister.asset.tenureTab.chargePeriod"/>:&nbsp;</label>
						<das:userdata var="chargePeriod" userDataId="${assetProposal.asset.leaseOut.chargePeriodId}"/>		
						<strong>${assetProposal.asset.leaseOut.chargePeriod.name}</strong>
					</div>
			  		
		   			<div class="col-sm-3">
				   		<label for="cost" class=""><spring:message code="sales.createproposal.cost" /></label> 
				        <strong>${assetProposal.asset.leaseOut.leaseCharge}</strong>
		   			</div>
		   		</div>
				
				<%-- Extras isn't functional yet because the way the lease out extras is pulled through to the asset proposal doesn't work properly.
				This needs re-thinking because it currently only identifies the Id and not the object. --%>
	   			<div class="col-sm-8">
	   		   		<label for="equipmentInfo"> Extras: </label>
					<div class="clearfix">
						<div class="col-sm-3">
						<c:forEach items="${assetProposal.assetProposalExtras}" var="assetProposalExtra">
       						<c:forEach items="${assetProposal.asset.leaseOut.leaseOutExtras}" var="leaseOutExtras">
					            <c:if test="${assetProposalExtra.leaseOutExtraId eq leaseOutExtras.id}">
					            	<label>${leaseOutExtras.leaseOutExtras.name}: </label>
					            	<Strong>${leaseOutExtras.extraCost} </Strong>
					            	<br/>
					            </c:if>
					         </c:forEach>
				       </c:forEach>  
			        </div>
				</div>
			 	<hr class="smallHr">
								
				<%--			<form:hidden path="assetProposalExtras[${var.index}].id" id="extraId"  />
							<div class="col-sm-3">
					            <div class="e1" id="extraNameDiv" title="Details pulled in from create Proposal tab.">
					               	<das:userdata var="extraUD" userDataId="${extra.leaseOutExtras.extrasId}"/>
					               	<form:label path="assetProposalExtras[${var.index}].leaseOutExtras.extraCost">${extraUD.name}</form:label>
								</div>
							</div>
							
		 					<div class="col-sm-3">			
								<div class="e2 input-group" id="extraCostDiv">
									<form:hidden path="assetProposalExtras.leaseOutExtras.extraCost"/>
									<label for="assetProposalExtras.leaseOutExtras[${var.index}].extraCost" class="input-group-addon">£</label>	
									<form:input  path="assetProposalExtras.leaseOutExtras[${var.index}].extraCost" class="form-control" data-sys-ref-id-list-div="extraCostDiv" disabled="true"/>
								</div>
							</div> --%>
		
		<%-- 					<div class="col-sm-5">	
					        	<div class="checkbox">
					        		<form:label path="asset.leaseIn.maintenanceIncluded">Include : ${proposalExtraUD.name}</form:label>
									<form:checkbox path="asset.leaseIn.maintenanceIncluded" class="checkbox" id="addPropExtrasId" /><!-- TODO What checkbox to use? -->
								</div>
							</div> --%>
		   			<div class="col-sm-3">
				   		<label for="price" class=""><spring:message code="sales.createproposal.proposedPrice"/></label> 
				        <strong>${assetProposal.totalProposedCost}</strong>
		   			</div>
		   		</div>
		   		
		   		<div class="clearfix">
		   			<div class="col-sm-3">
		   			</div>
			  		
		   			<div class="col-sm-3">
						<label for="proposedPrice" class="margin-top"><spring:message code="sales.createproposal.price"/></label> 
				       	<strong>${assetProposal.periodicCost}</strong>
			    	</div>
		   		</div>
	  		
	  	</div>
	</div>
</div>
    
	<c:choose>
		<c:when test="${empty readOnly}">	
			<div class=" pull-right btn no-radius btn-danger ">
				<a class="button-text-format" href="${pageContext.request.contextPath}/das/assetAvailability">Cancel</a>	
			</div>
			<input class="pull-right margin-right btn no-radius btn-success" type="submit" value="Send Proposal">
		</c:when>
		<c:otherwise>	
			<div class=" pull-right btn no-radius btn-success">
				<a class="button-text-format" href="${pageContext.request.contextPath}/das/assetAvailability">Return</a>
			</div>
			
			<input class="pull-right margin-right btn no-radius btn-success" disabled="disabled" type="submit"  value="Proposal Sent">
		</c:otherwise>
	</c:choose>

	</form:form>
</div>
			

