<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${empty custodianItem}">
		No custodian found
	</c:when>
	<c:otherwise>
		<li id="custodian_name" class="list-group-item" data-type="custodian">
			${custodianItem.name}
		</li>
		<li id="custodian_address" class="list-group-item" data-type="custodian">
				${address.addressLine1}<br>
				${address.addressLine2}<br>
				${address.addressLine3}<br>
				${address.zipPostCode}<br>
				${address.country}<br>
				${address.typeOfPlace}	
		</li>	
	</c:otherwise>
</c:choose>