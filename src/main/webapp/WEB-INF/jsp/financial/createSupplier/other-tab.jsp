<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<spring:message var="sysGenPlaceHolder"
	code="placeholder.systemGenerated" />
	
<div class="tab-content">

	<form:form action="${pageContext.request.contextPath}/das/financial/supplier/create"
		modelAttribute="company" id="droppableForm" 
		class="form-horizontal draftCompanyForm droppable-form" role="form"
		method="post">
	
		
		

				<div class="clearfix">
					<h4>
						<i class="icon-arrow"></i>
						<spring:message code="finance.supplier.supplierDetailsTab.documentInformation"/>
					</h4>
					<hr class="smallHr">
					
					<div class="panel-body">

						<div class="form-group clearfix">

							<div class="col-sm-3">
								<div class="checkbox">
									<label for="OTHER_SYSTEM_ID"><spring:message code="finance.supplier.supplierDetailsTab.otherSystemIdNumber"/></label>
									<input type="checkbox" name="isPart" class="checkbox"/>
								</div>
			                </div>
							
							<div class="col-sm-3">
								<label for="SYSTEM_ID_NUMBER"><spring:message code="finance.supplier.supplierDetailsTab.systemIdNumber"/></label>
								<input id="SYSTEM_ID_NUMBER" name="SYSTEM_ID_NUMBER" class="form-control" type="text">
							</div>
							
						</div>
						
						<div class="form-group clearfix">

							<div class="col-sm-4">
								<label for="TERM_CONDITIONS_DOCUMENT"><spring:message code="finance.supplier.supplierDetailsTab.terms&ConditionsDocument"/></label>
				                <div class="fileupload fileupload-file fileupload-new" data-provides="fileupload">
									<div class="input-group">
										<div class="form-control uneditable-input">
											<i class="fa fa-file fileupload-exists"></i>
											<span class="fileupload-preview"></span>
										</div>
										<div class="input-group-btn">
											<div class="btn btn-light-grey btn-file">
												<span class="fileupload-new"><i class="fa fa-folder-open-o"></i><spring:message code="select.file"/></span>
												<span class="fileupload-exists"><i class="fa fa-folder-open-o"></i><spring:message code="button.change"/></span>
												<input id="RISK_ASSESMENT_UPLOAD" name="RISK_ASSESMENT_UPLOAD" type="file" class="file-input">
											</div>
											<a href="#" class="btn btn-light-grey fileupload-exists" data-dismiss="fileupload">
												<i class="fa fa-times"></i><spring:message code="button.remove"/>
											</a>
										</div>
									</div>
								</div>
							</div>

							<div class="col-sm-offset-2 col-sm-4">
								<label for="OTHER_DOCUMENTS"><spring:message code="finance.supplier.supplierDetailsTab.otherDocuments"/></label>
				                <div class="fileupload fileupload-file fileupload-new" data-provides="fileupload">
									<div class="input-group">
										<div class="form-control uneditable-input">
											<i class="fa fa-file fileupload-exists"></i>
											<span class="fileupload-preview"></span>
										</div>
										<div class="input-group-btn">
											<div class="btn btn-light-grey btn-file">
												<span class="fileupload-new"><i class="fa fa-folder-open-o"></i><spring:message code="select.file"/></span>
												<span class="fileupload-exists"><i class="fa fa-folder-open-o"></i><spring:message code="button.change"/></span>
												<input id="RISK_ASSESMENT_UPLOAD" name="RISK_ASSESMENT_UPLOAD" type="file" class="file-input">
											</div>
											<a href="#" class="btn btn-light-grey fileupload-exists" data-dismiss="fileupload">
												<i class="fa fa-times"></i><spring:message code="button.remove"/>
											</a>
										</div>
									</div>
								</div>
							</div>

						</div>
						
						<div class="form-group clearfix">

							<div class="col-sm-12">
       							<label for="NOTES"><spring:message code="finance.supplier.supplierDetailsTab.notes"/></label>
       							<textarea id="NOTES" name="NOTES" placeholder="Enter Text" class="form-control" data-field-to-copy="desc" autocomplete="off" rows="4"></textarea>
   							</div>

						</div>

					</div>
					
				</div>
					

				<%--DOCUMENT INFORMATION HAS NOT BEEN MAPPED YET --%>

					
	<%-- NEED TO BE MAPPED
		<div class="form-group clearfix">
		
			<div class="col-sm-2">
				<div class="checkbox">
					<label for="SHUTDOWNS"><spring:message code="finance.supplier.otherDetailsTab.shutdowns"/></label>
					<input type="checkbox" name="SHUTDOWNS" class="checkbox"/>
				</div>	
			</div>
	
			<div class="col-sm-2">
                <label for="from"><spring:message code="finance.supplier.otherDetailsTab.shutdownStart"/></label>
				<div class="input-group">
					<label for="from" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
					<input id="from" path="from" class="form-control date-picker" >
				</div>
			</div>
			
			<div class="col-sm-2">
                <label for="to"><spring:message code="finance.supplier.otherDetailsTab.shutdownEnd"/></label>
				<div class="input-group">
					<label for="to" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
					<input id="to" path="to" class="form-control date-picker" >
				</div>
			</div>
			
			<div class="col-sm-2">
				<label for="ACCREDITATIONS" class=""><spring:message code="finance.supplier.otherDetailsTab.accreditations"/></label>
				<form:select path="accreditationsList" class="form-control" >
	            	<option></option>
	            	<form:options items="${accreditationsList}" itemValue="id" itemLabel="name" data-field-to-copy="assetStatus"/>
	            </form:select>
			</div>
			
			<div class="col-sm-2">
                <label for="from"><spring:message code="finance.supplier.otherDetailsTab.accreditationFrom"/></label>
				<div class="input-group">
					<label for="from" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
					<input id="from" path="from" class="form-control date-picker" >
				</div>
			</div>
			
			<div class="col-sm-2">
                <label for="to"><spring:message code="finance.supplier.otherDetailsTab.accreditationTo"/></label>
				<div class="input-group">
					<label for="to" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
					<input id="to" path="to" class="form-control date-picker" >
				</div>
			</div>
			
		</div>	
	--%>	
		<div class="clearfix">
			<hr class="smallHr">
			<input type="submit" class="btn btn-success" value="<spring:message code="company.saveCompany"/>">
		</div>
	</form:form>
	
</div>