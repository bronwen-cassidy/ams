<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<button class="btn btn-success pull-right add-list-item"><i class="glyphicon glyphicon-plus"></i></button>
<h3>Editing <strong class="edit-list-title">${userDataFieldName}</strong></h3>

<hr>

<form class="list-form">
	<c:forEach var="listItem" items="${listItems}">
		<div class="form-group">
		    <div class="input-group list-option">
		    	<span class="input-group-btn move-list-item">
		    		<button class="btn btn-default move-list-item-btn" type="button">
		    			<i class="glyphicon glyphicon-resize-vertical"></i>
		    		</button>
		    	</span>
		    	<input type="hidden" name="listItemId" value="${listItem.id}">
				<input type="hidden" name="listItemVersion" value="${listItem.versionNumber}">
				<input type="hidden" name="listItemActive" value="${listItem.active}">
				<input type="hidden" name="listItemDataTypeId" value="${listItem.userDataTypeId}">
				<input type="text" name="listItemValue" class="form-control" value="${listItem.name}" readonly>
		        <span class="input-group-btn input-modify-btn-group">
		            <button class="btn btn-success save-list-item-btn hide" type="button">
		                <i class="glyphicon glyphicon-ok"></i>
		            </button>
		            <button class="btn btn-warning edit-list-item-btn" type="button">
		                <i class="glyphicon glyphicon-pencil"></i>
		            </button>
		            <button class="btn btn-danger remove-list-item-btn" type="button">
		                <i class="glyphicon glyphicon-remove"></i>
		            </button>
		        </span>
		    </div>
		</div>
	</c:forEach>
</form>