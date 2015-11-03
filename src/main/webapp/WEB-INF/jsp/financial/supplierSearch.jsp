<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="das" uri="das-tags" %>

<spring:message var="sysGenPlaceHolder"
	code="placeholder.systemGenerated" />

<div class="tab-content">

	<div id="general_tab" class="tab-pane active">

		<form:form action="${pageContext.request.contextPath}/das/financial/supplier/searchSupplier" modelAttribute="supplierClientSearch" class="form-horizontal" role="form">

			<input type="submit" value="Search Supplier">

			<div class="form-group clearfix">

				<div class="col-sm-6">
			        <label for="" class=""><spring:message code="assetRegister.asset.uid"/></label>
		            <form:input path="uid" class="form-control" />
				</div>

				<div class="col-sm-6">
		        	<label for="" class=""><spring:message code="assetRegister.asset.createdBy"/></label>
		            <input placeholder="" class="form-control" type="text" id="created_by"/>
				</div>

			</div>

			<div class="form-group clearfix">

				<div class="col-sm-3">
	                <label for="name"><spring:message code="assetRegister.asset.generalTab.name"/></label>
	                <form:input path="name" class="form-control asset droppable" autocomplete="off" data-type="asset" data-fields="manufacturername manufacturerpn"/>
	            </div>

				<div class="col-sm-3">
	            	<label for=""><spring:message code="assetRegister.asset.generalTab.serialNumber"/></label>
	                <form:input path="" type="text" class="form-control asset" autocomplete="off"/>
				</div>

				<div class="col-sm-3">
	                <label for=""><spring:message code="assetRegister.asset.generalTab.status"/></label>
	                <das:userdata var="statusesList" userDataType="ASSET_STATUSES"/>
	                <form:select path="" class="form-control" >
	                	<option></option>
	                	<form:options items="${statusesList}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
	                </form:select>
                </div>
                
                <div class="col-sm-3">
	                <label for=""><spring:message code="assetRegister.asset.generalTab.category"/></label>
	                <das:userdata var="categoriesList" userDataType="ASSET_CATEGORIES"/>
	                <form:select path="" cssClass="form-control" cssErrorClass="form-control error">
	                	<option></option>
	                	<form:options items="${categoriesList}" itemValue="id" itemLabel="name" data-field-to-copy="category"/>
	                </form:select>
	            </div>

			</div>

		</form:form>

	</div>

</div>
