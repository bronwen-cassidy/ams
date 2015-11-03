<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags"%>

<c:if test="${empty itemSearchResults}">
	No assets found
</c:if>

<c:forEach items="${itemSearchResults}" var="item" varStatus="loopvar">
	<das:draggable var="droppableAttrs" bean="${item}"/>
	<c:set var="draggable_id" value="draggable_${item.modelType}_${item.id}_${loopvar.index}"/>
	<li id="${draggable_id}" class="draggable list-group-item" >
		${item.displayName}
	</li>
	<script>
	$('#${draggable_id}').data({${droppableAttrs}});
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