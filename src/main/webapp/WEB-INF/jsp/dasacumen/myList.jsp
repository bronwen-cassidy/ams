<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div class="container">
	
   <table id="listTable" class="col-sm-12 table table-bordered table-hover single-table">
   		<thead>
	   		<tr >
	   			<th><input type="checkbox"  /></th>
	   			<th><spring:message code="myList.listName"/></th>
	   			<th><spring:message code="myList.entityType"/></th>
	   		</tr>
   		</thead>
   		<tbody>
		    <c:forEach items="${usersLists}" var="aList">	
				<tr class="col-sm-12 bucketData" data-bucket-type="${aList.entityType}" data-bucket-id="${aList.id}" data-id="${aList.id}" data-version-number="${aList.versionNumber}">
			   		<th class="checkbox-holder-size"><input type="checkbox"/></th>
			   	    <td class="col-sm-6 modal-table-td-styling"> 
			   	   		<a href="${pageContext.request.contextPath}/das/list/list?listId=${aList.id}">${aList.name}</a>
			   	    </td>
				    <td class="col-sm-6  modal-table-td-styling">${aList.entityType}</td>
			    </tr>
		    </c:forEach>
	    </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/das/list/deleteList?" class="btn btn-danger margin-top pull-right selected-row-dependant" data-selected-row-attrs="id versionNumber"><spring:message code="utilitly.delete"/></a>
 </div>
