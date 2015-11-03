<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tilesx:useAttribute id="parentUserDataCat" name="parentUserDataCat"/> 

<div class="panel-group" id="ud_accordion_${parentUserDataCat.id}">
	<c:forEach var="innerUserDataCat" items="${parentUserDataCat.childCategories}">
		<div class="panel panel-default">
			<div class="panel-heading accordion_First accordian-toggle" data-toggle="collapse" data-parent="#ud_accordion_${parentUserDataCat.id}" href="#ud_collapse_${innerUserDataCat.id}">
				<h4 class="panel-title"><spring:message code="${innerUserDataCat.languageCode}"/></h4>
			</div>
			<div id="ud_collapse_${innerUserDataCat.id}" class="accordion-body collapse">
				<div class="accordion-inner">
					<c:if test="${not empty innerUserDataCat.childCategories}">
					<tiles:insertTemplate template="userDataInnerLists.jsp">
						<tiles:putAttribute name="parentUserDataCat" value="${innerUserDataCat}"/>
					</tiles:insertTemplate>
					</c:if>
					<c:if test="${not empty innerUserDataCat.userDataTypes}">
						<div class="panel-body">
                        	<ul class="list-group">
							<c:forEach var="userDataType" items="${innerUserDataCat.userDataTypes}">
								<li class="list-group-item is-list" data-list-id="${userDataType.id}"><spring:message code="${userDataType.langCode}"/></li>
							</c:forEach>
							</ul>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

