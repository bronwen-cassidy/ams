<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
	<div class="container">

	<div class="form-panel panel panel-default no-margin">

		<div class="panel-heading">
		
			<i class="fa fa-square-o"></i>
			<strong class="pull-left detail-heading">Details View</strong><a href="${pageContext.request.contextPath}/das//financial/client?partyId=${party.id}" class="btn btn-primary btn-position">Edit</a>
			<p class="pull-right small-heading">Party Id:&nbsp<strong class="detail-heading">${party.id}</strong></p>
			<p class="pull-right small-heading">UID : ${party.uid}</p>
			<p class="pull-right small-heading">Name:&nbsp<strong class="detail-heading">${party.name}</strong></p>
			
		</div>
	</div>

	<div class="inner-scroll">

		<div class="panel-body-thin">

			<div class="tabbable panel-tabs">

				<div class="tab-content">

					<div class="row">

						<div class="col-md-6 padding-bottom no-padding-left">

							<div class="panel-heading-separate col-md-12">
								PRIMARY CONTACT DETAILS
							</div>

							<div class="panel-body-thin col-md-12">
<!-- ** NOTE ** ALL FIELDS ARE ONLY HARD CODED BELOW UNTIL THEY ARE CONFIRMED WHAT WE WANT THEN WILL CHANGE TO 'spring:message'. -->
								<div class="col-md-8">
									<label class="pull-left">UID :&nbsp</label>
									<strong>${party.uid}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">Name :&nbsp</label>
									<strong>${party.name}</strong>
								</div>
								
								<%-- NEED TO FIND IF THESE ARE NEEDED	
								<div class="col-md-6">
									<label class="pull-left">Position :&nbsp</label>
									<strong></strong>
								</div>
								--%>
								
								<div class="col-md-6">
									<label class="pull-left">Telephone :&nbsp</label>
									<strong>${partyTel.telNo}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">Email :&nbsp</label>
									<strong>${partyEmail.emailAddress}</strong>
								</div>
							
								<div class="col-md-8">
									<label class="pull-left"></label>
									<strong></strong>
								</div>
	
							</div>

						</div>


						<div class="col-md-6 padding-bottom no-padding-right">

							<div class="panel-heading-separate col-md-12">
								CONTACT ADDRESS
							</div>

							<div class="panel-body-thin col-md-12">

								<div class="col-md-8">
									<label class="pull-left">Address :&nbsp</label>
									<strong> ${contactAddress.addressLine1}  </strong>
									<strong> ${contactAddress.addressLine2}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">Post Code :&nbsp</label>
									<strong> ${contactAddress.zipPostCode}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">City :&nbsp</label>
									<strong> ${contactAddress.city}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">Country :&nbsp</label>
									<strong>${contactAddress.country}</strong>
								</div>
								
								<%-- NEED TO FIND IF THESE ARE NEEDED	
								<div class="col-md-6">
									<label class="pull-left">Type Of Place :&nbsp</label>
									<strong>${contactAddress.typeOfPlace}</strong>
								</div>
								--%>

								<div class="col-md-8">
									<label class="pull-left"></label>
									<strong></strong>
								</div>
								
							</div>

						</div>
					</div>
					
					<div class="row">

						<div class="col-md-6 padding-bottom no-padding-left">

							<div class="panel-heading-separate col-md-12">
								COMPANY INFO
							</div>

							<div class="panel-body-thin col-md-12">
							
								<div class="col-md-6">
									<label class="pull-left">Company Name :&nbsp</label>
									<strong>${party.name}</strong>
								</div>
								
								<%-- NEED TO FIND IF THESE ARE NEEDED
								<div class="col-md-6">
									<label class="pull-left">Status :&nbsp</label>
									<strong></strong>
								</div>
								--%>
								
								<div class="col-md-6">
									<label class="pull-left">Date Acc Opened :&nbsp</label>
									<strong>${party.createdDate}</strong>
								</div>
								
								<div class="col-md-6">
									<label class="pull-left">Website :&nbsp</label>
									<strong>${partyWebsite.websiteUrl}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">Supplier Reference :&nbsp</label>
									<strong>${party.referenceForUs}</strong>
								</div>
								
								<%-- NEED TO FIND IF THESE ARE NEEDED 
								<div class="col-md-6">
									<label class="pull-left">Category :&nbsp</label>
									<strong>${category}</strong>
								</div>
								
								<div class="col-md-6">
									<label class="pull-left">Rating :&nbsp</label>
									<strong></strong>
								</div>
								--%>
								
									<div class="col-md-8">
									<label class="pull-left">Address :&nbsp</label>
									<strong> ${partyAaddress.addressLine1}  </strong>
									<strong> ${partyAaddress.addressLine2}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">Post Code :&nbsp</label>
									<strong> ${partyAaddress.zipPostCode}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">City :&nbsp</label>
									<strong> ${partyAaddress.city}</strong>
								</div>

								<div class="col-md-6">
									<label class="pull-left">Country :&nbsp</label>
									<strong>${partyAaddress.country}</strong>
								</div>
								<div class="col-md-8">
									<label class="pull-left"></label>
									<strong></strong>
								</div>
	
							</div>

						</div>


						<div class="col-md-6 padding-bottom no-padding-right">

							<div class="panel-heading-separate col-md-12">
								DOCUMENT INFO
							</div>

							<div class="panel-body-thin col-md-12">

								<div class="col-md-8">
									<label class="pull-left">System ID No :&nbsp</label>
									<strong></strong>
								</div>

								<div class="col-md-8">
									<label class="pull-left">Terms & Conditions Docutments :&nbsp</label>
									<strong></strong>
								</div>

								<div class="col-md-8">
									<label class="pull-left">Other Documents :&nbsp</label>
									<strong></strong>
								</div>
								
								<div class="col-md-8">
									<label class="pull-left">Notes :&nbsp</label>
									<strong></strong>
								</div>
								
								<div class="col-md-8">
									<label class="pull-left"></label>
									<strong></strong>
								</div>
								
							</div>

						</div>
					</div>

				</div>
	
			</div>
		
		</div>
		
	</div>
