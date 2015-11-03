<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<ul class="nav nav-tabs">
	<tilesx:useAttribute id="tabList" name="tabList"/>
	<tilesx:useAttribute id="activeTab" name="activeTab" ignore="true"/>
			
	<c:forEach var="aTabs" items="${tabList}">
		<c:set var="url" value="${pageContext.request.contextPath}/${aTabs}" />

		<c:choose>
			<c:when test="${activeTab == aTabs.role}">
				<li class="active"><a href="${url}" ><spring:message code="${aTabs.role}"/></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${url}" ><spring:message code="${aTabs.role}"/></a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</ul>