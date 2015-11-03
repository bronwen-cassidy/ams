<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3>Lists</h3>

<hr>
<ul class="list-menu list-menu-parent">

	<c:forEach var="listParents" items="${topLevelUserDataCats}">
    <li class="list-menu-item has-submenu parent">
        <span class="sub-menu-heading">${listParents.name}</span>
        <ul class="list-menu-submenu">
	        
	            <c:forEach var="listChildren" items="${listParents.childCategories}">
	            <li class="list-menu-subitem has-submenu">
	            	<span class="sub-menu-heading">${listChildren.name}</span>
	            	<ul class="list-menu-submenu">
		            	<c:forEach var="innerChildren" items="${listChildren.childCategories}">
			            <li class="list-menu-subitem has-submenu">
			            	<span class="sub-menu-heading">${innerChildren.name}</span>
			            	<ul class="list-menu-submenu">
			            	
			            		<c:forEach var="lists" items="${innerChildren.userDataTypes}">
			            			<li class="list-menu-subitem is-list" data-list-id="${lists.id}">${lists.name}</li>
			            		</c:forEach>
			            		
			            	</ul>
			            </li>
			            </c:forEach>
	            		
	            	</ul>
	            </li>
	            </c:forEach>
	            
        </ul>
    </li>
    </c:forEach>
    
</ul>