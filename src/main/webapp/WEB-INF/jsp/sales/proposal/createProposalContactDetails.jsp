<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="clientContactDetails">
    <div class="clearfix">
    	<div class="col-sm-3">
          	<label for="addressLine1" class="">
          		<spring:message code="sales.createproposal.addressLine1" />
          	</label>
            <strong>${contactDetails.partyAddress.address.addressLine1}</strong>
        </div>
        <div class="col-sm-3">
         	<label for="email" class="">
          		<spring:message code="sales.createproposal.email" />
          	</label> 
            <strong>${contactDetails.partyEmail.email.emailAddress}</strong>
        </div> 
    </div>
    <div class="clearfix">
    	<div class="col-sm-3">
			<label for="addressLine2" class="">
				<spring:message code="sales.createproposal.addressLine2" />
			</label> 
             <strong>${contactDetails.partyAddress.address.addressLine2}</strong>
       	</div> 
       	<div class="col-sm-3">
         		<label for="telephone" class="">
         			<spring:message code="sales.createproposal.telephone" />
         		</label> 
           	<strong>${contactDetails.partyTelephoneNumber.telephoneNumber.telNo}</strong>
       	</div> 
    </div>
    <div class="clearfix">
    	<div class="col-sm-4">
          	<label for="city" class="">
          		<spring:message code="sales.createproposal.city" />
          	</label> 
            <strong>${contactDetails.partyAddress.address.city}</strong>
        </div> 
    </div>
    <div class="clearfix">
    	<div class="col-sm-4">
  			<label for="postcode" class="">
  				<spring:message code="sales.createproposal.postcode" />
  			</label> 
            <strong>${contactDetails.partyAddress.address.zipPostCode}</strong>
  		</div> 
    </div>
    <div class="clearfix">
   	<div class="col-sm-4"><!-- @TODO XXX -->
	  	<label for="country" class=""><spring:message code="sales.createproposal.country" /></label> 
    	<strong>${contactDetails.partyAddress.address.country}</strong>
  	</div>
  	</div>
</div>