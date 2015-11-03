<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="">

	${userDataFieldName}<br/>
    <c:forEach var="aVal" items="${userDataField}">
    	${aVal.id} ${aVal.value}<br/>
    </c:forEach>

</div><!-- end user data -->