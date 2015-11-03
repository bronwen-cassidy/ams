<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="das" uri="das-tags"%>

<div class="tab-content">
					
<form:form action="${pageContext.request.contextPath}/das/assetController/saveDraftAsset" modelAttribute="asset.maintenance" id="assetMaintenanceForm" class="form-horizontal draftForm droppable-form" role="form" method="post">

 		<das:droppable bean="${asset}" formId="assetMaintenanceForm"/>		
 		
		<div class="panel-group accordion-custom accordion-teal" id="accordion">

			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title">
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#coreSkills_panel">
						<i class="icon-arrow"></i>
						<spring:message code="assetRegister.asset.maintenance.coreSkills"/>
					</a></h4>

				</div>

				<div id="purchase_panel" class="panel-collapse in">

					<div class="panel-body">

						<div class="form-group clearfix">
		
							<div class="col-sm-3">
				                <label class="label-icons" for="DISCIPLINE"><spring:message code="assetRegister.asset.maintenance.industry"/> <i class="input-add-icon fa fa-plus-square"></i><i class="input-remove-icon fa fa-minus-square"></i></label>
				                <input id="NAME" name="" class="form-control asset" data-field-to-copy="name" type="text" value="" autocomplete="off"/>
				            </div>
				
							<div class="col-sm-3">
				            	<label class="label-icons" for="DISCIPLINE"><spring:message code="assetRegister.asset.maintenance.discipline"/> <i class="input-add-icon fa fa-plus-square"></i><i class="input-remove-icon fa fa-minus-square"></i></label>
				                <input id="DISCIPLINE" name="DISCIPLINE" class="form-control asset" data-field-to-copy="DISCIPLINE" type="text" value="" autocomplete="off"/>
							</div>
				
							<div class="col-sm-3">
								<label class="label-icons" for="CORE_Skill/QUALIFICATION"><spring:message code="assetRegister.asset.maintenance.coreSkillQualification"/> <i class="input-add-icon fa fa-plus-square"></i><i class="input-remove-icon fa fa-minus-square"></i></label>
								<input id="CORE_Skill/QUALIFICATION" name="CORE_Skill/QUALIFICATION" class="form-control" type="text" />
							</div>
									
						</div>
						
						<div class="form-group clearfix">
						
							<div class="col-sm-3">
								<div class="checkbox checkbox-padding-top">
									<label for="tag"><spring:message code="assetRegister.asset.maintenance.tag"/></label>
									<form:checkbox path="tag" id="tag" class="checkbox"/>
								</div>
							</div>
							
							<%--	
							<div class="col-sm-3">
					            <label for="tagNote"><spring:message code="assetRegister.asset.maintenance.tag"/></label>
								<form:input path="tagNote" type="text" id="tagNote" name="tagNote"  class="form-control asset" data-field-to-copy="name" value="" autocomplete="off" />
							</div>
							
							<div class="col-sm-3">
								<label for="faultCodeCategory"><spring:message code="assetRegister.asset.maintenance.faultCodeCategory"/></label>
								<form:input path="faultCodeCategoryId" id="faultCodeCategory" name="faultCodeCategory" class="form-control asset" data-field-to-copy="name" type="text" value="" autocomplete="off" />
							</div>
			
							<div class="col-sm-3">
								<label for="faultCode"><spring:message code="assetRegister.asset.maintenance.faultCode"/></label>
								<form:input path="faultCodeId" id="faultCode" name="faultCode" class="form-control asset" data-field-to-copy="name" type="text" value="" autocomplete="off" />
							</div>
							--%>
		
						</div>
						
						
						<div class="form-group clearfix">
							<div class="col-sm-3">
								<div class="checkbox">
									<label for="ppmRequired"><spring:message code="assetRegister.asset.maintenance.ppm"/></label>
									<form:checkbox path="ppmRequired" id="ppmRequired" name="ppmRequired"  class="checkbox"/>
								</div>
							</div>
								
							<div class="col-sm-9">
								<label for="ppmRequiredNote"><spring:message code="assetRegister.asset.maintenance.ppmRequiredNote"/></label>
				                <form:textarea path="ppmRequiredNote" class="form-control" id="ppmRequiredNote" name="ppmRequiredNote" placeholder="Enter Text" autocomplete="off" rows="4"/>
				            </div>
						</div>
						
						<div class="form-group clearfix">
							<div class="col-sm-3">
								<div class="checkbox">
									<label for="maintenanceMandatory"><spring:message code="assetRegister.asset.maintenance.maintenanceMandatory"/></label>
									<form:checkbox path="maintenanceMandatory" id="maintenanceMandatory" name="maintenanceMandatory"  class="checkbox"/>
								</div>
							</div>
								
							<div class="col-sm-9">
								<label for="maintenanceMandatoryNote"><spring:message code="assetRegister.asset.maintenance.maintenanceMandatoryNote"/></label>
				                <form:textarea path="maintenanceMandatoryNote" class="form-control" id="maintenanceMandatoryNote" name="maintenanceMandatoryNote" placeholder="Enter Text" autocomplete="off" rows="4"/>
				            </div>
						</div>
						
						<div class="form-group clearfix">
							<div class="col-sm-3">
								<div class="checkbox">
									<label for="maintenanceDocumentType"><spring:message code="assetRegister.asset.maintenance.maintenanceDocumentType"/></label>
									<form:checkbox path="maintenanceDocumentType" id="maintenanceDocumentType" name="maintenanceDocumentType"  class="checkbox"/>
								</div>
							</div>
								
							<div class="col-sm-3">
								<label for="documentType"><spring:message code="assetRegister.asset.maintenance.documentType"/></label>
				                <form:input path="documentType" type="text" id="documentType" name="documentType" class="form-control asset"  autocomplete="off"/>
				            </div>
				        
				            <div class="col-sm-3">
				            <label for="attachDocuemnt"><spring:message code="assetRegister.asset.maintenance.attachDocument"/></label>
					        	<div class="fileupload fileupload-file fileupload-new" data-provides="fileupload">
									<div class="input-group">
										<div class="form-control uneditable-input">
											<i class="fa fa-file fileupload-exists"></i>
												<span class="fileupload-preview"></span>
										</div>
									</div>
									<div class="input-group-btn">
										<div class="btn btn-light-grey btn-file">
											<span class="fileupload-new"><i class="fa fa-folder-open-o"></i> <spring:message code="select.file"/></span>
											<span class="fileupload-exists"><i class="fa fa-folder-open-o"></i> <spring:message code="button.change"/></span>
											<input id="attachDocument" path="attachdocument" name="attachdocument" type="file" class="file-input"/>
										</div>
										<a href="#" class="btn btn-light-grey fileupload-exists" data-dismiss="fileupload">
											<i class="fa fa-times"></i> <spring:message code="button.remove"/>
										</a>
									</div>
									
								</div>
							</div>
						</div>
						
						<div class="form-group clearfix">
							<div class="col-sm-3">
								<div class="checkbox">
									<label for="thirdPartyProvider"><spring:message code="assetRegister.asset.maintenance.thirdParty"/></label>
									<form:checkbox path="thirdPartyProvider" id="thirdPartyProvider" name="thirdPartyProvider"  class="checkbox"/>
								</div>
							</div>
							 	
				            <div class="col-sm-3">
				            	<label for="thirdPartySupplier"><spring:message code="assetRegister.asset.maintenance.supplier"/></label>
				                <form:input path="thirdPartySupplierId" id="thirdPartySupplier" name="thirdPartySupplier" class="form-control scratchpad-trigger" data-scratchpad-trigger-id="supplier" autocomplete="off" type="text"/>
				            </div>

						</div>
						
					</div>
						
				</div>

			</div>

		</div>
		
</form:form>

</div>