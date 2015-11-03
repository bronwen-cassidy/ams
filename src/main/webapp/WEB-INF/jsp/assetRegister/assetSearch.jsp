<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags"%>

<div class="tab-content">
	<div id="assetSearch_tab" class="tab-pane active ">
		<form:form action="${pageContext.request.contextPath}/das/assetSearch/search" modelAttribute="assetSearch" class="form-horizontal" role="form">
			
			<div class="search-container">
				<div class="form-group clearfix">
	
					<div class="col-sm-3">
				        <label for="uid" class=""><spring:message code="assetRegister.asset.uid"/></label>
			            <form:input path="uid" class="form-control" />
					</div>
	
					<div class="col-sm-3">
			        	<label for="created_by" class=""><spring:message code="assetRegister.asset.createdBy"/></label>
			            <input placeholder="${user.userName}" class="form-control" type="text" id="created_by"/>
					</div>
	
				</div>
	
				<div class="form-group clearfix">
	
					<div class="col-sm-3">
		                <label for="name"><spring:message code="assetRegister.asset.search.asset.name"/></label>
	   	                <das:userdata var="assetNameList" userDataType="ASSET_NAMES"/>
	   	                <form:select path="name" class="form-control" >
		                	<option></option>
		                	<form:options items="${assetNameList}" itemValue="name" itemLabel="name" data-field-to-copy="name"/>
		                </form:select>
		            </div>
	
					<div class="col-sm-3">
		            	<label for="serialNumber"><spring:message code="assetRegister.asset.generalTab.serialNumber"/></label>
		                <form:input path="serialNumber" type="text" class="form-control asset" autocomplete="off"/>
					</div>
	
					<div class="col-sm-3">
		                <label for="assetStatusId"><spring:message code="assetRegister.asset.generalTab.status"/></label>
		                <das:userdata var="statusesList" userDataType="ASSET_STATUSES"/>
		                <form:select path="assetStatusId" class="form-control" >
		                	<option></option>
		                	<form:options items="${statusesList}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
		                </form:select>
	                </div>
	                
	                <div class="col-sm-3">
		                <label for="categoryId"><spring:message code="assetRegister.asset.generalTab.category"/></label>
		                <das:userdata var="categoriesList" userDataType="ASSET_CATEGORIES"/>
		                <form:select path="categoryId" cssClass="form-control" cssErrorClass="form-control error">
		                	<option></option>
		                	<form:options items="${categoriesList}" itemValue="id" itemLabel="name" data-field-to-copy="category"/>
		                </form:select>
		            </div>
	
				</div>
	
				<div class="form-group clearfix">
	
					<div class="col-sm-3">
		                <label for="custodianName"><spring:message code="assetRegister.asset.search.custodian.name"/></label>
		                <form:input path="custodianName" class="form-control asset droppable" autocomplete="off" data-type="asset" data-fields="custodianName"/>
		            </div>
		            
	        		<div class="col-sm-3">
		                <label for="supplierName"><spring:message code="assetRegister.asset.search.supplier.name"/></label>
		                <form:input path="supplierName" class="form-control asset droppable" autocomplete="off" data-type="asset" data-fields="supplierName"/>
		            </div>
		            
	         		<div class="col-sm-3">
		                <label for="manufacturerName"><spring:message code="assetRegister.asset.search.manufacturer.name"/></label>
		                <form:input path="manufacturerName" class="form-control asset droppable" autocomplete="off" data-type="asset" data-fields="manufacturerName"/>
		            </div>
	
		            <div class="col-sm-3">
		                <label for="clientName"><spring:message code="assetRegister.asset.search.client.name"/></label>
		                <form:input path="" class="form-control asset droppable" disabled="true" autocomplete="off" data-type="asset" data-fields="clientName"/>
		            </div>
	
				</div>	            
	
				<div class="form-group clearfix">
	            
	        		<div class="col-sm-3">
		            	<label for="assetNumberPart1Id">&nbsp;<spring:message code="assetRegister.asset.generalTab.assetNumberPart1"/></label>
		                	<das:userdata var="assNum1List" userDataType="ASSET_NUMBER_PART_1"/>
		                	<form:select path="assetNumberPart1Id" cssClass="form-control" cssErrorClass="form-control error" >
		                		<option></option>
		                	<form:options items="${assNum1List}" itemValue="id" itemLabel="name"/>
		                </form:select>	            
		            </div>
		            
	         		<div class="col-sm-3">
		            	<label for="assetNumberPart2Id">&nbsp;<spring:message code="assetRegister.asset.generalTab.assetNumberPart2"/></label>
		                	<das:userdata var="assNum2List" userDataType="ASSET_NUMBER_PART_2"/>
		                	<form:select path="assetNumberPart2Id" cssClass="form-control" cssErrorClass="form-control error" >
		                		<option></option>
		                	<form:options items="${assNum2List}" itemValue="id" itemLabel="name"/>
		                </form:select>
		            </div>
	
	         		<div class="col-sm-3">
		            	<label for="assetNumberPart3Id">&nbsp;<spring:message code="assetRegister.asset.generalTab.assetNumberPart3"/></label>
		                	<das:userdata var="assNum3List" userDataType=""/>
		                	<form:select path="assetNumberPart3Id" cssClass="form-control" cssErrorClass="form-control error" >
		                		<option></option>
		                	<form:options items="${assNum3List}" itemValue="id" itemLabel="name"/>
		                </form:select>
		            </div>
		            
	         		<div class="col-sm-3">
		            	<label for="assetNumberPart4Id">&nbsp;<spring:message code="assetRegister.asset.generalTab.assetNumberPart4"/></label>
		                	<form:select path="assetNumberPart4Id" cssClass="form-control" cssErrorClass="form-control error" disabled="true" >
		                		<option></option>
		                	<form:options items="${assNum4List}" itemValue="id" itemLabel="name"/>
		                </form:select>
		            </div>
				</div>
	
				<div class="form-group clearfix">
					<div class="col-sm-3">
		                <label for="assetNumber"><spring:message code="assetRegister.asset.generalTab.assetNumber"/></label>
		                <form:input path="assetNumber" class="form-control asset droppable" autocomplete="off" data-type="asset" data-fields="assetNumber"/>
		            </div>
				</div>	
				
				<div class="panel-group accordion-custom accordion-teal" id="accordion">
				
					<div class="inner-form-panel panel panel-default">
	
						<div class="panel-heading">
	
							<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#custodian_part_panel">
								<i class="icon-arrow"></i>
								More Asset Search Fields
							</a></h4>
						</div>
	
						<div id="custodian_part_panel" class="panel-collapse collapse" style="height: 0px;">
	
							<div class="panel-body">
	
								<div class="form-group clearfix">
	
						            <div class="col-sm-3">
					            	<label for="countryId">&nbsp;<spring:message code="assetRegister.asset.generalTab.country"/></label>
					                	<das:userdata var="countryList" userDataType="COUNTRY"/>
					                	<form:select path="countryId" cssClass="form-control" cssErrorClass="form-control error" >
					                		<option></option>
					                	<form:options items="${countryList}" itemValue="id" itemLabel="name"/>
					                	</form:select>					           
	
					                 </div>
	
						            <div class="col-sm-3">
	         				         <label for="depreciationCodeId">&nbsp;<spring:message code="assetRegister.asset.financeTab.depreciation"/></label>
					                	<form:select path="depreciationCodeId" cssClass="form-control" cssErrorClass="form-control error" disabled="true">
					                		<option></option>
					                	</form:select>	
						            </div>
						            
						            <div class="col-sm-3">
	         				         <label for="companyId">&nbsp;<spring:message code="assetRegister.asset.generalTab.company"/></label>
					                	<das:userdata var="companyList" userDataType="COMPANY"/>
					                	<form:select path="companyId" cssClass="form-control" cssErrorClass="form-control error">
					                		<option></option>
					                	<form:options items="${companyList}" itemValue="id" itemLabel="name"/>
					                	</form:select>						            
					                </div>
						            
						            <div class="col-sm-3">
						            <label for="divisionId">&nbsp;<spring:message code="assetRegister.asset.generalTab.division"/></label>
					                	<das:userdata var="divisionList" userDataType="DIVISION"/>
					                	<form:select path="divisionId" cssClass="form-control" cssErrorClass="form-control error">
					                		<option></option>
					                	<form:options items="${divisionList}" itemValue="id" itemLabel="name"/>
					                	</form:select>	
						            </div>
						            					            
								</div>
								
								<div class="form-group clearfix">
	
						            <div class="col-sm-3">
					            	<label for="departmentId">&nbsp;<spring:message code="assetRegister.asset.generalTab.department"/></label>
					                	<das:userdata var="departmentList" userDataType="DEPARTMENT"/>
					                	<form:select path="departmentId" cssClass="form-control" cssErrorClass="form-control error" >
					                		<option></option>
					                	<form:options items="${departmentList}" itemValue="id" itemLabel="name"/>
					                	</form:select>					           
					                 </div>
	
						            <div class="col-sm-3">
	         				         <label for="locationId">&nbsp;<spring:message code="assetRegister.asset.generalTab.location"/></label>
					                	<das:userdata var="locationList" userDataType="LOCATION"/>
					                	<form:select path="locationId" cssClass="form-control" cssErrorClass="form-control error" >
					                		<option></option>
					                	<form:options items="${locationList}" itemValue="id" itemLabel="name"/>
					                	</form:select>	
						            </div>
						            
						            <div class="col-sm-3">
	         				         <label for="siteId">&nbsp;<spring:message code="assetRegister.asset.generalTab.site"/></label>
					                	<das:userdata var="siteList" userDataType="SITE"/>
					                	<form:select path="siteId" cssClass="form-control" cssErrorClass="form-control error">
					                		<option></option>
					                	<form:options items="${siteList}" itemValue="id" itemLabel="name"/>
					                	</form:select>						            
					                </div>
						            					            
								</div>
								
							</div>
						</div>
					</div>
					<div class="full-search">
						<input type="submit" class="btn btn-success" value="<spring:message code="search.submitSearchButtonName"/>">
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
