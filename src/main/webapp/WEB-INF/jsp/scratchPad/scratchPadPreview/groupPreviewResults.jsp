<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${empty groupItem}">
		No groups found
	</c:when>
	<c:otherwise>
		<c:forEach items="${groupItem}" var="item">
			<li id="supplier_name" class="list-group-item" data-type="supplier">
				${item.name}
			</li>
			<br>
		</c:forEach>
	</c:otherwise>
</c:choose>
	