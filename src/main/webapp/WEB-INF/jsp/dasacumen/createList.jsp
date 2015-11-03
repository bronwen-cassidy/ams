<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
$('#modal-header').ready(function() {
	$('#listGroupForm').submit(function(event) {
		console.log("submitting listGroupForm");
		event.preventDefault();
		
		var input = $("#name").val();
		
		var formAsArray = $('#listGroupForm').serializeArray();
		var entityIds = bucket.rows.getSelectedRowsAttr('bucketId');
		
		var entityItems = { name:'entityIds', value:entityIds};
		var entityType = { name:'entityType', value:bucket.rows.type};
		
		formAsArray.push(entityItems);
		formAsArray.push(entityType);
		
		if(!(input=="")){
			$.ajax({
				url : $("#listGroupForm").attr("action"),
				data : formAsArray,
				type : "POST",
				success : function(response) {
		  		    console.log(response); 
		  		  	$('#modalMessages').append("List created");
		  		  	
		  		  	$('#modal').delay(10000).modal('hide');
				},
					//Below used for debugging any faults:-
				error : function(xhr, status, error) {
					console.log("failed to save list ");
				} 
			});
		}
		else{
			$.ajax({
				success : function(response) {
				     console.log(response); 
				 	$('#modalMessages').append("You Must Give The List a Name");
				},
				//Below used for debugging any faults:-
				error : function(xhr, status, error) {
				console.log("failed to save list ");
				}
			});
		}
	});
	/* Because this is a modal, need to attach the on click event handler here.
	*/
 	 $("a.selected-rows-dependant").click(function(event){
 		event.preventDefault();
		amendSelectedRowsDataToLink($(this));	
			$.ajax({
				url:$(this).attr("href"),
				success : function(response) {
				     console.log(response); 
				    $('#modal').delay(10000).modal('hide');
				},
				//Below used for debugging any faults:-
				error : function(xhr, status, error) {
				console.log("failed to save list ");
				}
			});
		});
 	});  
</script>

<div class="modal-header">
	<div class="clearfix  form-group">
		<a class="close" data-dismiss="modal">—</a>
		<h3>Create List</h3>
	</div>
	<div class="input-group">

		<label class="input-group-btn"> 
			<form:form action="${pageContext.request.contextPath}/das/list/createNewList" modelAttribute="entityList" id="listGroupForm" class="form-horizontal" role="form" method="post" name="createForm">

				<span class="no-radius input-group-addon create-icon"><i
					class="fa fa-pencil"></i></span>
				<input id="name" name="name" class="create-list-input" type="text"
					placeholder="Create List Name...">

				<input class="btn no-radius btn-success" type="submit"
					value="Create">

			</form:form>

		</label>
	</div>
	<div id="modalMessages"></div>
</div>
<div class="content-modal">
	<div class="modal-inner-scroll">
	<div id="modalMessages"></div>
		<table class="col-sm-12 table table-bordered table-hover">
			<tr class="col-sm-12">
				<th>List Name</th>
				<th>Entity Type</th>
			</tr>
			<c:forEach items="${usersLists}" var="aList">
					<tr>
						<td class="col-sm-6 modal-table-td-styling">
						<a class="selected-rows-dependant" data-selected-row-attrs="bucketId=entityIds" href="${pageContext.request.contextPath}/das/list/addToList?id=${aList.id}&userId=${aList.userId}&entityType=${aList.entityType}&name=${aList.name}&versionNumber=${aList.versionNumber}&">${aList.name}</a>
						</td>
						<td class="col-sm-6 modal-table-td-styling">${aList.entityType}</td>
					</tr>
			</c:forEach>
		</table>
	</div>

</div>
<div class="modal-footer">
	<!-- <a href="#" class="btn btn-danger" data-dismiss="modal"><spring:message code="modal.close"/></a>
    <a href="#" class="btn btn-success"><spring:message code="modal.saveChanges"/></a> -->
</div>
