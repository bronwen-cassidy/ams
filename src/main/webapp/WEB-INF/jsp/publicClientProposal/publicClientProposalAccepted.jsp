<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="das" uri="das-tags"%>


<div class="PCRbackground">
	<div class="PCRAcceptDecline col-sm-12"><spring:message code="publicClient.proposal.proposalAccepted"/> ${assetProposal.email.emailAddress}.</div>
</div>