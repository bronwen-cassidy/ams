<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="das" uri="das-tags"%>

<div class="PCRbackground">
	<div class="PCRspacer">
		<div class="col-sm-12">
			<div class="PCRLeaseCompanyLogo col-sm-3 pull-left"></div>

			<div class="PCRLeaseCompanyInfo col-sm-8 pull-right"><div class="col-sm-12"><p class="col-sm-12" style="text-align:center">Ferguson Group</p><p class="col-sm-6 PCRLeaseCompanyInfoText pull-left" style="margin-left:10vw">UK Headquarters<br>Ferguson House,<br>
Midmill Business Park,<br>
Kintore, Aberdeen<br>
AB51 0QG<br></p><p class="col-sm-6 PCRLeaseCompanyInfoText pull-right" style="margin-right:10vw">UK Divisional Office<br>Ferguson Group Ltd<br>
Denmore Road,<br>
Bridge of Don, Aberdeen,<br>
AB51 0QG</p></div></div>
		</div>

		<div class="container col-sm-12" style="text-align: center">
			<div class="col-sm-6 pull-left" style="margin-top: 6vh">
				<div class="form-group clearfix">
					<div class="col-sm-12">
						<label for="customerName" class=""> <spring:message
								code="publicClient.proposal.customerName" />
						</label> <strong>${assetProposal.company.name}</strong>
					</div>
					<div class="col-sm-12">
						<label for="telephone" class=""> <spring:message
								code="publicClient.proposal.telephone" />
						</label> <strong>${assetProposal.contactParty.partyContactDetails.partyTelephoneNumber.telephoneNumber.telNo}</strong>
					</div>
					<div class="col-sm-12">
						<label for="email" class=""> <spring:message
								code="publicClient.proposal.email" />
						</label> <strong>${assetProposal.contactParty.partyContactDetails.partyEmail.email.emailAddress}</strong>
					</div>
				</div>
			</div>
			<div class="col-sm-6 pull-right" style="margin-top: 4vh">

				<div class="form-group clearfix">
					<div class="col-sm-12">
						<label for="addressLineOne" class=""> <spring:message
								code="publicClient.proposal.addressLineOne" /></label> <strong>${assetProposal.address.addressLine1}</strong>
					</div>

					<div class="col-sm-12">
						<label for="town" class=""><spring:message
								code="publicClient.proposal.addressLineTwo" /></label> <strong>${assetProposal.address.addressLine2}</strong>
					</div>
					<div class="col-sm-12">
						<label for="city" class=""><spring:message
								code="publicClient.proposal.addressCity" /></label> <strong>${assetProposal.address.city}</strong>
					</div>
					<div class="col-sm-12">
						<label for="postCode" class=""><spring:message
								code="publicClient.proposal.postcode" /></label> <strong>${assetProposal.address.zipPostCode}</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="PCRFormContainerTwo"></div>
		<div class="col-xs-12" style="margin-left: auto; margin-right: auto">
			<div class="table-responsive col-sm-12">
				<table class="table-striped table-bordered col-sm-12"
					style="text-align: center">
					<thead>
						<tr>
							<th style="text-align: center">Item</th>
							<th style="text-align: center">${assetProposal.asset.name}</th>
							<th></th>
						</tr>

					</thead>
					<tbody>
						<tr>
							<td>Quantity</td>
							<td>1</td>
							<td></td>
						</tr>

						<tr>
							<td>Date</td>
							<td>${assetProposal.assetSchedule.leaseCommences} - ${assetProposal.assetSchedule.leaseExpires}</td>
							<td></td>
						</tr>
						
						<c:forEach items="${assetProposal.assetProposalExtras}" var="assetProposalExtra">
       						<c:forEach items="${assetProposal.asset.leaseOut.leaseOutExtras}" var="leaseOutExtras">
					            <c:if test="${assetProposalExtra.leaseOutExtraId eq leaseOutExtras.id}">
						            <tr>
						            	<td>Lease Out Extra</td>
						            	<td>${leaseOutExtras.leaseOutExtras.name}</td>
						            	<td>${leaseOutExtras.extraCost} </td>
						            </tr>
					            </c:if>
					         </c:forEach>
				       	</c:forEach> 							
						
						<%-- <c:forEach var="extra" items="${assetProposal.asset.leaseOutExtras}" varStatus="var">
						--%>
		
						<%-- </c:forEach> --%>
						

					</tbody>
				</table>

				<div class="table-responsive" style="margin-top: 2vh">
					<table class="table table-striped table-bordered"
						style="text-align: center">
						<thead>
							<tr>
								<th>Proposed Price:</th>
								<th>${assetProposal.totalProposedCost}</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<div class="PCRprewarning col-sm-12"><spring:message code="publicClient.proposal.submitPreWarning"/></div>
		<div class="PCRbuttonwrap col-sm-12">
			<div class="col-sm-6">
				<a
					href="${pageContext.request.contextPath}/das/publicClientProposal/terms"
					class="PCRButtonOne btn no-radius btn-success pull-right">
					<spring:message code="publicClient.proposal.buttonContinue"/> </a>
			</div>
			<div class="col-sm-6 pull-right">
				<a
					href="${pageContext.request.contextPath}/das/publicClientProposal/decline"
					class="PCRButtonOne btn no-radius btn-danger pull-left" style="">
					<spring:message code="publicClient.proposal.buttonDecline"/> </a>
			</div>
		</div>
	</div>
</div>
