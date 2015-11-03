<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h3><spring:message code="systemAdmin.user.userData.userDataCategories"/></h3>

<div class="panel-group" id="ud_accordion">
	<c:forEach var="topLevelUserDataCat" items="${topLevelUserDataCats}">
		<div class="panel panel-default">
			<div class="panel-heading accordion_First accordian-toggle"
				data-toggle="collapse" data-parent="#ud_accordion" href="#ud_collapse_${topLevelUserDataCat.id}">
				<h4 class="panel-title"><spring:message code="${topLevelUserDataCat.languageCode}"/></h4>
			</div>
			<div id="ud_collapse_${topLevelUserDataCat.id}" class="accordion-body collapse">
				<div class="accordion-inner">
					<tiles:insertTemplate template="userDataInnerLists.jsp">
						<tiles:putAttribute name="parentUserDataCat" value="${topLevelUserDataCat}"/>
					</tiles:insertTemplate>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
