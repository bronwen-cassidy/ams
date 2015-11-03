<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
console.log("view list page being loaded");

$('#modal-header').ready(function() {
	$('#listGroupForm').submit(function(event) {
			console.log("submitting listGroupForm");
			event.preventDefault();
		});
});
</script>

<div class="modal-header">
	<div class="clearfix">
	<a class="close" data-dismiss="modal">×</a>
	<h3>Lists</h3>
	

	</div>
	
	
	
   	

 </div>
 <div class="content-modal">
   <div class="modal-inner-scroll">
   <table class="col-sm-12 table table-bordered table-hover">
   		<tr>
   			<th>List Name</th>
   			<th>Entity Type</th>
   		</tr>
	   <c:forEach items="${usersLists}" var="aList">
		   <tr class="col-sm-12 bucketData" data-listId="${aList.id}">
		   	   <td class="col-sm-6 modal-table-td-styling"><a href="${pageContext.request.contextPath}/das/list/list?listId=${aList.id}">${aList.name}</a></td>
			   <td class="col-sm-6  modal-table-td-styling">${aList.entityType}</td>
		   </tr>
	   </c:forEach>
   </table>
   </div>
   
 </div>
 <div class="modal-footer">	
    <a href="#" class="btn btn-danger" data-dismiss="modal"><spring:message code="modal.close"/></a>
  </div>
