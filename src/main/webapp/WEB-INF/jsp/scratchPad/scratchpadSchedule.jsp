<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib prefix="das" uri="das-tags"%>
<div>
<c:choose>
	<c:when test="${empty assetProposalItems}">
		<spring:message code="scratchpad.sales.schedule.empty"/>
	</c:when>
	<c:otherwise>
		<c:forEach items="${assetProposalItems}" var="assetProposal">
			<ul class="list-group panel-body search-panel-body">
				<li id="schedule_name" class="list-group-item" data-type="assetProposal">
					${assetProposal.asset.name}
				</li>
				<li id="proposal_dates" class="list-group-item" data-type="assetProposal">
					<spring:message code="scratchpad.sales.schedule.leaseCommences"/> <br>
					<spring:eval expression="assetProposal.assetSchedule.leaseCommences"/><br><br>
					<spring:message code="scratchpad.sales.schedule.leaseExpires"/><br>
					<spring:eval expression="assetProposal.assetSchedule.leaseExpires"/><br><br>
					<spring:message code="scratchpad.sales.schedule.company"/><br>
					${assetProposal.company.name}<br><br>
					<spring:message code="scratchpad.sales.schedule.location"/><br>
					${assetProposal.address.addressLine1}<br>
					${assetProposal.address.addressLine2}<br>
					${assetProposal.address.addressLine3}<br>
					${assetProposal.address.zipPostCode}<br>
					${assetProposal.address.city}
				</li>
			</ul>
		</c:forEach>				
	</c:otherwise>
</c:choose>
</div>