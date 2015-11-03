<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:if test="${empty proposalList}">
	<td colspan="9" class="no-proposals-found">No proposals found</td>	
</c:if>
<input type="hidden" class="total-results" value="${searchTotalCount}" />
<c:forEach items="${proposalList}" var="assetProposal">
<tr> 
	<th class="checkbox-holder-size"><input type="checkbox" /></th>   
	<td style="width:18%;"><a href="${pageContext.request.contextPath}/das/proposal/proposalDetails?proposalId=${assetProposal.id}">${assetProposal.id}</a></td>
	<td style="width:16%;">${assetProposal.asset.name}</td>
	<td style="width:16%;">${assetProposal.company.name}</td>
	<td style="width:15%;">${assetProposal.contactParty.partyContactDetails.partyTelephoneNumber.telephoneNumber.telNo}</td>
	<td style="width:29%;">${assetProposal.contactParty.partyContactDetails.partyEmail.email.emailAddress}</td>
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