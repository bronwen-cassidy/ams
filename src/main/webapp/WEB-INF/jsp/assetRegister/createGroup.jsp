<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
$('#modal-header').ready(function() {
	$('#listGroupForm').submit(function(event) {
			console.log("submitting listGroupForm");
			event.preventDefault();
			
			var formAsArray = $('#listGroupForm').serializeArray();
			var assetIds = bucket.rows.getSelectedRowsAttr('assetId');
			var groupItems = { name:'assetIds', value:assetIds };
			formAsArray.push(groupItems);
			$.ajax({
				url : $("#listGroupForm").attr("action"),
				data : formAsArray,
				type : "POST",
				success : function(response) {
		  		    console.log(response); 
		  		  	//$('#modal').modal()
		  		  	$('#modalMessages').append("Group created");
		  		  	
		  		  	$('#modal').delay(10000).modal('hide');
				},
 				//Below used for debugging any faults:-
				error : function(xhr, status, error) {
					console.log("failed to create Group ");
				} 
			});
		});
	/* Because this is a modal, need to attach the on click event handler here.
	*/
	 $("a.selected-rows-dependant").click(function(event){
		amendSelectedRowsDataToLink($(this));	
		event.preventDefault();
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
		<a class="close" data-dismiss="modal">×</a>
		<h3>Create Group</h3>	
	</div>
	<div class="input-group">
	    
		<label class="input-group-btn">

			<form:form action="${pageContext.request.contextPath}/das/group/createNewGroup" modelAttribute="group" id="listGroupForm" class="form-horizontal" role="form" method="post">
				
				<span class="no-radius input-group-addon create-icon"><i class="fa fa-pencil"></i></span>
				<input id="name" name="name" class="create-list-input" type="text" placeholder="Create Group Name...">
				
        		<input class="btn no-radius btn-success" type="submit" value="Create">
        		
        	</form:form>
    
      	</label>
	</div>
	<div id="modalMessages">
	</div>
</div>
<div class="content-modal">
   <div class="modal-inner-scroll">
   <table class="col-sm-12 table table-bordered table-hover">
   		<tr class="col-sm-12">
   			<th>Group Name</th>
   		</tr>
	   <c:forEach items="${usersGroups}" var="aGroup">
		   <tr>
				<td class="col-sm-6 modal-table-td-styling">
				<a class="selected-rows-dependant" data-selected-row-attrs="assetId=assetIds" href="${pageContext.request.contextPath}/das/group/addToGroup?id=${aGroup.id}&name=${aGroup.name}&versionNumber=${aGroup.versionNumber}&">${aGroup.name}</a>
				</td>
		   </tr>
	   </c:forEach>
   </table>
   </div>
   
 </div>
 <div class="modal-footer">
    <!-- <a href="#" class="btn btn-danger" data-dismiss="modal"><spring:message code="modal.close"/></a>
    <a href="#" class="btn btn-success"><spring:message code="modal.saveChanges"/></a> -->
  </div>
