<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container">
	<div class="form-panel panel panel-default">
		<div class="panel-heading">
			<i class="fa fa-square-o"></i>
			
			<tiles:getAsString name="tabsTitle" ignore="false"/>
			<tilesx:useAttribute id="theTabs" name="tabs"/>
			<tilesx:useAttribute id="activeTab" name="activeTab"/>
		
			<c:if test="${asset.criticalAsset == true}">
				<label class="margin-left critical-warning">
					You are editing a critical asset
				</label>
			</c:if>
				
			<c:if test="${not empty asset.id && activeTab!='assetRegister.asset.menuTab.summary'}">
				<div class="save-asset">
					<a class="margin-left btn-success" type="button" onclick="saveAsset('/das/assetController/saveAsset?tab=${activeTab}')">
						Save Asset
					</a>
				</div>
			</c:if>
			
			<div class="tabbable panel-tabs">
			    <ul class="form-tabs nav nav-tabs">
					<c:forEach var="tab" items="${theTabs}">
					  	<c:choose>
					  		<c:when test="${activeTab == tab.role}">
					  			<li class="active">
					  				<a href="${pageContext.request.contextPath}/${tab}">
					  					<spring:message code="${tab.role}"/>
					  				</a>
					  			</li>
					  		</c:when>
					  		<c:otherwise>
					  			<li>
					  				<a href="${pageContext.request.contextPath}/${tab}">
					  					<spring:message code="${tab.role}"/>
					  				</a>
					  			</li>
					  		</c:otherwise>
					  	</c:choose>
					</c:forEach>	        
			    </ul>
		    </div>
		</div>
		<div class="panel-body inner-scroll">
		     <tiles:insertAttribute name="tabContent"/>
	    </div>
    </div>
</div>