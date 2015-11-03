<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div class="container">
	
   <table id="listTable" class="col-sm-12 table table-bordered table-hover single-table">
   		<thead>
	   		<tr >
	   			<th><input type="checkbox" /></th>
	   			<th class="col-sm-12">Group Name</th>
	   		</tr>
   		</thead>
   		<tbody>
		    <c:forEach items="${usersLists}" var="aGroup">	
				<tr class="col-sm-12 bucketData" data-bucket-id="${aGroup.id}" data-bucket-type="${aGroup.modelType}" data-id="${aGroup.id}" data-version-number="${aGroup.versionNumber}">
			   		<th class="checkbox-holder-size"><input type="checkbox"/></th>
			   	    <td class="col-sm-6 modal-table-td-styling"> 
			   	   		<a href="${pageContext.request.contextPath}/das/group/groupContent?groupId=${aGroup.id}">${aGroup.name}</a>
			   	    </td>
			    </tr>
		    </c:forEach>
	    </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/das/group/deleteGroup?" class="btn btn-danger margin-top selected-row-dependant" data-selected-row-attrs="id versionNumber"><spring:message code="utilitly.delete"/></a>
 </div>
