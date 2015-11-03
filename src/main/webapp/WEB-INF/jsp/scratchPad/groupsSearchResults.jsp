<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags"%>

<c:if test="${empty groupSearchResults}">
	No groups found
</c:if>

<c:forEach items="${groupSearchResults}" var="group" varStatus="loopvar">
	<das:draggable var="draggableAttrs" bean="${group}"/>
	<c:set var="draggable_id" value="draggable_${group.modelType}_${group.id}_${loopvar.index}"/>
	<li id="${draggable_id}" class="list-group-item draggable">
		${group.name}
	</li>
	<script>
	$('#${draggable_id}').data({${draggableAttrs}});
	</script>
</c:forEach>
<script>
$('.draggable').draggable({
	revert: 'invalid',
    helper: 'clone',
    stack: '.draggable',
    appendTo: 'body',
    containment: 'window',
    scroll: false
});
</script>