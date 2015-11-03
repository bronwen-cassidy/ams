	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags" %>


<div class="tab-content" id="createProposalPage">
	<form:form action="${pageContext.request.contextPath}/das/proposal/create" modelAttribute="assetProposal" id="proposalForm" class="form-horizontal proposalForm" role="form" method="post">
		
	<form:hidden path="asset.id" id="assetId" />
				
	<font color="red">
		<b><form:errors title="Errors" path="*" cssClass="validation-error"/></b> <!-- exception error handling! -->
	</font>	
	
	<!-- Customer Information -->
	<div class="panel-body">
		<div class="form-group">
			<label for="cusotmerInfo" class="panel-title">
		   		<spring:message code="sales.createproposal.customerInfo" />
		   	</label>
			<hr class="smallHr">
	<div class="sales-container">
		<div class="clearfix">
	        <div class="col-sm-3">
	          	<label for="clientName" class=""><spring:message code="sales.landingScreen.client" /></label>
	            <strong>${assetProposal.company.name}</strong>
	        </div>
	        <div class="col-sm-3" id="contactDetails">
				<label for="contactDetails" class="">Contact:</label> 
				<select id="proposalContactDetail">
					<option data-person-id="0"></option>
					<c:forEach items="${listOfContacts}" var="person">
				   		<option data-person-id="${person.id}">${person.forenames} ${person.surname}</option>
				   	</c:forEach>
				</select>
			</div>
	    </div>
	    <div id="clientContactDetails">
		    <div class="clearfix">
		    	<div class="col-sm-3">
		          	<label for="addressLine1" class="">
		          		<spring:message code="sales.createproposal.addressLine1" />
		          	</label>
		            <strong>${assetProposal.address.addressLine1}</strong>
		        </div>
		        <div class="col-sm-3">
		         	<label for="email" class="">
		          		<spring:message code="sales.createproposal.email" />
		          	</label> 
		            <strong>${assetProposal.email.emailAddress}</strong>
		        </div> 
		    </div>
		    <div class="clearfix">
		    	<div class="col-sm-3">
					<label for="addressLine2" class="">
						<spring:message code="sales.createproposal.addressLine2" />
					</label> 
	              <strong>${assetProposal.address.addressLine2}</strong>
	        	</div> 
	        	<div class="col-sm-3">
	          		<label for="telephone" class="">
	          			<spring:message code="sales.createproposal.telephone" />
	          		</label> 
	            	<strong>${assetProposal.telephone.telNo}</strong>
	        	</div> 
		    </div>
		    <div class="clearfix">
		    	<div class="col-sm-4">
		          	<label for="city" class="">
		          		<spring:message code="sales.createproposal.city" />
		          	</label> 
		            <strong>${assetProposal.address.city}</strong>
		        </div> 
		    </div>
		    <div class="clearfix">
		    	<div class="col-sm-4">
		  			<label for="postcode" class="">
		  				<spring:message code="sales.createproposal.postcode" />
		  			</label> 
		            <strong>${assetProposal.address.zipPostCode}</strong>
		  		</div> 
		    </div>
		    <div class="clearfix">
	    	<div class="col-sm-4"><!-- @TODO XXX -->
			  	<label for="country" class=""><spring:message code="sales.createproposal.country" /></label> 
		    	<strong>${assetProposal.address.country}</strong>
		  	</div>
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
		    <form:hidden path="asset.id" />
			   			<label for="equipment" class="">
			   				<spring:message code="sales.createproposal.equipment" />
			   			</label> 
	              <strong>${assetProposal.asset.name}</strong>
   		</div>
   		
   		<div class="col-sm-3">
		   				<label for="quantity" class="">
		   					<spring:message code="sales.createproposal.quantity" />
		   				</label> 
	              <strong>1</strong><!-- @TODO: hard coded for now, but in future we will implement multiple assets. An example of what we could do
	              						is pass multiple assetId's in an array and here we would get the size of that array	 -->
   		</div>
	   			</div>
	   			<div class="clearfix">
   		<div class="col-sm-3">
			<form:hidden path="assetSchedule.leaseCommences" />
						<label for="dateFrom">
							<spring:message code="sales.createproposal.dateFrom"/>
						</label>
			<strong><spring:eval expression="assetProposal.assetSchedule.leaseCommences"/></strong>
		</div>
		
		<div class="col-sm-3">
			<form:hidden path="assetSchedule.leaseExpires" />
						<label for="dateTo">
							<spring:message code="sales.createproposal.dateTo"/>
						</label>
			<strong><spring:eval expression="assetProposal.assetSchedule.leaseExpires"/></strong>
		</div>
   	</div>
	   			<div class="clearfix">
	   				<div class="col-sm-3">
			   			<label for="cost" class="">
			   				<spring:message code="sales.createproposal.cost" />
			   			</label> 
			            <strong>${assetProposal.asset.leaseOut.leaseCharge}</strong>
	   				</div>
	   				<div class="col-sm-3">
			  			<label for="perDayWeekMonth" class="">
			  				<spring:message code="sales.createproposal.perDayWeekMonth" />
			  			</label> 
			  			<strong>${assetProposal.asset.leaseOut.chargePeriod.name}</strong>
		  			</div>
		  			<div class="col-sm-3 pull-right">
			  			<label>
							Cost Before Extras:
			  			</label> 
			  			<strong id="costBeforeExtras"></strong>
		  			</div>
		  		</div>
	   			<div class="clearfix margin-bottom">
   	</div>	
   			</div>
   			
   			<div class="sales-container">
	   		   <label for="equipmentInfo" class="panel-title">
		   		Optional Extras:
		   </label>
		   <hr class="smallHr">
				<c:forEach var="extra" items="${assetProposal.asset.leaseOut.leaseOutExtras}" varStatus="var">
				<div class="clearfix">
				<form:input hidden="hidden" path="asset.leaseOut.leaseOutExtras[${var.index}].id" id="extraId"  />
					<div class="col-sm-3">
			            <div class="e1" id="extraNameDiv" title="Details pulled in from Tenure tab.">
			               	<das:userdata var="extraUD" userDataId="${extra.extrasId}"/>
			               	<form:label path="asset.leaseOut.leaseOutExtras[${var.index}].extraCost">Include: ${extraUD.name}</form:label>
						</div>
					</div>
					
					<div class="col-sm-3">			
						<div class="e2 input-group" id="extraCostDiv">
							<label for="asset.leaseOut.leaseOutExtras[${var.index}].extraCost" class="input-group-addon">£</label>
							<form:input path="asset.leaseOut.leaseOutExtras[${var.index}].extraCost" class="form-control" data-sys-ref-id-list-div="extraCostDiv" disabled="true"/>
						</div>
					</div>

					<div class="col-sm-3">	
		        		<label class="newcheckbox" ><%--onchange="setupExtras(asset.leaseOut.leaseOutExtras[${var.index}])"--%>
		        			<form:checkbox path="asset.leaseOut.leaseOutExtras[${var.index}].include" class="icheckbox_square-green checkBoxValue"/>
		        			<!-- TODO What checkbox to use? -->
		        			<%--<form:label path="asset.leaseIn.maintenanceIncluded" >Include : ${extraUD.name}</form:label>
							<form:checkbox path="asset.leaseOut.leaseOutExtras[${var.index}].include" class="checkbox checkBoxValue"/>--%>
		        		</label>
					</div>
				</div>						
				</c:forEach>
				<div class="clearfix">
					<div class="col-sm-3 pull-right" id="extrasPrice">
				   		<label for="extrasPrice" class="">Extras Cost:</label> 
				   		<strong id="extrasCost"></strong>
			  		</div>
				</div>
				<hr class="smallHr">
				<div class="clearfix">
				<%-- 
				<div class="col-sm-3 pull-right" id="refreshProposedPrice">
			   		<label for="price" class=""><spring:message code="" /></label> 
			   		<strong></strong>
	   			</div>
	   			--%>
				</div>
				<div class="clearfix">
				<%-- NOT BEING USED FOR DEMO SO COMMENTED OUT
					<div class="col-sm-3 pull-right" id="totalDiscount">
				   		<label for="totalDiscount" class="">Total discount:</label> 
				   		<select>
				   			<option>None</option>
				   			<option>2.5%</option>
				   			<option>5%</option>
				   			<option>10%</option>
				   			<option>15%</option>
				   		</select>
				   		<strong>${assetProposal.totalProposedCost}</strong>
			  		</div>
			  	--%>	
			  	<div class="col-sm-3">
					<label for="invoicingScheduleId"> <spring:message code="sales.createproposal.invoicingSchedule"/> </label>
					<das:userdata var="invoicingSchedule" userDataType="INVOICING_SCHEDULE"/>
		            <form:select id="invoicingScheduleId" path="invoicingScheduleId" cssClass="form-control" >
		               	<option></option>
		            	<form:options items="${invoicingSchedule}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
					</form:select>
		   		</div>
				</div>
			</div>
		</div>
	</div>
				
	<div class="clearfix">
		<div class="col-sm-3 pull-right" id="Total">
		<hr class="smallHr">
	   		<label for="Total" class="">Overall Total:</label> 
	   		<strong id="proposalGrandTotal"></strong>
  		</div>
			</div>
 	<hr class="smallHr">
   		 <a <%-- href="${pageContext.request.contextPath}/das/#" --%> class="margin-top"><spring:message code="sales.createproposal.addMore" /></a>
			<div class=" pull-right">
				<input class="btn no-radius btn-success" type="submit" value="Create Proposal">	
			</div>
	  </form:form>
   	</div>
