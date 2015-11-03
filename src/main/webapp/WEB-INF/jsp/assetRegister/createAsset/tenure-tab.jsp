<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="das" uri="das-tags"%>

<div class="tab-content">

<form:form  action="${pageContext.request.contextPath}/das/assetController/saveDraftAsset" modelAttribute="asset" id="assetTenuresForm" class="form-horizontal draftForm droppable-form" role="form" method="post">
		<font color="red">
			<b><form:errors title="Errors" path="*" cssClass="validation-error"/></b> <!-- exception error handling! -->
		</font>
 		<das:droppable bean="${asset}" formId="assetTenuresForm"/>		
 		
		<div class="panel-group accordion-custom accordion-teal" id="accordion">

			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">
	
					<h4 class="panel-title">
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#lease_in_panel">
						<i class="icon-arrow"></i>
						<spring:message code="assetRegister.asset.tenureTab.leaseIn"/>
					</a></h4>

				</div>

				<div id="lease_in_panel" class="panel-collapse in">
					<div class="panel-body">

						<div class="form-group clearfix">

							<div class="col-sm-3">
				                <label for="leaseIn.leaseTypeId"><spring:message code="assetRegister.asset.tenureTab.type"/></label>
				                <das:userdata var="leaseType" userDataType="LEASE_TYPE"/>
				                <form:select id="leaseIn.leaseTypeId" path="leaseIn.leaseTypeId" cssClass="form-control" >
				                	<option></option>
				                	<form:options items="${leaseType}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
		                		</form:select>
				            </div>
				            
							<div class="col-sm-3">
								<label for="LEASE_IN_AGREEMENT"><spring:message code="assetRegister.asset.tenureTab.attachLeaseAgreement"/></label>
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
											<input id="LEASE_IN_AGREEMENT" name="LEASE_IN_AGREEMENT" type="file" class="file-input">
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
						    	<form:hidden path="leaseIn.partyId" cssClass="droppable" data-dnd-accepts="supplierDraggable" data-dnd-field-to-copy="id"/>
						        <label for="leaseIn.partyName"><spring:message code="assetRegister.asset.tenureTab.leasedFrom"/></label>
						        <form:input id="leaseIn.partyName" path="leaseIn.partyName" cssClass="form-control scratchpad-trigger droppable" data-dnd-accepts="supplierDraggable" data-dnd-field-to-copy="name" data-scratchpad-trigger-id="supplier"/>
							</div>

							<div class="col-sm-3">
								<label for="leaseIn.leaseCharge"><spring:message code="assetRegister.asset.tenureTab.leaseInCharge"/></label>
								<div class="input-group">
									<label for="leaseIn.leaseCharge" class="input-group-addon">£</label>
									<form:input id="leaseIn.leaseCharge" path="leaseIn.leaseCharge" cssClass="form-control"/>
								</div>
							</div>
														
							<div class="col-sm-3">
				                <label for="leaseIn.chargePeriodId"><spring:message code="assetRegister.asset.tenureTab.chargePeriod"/></label>
				                <das:userdata var="chargePeriods" userDataType="CHARGE_PERIOD"/>
				                <form:select id="leaseIn.chargePeriodId" path="leaseIn.chargePeriodId" cssClass="form-control" >
				                	<option></option>
				                	<form:options items="${chargePeriods}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
				                </form:select>
			                </div>
						</div>

				        <div class="form-group clearfix">
				        
				        	<div class="col-sm-3">
				                <label for="leaseIn.leaseCommences"><spring:message code="assetRegister.asset.tenureTab.leaseCommences"/></label>
								<div class="input-group">
									<label for="leaseIn.leaseCommences" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
									<form:input id="leaseIn.leaseCommences" path="leaseIn.leaseCommences" cssClass="form-control date-picker" />
								</div>
							</div>
				        	
				        	<div class="col-sm-3">
				                <label for="leaseIn.leaseExpires"><spring:message code="assetRegister.asset.tenureTab.leaseExpires"/></label>
								<div class="input-group">
									<label for="leaseIn.leaseExpires" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
									<form:input id="leaseIn.leaseExpires" path="leaseIn.leaseExpires" cssClass="form-control date-picker" />
								</div>
							</div>						

							<div class="col-sm-3">
								<label for="leaseIn.leasePeriod"><spring:message code="assetRegister.asset.tenureTab.timePeriod"/></label>
								<form:input id="leaseIn.leasePeriod" path="leaseIn.leasePeriod" cssClass="form-control date-period-calc" data-from-date_calc="leaseIn.leaseCommences" data-to-date_calc="leaseIn.leaseExpires" data-charge-period_calc="leaseIn.chargePeriodId" data-lease-charge_calc="leaseIn.leaseCharge" readonly="true" />
							</div>
							
							<div class="col-sm-3">
								<label for="leaseIn.leaseCost"><spring:message code="assetRegister.asset.tenureTab.cost"/></label>
								<div class="input-group">
									<label for="leaseIn.leaseCost" class="input-group-addon">£</label>
									<form:input id="leaseIn.leaseCost" path="leaseIn.leaseCost" cssClass="form-control cost-calc" data-from-date_calc="leaseIn.leaseCommences" data-to-date_calc="leaseIn.leaseExpires" data-charge-period_calc="leaseIn.chargePeriodId" data-lease-charge_calc="leaseIn.leaseCharge" />
								</div>
							</div>							

				        </div>

				        <div class="form-group clearfix">
				        
					        <div class="col-sm-3">
					        	<div class="checkbox checkbox-padding-top">
					        		<form:label path="leaseIn.maintenanceIncluded"><spring:message code="assetRegister.asset.tenureTab.maintenance"/></form:label>
									<form:checkbox id="leaseIn.maintenanceIncluded" path="leaseIn.maintenanceIncluded" cssClass="checkbox" />
								</div>
							</div>
							
							<div class="col-sm-3">
					        	<div class="checkbox checkbox-padding-top">
					        		<form:label path="leaseIn.warrantyIncluded"><spring:message code="assetRegister.asset.tenureTab.warrenty"/></form:label>
									<form:checkbox id="leaseIn.warrantyIncluded" path="leaseIn.warrantyIncluded" cssClass="checkbox"/>
								</div>
							</div>

							<div class="col-sm-3">
								<label for="leaseIn.vatCodeId"><spring:message code="assetRegister.asset.tenureTab.vatCode"/></label>
								<das:userdata var="VATCodes" userDataType="VAT_CODE"/>
								<form:select id="leaseIn.vatCodeId" path="leaseIn.vatCodeId" cssClass="form-control" >
				                	<option></option>
				                	<form:options items="${VATCodes}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
				                </form:select>
							</div>

							<div class="col-sm-3">
								<label for="leaseIn.leaseValue"><spring:message code="assetRegister.asset.tenureTab.totalLeaseValue"/></label>
								<div class="input-group">
									<label for="leaseIn.leaseValue" class="input-group-addon">£</label>
									<form:input id="leaseIn.leaseValue" path="leaseIn.leaseValue" cssClass="form-control total-cost-calc" data-vat-code_calc="leaseIn.vatCodeId" data-cost_calc="leaseIn.leaseCost" />
								</div>
							</div>

				        </div>

					</div>

				</div>

			</div>

			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#lease_out_panel">
						<i class="icon-arrow"></i>
						<spring:message code="assetRegister.asset.tenureTab.leaseOut"/>
					</a></h4>

				</div>

				<div id="lease_out_panel" class="panel-collapse collapse" style="height: 0px;">
					<div class="panel-body">

						<div class="form-group clearfix">

							<div class="col-sm-3">
				                <label for="leaseOut.leaseTypeId"><spring:message code="assetRegister.asset.tenureTab.type"/></label>
				                <form:select id="leaseOut.leaseTypeId" path="leaseOut.leaseTypeId" cssClass="form-control" >
				                	<option></option>
				                	<form:options items="${leaseType}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
		                		</form:select>
				            </div>

							<div class="col-sm-3">
								<label for="LEASE_OUT_AGREEMENT"><spring:message code="assetRegister.asset.tenureTab.attachLeaseAgreement"/></label>
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
											<input id="LEASE_OUT_AGREEMENT" name="LEASE_OUT_AGREEMENT" type="file" class="file-input">
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
								<label for="leaseOut.leaseCharge"><spring:message code="assetRegister.asset.tenureTab.leaseOutCharge"/></label>
								<div class="input-group">
									<label for="leaseOut.leaseCharge" class="input-group-addon">£</label>
									<form:input id="leaseOut.leaseCharge" path="leaseOut.leaseCharge" cssClass="form-control"/>
								</div>
							</div>
							
							<div class="col-sm-3">
				                <label for="leaseOut.chargePeriodId"><spring:message code="assetRegister.asset.tenureTab.chargePeriod"/></label>
				                <form:select id="leaseOut.chargePeriodId" path="leaseOut.chargePeriodId" cssClass="form-control" >
				                	<option></option>
				                	<form:options items="${chargePeriods}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
				                </form:select>
			                </div>
			                
			                <div class="col-sm-3">
								<label for="leaseOut.vatCodeId"><spring:message code="assetRegister.asset.tenureTab.vatCode"/></label>
								<das:userdata var="VATCodes" userDataType="VAT_CODE"/>
								<form:select id="leaseOut.vatCodeId" path="leaseOut.vatCodeId" cssClass="form-control" >
				                	<option></option>
				                	<form:options items="${VATCodes}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
				                </form:select>
							</div>
							
							<div class="col-sm-3">
								<label for="leaseOut.leaseOutMargin"><spring:message code="assetRegister.asset.tenureTab.leaseOutMargin"/></label>
								<div class="input-group">
									<label for="leaseOut.leaseOutMargin" class="input-group-addon">£</label>
									<form:input id="leaseOut.leaseOutMargin" path="leaseOut.leaseOutMargin" cssClass="form-control lease-margin-calc" data-total-in_calc="leaseIn.leaseValue" data-total-out_calc="leaseOut.leaseValue" />
								</div>
							</div>
			                
			            </div>
				        
				        <div class="form-group clearfix">
				        
				        	<div class="col-sm-3">
								<label for="optionalExtra"><spring:message code="assetRegister.asset.tenureTab.optionalExtras"/></label>
								<das:userdata var="OptionalExtras" userDataType="OPTIONAL_EXTRA"/>
				                <select id="optionalExtra" name="extrasId" class="form-control" data-sys-ref-name-list-div="extraNameDiv">
				                	<option></option>
				                	<c:forEach items="${OptionalExtras}" var="extraUD">
				                		<option label="${extraUD.name}" value="${extraUD.id}">
				                	</c:forEach> 
				                </select>
							</div>
							
							<div class="col-sm-3">
								<label class="label-icons" for="extraCost"><spring:message code="assetRegister.asset.tenureTab.extrasCost"/>
									<i class="input-add-icon fa fa-plus-square" id="addOptionalExtra"></i>
								</label>
								<div class="input-group">
									<label for="extraCost" class="input-group-addon">£</label>
									<input id="extraCost" name="extraCost" class="form-control" data-sys-ref-id-list-div="extraCostDiv"/>
								</div>
							</div>
				        
				        </div>
				        
				        <div class="clearfix" id="refreshExtras">
							<c:forEach var="extra" items="${asset.leaseOut.leaseOutExtras}" varStatus="loopVar">
							<div class="clearfix">
								<div class="col-sm-3">
						            <div class="e1" id="extraNameDiv">
						               	<das:userdata var="extraUD" userDataId="${extra.extrasId}"/>
						               	<form:label class="optional-extras-tenure" id="extras-label-id-${loopVar.index}" path="leaseOut.leaseOutExtras[${loopVar.index}].extraCost">${extraUD.name}</form:label>
									</div>
								</div>
								
								<div class="col-sm-3">			
									<label class="label-icons" for="leaseOut.leaseOutExtras[${loopVar.index}].extraCost">
										<i class="input-remove-icon fa fa-minus-square" onclick="removeLeaseOutExtra(${loopVar.index})"></i>
									</label>
									<div class="e2 input-group" id="extraCostDiv">
										<label for="leaseOut.leaseOutExtras[${loopVar.index}].extraCost" class="input-group-addon">£</label>	
										<form:input id="extras-cost-id-${loopVar.index}" path="leaseOut.leaseOutExtras[${loopVar.index}].extraCost" class="form-control" data-sys-ref-id-list-div="extraCostDiv"/>
									</div>
								</div>
							</div>						
							</c:forEach>
				        </div>	

					</div>

				</div>

			</div>

		</div>

	</form:form>
</div>
