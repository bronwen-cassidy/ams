<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="das" uri="das-tags"%>

<spring:message var="sysGenPlaceHolder"
	code="placeholder.systemGenerated" />

<div class="tab-content">

	<div id="general_tab" class="tab-pane active">

		<form:form data-assetId="${asset.id}"
			action="${pageContext.request.contextPath}/das/assetController/saveDraftAsset"
			modelAttribute="asset" id="assetForm"
			class="form-horizontal draftForm droppable-form auto-file-upload" role="form"
			method="post" enctype="multipart/form-data">

			<font color="red">
				<b><form:errors title="Errors" path="*" cssClass="validation-error"/></b> <!-- exception error handling! -->
			</font>
			
			<das:droppable bean="${asset}" formId="assetForm" />

			<div class="form-group clearfix">

				<div class="col-sm-6">
					<label for="uid" class=""><spring:message
							code="assetRegister.asset.uid" /></label>
					<form:input path="uid" class="form-control" disabled="true"
						placeholder="${uid}" />
				</div>

				<div class="col-sm-6">
					<label for="created_by" class=""><spring:message
							code="assetRegister.asset.createdBy" /></label> <input
						placeholder="${user.userName}" class="form-control" type="text"
						id="created_by" disabled="disabled" />
				</div>

			</div>

			<div class="form-group clearfix">

				<div class="col-sm-3">
					<label for="name"><spring:message
							code="assetRegister.asset.generalTab.name" /></label>
					<form:input path="name" cssClass="form-control scratchpad-trigger"
						autocomplete="off" data-scratchpad-trigger-id="Asset_Search" />
				</div>

				<div class="col-sm-3">
					<label for="serialNumber"><spring:message
							code="assetRegister.asset.generalTab.serialNumber" /></label>
					<form:input path="serialNumber" class="form-control"
						autocomplete="off" />
				</div>

				<div class="col-sm-3">
					<label for="assetStatusId"><spring:message
							code="assetRegister.asset.generalTab.status" /></label>
					<das:userdata var="statusesList" userDataType="ASSET_STATUSES"/>
					<form:select path="assetStatusId" class="form-control">
						<option></option>
						<form:options items="${statusesList}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>

				<div class="col-sm-3">
					<label for="categoryId"><spring:message
							code="assetRegister.asset.generalTab.category" /></label>
					<das:userdata var="categoriesList" userDataType="ASSET_CATEGORIES"/>
					<form:select path="categoryId" cssClass="form-control"
						cssErrorClass="form-control error">
						<option></option>
						<form:options items="${categoriesList}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>

			</div>

			<div class="form-group clearfix">

				<div class="col-sm-3">
					<label for="assetNumberPart1Id"><spring:message
							code="assetRegister.asset.generalTab.assetNumber" /></label>
					<das:userdata var="assNum1List" userDataType="ASSET_NUMBER_PART_1"/>
					<form:select path="assetNumberPart1Id" cssClass="form-control"
						cssErrorClass="form-control error">
						<option></option>
						<form:options items="${assNum1List}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>

				<div class="col-sm-3">
					<label for="assetNumberPart2Id">&nbsp;</label>
					<das:userdata var="assNum2List" userDataType="ASSET_NUMBER_PART_2"/>
					<form:select path="assetNumberPart2Id" cssClass="form-control"
						cssErrorClass="form-control error">
						<option></option>
						<form:options items="${assNum2List}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>

				<div class="col-sm-3">
					<label for="assetNumberPart3Id">&nbsp;</label>
					<das:userdata var="assNum3List" userDataType="ASSET_NUMBER_PART_3"/>
					<form:select path="assetNumberPart3Id" cssClass="form-control"
						cssErrorClass="form-control error">
						<option></option>
						<form:options items="${assNum3List}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>

				<div class="col-sm-3">
					<label for="assetNumberPart4Id">&nbsp;</label>
					<form:input path="assetNumberPart4" disabled="true"
						placeholder="${sysGenPlaceHolder}" cssClass="form-control"
						cssErrorClass="form-control error" />
				</div>
			</div>

			<div class="form-group clearfix">
						
	            <div class="col-sm-6">
	                <label for="description"><spring:message code="assetRegister.asset.generalTab.description"/></label>
	                <form:textarea path="description" class="form-control" placeholder="Enter Text" autocomplete="off"  rows="4"/>
	            </div>
	            
	            <div class="col-sm-3">
					<label class="label-icons" for="otherSystemNameId"><spring:message code="assetRegister.asset.generalTab.otherSystemName"/>
						<i class="input-add-icon fa fa-plus-square" id="addOtherSystemName"></i>
						<i class="input-remove-icon fa fa-minus-square" id="removeOtherSystemName"></i>
					</label>
					<das:userdata var="otherSysName" userDataType="OTHER_SYSTEM_NAME"/>
					<select name="otherSystemNameId" class="form-control" id="otherSystemNameId">
						<c:forEach var="otherSysRef" items="${otherSysName}">
							<option value= "${otherSysRef.id}"><c:out default="" value="${otherSysRef.name}"/></option>
						</c:forEach>	
					</select>
				</div>		

				<div class="col-sm-3" >
					<label class="label-icons" for="otherSystemId"><spring:message code="assetRegister.asset.generalTab.otherSystemID"/>
						<i class="input-add-icon fa fa-plus-square" id="addOtherSystemId"></i>
						<i class="input-remove-icon fa fa-minus-square" id="removeOtherSystemId"></i>
					</label>
					<input id="otherSystemId" name="otherSystemId" class="form-control" type="text" data-sys-ref-id-list-div="otherSystemIdDiv"/>
				</div>
				
				<div class="col-sm-3" id="otherSystemNameDiv">
				</div>
			
				<div class="col-sm-3" id="otherSystemIdDiv">
				</div>
			</div>
			
			
			
			<div class="form-group clearfix">
				<div class="col-sm-3">

					<div class="fileupload fileupload-img fileupload-new"
						data-provides="fileupload">
						<input type="hidden">

						<div class="fileupload-new thumbnail">
							<img src="http://www.placehold.it/250x150" alt="">
						</div>
						<div class="fileupload-preview fileupload-exists thumbnail"></div>
						<div>
							<span class="btn btn-light-grey btn-file"><span
								class="fileupload-new"><i class="fa fa-picture-o"></i> <spring:message
										code="select.image" /></span><span class="fileupload-exists"><i
									class="fa fa-picture-o"></i> <spring:message
										code="button.change" /></span> <input id="IMAGE_UPLOAD"
								name="IMAGE_UPLOAD" type="file"> </span> <a href="#"
								class="btn fileupload-exists btn-light-grey"
								data-dismiss="fileupload"> <i class="fa fa-times"></i> <spring:message
									code="button.remove" />
							</a>
						</div>
					</div>

				</div>
				<div class="col-sm-3">
					<div class="checkbox">
						<label for="isAFacility"><spring:message code="assetRegister.asset.generalTab.showAsFacility" /></label>
						<form:checkbox id="isAFacility" path="isAFacility" name="isAFacility" class="checkbox" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="checkbox">
						<label for="isEquipment"><spring:message
								code="assetRegister.asset.generalTab.showAsEquipment" /></label>
						<form:checkbox id="isEquipment" path="isEquipment"
							name="isEquipment" class="checkbox" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="checkbox">
						<label for="isPart"><spring:message
								code="assetRegister.asset.generalTab.showAsPart" /></label>
						<form:checkbox id="isPart" path="isPart" name="isPart"
							class="checkbox" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="checkbox">
						<label for="trackingDevice"><spring:message
								code="assetRegister.asset.generalTab.trackingDevice" /></label>
						<form:checkbox id="trackingDevice" path="trackingDevice"
							name="trackingDevice" class="checkbox" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="checkbox">
						<label for="criticalAsset"><spring:message
								code="assetRegister.asset.generalTab.criticalAsset" /></label>
						<form:checkbox id="criticalAsset" path="criticalAsset"
							name="criticalAsset" class="checkbox" />
					</div>
				</div>

			</div>

			<div class="panel-group accordion-custom accordion-teal"
				id="accordion">

				<div class="inner-form-panel panel panel-default">

					<div class="panel-heading">

						<h4 class="panel-title">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion" href="#supplier_info_panel"> <i
								class="icon-arrow"></i> <spring:message
									code="assetRegister.asset.generalTab.supplierInformation" />
							</a>
						</h4>

					</div>

					<div id="supplier_info_panel" class="panel-collapse in">

						<div class="panel-body">

							<div class="form-group clearfix">

								<div class="col-sm-3">
									<form:hidden path="supplierId" />
									<label for="supplierName"><spring:message
											code="assetRegister.asset.generalTab.supplier" /></label>
									<form:input id="supplierName" type="text" path="supplierName"
										class="form-control scratchpad-trigger"
										data-scratchpad-trigger-id="supplier" />
								</div>

								<div class="col-sm-3">
									<label for="supplierPn"><spring:message
											code="assetRegister.asset.generalTab.supplierPN" /></label>
									<form:input id="supplierPn" type="text" path="supplierPn"
										class="form-control" />
								</div>

								<div class="col-sm-3">
									<form:hidden path="manufacturerId" />
									<label for="manufacturerName"><spring:message
											code="assetRegister.asset.generalTab.manufacturer" /></label> <input
										id="manufacturerName" type="text" name="manufacturerName"
										class="form-control" />
								</div>

								<div class="col-sm-3">
									<label for="manufacturerPn"><spring:message
											code="assetRegister.asset.generalTab.manufacturerPN" /></label>
									<form:input id="manufacturerPn" type="text"
										path="manufacturerPn" class="form-control" />
								</div>

							</div>

						</div>

					</div>

				</div>

				<div class="inner-form-panel panel panel-default">

					<div class="panel-heading">

						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#custodian_part_panel"> <i
								class="icon-arrow"></i> <spring:message
									code="assetRegister.asset.generalTab.custodianAndLocation" />
							</a>
						</h4>

					</div>

					<div id="custodian_part_panel" class="panel-collapse collapse"
						style="height: 0px;">

						<div class="panel-body">

							<div class="form-group clearfix">

								<div class="col-sm-3">
									<form:hidden path="custodianId" />
									<label for="custodianname"><spring:message
											code="assetRegister.asset.generalTab.custodian" /></label>
									<form:input path="custodianName"
										cssClass="form-control scratchpad-trigger"
										data-scratchpad-trigger-id="Custodian" />
								</div>

							</div>

							<div class="form-group clearfix">
							<%--
								TODO: the javascript that calls auto-loading-user-data should auto select the options from the drop down.
								This currently just disables the other drop downs so auto-loading-user-data has been commented out for now
							--%>
								<div class="col-sm-3">
									<label for="countryId"><spring:message code="assetRegister.asset.generalTab.country" /></label>
									<das:userdata var="countryList" userDataType="COUNTRY"/>
									<form:select id="countryId" path="countryId" class="form-control <%--auto-loading-user-data--%>" data-child-user-data="companyId">
										<option></option>
										<form:options items="${countryList}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>

								<div class="col-sm-3">
									<label for="companyId"><spring:message code="assetRegister.asset.generalTab.company" /></label>
									<das:userdata var="companyList" userDataType="COMPANY"/>
									<form:select id="companyId" path="companyId" class="form-control <%--auto-loading-user-data--%>" data-child-user-data="divisionId">
										<option></option>
										<form:options items="${companyList}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>

								<div class="col-sm-3">
									<label for="divisionId"><spring:message code="assetRegister.asset.generalTab.division" /></label>
									<das:userdata var="divisionList" userDataType="DIVISION"/>
									<form:select id="divisionId" path="divisionId" class="form-control">
										<option></option>
										<form:options items="${divisionList}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>

								<div class="col-sm-3">
									<label for="siteId"><spring:message code="assetRegister.asset.generalTab.site" /></label>
									<das:userdata var="siteList" userDataType="SITE"/>
									<form:select id="siteId" path="siteId" class="form-control <%--auto-loading-user-data--%>" data-child-user-data="departmentId">
										<option></option>
										<form:options items="${siteList}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>

							</div>

							<div class="form-group clearfix">

								<div class="col-sm-3">
									<label for="departmentId"><spring:message code="assetRegister.asset.generalTab.department" /></label>
									<das:userdata var="departmentList" userDataType="DEPARTMENT"/>
									<form:select id="departmentId" path="departmentId" class="form-control <%--auto-loading-user-data--%>" data-child-user-data="locationId">
										<option></option>
										<form:options items="${departmentList}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>

								<div class="col-sm-3">
									<label for="locationId"><spring:message code="assetRegister.asset.generalTab.location" /></label>
									<das:userdata var="locationList" userDataType="LOCATION"/>
									<form:select id="locationId" path="locationId" class="form-control">
										<option></option>
										<form:options items="${locationList}" itemValue="id" itemLabel="name" />
									</form:select>
								</div>

							</div>

						</div>

					</div>

				</div>

				<div class="inner-form-panel panel panel-default">

					<div class="panel-heading">

						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThree"> <i
								class="icon-arrow"></i> <spring:message
									code="assetRegister.asset.generalTab.group" />
							</a>
						</h4>

					</div>

					<div id="collapseThree" class="panel-collapse collapse"
						style="height: 0px;">

						<div class="panel-body">

							<div class="form-group">

								<div class="col-sm-12">

									<div class="form-group clearfix">

										<div class="col-sm-4">
											<label class="label-icons" for="PART"><spring:message
													code="assetRegister.asset.generalTab.partOfGroup" /> <i
												class="input-add-icon fa fa-plus-square"></i> <i
												class="input-remove-icon fa fa-minus-square"></i> <input
												id="PART" name="PART"
												class="form-control scratchpad-trigger"
												data-scratchpad-trigger-id="Groups" type="text" /> </label>
										</div>

										<div class="col-sm-4">
											<label class="label-icons" for="CHILD_OF"><spring:message
													code="assetRegister.asset.generalTab.childOf" /> <i
												class="input-add-icon fa fa-plus-square"></i> <i
												class="input-remove-icon fa fa-minus-square"></i> <input
												id="CHILD_OF" name="CHILD_OF" class="form-control"
												type="text" /> </label>
										</div>

										<div class="col-sm-4">
											<label class="label-icons" for="PARTS"><spring:message
													code="assetRegister.asset.generalTab.parts" /> <i
												class="input-add-icon fa fa-plus-square"></i> <i
												class="input-remove-icon fa fa-minus-square"></i> <input
												id="PARTS" name="PARTS" class="form-control" type="text" />
											</label>
										</div>

									</div>

								</div>

							</div>

						</div>

					</div>

				</div>

				<div class="inner-form-panel panel panel-default">

					<div class="panel-heading">

						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFour"> <i
								class="icon-arrow"></i> <spring:message
									code="assetRegister.asset.generalTab.riskManagement" />
							</a>
						</h4>

					</div>

					<div id="collapseFour" class="panel-collapse collapse"
						style="height: 0px;">

						<div class="panel-body">

							<div class="form-group clearfix">

								<div class="col-sm-4">
									<div class="col-sm-8">
										<div class="checkbox">
											<form:checkbox path="riskAssessment" name="riskAssessment"
												class="checkbox" />
											<label class="label-icons" for="riskAssessment"><spring:message
													code="assetRegister.asset.generalTab.riskAssessment" /> <i
												class="input-add-icon fa fa-plus-square"></i> <i
												class="input-remove-icon fa fa-minus-square"></i> </label>
										</div>
									</div>

									<div id="riskAssessmentDocList"></div>

									<div class="col-sm-10 pad-top">
										<div class="fileupload fileupload-file fileupload-new" data-provides="fileupload">
											<div class="input-group">
												<div class="form-control uneditable-input">
													<i class="fa fa-file fileupload-exists"></i> 
													<span class="fileupload-preview">
													</span>
												</div>
											</div>
											<div class="input-group-btn">
												<div class="btn btn-light-grey btn-file">
													<span class="fileupload-new">
														<i class="fa fa-folder-open-o"></i>
														<spring:message code="select.file" />
													</span>
													<span class="fileupload-exists">
														<i class="fa fa-folder-open-o"></i> 
														<spring:message code="button.change" />
													</span> 
													<input id="docFile" name="docFile" type="file" class="file-input auto-upload" data-doc-list-div="riskAssessmentDocList">
												</div>
												<a href="#" class="btn btn-light-grey fileupload-exists" data-dismiss="fileupload">
													<i class="fa fa-times"></i>
													<spring:message code="button.remove" />
												</a>
											</div>
										</div>
									</div>
								</div>

								<div class="col-sm-4">
									<div class="col-sm-8">
										<div class="checkbox">
											<form:checkbox path="bcp" name="bcp" class="checkbox" />
											<label class="label-icons" for="bcp">
												<spring:message code="assetRegister.asset.generalTab.bcp" />
												<i class="input-add-icon fa fa-plus-square"></i>
												<i class="input-remove-icon fa fa-minus-square"></i> 
											</label>
										</div>
									</div>

									<div class="col-sm-10 pad-top">
										<div class="fileupload fileupload-file fileupload-new" data-provides="fileupload">
											<div class="input-group">
												<div class="form-control uneditable-input">
													<i class="fa fa-file fileupload-exists"></i> 
													<span class="fileupload-preview">
													</span>
												</div>
											</div>
											<div class="input-group-btn">
												<div class="btn btn-light-grey btn-file">
													<span class="fileupload-new">
														<i class="fa fa-folder-open-o"></i>
														<spring:message code="select.file" />
													</span>
													<span class="fileupload-exists">
														<i class="fa fa-folder-open-o"></i>
														<spring:message code="button.change" />
													</span>
													<input id="BCP_UPLOAD" name="BCP_UPLOAD" type="file" class="file-input auto-upload">
												</div>
												<a href="#" class="btn btn-light-grey fileupload-exists" data-dismiss="fileupload">
													<i class="fa fa-times"></i>
													<spring:message code="button.remove" />
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
