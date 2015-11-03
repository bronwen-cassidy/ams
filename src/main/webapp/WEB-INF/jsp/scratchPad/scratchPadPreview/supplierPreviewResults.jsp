<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${empty supplierItem}">
		No supplier found
	</c:when>
	<c:otherwise>
		<li id="supplier_name" class="list-group-item" data-type="supplier">
			${supplierItem.name}
		</li>
		<li id="supplier_address" class="list-group-item" data-type="supplier">
				${address.addressLine1}<br>
				${address.addressLine2}<br>
				${address.addressLine3}<br>
				${address.zipPostCode}<br>
				${address.country}<br>
				${address.typeOfPlace}	
		</li>				
	</c:otherwise>
</c:choose>