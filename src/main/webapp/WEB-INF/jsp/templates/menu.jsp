<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="navbar-content">
	<div class="main-navigation navbar-collapse collapse">
		<div class="profile">
		  	<div class="login-text">
		  	</div>
	  	</div>
	  <hr>
	  	<div class="sidebar-toggler">
	  	   	<a href="#" class="menu-toggle">
	  	   		<i class="fa fa-bars"></i>
	  		</a>
	  	</div>
	  	
	    <!-- start: MAIN NAVIGATION MENU -->
	    <ul class="main-navigation-menu">
			<tilesx:useAttribute id="menuList" name="menuList" />
			<tilesx:useAttribute id="activeMenu" name="activeMenu" ignore="true" />
	
			<c:forEach var="aMenu" items="${menuList}">
				<c:choose>
					<c:when test="${activeMenu == aMenu.role}">
						<li class="active">
							<a href="${pageContext.request.contextPath}/${aMenu}">
								<span class="title">
									<spring:message code="${aMenu.role}"/>
								</span>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${pageContext.request.contextPath}/${aMenu}">
								<span class="title">
									<spring:message code="${aMenu.role}"/>
								</span>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
    <!-- end: MAIN NAVIGATION MENU -->
	</div>
</div>