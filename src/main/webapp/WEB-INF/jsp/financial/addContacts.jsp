<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="das-tags" prefix="das" %>

<spring:message var="sysGenPlaceHolder" code="placeholder.systemGenerated" />

	<form:form action="${pageContext.request.contextPath}/das/contact/create?parentPartyId=${parentPartyId}&view=${view}" modelAttribute="person" id="droppableForm" 
		class="form-horizontal droppable-form" role="form"	method="post" >
		
		<input id="parentPartyId" value="${parentPartyId}" type="hidden">
		<input id="view" value="${view}" type="hidden">
		
					<div class="panel-body">
	<h4>
								<spring:message code="finance.supplier.supplierDetailsTab.contactInformation"/>
	</h4>
	<hr class="smallHr">
						<div class="form-group clearfix">
							<div class="col-sm-4">
								<label for="GENDER"><spring:message code="finance.supplier.supplierDetailsTab.gender"/></label>
								<form:select path="gender" id="GENDER" class="form-control"><%--Add below into userData? --%>
									<form:option value="Mr"/>Mr
									<form:option value="Mrs"/>Mrs
									<form:option value="Miss"/>Miss
								</form:select>
							</div>
							
							<div class="col-sm-4">
								<label for="SURNAME"><spring:message code="finance.supplier.supplierDetailsTab.surname"/></label>
								<form:input path="surname" id="SURNAME" class="form-control" type="text"/>
							</div>
							
							<div class="col-sm-4">
								<label for="FORENAMES"><spring:message code="finance.supplier.supplierDetailsTab.forenames"/></label>
								<form:input path="forenames" id="FORENAMES" class="form-control" type="text"/>
							</div>

						</div>
					 
						<div class="form-group clearfix">
						
							<div class="col-sm-4">
								<label for="POSITION"><spring:message code="finance.supplier.supplierDetailsTab.position"/></label>
								<form:input path="position" id="POSITION" class="form-control" type="text"/>
							</div>
							
							<div class="col-sm-4">
								<label for="EMAIL" class=""><spring:message code="finance.supplier.supplierDetailsTab.email"/></label>
								<input name="emailAddress" id="new-per-emailAddress" class="form-control" type="text"/>						
							</div>
	  						<div class="col-sm-4">
								<label for="TELEPHONE_NUMBER" class=""><spring:message code="address.telephoneNumber"/></label>
								<input name="telNo" id="new-per-tel-no" class="form-control" type="text"/>
							</div> 
						
			            </div>
			            
					 
						<div class="form-group clearfix">
						<div class="col-sm-4">
								<label for="POSTCODE"><spring:message code="address.postcode"/></label>
								<div class="input-group">
									<input id="new-per-zipPostCode" name="zipPostCode" class="form-control" type="text"/>
									<span class="input-group-btn"> <button type="button" class="btn btn-default lookup-postcode-btn"><i class="fa fa-search"></i></button> </span>
								</div>
							</div>
						</div>

						<div class="form-group clearfix">
							<div class="col-sm-3">
								<label for="ADDRESS_LINE_1"><spring:message code="address.addressLine1"/></label>
								<input id="new-per-address-line-1" name="addressLine1" class="form-control" type="text"/>
							</div>
		
							<div class="col-sm-3">
								<label for="ADDRESS_LINE_2"><spring:message code="address.addressLine2"/></label>
								<input id="new-per-address-line-2" name="addressLine2" class="form-control" type="text"/>
							</div>

							<div class="col-sm-3">
								<label for="CITY"><spring:message code="address.city"/></label>
								<input id="new-per-city" name="city" class="form-control" type="text"/>
							</div>
							
							<div class="col-sm-3">
								<label for="COMPANY_NAME"><spring:message code="address.country"/></label>
	                            <input id="new-per-country" name="country" class="form-control" type="text" />
							</div>
						</div>
						
			<div class="clearfix">
			<hr class="smallHr">
			<input type="submit" class="btn btn-success" value="Save Contact" id="add-new-person-contact">
		</div>
	</div>

	</form:form>
