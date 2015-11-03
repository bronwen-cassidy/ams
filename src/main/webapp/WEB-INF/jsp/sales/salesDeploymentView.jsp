<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container">
	<h2 class="col-sm-12" style="text-align: center">View Restricted
		to Deployment Managers</h2>
	<div class="clearfix margin-top asset-table">
		<table
			class=" table-header-bar context-opener table multi-table table-hover"
			data-type="ASSETS">
			<thead>
				<tr class="alternateRow">
					<th><input type="checkbox" /></th>
					<th style="width: 18%;"><spring:message	code="sales.landingScreen.orderReference" /></th>
					<th style="width: 16%;"><spring:message	code="sales.landingScreen.assetName" /></th>
					<th style="width: 16%;"><spring:message	code="sales.landingScreen.client" /></th>
					<th style="width: 15%;"><spring:message	code="sales.landingScreen.telephone" /></th>
					<th style="width: 29%;"><spring:message	code="sales.landingScreen.email" /></th>
					<th style="width: 10%;"><spring:message	code="sales.landingScreen.status" /></th>
				</tr>
			</thead>
		</table>
		<table
			class="table-container context-opener table multi-table table-hover scroll-content"
			data-type="ASSETPROPOSAL">
			<c:if test="${!empty proposalList}">
				<tbody>
					<c:forEach items="${proposalList}" var="assetProposal">
						<tr>
							<th class="checkbox-holder-size"><input type="checkbox" /></th>
							<td style="width: 18%;"><a href="${pageContext.request.contextPath}/das/deployment/deploymentview?proposalId=${assetProposal.id}">${assetProposal.id}</a></td>
							<td style="width: 16%;">${assetProposal.asset.name}</td>
							<td style="width: 16%;">${assetProposal.company.name}</td>
							<td style="width: 15%;">${assetProposal.telephone.telNo}</td>
							<td style="width: 29%;">${assetProposal.email.emailAddress}</td>
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
								</label>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
			<tfoot>
				<tr>
					<td colspan="9" class="load-more-assets text-center"><span
						class="caret pull-left"></span> Load more results <span
						class="caret pull-right"></span></td>
				</tr>
			</tfoot>

		</table>
	</div>
</div>