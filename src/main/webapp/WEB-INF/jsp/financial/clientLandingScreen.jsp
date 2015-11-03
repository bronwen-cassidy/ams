<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	
	<div class="clearfix">
		<div class="col-sm-3 pull-left no-padding">
		    <select class="asset-filter form-control"><!-- @TODO Currently using asset js BUT we need to setup new JS file once it is decided on what is required to load/paginate data. -->
		        <option disabled selected><spring:message code="placeholder.filterBy"/></option>
		        <option value="C">Categories @TODO</option>
		    </select>
		</div>

		<div class="col-sm-6 pull-right no-padding">
		    <a href="${pageContext.request.contextPath}/das/financial/client" class="btn btn-default pull-right"><spring:message code="sideMenu.finance.createNewClient"/></a>
		    <a href="${pageContext.request.contextPath}/das/financial/client/searchClient" class="margin-right btn btn-default margin-right-three pull-right"><spring:message code="search.submitSearchButtonName"/></a>
		</div>
	</div>
	
	<table class="table margin-top table-bordered table-hover asset-table">
		<thead>
			<tr>
				<th><spring:message code="finance.client.clientDetailsTab.name"/></th>
				<th><spring:message code="finance.client.clientDetailsTab.clientAddress"/></th>
				<th><spring:message code="finance.client.clientDetailsTab.email"/></th>
			</tr>
		</thead>
		<c:if test="${!empty partyList}">
		<tbody>
			<c:forEach items="${partyList}" var="party">
				<tr>            
					<td><a href="${pageContext.request.contextPath}/das/financial/client/readOnlyDetails?partyId=${party.id}">${party.name}</a></td>
					<td>${party.partyContactDetails.partyAddress.address.addressLine1}, ${party.partyContactDetails.partyAddress.address.addressLine2}, ${party.partyContactDetails.partyAddress.address.city}</td>
					<td>${party.partyContactDetails.partyEmail.email.emailAddress}</td>
				</tr>                         
			</c:forEach>	
		</tbody>
		</c:if>
	</table>
</div>
