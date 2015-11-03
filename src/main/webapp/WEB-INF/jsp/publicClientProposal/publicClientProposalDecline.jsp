<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="PCRbackground">
<h1 class="PCRDeclineText1 col-sm-12">You have declined this proposal.</h1>
<h2 class="PCRDeclineText2 col-sm-12">Please enter the reason for rejecting the proposal sent.</h2> 
<form:form action="${pageContext.request.contextPath}/das/publicClientProposal/submitDeclineForm" class="col-sm-12" role="form" method="post" modelAttribute="assetProposal">
<div class="col-sm-12">
<form:input path="declineComment" method="POST" class="PCRDeclineTextBox" placeholder="Enter your text here..."></form:input> </div>
<input type="submit" value="Submit"/ class="PCRDeclineSubmitButton">
</form:form>
</div>