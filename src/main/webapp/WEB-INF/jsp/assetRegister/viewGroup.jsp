<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="modal-header">
   <a class="close" data-dismiss="modal">×</a>
   <h3>Group</h3>
 </div>
 <div class="content-modal">
   <div class="modal-inner-scroll">
   <table class="col-sm-12 table table-bordered table-hover">
   		<tr>
   			<th>Group Name</th>
   		</tr>
	   <c:forEach items="${usersGroups}" var="aGroup">
		   <tr class="col-sm-12 bucketData" data-listId="${aGroup.id}">
		   	   <td class="col-sm-6 modal-table-td-styling"><a href="${pageContext.request.contextPath}/das/group/groupContent?groupId=${aGroup.id}">${aGroup.name}</a></td>
			   <%--<td class="col-sm-6  modal-table-td-styling">${aGroup.entityType}</td>    This may be needed--%>
		   </tr>
	   </c:forEach>
   </table>
   </div>
   
 </div>
 <div class="modal-footer">
    <a href="#" class="btn btn-danger" data-dismiss="modal"><spring:message code="modal.close"/></a>
  </div>