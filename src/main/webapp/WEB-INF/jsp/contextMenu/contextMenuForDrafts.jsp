<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tilesx:useAttribute id="contextMenu_edit" name="contextMenu.edit"/>
<tilesx:useAttribute id="contextMenu_delete" name="contextMenu.delete"/>

<div class="contextmenu">
    <ul>
    	<li><a class="context-edit selected-row-dependant" data-selected-row-attrs="draftId" href="${pageContext.request.contextPath}/das/assetController?"><i class="fa clip-cube"></i> Edit</a></li>
        <li><a class="context-delete selected-row-dependant" data-selected-row-attrs="draftId" href="${pageContext.request.contextPath}/das/assetRegister/deleteDraft?"><i class="fa clip-cube"></i> Delete</a></li>
    </ul>
</div>
