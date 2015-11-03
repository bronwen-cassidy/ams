<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="das-tags" prefix="das" %>

<spring:message var="sysGenPlaceHolder"
	code="placeholder.systemGenerated" />

<div class="tab-content">

	<form:form action="${pageContext.request.contextPath}/das/financial/client/create"
		modelAttribute="company" id="droppableForm" 
		class="form-horizontal draftClientForm droppable-form" role="form"
		method="post">
		
		<div class="form-group clearfix">
			<div class="col-sm-6">
		        <label for="uid" class=""><spring:message code="assetRegister.asset.uid"/></label>
	            <form:input id="UID" path="uid" name="UID" class="form-control" disabled="true" placeholder="${sysGenPlaceHolder}" />
			</div>
			<div class="col-sm-6">
	        	<label for="CREATED_BY" class=""><spring:message code="assetRegister.asset.createdBy"/></label>
	           <form:input path="createdBy" placeholder="${user.userName}" class="form-control" type="text" id="CREATED_BY" disabled="true" />
			</div>
		</div>

		<div class="form-group clearfix">
			<div class="col-sm-6">
				<label for="COMPANY_NAME" class=""><spring:message code="company.companyName"/></label>
				<form:input id="COMPANY_NAME" path="name" class="form-control" type="text"/>							
			</div>
			
			<%--COMMENTED OUT UNTILL WE DECIDE IF IT IS NEEDED
			<div class="col-sm-4">
				<label for="STATUS" class=""><spring:message code="finance.supplier.supplierDetailsTab.status"/></label>
				<form:select path="" class="form-control" >
	               	<option></option>
	              	<form:options items="${statusesList}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
	            </form:select>							
			</div> --%>

			<div class="col-sm-6">
				<label for="DATE_ACCOUNT_OPENED" class=""><spring:message code="finance.client.clientDetailsTab.dateAccountOpened"/></label>
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
					<form:input id="DATE_ACCOUNT_OPENED" path="createdDate" name="DATE_ACCOUNT_OPENED" disabled="true" class="form-control date-picker hasDatepicker" type="text"/>
				</div>
			</div>
		</div>
		
		
		<!-- WEBSITE INFORMATION -->
			<div class="inner-form-panel panel panel-defualt">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#websiteInformation">
							<i class="icon-arrow"></i>
							Website information
						</a>
					</h4>
				</div>
				<div id="websiteInformation" class="panel-collapse collapse" style="height: 0px">
					<div class="panel-body">
						<div class="form-group clearfix">
							<div class="clearfix">
								<div class="col-sm-6">
									<label class="label-icons" for="partyWebsites[${loopVar.index}].website.websiteUrl">
										<spring:message code="finance.supplier.supplierDetailsTab.companyWebsite"/>
									</label>
								</div>
							</div>
							
							<div id="refreshClientWebsites" class="clearfix">
								<c:forEach items="${company.partyWebsites}" var="partyWebsite" varStatus="loopVar">
									<div class="clearfix">
										<div class="col-sm-6">
											<form:input path="partyWebsites[${loopVar.index}].website.websiteUrl" cssClass="form-control" id="party-website-${loopVar.index}"></form:input>
										</div>
									</div>
								</c:forEach>
							</div>
							
							<div class="clearfix margin-top">
								<div class="col-sm-6">
									<input id="new-client-website" name="newWebsite" class="form-control" type="text"/>						
								</div>
								<div class="col-sm-1 text-center">
									<button type="button" class="btn btn-success" id="add-new-client-website">Save Website</button>
								</div>
							</div>
						<%--<div class="clearfix">
								<div class="text-center margin-top">
									<button type="button" class="btn btn-success" style="display:none"> Add Website</button>
								</div>
							</div>--%>
						</div>
					</div>
				</div>
			</div>
		
		<!-- EMAIL INFORMATION -->
		<div class="inner-form-panel panel panel-defualt">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#emailInformation">
						<i class="icon-arrow"></i>
						Email information
					</a>
				</h4>
			</div>
			<div id="emailInformation" class="panel-collapse collapse" style="height: 0px">
				<das:userdata var="emailTypes" userDataType="EMAIL_TYPE"/>
				<div class="panel-body">
					<div class="form-group clearfix">
						<div class="clearfix">
							<div class="col-sm-6">
								<label class="label-icons" for="partyEmails[${loopVar.index}].email.emailAddress">
								<spring:message code="finance.supplier.supplierDetailsTab.email"/>
								</label>
						</div>
						<div class="col-sm-5">
								<label class="label-icons" for="partyEmails[${loopVar.index}].emailType">
									<spring:message code="finance.supplier.supplierDetailsTab.emailType"/>
								</label>
							</div>
							<div class="col-sm-1">
								<label class="label-icons text-center" for="primaryEmailRb" id="primaryEmail">
									Primary Email
								</label>
							</div>
					</div>
						
						<div id="refreshClientEmails" class="clearfix">
						<c:forEach items="${company.partyEmails}" var="partyEmail" varStatus="loopVar">
								<div class="clearfix emails">
									<div class="col-sm-6">
										<form:input path="partyEmails[${loopVar.index}].email.emailAddress" cssClass="form-control" id="party-email-${loopVar.index}"></form:input>
									</div>
									<div class="col-sm-5">
										<form:select path="partyEmails[${loopVar.index}].emailType" class="form-control">
											<option></option>
											<form:options items="${emailTypes}" itemValue="id" itemLabel="name" />
										</form:select>
									</div>
									<div class="col-sm-1 text-center">
										<c:choose>
											<c:when test="${partyEmail.email eq company.partyContactDetails.partyEmail.email}">
												<input id="primary-email-rb-${loopVar.index}" name="primaryEmailRb" type="radio" onchange="updateClientPrimaryEmail(${loopVar.index})" checked="checked"/>									
												</c:when>
											<c:otherwise>
												<input id="primary-email-rb-${loopVar.index}" name="primaryEmailRb" type="radio" onchange="updateClientPrimaryEmail(${loopVar.index})" />	
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
						<div id="refreshEmailInput" class="clearfix margin-top">
							<div class="col-sm-6">
								<input id="new-client-email" name="newEmail" class="form-control" type="text"/>						
							</div>
							<div class="col-sm-5">
								<select id="new-client-email-type" name="newEmailType" class="form-control">
									<option></option>
									<c:forEach items="${emailTypes}" var="emailType">
										<option value="${emailType.id}">${emailType.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-1 text-center">
								<button type="button" class="btn btn-success" id="add-new-client-email">Save Email</button>
							</div>
						</div>
						<div class="clearfix">
							<div class="text-center margin-top">
								<button type="button" class="addNewEmail btn btn-success" style="display:none"> Add Email</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- CONTACT INFORMATION -->
			<div class="inner-form-panel panel panel-defualt">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#addressInformation">
							<i class="icon-arrow"></i>
							<spring:message code="finance.supplier.supplierDetailsTab.contactInformation"/>
						</a>
					</h4>
				</div>
				<div id="addressInformation" class="panel-collapse collapse" style="height: 0px">
					<das:userdata var="addressTypes" userDataType="ADDRESS_TYPE"/>
					<div class="panel-body">
						<div class="clearfix">
							<div id="refreshClientAddress" class="clearfix">
							<c:forEach items="${company.partyAddresses}" var="partyAddress" varStatus="loopVar">
								<div class="clearfix Addresses">
									<h5 class="panel-title">
										Address ${loopVar.count}
									</h5>
									<hr class="smallHr">
									<div class="clearfix">
										<div class="col-sm-6">
											<label for="addressLine1"><spring:message code="address.addressLine1"/></label>
											<form:input id="address-line-1-${loopVar.index}" path="partyAddresses[${loopVar.index}].address.addressLine1" class="form-control" type="text"/>
										</div>
										<div class="col-sm-5">
											<label for="addressLine2"><spring:message code="address.addressLine2"/></label>
											<form:input id="address-line-2-${loopVar.index}" path="partyAddresses[${loopVar.index}].address.addressLine2" class="form-control" type="text"/>
										</div>
										<div class="col-sm-1 text-center">
											<label class="label-icons text-center" for="primaryAddressRb" id="primaryAddress">
												Primary Address
											</label>
											<c:choose>
												<c:when test="${partyAddress.address eq company.partyContactDetails.partyAddress.address}">
													<input id="primary-address-rb-${loopVar.index}" name="primaryAddressRb" type="radio" onchange="updateClientPrimaryAddress(${loopVar.index})" checked="checked"/>									
												</c:when>
												<c:otherwise>
													<input id="primary-address-rb-${loopVar.index}" name="primaryAddressRb" type="radio" onchange="updateClientPrimaryAddress(${loopVar.index})" />	
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="clearfix margin-top">
						     			<div class="col-sm-3">
											<label for="city"><spring:message code="address.city"/></label>
											<form:input id="city-${loopVar.index}" path="partyAddresses[${loopVar.index}].address.city" class="form-control" type="text"/>
										</div>		
										<div class="col-sm-3">
											<label for="country"><spring:message code="address.country"/></label>
								        	<form:input id="country-${loopVar.index}" path="partyAddresses[${loopVar.index}].address.country" class="form-control" type="text" />
										</div>
										<div class="col-sm-3">
											<label for="addressType"><spring:message code="address.addressType"/></label>
											<form:select id="address-type-${loopVar.index}" path="partyAddresses[${loopVar.index}].addressType" class="form-control" >
												<option></option>
												<form:options items="${addressTypes}" itemValue="id" itemLabel="name"/>						
											</form:select>
										</div>
										<div class="col-sm-2">
											<label for="postcode"><spring:message code="address.postcode"/></label>
											<div class="input-group">
												<form:input id="postcode-${loopVar.index}" path="partyAddresses[${loopVar.index}].address.zipPostCode" class="form-control" type="text"/>
												<span class="input-group-btn"> <button type="button" class="btn btn-default lookup-postcode-btn"><i class="fa fa-search"></i></button> </span>
											</div>
										</div>
				    				</div>
				    				<hr>
								</div>					
							</c:forEach>
							</div>
							<div id="refreshAddressInput" class="clearfix margin-top">
								<div class="clearfix">
									<div class="col-sm-6">
										<label for="newAddressLine1"><spring:message code="address.addressLine1"/></label>
										<input id="new-client-address-line-1" name="newAddressLine1" class="form-control" type="text"/>
									</div>
									<div class="col-sm-5">
										<label for="newAddressLine2"><spring:message code="address.addressLine2"/></label>
										<input id="new-client-address-line-2" name="newAddressLine2" class="form-control" type="text"/>
									</div>
								</div>
								<div class="clearfix margin-top">
									<div class="col-sm-3">
										<label for="newCity"><spring:message code="address.city"/></label>
										<input id="new-client-city" name="newCity" class="form-control" type="text"/>
									</div>
									<div class="col-sm-3">
										<label for="newCountry"><spring:message code="address.country"/></label>
							        	<input id="new-client-country" name="newCountry" class="form-control" type="text" />
									</div>
									<div class="col-sm-3">
										<label for="newAddressType"><spring:message code="address.addressType"/></label>
										<select id="new-client-address-type" name="newAddressType" class="form-control" >
											<option></option>
											<c:forEach items="${addressTypes}" var="addressType">
												<option value="${addressType.id}">${addressType.name}</option>
											</c:forEach>								
										</select>	
									</div>
									<div class="col-sm-2">
										<label for="newZipPostcode"><spring:message code="address.postcode"/></label>
										<div class="input-group">
											<input id="new-client-post-zipcode" name="newZipPostcode" class="form-control" type="text"/>
											<span class="input-group-btn"> <button type="button" class="btn btn-default lookup-postcode-btn"><i class="fa fa-search"></i></button> </span>
										</div>
									</div>
									<div class="col-sm-1 text-center">
										<button type="button" class="btn btn-success button-margin-top" id="add-new-client-address">Save Address</button>
									</div>
								</div>
							</div>
							<div class="clearfix">
								<div class="text-center">
									<button type="button" class="addNewAddress btn btn-success" style="display:none"> Add Email</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<!-- TELEPHONE INFORMATION -->
			<div class="inner-form-panel panel panel-defualt">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#telephoneInformation">
							<i class="icon-arrow"></i>
							Telephone information
						</a>
					</h4>
				</div>
				<div id="telephoneInformation" class="panel-collapse collapse" style="height: 0px">
				<das:userdata var="telNoTypes" userDataType="TELEPHONE_NUMBER_TYPE"/>
					<div class="panel-body">
						<div class="form-group clearfix">
							<div class="clearfix">
								<div class="col-sm-6">
									<label class="label-icons" for="partyTelephones[${loopVar.index}].telephone.telNo">
										Telephone number
									</label>
								</div>
								<div class="col-sm-5">
									<label class="label-icons" for="partyTelephones[${loopVar.index}].telNumberType">
										Telephone number type
									</label>
								</div>
								<div class="col-sm-1">
									<label class="label-icons text-center" for="primaryTelephoneRb" id="primaryTelephone">
										Primary telephone
									</label>
								</div>
							</div>
						
							<div id="refreshClientTelephone" class="clearfix">
							<c:forEach items="${company.partyTelephoneNumbers}" var="partyTelNo" varStatus="loopVar">
								<div class="clearfix telephoneNumbers margin-top">
									<div class="col-sm-6">
										<form:input path="partyTelephoneNumbers[${loopVar.index}].telephoneNumber.telNo" cssClass="form-control" id="party-telephoneNumber-${loopVar.index}"></form:input>
									</div>
									<div class="col-sm-5">
										<form:select path="partyTelephoneNumbers[${loopVar.index}].telNumberType" class="form-control" id="party-telephoneNumber-type-${loopVar.index}">
											<option></option>
											<form:options items="${telNoTypes}" itemValue="id" itemLabel="name" />
										</form:select>
									</div>
									<div class="col-sm-1 text-center">
										<c:choose>
											<c:when test="${partyTelNo.telephoneNumber eq company.partyContactDetails.partyTelephoneNumber.telephoneNumber}">
												<input id="primary-telephone-rb-${loopVar.index}" name="primaryTelephoneRb" type="radio" onchange="updateClientPrimaryTelephoneNumber(${loopVar.index})" checked="checked"/>									
											</c:when>
											<c:otherwise>
												<input id="primary-telephone-rb-${loopVar.index}" name="primaryTelephoneRb" type="radio" onchange="updateClientPrimaryTelephoneNumber(${loopVar.index})" />	
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
							</div>
							<div id="refreshTelephoneInput" class="clearfix margin-top">
								<div class="col-sm-6">
									<input id="new-client-telephone-number" name="newTelephoneNumber" class="form-control" type="text"/>						
								</div>
								<div class="col-sm-5">
									<select id="new-client-telephone-type" name="newTelephoneType" class="form-control">
										<option></option>
										<c:forEach items="${telNoTypes}" var="telNoType">
											<option value="${telNoType.id}">${telNoType.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-1 text-center">
									<button type="button" class="btn btn-success" id="add-new-client-telephone">Save Number</button>
								</div>
							</div>
						</div>
						<div class="clearfix">
							<div class="text-center">
								<button type="button" class="addNewTelephone btn btn-success" style="display:none">Add Telephone</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		

		<div id="address_panel">
			<div class="panel-body">
				<%-- <div class="form-group clearfix">
				NOT NEEDED YET BUT WILL BE WHEN ADDING OTHER CONTACT DETAILS  
					<div class="col-sm-4">
						<label for="NAME"><spring:message code="scratchpad.asset.name"/></label>
						<input id="NAME" name="NAME" class="form-control" type="text">
					</div>

					<div class="col-sm-4">
						<label for="POSITION"><spring:message code="finance.client.clientDetailsTab.position"/></label>
						<input id="POSITION" name="POSITION" class="form-control" type="text">
					</div>
				
				</div>
				
				<%-- NOT NEEDED YET UNTILL ADDING ANOTHER PHONE NUMBER	
	            <div class="form-group clearfix">

		          	<div class="col-sm-3">
						<label for="MOBILE_NUMBER" class=""><spring:message code="finance.client.clientDetailsTab.mobileNumber"/></label>
						<input id="MOBILE_NUMBER" name="MOBILE_NUMBER" class="form-control" type="text">
					</div>
		            
		            <div class="col-sm-3">
						<div class="checkbox">
							<label for="isPart"><spring:message code="finance.client.clientDetailsTab.outOfOursContactNumber"/></label>
							<input type="checkbox" name="isPart" class="checkbox"/>
						</div>
	                </div>
	            
	            </div>
						--%>  
			</div>
		</div>
		<div>
			<h4 class="panel-title">
				<i class="icon-arrow"></i>
				<spring:message code="finance.client.clientDetailsTab.companyInformation"/>
			</h4>
			<hr class="smallHr">
		</div>
		<div id="company_Information_panel">
			<div class="panel-body">
				<div class="form-group clearfix">
					<div class="col-sm-3">
						<label for="CLIENT_NUMBER"><spring:message code="client.clientNumber"/></label>
						<form:input path="referenceForUs" name="CLIENT_NUMBER" class="form-control" type="text"/>
					</div>
					
					<%--COMMENTED OUT UNTILL WE DECIDE IF IT IS NEEDED
					<div class="col-sm-3">
						<label for="CATEGORIES" class=""><spring:message code="finance.client.clientDetailsTab.categories"/></label>
						<form:select path="" cssClass="form-control"
							cssErrorClass="form-control error">
							<option></option>
							<form:options items="${categoriesList}" itemValue="id"
								itemLabel="name" />
						</form:select>								
					</div>--%>

					<%--COMMENTED OUT UNTILL WE DECIDE IF IT IS NEEDED
					<div class="col-sm-3">
						<label for="RATING" class=""><spring:message code="finance.client.clientDetailsTab.rating"/></label>
						<form:select path="" class="form-control" >
               			<option></option>
               				<form:options items="${ratingList}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
               			</form:select>									
					</div>--%>

				</div>
			</div>
		</div>
		<div class="clearfix">
			<div class="col-sm-1">
				<input type="submit" class="btn btn-block btn-success financial-save" value="<spring:message code="company.saveCompany"/>">
			</div>
		</div>
	</form:form>
	<hr>
	
	<div class="display-contacts" style="display:none">
		
		<div class="clearfix form-group">
			<div class="col-sm-1">
				<h4>
					<spring:message code="finance.supplier.supplierDetailsTab.contactDetails"/>
				</h4>
			</div>
			<div class="col-sm-2 pull-right">			
				<a href="${pageContext.request.contextPath}/das/contact?parentPartyId=${company.id}&view=client" class="btn btn-success thin-button pull-right">Add Contact +</a>
			</div>
		</div>					
		<table class=" table-header-bar context-opener table multi-table table-hover" data-type="ASSETS">
			<thead>
				<tr class="alternateRow">
					<th class="table-header-width"><spring:message code="finance.supplier.supplierDetailsTab.gender"/></th>
					<th class="table-header-width"><spring:message code="finance.supplier.supplierDetailsTab.surname"/></th>
					<th class="table-header-width"><spring:message code="finance.supplier.supplierDetailsTab.forenames"/></th>
					<th class="table-header-width"><spring:message code="finance.supplier.supplierDetailsTab.telephone"/></th>
					<th class="table-header-width"><spring:message code="finance.supplier.supplierDetailsTab.address"/></th>
					<th class="table-header-width"><spring:message code="finance.supplier.supplierDetailsTab.email"/></th>
					<th class="table-header-width"><spring:message code="finance.supplier.supplierDetailsTab.position"/></th>
				</tr>
			</thead>
		</table>
	
		<table class="table-container context-opener table multi-table table-hover scroll-content">
			<tbody>		
				<c:forEach var="personContacts" items="${personPartyList}" varStatus="var">
					<tr>
						<td>${personContacts.gender} </td>
						<td>${personContacts.surname}</td>
						<td>${personContacts.forenames}</td>
						<c:forEach var="telNumbers" items="${personContacts.partyTelephoneNumbers}">
							<td>${telNumbers.telephoneNumber.telNo}</td>
						</c:forEach>
						<c:forEach var="addresses" items="${personContacts.partyAddresses}">
							<td>${addresses.address.addressLine1} ${addresses.address.addressLine1} ${addresses.address.zipPostCode}</td>
						</c:forEach>
						<c:forEach var="emailList" items="${personContacts.partyEmails}">
							<td>${emailList.email.emailAddress}</td>
						</c:forEach>
						<td>${personContacts.position}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>