<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tilesx:useAttribute id="contextMenu_open" name="contextMenu.open"/>
<tilesx:useAttribute id="contextMenu_clone" name="contextMenu.clone"/> 
<tilesx:useAttribute id="contextMenu_edit" name="contextMenu.edit"/>
<tilesx:useAttribute id="contextMenu_copy" name="contextMenu.copy"/> 
<tilesx:useAttribute id="contextMenu_paste" name="contextMenu.paste" />
<tilesx:useAttribute id="contextMenu_multiEdit" name="contextMenu.multiEdit"/>
<tilesx:useAttribute id="contextMenu_multiAttach" name="contextMenu.multiAttach"/> 
<tilesx:useAttribute id="contextMenu_addToList" name="contextMenu.addToList"/> 
<tilesx:useAttribute id="contextMenu_addToGroup" name="contextMenu.addToGroup"/> 
 

<div class="contextmenu">
    <ul>
    	<li><a class="context-open" href="#"><i class="fa clip-folder-open"></i> Open</a></li>
        <hr>
        <li><a class="context-edit selected-row-dependant" data-selected-row-attrs="assetId" href="${pageContext.request.contextPath}/das/assetController/cloneAsset?"><i class="fa clip-cube"></i> Clone</a></li>
        <li><a class="context-edit selected-row-dependant" data-selected-row-attrs="assetId" href="${pageContext.request.contextPath}/das/assetController?"><i class="fa clip-cube"></i> Edit</a></li>
        <li><a href="#"><i class="fa clip-copy"></i> Copy</a></li>
        <li><a href="#"><i class="fa clip-paste"></i> Paste</a></li>
        <li><a href="#"><i class="fa clip-cube-2"></i> Multi Edit</a></li>
        <li><a href="#"><i class="fa clip-paperclip"></i> Multi Attach</a></li>
        <hr>
        <li><a data-toggle="modal" data-target="#modal" href="${pageContext.request.contextPath}${contextMenu_addToList}"><i class="fa fa-plus"></i> Add to lists</a></li>
        <hr>
        <li><a data-toggle="modal" data-target="#modal" href="${pageContext.request.contextPath}${contextMenu_addToGroup}"><i class="fa fa-plus-square"></i> Add to group</a></li>
    </ul>
</div>
