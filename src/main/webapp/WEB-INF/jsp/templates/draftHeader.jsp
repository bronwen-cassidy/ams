<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>



<div class="navbar navbar-inverse navbar-fixed-top">

  <div class="container">
  
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

      <a class="navbar-brand" href="${pageContext.request.contextPath}/das/splash/">Aspect</a>
      
    </div><!-- /end navbar header -->

    <div class="navbar-tools">
    
    		<a href="<c:url value='/j_spring_security_logout'/>">
		  		<button class="btn logout-button logout"><spring:message code="app.logout"/><i class="fa fa-sign-out"></i></button>
		  	</a>

	      <form action="${pageContext.request.contextPath}/das/assetSearch/search" method="get" id="search" class="navbar-form pull-right">
	        <input name="query" type="text" class="form-control" placeholder="<spring:message code="utility.search"/>">
	      </form>
	
	      <!-- start: TOP NAVIGATION MENU -->
	      <ul class="nav navbar-right">
	
			<!-- start: MENU ITEMS -->
			<%--  <tilesx:useAttribute id="navLinks" name="navLinks"/>
			<c:forEach var="aNav" items="${navLinks}">
				<c:set var="url" value="${pageContext.request.contextPath}/${aNav}" />
		        <li><a href="${url}"><spring:message code="${aNav.role}"/></a></li>
		       
			</c:forEach> --%>
			
			<li class="navbar-right-main-link"><a class="utility-head-links"><spring:message code="header.create"/></a>
		        <ul class="utility-dropdown">
			        <li><a class="utility-droplinks" href="${pageContext.request.contextPath}/das/assetController"><spring:message code="header.utilitybar.createrecord.newasset"/></a></li>
			        <li><a class="utility-droplinks" href="${pageContext.request.contextPath}/das/financial/supplier/create"><spring:message code="header.utilitybar.createrecord.newsupplier"/></a></li>
			        <li><a class="utility-droplinks" href="${pageContext.request.contextPath}/das/financial/client/create"><spring:message code="header.utilitybar.createrecord.newclient"/></a></li>
		        </ul>
	      	</li>
	      	<li class="navbar-right-main-link"><a class="utility-head-links"><spring:message code="header.file"/></a>
		        <ul class="utility-dropdown">
			       <li><a class="utility-droplinks" href=""><spring:message code="header.utilitybar.file.print"/></a></li>
			       <li><a class="utility-droplinks" data-toggle="modal" data-target="#modal" href="${pageContext.request.contextPath}/das/email/sendEmail"><spring:message code="header.utilitybar.file.email"/></a></li>
			       <li><a class="utility-droplinks" href=""><spring:message code="header.utilitybar.file.sms"/></a></li>
			       <li><a class="utility-droplinks" href=""><spring:message code="header.utilitybar.file.exporttoexcel"/></a></li>
		        </ul>
	      	</li>
	      	<li class="navbar-right-main-link"><a class="utility-head-links"><spring:message code="header.edit"/></a>
		        <ul class="utility-dropdown">
			        <li><a class="utility-droplinks" href=""><spring:message code="header.utilitybar.edit.clone"/></a></li>
		        </ul>
	      	</li>


        <!-- end: MENU ITEMS -->

        <!-- start: DROPDOWN MENU ITEMS -->
        <li class="dropdown">

          <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" data-close-others="true" href="#">
            <i class="clip-list-5"></i>
            <span class="badge"> 12</span>
          </a>

          <ul class="dropdown-menu">

            <li>
              <span class="dropdown-menu-title"> You have 12 pending tasks</span>
            </li>

            <li>
              <div class="drop-down-wrapper">
                <ul>

                  <li>
                    <a class="todo-actions" href="javascript:void(0)">
                      <i class="fa fa-square-o"></i>
                      <span class="desc" style="opacity: 1; text-decoration: none;">Staff Meeting</span>
                      <span class="label label-danger pull-right" style="opacity: 1;"> today</span>
                    </a>
                  </li>

                </ul>

              </div>
              
            </li>

            <li class="view-all">
              <a href="javascript:void(0)">
                See all tasks <i class="fa fa-arrow-circle-o-right"></i>
              </a>
            </li>

          </ul>

        </li>
        <!-- end: DROPDOWN MENU ITEMS -->

      </ul>
      <!-- end: TOP NAVIGATION MENU -->
    </div>

  </div>

</div>