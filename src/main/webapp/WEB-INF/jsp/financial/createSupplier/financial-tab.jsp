<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="tab-content">

	<form:form action="${pageContext.request.contextPath}/das/financial/supplier/create"
		modelAttribute="company" id="droppableForm" 
		class="form-horizontal draftCompanyForm droppable-form" role="form"
		method="post">
		
		<input type="submit" value="<spring:message code="company.saveCompany"/>">
		
		<div class="form-group clearfix">
			
			<%--NEED TO FIND OUT IF THIS IS NEEDED
			<div class="col-sm-3">
		        <label for="TAX_CODE" class=""><spring:message code="finance.supplier.financialDetailsTab.taxcode"/></label>
	            <select id="TAX_CODE" name="TAX_CODE" class="form-control" data-field-to-copy="#">
	            	<option></option>
	            </select>
			</div>
			 --%>
		
			<div class="col-sm-3">
		        <label for="VAT" class=""><spring:message code="finance.supplier.financialDetailsTab.vat"/></label>
	            <form:select path="vatNo" class="form-control" >
	            	<option></option>
	            	<form:options items="${VATCode}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
	            </form:select>	
			</div>
	
			<div class="col-sm-3">
		        <label for="CURRENCY_ACCEPTED" class=""><spring:message code="finance.supplier.financialDetailsTab.currencyAccepted"/></label>
	            <form:select path="currencyAcceptedId" class="form-control" >
	            	<option></option>
	            	<form:options items="${currency}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
	            </form:select>
			</div>
			
		</div>
	
		
		<div class="panel-group accordion-custom accordion-teal" id="accordion">
	<%-- COMMENTED OUT FOR NOW UNTILL WE CAN ADD MULTIPLE ADDRESSES
			<div class="inner-form-panel panel panel-default">
	
				<div class="panel-heading">
	
					<h4 class="panel-title">
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#account_address_panel">
						<i class="icon-arrow"></i>
						<spring:message code="finance.supplier.financialDetailsTab.accountAddressIfDifferent"/>
					</a></h4>
	
				</div>
	
				<div id="account_address_panel" class="panel-collapse in">
	
					<div class="panel-body">
					
						<div class="form-group clearfix">
						
							<div class="col-sm-3">
								<label for="POSTCODE"><spring:message code="address.postcode"/></label>
								<div class="input-group">
									<input id="POSTCODE" name="POSTCODE" class="form-control" type="text">
									<span class="input-group-btn"> <button type="button" class="btn btn-default lookup-postcode-btn"><i class="fa fa-search"></i></button> </span>
								</div>
							</div>
							
							<div class="col-sm-3">
								<label for="TELEPHONE_NUMBER" class=""><spring:message code="address.telephoneNumber"/></label>
								<input id="TELEPHONE_NUMBER" name="TELEPHONE_NUMBER" class="form-control" type="text">								
							</div>
							
						</div>
	
						<div class="form-group clearfix">
	
							<div class="col-sm-3">
								<label for="CITY"><spring:message code="address.city"/></label>
								<input id="CITY" name="CITY" class="form-control" type="text">
							</div>
							
							<div class="col-sm-3">
								<label for="COUNTRY"><spring:message code="address.country"/></label>
								<form:select path="countryList" class="form-control" >
	            					<option></option>
	            					<form:options items="${countryList}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
	            				</form:select>
							</div>
	
							<div class="col-sm-3">
								<label for="ADDRESS_LINE_1"><spring:message code="address.addressLine1"/></label>
								<input id="ADDRESS_LINE_1" name="ADDRESS_LINE_1" class="form-control" type="text">
							</div>
	
							<div class="col-sm-3">
								<label for="ADDRESS_LINE_2"><spring:message code="address.addressLine2"/></label>
								<input id="ADDRESS_LINE_2" name="ADDRESS_LINE_2" class="form-control" type="text">
							</div>
							
						</div>
					
					</div>
	
				</div>
	
			</div>
		--%>
			
			<%-- COMMENTED OUT UNTILL WE HAVE SOMETHING TO MAP TO. NOT SURE IF THESE DETAILS BELONG ON THIS SCREEN
			<div class="inner-form-panel panel panel-default">
	
				<div class="panel-heading">
	
					<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#bank_information_panel">
						<i class="icon-arrow"></i>
						<spring:message code="finance.supplier.financialDetailsTab.bankinformation"/>
					</a></h4>
	
				</div>
	
				<div id="bank_information_panel" class="panel-collapse collapse" style="height: auto;">
	
					<div class="panel-body">
	
						<div class="form-group clearfix">
						
							<div class="col-sm-3">
						        <label for="TERMS_AGREED" class=""><spring:message code="finance.supplier.financialDetailsTab.termsAgreed"/></label>
					            <form:select path="terms" class="form-control" >
					            	<option></option>
					            	<form:options items="${terms}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
					   			</form:select>
							</div>
						
							<div class="col-sm-3">
						        <label for="SPEND_LIMIT" class=""><spring:message code="finance.supplier.financialDetailsTab.spendLimit"/></label>
					            <div class="input-group">
									<span class="input-group-addon">£</span>
					            	<form:select path="spendLimit" class="form-control" >
					            		<option></option>
					            		<form:options items="${spendLimit}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
					   			</form:select>
					            </div>	
							</div>
	
							<div class="col-sm-3">
								<label for="DISCOUNT" class=""><spring:message code="finance.supplier.financialDetailsTab.discount"/></label>
								<input id="DISCOUNT" name="DISCOUNT" class="form-control" type="text">
							</div>
				
	
						</div>
	
					</div>
	
				</div>
	
			</div>
			--%>
	
			<div class="inner-form-panel panel panel-default">
	
				<div class="panel-heading">
	
					<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#bank_details_panel">
						<i class="icon-arrow"></i>
						<spring:message code="bank.information.bankDetails"/>
					</a></h4>
	
				</div>
	
				<div id="bank_details_panel" class="panel-collapse collapse" style="height: auto;">
	
					<div class="panel-body">
						<div class="clearfix">
							<div class="clearfix">
								<div class="col-sm-3">
									<label class="label-icons" for="partyBankDetails[${loopVar.index}].bankDetails.bankName">
										<spring:message code="bank.information.bankName"/>
									</label>
								</div>
								<div class="col-sm-3">
									<label class="label-icons" for="partyBankDetails[${loopVar.index}].bankDetails.bankAddress">
										<spring:message code="bank.information.bankAddress"/>
									</label>
								</div>
								<div class="col-sm-3">
									<label class="label-icons" for="partyBankDetails[${loopVar.index}].bankDetails.accountNumber">
										<spring:message code="bank.information.accountNumber"/>
									</label>
								</div>
								<div class="col-sm-3">
									<label class="label-icons" for="partyBankDetails[${loopVar.index}].bankDetails.sortCode">
										<spring:message code="bank.information.sortCode"/>
									</label>
								</div>
							</div>
							
							<div id="refreshBankDetails" class="clearfix">
								<c:forEach items="${company.partyBankDetails}" var="partyBankDetail" varStatus="loopVar">
									<div class="clearfix">
										<div class="col-sm-3">
											<form:input path="partyBankDetails[${loopVar.index}].bankDetails.bankName" cssClass="form-control" id="party-bank-detail-${loopVar.index}"></form:input>
										</div>
										<div class="col-sm-3">
											<form:input path="partyBankDetails[${loopVar.index}].bankDetails.bankAddress" cssClass="form-control" id="party-bank-detail-${loopVar.index}"></form:input>
										</div>
										<div class="col-sm-3">
											<form:input path="partyBankDetails[${loopVar.index}].bankDetails.accountNumber" cssClass="form-control" id="party-bank-detail-${loopVar.index}"></form:input>
										</div>
										<div class="col-sm-3">
											<form:input path="partyBankDetails[${loopVar.index}].bankDetails.sortCode" cssClass="form-control" id="party-bank-detail-${loopVar.index}"></form:input>
										</div>
									</div>
								</c:forEach>
							</div>
								
							<div class="clearfix margin-top">
								<div class="col-sm-3">
									<input id="new-bank-name" name="newBankName" class="form-control" type="text"/>						
								</div>
								<div class="col-sm-3">
									<input id="new-bank-address" name="newBankAddress" class="form-control" type="text"/>						
								</div>
								<div class="col-sm-3">
									<input id="new-bank-account-number" name="newBankAccountNumber" class="form-control" type="text"/>						
								</div>
								<div class="col-sm-3">
									<input id="new-bank-sort-code" name="newBankSortCode" class="form-control" type="text"/>						
								</div>
								
								<div class="col-sm-1 text-center">
									<button type="button" class="btn btn-success" id="add-new-bank-detail">Save Bank Detail</button>
								</div>
							</div>
						</div>
	
					</div>
	
			</div>
			
		</div>
	 
	</form:form>

</div>