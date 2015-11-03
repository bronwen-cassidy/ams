<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="das" uri="das-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<div class="tab-content">
	

		<div class="panel-group accordion-custom accordion-teal" id="accordion">

			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#warranty_panel">
							<i class="icon-arrow"></i>
							<spring:message code="assetRegister.asset.indemnitiesTab.warranty"/>
						</a>
					</h4>
				</div>

				<div id="warranty_panel" class="panel-collapse in">
					<div class="panel-body">
					
						<form:form  action="${pageContext.request.contextPath}/das/assetController/saveDraftAsset" modelAttribute="asset" id="assetWarrentiesForm" class="form-horizontal draftForm droppable-form" role="form" method="post">	
						
							<font color="red">
								<b><form:errors title="Errors" path="*" cssClass="validation-error"/></b> <!-- exception error handling! -->
							</font>					
						
							<div class="form-group clearfix">
 								<div class="col-sm-3">
									<das:userdata var="warrantyTypes" userDataType="WARRANTY_TYPE"/>
									<label for="warranty.assetWarrantyTypeId"><spring:message code="assetRegister.asset.indemnitiesTab.warrantyType"/></label>
									<form:select id="warranty.assetWarrantyTypeId" path="warranty.assetWarrantyTypeId" class="form-control" >
										<option></option>
										<form:options items="${warrantyTypes}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>
				           		<div class="col-sm-3">
								<label for="warranty.warrantyPolicyId"><spring:message code="assetRegister.asset.indemnitiesTab.existingPolices"/></label>
									<form:select id="warranty.warrantyPolicyId" path="warranty.warrantyPolicyId" class="form-control" >
										<option></option>
									    <c:forEach var="warPolicy" items="${warrantyPolicyList}"><!-- TODO CHANGE BELOW TEXT TO READ FROM MESSAGE FILES! -->
									        <form:option value="${warPolicy.id}"><c:out value="Policy No: ${warPolicy.policyNumber} / Cost : ${warPolicy.cost} / Expiry Date : ${warPolicy.expiryDate}"/></form:option>
									    </c:forEach>
									</form:select>
								</div>
				           		<div class="col-sm-3">
									<input type="button" id="warranty_policy_toggle" class="btn btn-primary newPolicy-margintop" value="<spring:message code="create.createNewPolicy"/>">
								</div> 
							</div>
						</form:form>
						<form:form action="${pageContext.request.contextPath}/das/warrantyPolicy/createWarrantyPolicy" modelAttribute="warrantyPolicy" id="warrantyPolicyForm" class="form-horizontal droppable-form" role="form" method="post">
							<div class="warranty_policy">
							
								<das:droppable bean="${warrantyPolicy}" formId="warrantyPolicyForm"/>	
								<das:droppable bean="${asset}" formId="warrantyPolicyForm"/>
								
								<div class="form-group clearfix">
									<div class="col-sm-3">
										<label for="commencementDate"><spring:message code="assetRegister.asset.indemnitiesTab.warrantyCommences"/></label>
										<div class="input-group">
											<label for="commencementDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
											<form:input id="commencementDate" path="commencementDate" cssClass="form-control date-picker"/>
										</div>
									</div>
						     		<div class="col-sm-3">
										<label for="warrantyExpires"><spring:message code="assetRegister.asset.indemnitiesTab.warrantyExpires"/></label>
										<div class="input-group">
											<label for="expiryDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
											<form:input id="expiryDate" path="expiryDate" cssClass="form-control date-picker" />
										</div>
									</div>
									<div class="col-sm-3">
										<das:userdata var="warrantyTypes" userDataType="WARRANTY_TYPE"/>
										<label for="warranty.warrantyPolicy.typeId"><spring:message code="assetRegister.asset.indemnitiesTab.warrantyType"/></label>
										<form:select id="warranty.warrantyPolicy.typeId" path="typeId" class="form-control" >
											<option></option>
											<form:options items="${warrantyTypes}" itemValue="id" itemLabel="name" />
										</form:select>
									</div>
								 	<div class="col-sm-3">
									    <form:hidden path="supplierId" cssClass="droppable" data-dnd-accepts="supplierDraggable" data-dnd-field-to-copy="id"/>
										<label for="supplierName"><spring:message code="assetRegister.asset.indemnitiesTab.warrantySupplier"/></label>
										<form:input id="supplierName" path="supplierName" class="form-control asset scratchpad-trigger droppable" data-dnd-accepts="supplierDraggable" data-dnd-field-to-copy="name" data-scratchpad-trigger-id="supplier"/>
									</div>
								</div>
								<div class="form-group clearfix">
									<div class="col-sm-3">
										<label for="warranty.cost"><spring:message code="assetRegister.asset.indemnitiesTab.cost"/></label>
										<div class="input-group">
											<label for="cost" class="input-group-addon">£</label>
											<form:input id="cost" path="cost" cssClass="form-control asset"/>
										</div>
									</div>
									<div class="col-sm-9">
										<label for="WARRANTY_ATTACH"><spring:message code="assetRegister.asset.indemnitiesTab.attachWarranty"/></label>
										<div class="fileupload fileupload-file fileupload-new" data-provides="fileupload">
											<div class="input-group">
												<div class="form-control uneditable-input max-policy-width">
													<i class="fa fa-file fileupload-exists"></i>
													<span class="fileupload-preview"></span>
												</div>
											</div>
											<div class="input-group-btn">
												<div class="btn btn-light-grey btn-file">
													<span class="fileupload-new"><i class="fa fa-folder-open-o"></i> <spring:message code="select.file"/></span>
													<span class="fileupload-exists"><i class="fa fa-folder-open-o"></i> <spring:message code="button.change"/></span>
													<input id="WARRANTY_ATTACH" name="WARRANTY_ATTACH" type="file" class="file-input">
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
											<div class="checkbox checkbox-padding-top">
												<label for="om"><spring:message code="assetRegister.asset.indemnitiesTab.originalManufacturer"/></label>
												<form:checkbox id="om" path="om" name="om" class="checkbox"/>
											</div>
				           				</div>
									<div class="col-sm-3">
										<label class="label-icons" for="policyNumber">
											<spring:message code="assetRegister.asset.indemnitiesTab.policyNumber"/>
											<i class="input-add-icon fa fa-plus-square"></i>
											<i class="input-remove-icon fa fa-minus-square"></i>
										</label>
										<form:input id="policyNumber" path="policyNumber" cssClass="form-control" type="text" />
									</div>
								</div>
								<hr>
								<button type="submit" class="btn btn-success pull-right" id="createWarrantyPolicy"><spring:message code="create.createPolicy"/></button>
								<div class="btn btn-danger pull-right margin-right" id="warrantyCancel"><spring:message code="create.cancel"/></div>
							</div>
						</form:form>
					</div>
				</div>

			</div>

			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#insurance_panel">
							<i class="icon-arrow"></i>
							<spring:message code="assetRegister.asset.indemnitiesTab.insurance"/>
						</a>
					</h4>
				</div>

				<div id="insurance_panel" class="panel-collapse collapse" style="height: 0px;">
					<div class="panel-body">
						<form:form  action="${pageContext.request.contextPath}/das/assetController/saveDraftAsset" modelAttribute="asset" id="assetInsuraceTypesForm" class="form-horizontal draftForm droppable-form" role="form" method="post">
							<div class="form-group clearfix">
								
								<div class="col-sm-3">
									<das:userdata var="insuranceTypes" userDataType="INSURANCE_TYPE"/>
									<label for="assetInsurance.insuranceTypeId"><spring:message code="assetRegister.asset.indemnitiesTab.insuranceType"/></label>								
									<form:select id="assetInsurance.insuranceTypeId" path="assetInsurance.insuranceTypeId" class="form-control" >
										<option></option>
										<form:options items="${insuranceTypes}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>
										
								<div class="col-sm-3">
									<div class="checkbox checkbox-padding-top ">
										<label for="assetInsurance.mandatory"><spring:message code="assetRegister.asset.indemnitiesTab.mandatory"/></label>
										<form:checkbox id="assetInsurance.mandatory" path="assetInsurance.mandatory" class="checkbox "  />								
									</div>
								</div>
								
								<div class="col-sm-3">	
								<label for="assetInsurance.insuranceTypePolicyLink.insurancePolicy.id"><spring:message code="assetRegister.asset.indemnitiesTab.existingPolices"/></label>
									<form:select id="assetInsurance.insuranceTypePolicyLink.insurancePolicy.id" name="assetInsurance.insuranceTypePolicyLink.insurancePolicy.id"  path="assetInsurance.insuranceTypePolicyLink.insurancePolicy.id" class="form-control" >
										<option></option>
									    <c:forEach var="insPolicy" items="${insurancePolicyList}"><!-- TODO CHANGE BELOW TEXT TO READ FROM MESSAGE FILES! -->
									        <form:option value="${insPolicy.id}"><c:out value="Policy No: ${insPolicy.policyNumber} / Cost : ${insPolicy.cost} / Expiry Date : ${insPolicy.expiryDate}"/></form:option>
									    </c:forEach>
									</form:select>
								</div>
	
								<div class="col-sm-3">
									<input type="button" id="insurance_policy_toggle" class="btn btn-primary newPolicy-margintop" value="<spring:message code="create.createNewPolicy"/>">
								</div>
								
							</div>
						</form:form>
						<form:form action="${pageContext.request.contextPath}/das/insurancePolicy/createInsurancePolicy" modelAttribute="insurancePolicy" id="insurancePolicyForm" class="form-horizontal droppable-form" role="form" method="post">
							
							<div class="insurance_policy">
							
								<das:droppable bean="${insurancePolicy}" formId="insurancePolicyForm"/>	
								
								<div class="form-group clearfix">
									<div class="col-sm-3">
										<label for="commencementDate"><spring:message code="assetRegister.asset.indemnitiesTab.insuranceCommences"/></label>
										<div class="input-group">
											<label for="commencementDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
											<form:input id="commencementDate" path="commencementDate" class="form-control date-picker" type="text" />
										</div>
									</div>
							     	<div class="col-sm-3">
										<label for="expiryDate"><spring:message code="assetRegister.asset.indemnitiesTab.insuranceExpires"/></label>
										<div class="input-group">
											<label for="expiryDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
											<form:input id="expiryDate" path="expiryDate" class="form-control date-picker" type="text" />
										</div>
									</div>
									<div class="col-sm-3">
								    	<form:hidden path="partyId" cssClass="droppable" data-dnd-accepts="supplierDraggable" data-dnd-field-to-copy="id"/>
										<label for="insuranceSupplier"><spring:message code="assetRegister.asset.indemnitiesTab.insuranceSupplier"/></label>
										<form:input id="insuranceSupplier" path="insuranceSupplier" class="form-control scratchpad-trigger droppable" data-dnd-accepts="supplierDraggable" data-dnd-field-to-copy="name" data-scratchpad-trigger-id="supplier"/>
									</div>
									<div class="col-sm-3">
										<label for="cost"><spring:message code="assetRegister.asset.indemnitiesTab.cost"/></label>
										<div class="input-group">
											<label for="cost" class="input-group-addon">£</label>
											<form:input id="cost" path="cost" class="form-control asset" type="text" />
										</div>
									</div>
								</div>
						
								<div class="form-group clearfix">
									<div class="col-sm-12">
										<label for="INSURANCE_ATTACH_POLICY"><spring:message code="assetRegister.asset.indemnitiesTab.attachPolicy"/></label>
						           		<div class="fileupload fileupload-file fileupload-new" data-provides="fileupload">
											<div class="input-group">
												<div class="form-control uneditable-input max-policy-width">
													<i class="fa fa-file fileupload-exists"></i>
													<span class="fileupload-preview"></span>
												</div>
											</div>
					
											<div class="input-group-btn">
												<div class="btn btn-light-grey btn-file">
													<span class="fileupload-new"><i class="fa fa-folder-open-o"></i> <spring:message code="select.file"/></span>
													<span class="fileupload-exists"><i class="fa fa-folder-open-o"></i> <spring:message code="button.change"/></span>
													<input id="INSURANCE_ATTACH_POLICY" name="INSURANCE_ATTACH_POLICY" type="file" class="file-input">
												</div>
												<a href="#" class="btn btn-light-grey no-radius fileupload-exists" data-dismiss="fileupload">
													<i class="fa fa-times"></i> <spring:message code="button.remove"/>
												</a>
											</div>
										</div>
									</div>
								</div>
					
								<div class="form-group clearfix">
									<div class="col-sm-3">
										<label class="label-icons" for="policyNumber">
											<spring:message code="assetRegister.asset.indemnitiesTab.policyNumber"/>
											<i class="input-add-icon fa fa-plus-square"></i>
											<i class="input-remove-icon fa fa-minus-square"></i>
										</label>
										<form:input id="policyNumber" path="policyNumber" class="form-control" type="text" />
									</div>
								</div>
				
								<hr>
							<button type="submit" class="btn btn-success pull-right" id="createInsurancePolicy"><spring:message code="create.createPolicy"/></button>
							<div class="btn btn-danger pull-right margin-right" id="insuranceCancel"><spring:message code="create.cancel"/></div>
						</div>
					</form:form>
				</div>

			</div>

		</div>

	</div>
</div>
