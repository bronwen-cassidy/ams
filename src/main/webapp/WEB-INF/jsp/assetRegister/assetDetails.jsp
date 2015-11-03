<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
<div class="container">

	<div class="form-panel panel panel-default no-margin no-border">
		<div class="panel-heading header-border">
		<a href="${pageContext.request.contextPath}/das/assetController?assetId=${asset.id}" class="btn btn-danger print-buttons">Edit Asset</a>
		<a class="btn btn-success print-buttons" onclick="printPage(${asset.id})">Print</a>
		<div id="printerDiv" style="display:none"></div>
			<i class="fa fa-square-o"></i>
			
			<strong class="pull-left detail-heading"><spring:message code="assetRegister.asset.assetdetails.assetDetailsView"/></strong>
			<p class="pull-right small-heading"><spring:message code="assetRegister.asset.generalTab.assetNumber"/>:&nbsp;<strong class="detail-heading">${asset.assetNumber}</strong></p>
			<p class="pull-right small-heading"><spring:message code="assetRegister.asset.uid"/>:&nbsp;<strong class="detail-heading">${asset.uid}</strong></p>
			<p class="pull-right small-heading"><spring:message code="assetRegister.asset.generalTab.name"/>:&nbsp;<strong class="detail-heading">${asset.name}</strong></p>
		
		</div>
		
	</div>
	
	<div class="inner-scroll detailed-view">
		
		<div class="panel-body">
		
		<hr>
			<div class="tabbable panel-tabs">
				
				<div class="tab-content">
				
				
					<div class="left-column col-sm-6">

						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">
								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.assetdetails.assetDetails"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
								
									<div class="word-wrap col-md-8">
										<label class="pull-left"><spring:message code="assetRegister.asset.uid"/>:&nbsp;</label>
										<strong>${asset.uid}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.status"/>:&nbsp;</label>
										<strong>${asset.assetStatus.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.assetNumber"/>:&nbsp;</label>
										<strong>${asset.assetNumber}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.serialNumber"/>:&nbsp;</label>
										<strong>${asset.serialNumber}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.createdBy"/>:&nbsp;</label>
										<strong>${asset.createdBy}</strong>
									</div>
										
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.category"/>:&nbsp;</label>
										<strong>${asset.category.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.name"/>:&nbsp;</label>
										<strong>${asset.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.otherSystemName"/>:&nbsp;</label>
										<strong></strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.otherSystemID"/>:&nbsp;</label>
										<strong></strong>
									</div>
	
									<div class="word-wrap col-md-8">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.description"/>:&nbsp;</label>
										<strong>${asset.description}</strong>
									</div>
		
								</div>

							</div>
							
						</div>

						<div class="col-md-12 padding-bottom pad-top ">
							
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.generalTab.custodianAndLocation"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.custodian"/>:&nbsp;</label>
										<strong>${asset.custodian.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.company"/>:&nbsp;</label>
										<strong>${asset.company.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.division"/>:&nbsp;</label>
										<strong>${asset.division.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.site"/>:&nbsp;</label>
										<strong>${asset.site.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.department"/>:&nbsp;</label>
										<strong>${asset.department.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.country"/>:&nbsp;</label>
										<strong>${asset.country.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.location"/>:&nbsp;</label>
										<strong>${asset.location.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.trackingDevice"/>:&nbsp;</label>
										<strong>${asset.trackingDevice}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.criticalAsset"/>:&nbsp;</label>
										<strong>${asset.criticalAsset}</strong>
									</div>
									
								</div>
				
							</div>

						</div>
					
						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.financeTab.purchaseDetails"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.purchasePrice"/>:&nbsp;</label>
										<strong>${asset.purchasePrice}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.vatCode"/>:&nbsp;</label>
										<strong>${asset.vatCode.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.totalCost"/>:&nbsp;</label>
										<strong>${asset.cost}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.purchaseLeadTime"/>:&nbsp;</label>
										<strong>${asset.purchaseLeadTime}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.budgetLimit"/>:&nbsp;</label>
										<strong>${asset.budgetLimit}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.dateOfPurchase"/>:&nbsp;</label>
										<strong><spring:eval expression="asset.dateOfPurchase"/></strong>
									</div>
	
								</div>
							
							</div>
							
						</div>

						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.financeTab.commissioning"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.installationDate"/>:&nbsp;</label>
										<strong><spring:eval expression="asset.installationDate"/></strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.commissioningDate"/>:&nbsp;</label>
										<strong><spring:eval expression="asset.commissioningDate"/></strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.decommissioningDate"/>:&nbsp;</label>
										<strong><spring:eval expression="asset.decommissioningDate"/></strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.endOfLifeDate"/>:&nbsp;</label>
										<strong><spring:eval expression="asset.endOfLifeDate"/></strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.lifeExpectancy"/>:&nbsp;</label>
										<strong>${asset.lifeExpectancy}</strong>
									</div>
	
								</div>
								
							</div>
						
						</div>
					
						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.tenureTab.leaseIn"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.type"/>:&nbsp;</label>
										<strong>${asset.leaseIn.leaseType.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.leasedFrom"/>:&nbsp;</label>
										<strong>${asset.leaseIn.partyName}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.leaseInCharge"/>:&nbsp;</label>
										<strong>${asset.leaseIn.leaseCharge}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.vatCode"/>:&nbsp;</label>
										<strong>${asset.leaseIn.vatCode.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.totalLeaseValue"/>:&nbsp;</label>
										<strong>${asset.leaseIn.leaseValue}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.attachLeaseAgreement"/>:&nbsp;</label>
										<button><spring:message code="view.viewfile"/></button>
									</div>
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.leasePeriod"/>:&nbsp;</label>
										<strong>${asset.leaseIn.leasePeriod}</strong>
									</div>
							
								</div>
								
							</div>

						</div>
						
						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.tenureTab.leaseOut"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.type"/>:&nbsp;</label>
										<strong>${asset.leaseOut.leaseType.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.leaseOutCharge"/>:&nbsp;</label>
										<strong>${asset.leaseOut.leaseCharge}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.vatCode"/>:&nbsp;</label>
										<strong>${asset.leaseOut.vatCode.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.attachLeaseAgreement"/>:&nbsp;</label>
										<br>
										<button><spring:message code="view.viewfile"/></button>
									</div>
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.leasePeriod"/>:&nbsp;</label>
										<strong>${asset.leaseOut.leasePeriod}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.tenureTab.leaseOutMargin"/>:&nbsp;</label>
										<strong>${asset.leaseOut.leaseOutMargin}</strong>
									</div>
	
								</div>
	
							</div>
							
						</div>

					</div>
					
					<div class="right-column col-sm-6">
						
						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.generalTab.supplierInformation"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.supplier"/>:&nbsp;</label>
										<strong>${asset.supplier.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.supplierPN"/>:&nbsp;</label>
										<strong>${asset.supplierPn}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.manufacturer"/>:&nbsp;</label>
										<strong>${asset.manufacturer.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.manufacturerPN"/>:&nbsp;</label>
										<strong>${asset.manufacturerPn}</strong>
									</div>
									
									<div class="word-wrap col-md-4">
										<label class="pull-left"><spring:message code="address.telephoneNumber"/>:&nbsp;</label>
										<strong>${asset.supplier.partyContactDetails.partyTelephoneNumber.telephoneNumber.telNo}</strong>
									</div>		
									
									<div class="word-wrap col-sm-4">
										<label class="pull-left"><spring:message code="finance.supplier.supplierDetailsTab.email"/>:&nbsp;</label>
										<strong><a class="utility-droplinks" data-toggle="modal" data-target="#modal" href="${pageContext.request.contextPath}/das/email?partyId=${asset.supplier.id}&assetId=${asset.id}">
											${asset.supplier.partyContactDetails.partyEmail.email.emailAddress}</a></strong>
									</div>						
	
								</div>
							
							</div>

						</div>
						
						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.generalTab.groupParts"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-3">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.partOfGroup"/>:&nbsp;</label>
										<strong>${asset.partOfGroup}<br/>
										<c:forEach items="${asset.groups}" var="group">${group.name}<br/></c:forEach>
										</strong>
									</div>
	
									<div class="word-wrap col-md-3">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.childOf"/>:&nbsp;</label>
										<strong></strong>
									</div>
	
									<div class="word-wrap col-md-3">
										<label class="pull-left"><spring:message code="assetRegister.asset.generalTab.parts"/>:&nbsp;</label>
										<strong>${asset.parts}</strong>
									</div>
	
									<!-- <div class="word-wrap col-md-3">
										<button>View Image</button>
									</div>-->
									
									<div class="word-wrap col-md-3">
										<img src="${pageContext.request.contextPath}/images/${asset.uid}.jpg" style="height:auto; width:auto; max-width:90px; max-height:100px;">
									</div>
	
								</div>
	
							</div>
						
						</div>
						
						<div class="col-md-12 padding-bottom pad-top ">
							
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.generalTab.riskManagement"/>
								</div>

							<div class="panel-body-thin col-md-12">

									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.generalTab.riskAssessment"/>:&nbsp;</label>
										<strong>${asset.riskAssessment}&nbsp</strong>
										<button><spring:message code="view.viewfile"/></button>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.generalTab.bcp"/>:&nbsp;</label>
										<strong>${asset.bcp}&nbsp</strong>
										<button><spring:message code="view.viewfile"/></button>
									</div>
									
								</div>

							</div>

						</div>
						<div class="col-md-12 padding-bottom pad-top ">
							
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.financeTab.depreciation"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.depreciationCode"/>:&nbsp;</label>
										<strong>${asset.depreciationCode.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.currentDepreciatedValue"/>&nbsp;</label>
										<strong></strong><%--TODO--%>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.financeTab.predictedEndOfLifeValue"/>:&nbsp;</label>
										<strong>${asset.predictedEndOfLifeValue}</strong>
									</div>
	
								</div>
	
							</div>
						
						</div>
						
						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">
	
								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.indemnitiesTab.insurance"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.insuranceType"/>:&nbsp;</label>
										<strong>${asset.assetInsurance.insuranceType.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.insuranceSupplier"/>:&nbsp;</label>
										<strong>${asset.assetInsurance.insuranceTypePolicyLink.insurancePolicy.insuranceSupplier.name}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
 										<label><spring:message code="assetRegister.asset.indemnitiesTab.insuranceCommences"/>:&nbsp;</label> 
										<strong><spring:eval expression="asset.assetInsurance?.insuranceTypePolicyLink?.insurancePolicy?.commencementDate"/></strong>
									</div> 
									
									<div class="word-wrap col-md-4">
 										<label><spring:message code="assetRegister.asset.indemnitiesTab.insuranceExpires"/>:&nbsp;</label> 
										<strong><spring:eval expression="asset.assetInsurance?.insuranceTypePolicyLink?.insurancePolicy?.expiryDate"/></strong>
									</div> 
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.cost"/>:&nbsp;</label>
										<strong>£${asset.assetInsurance.insuranceTypePolicyLink.insurancePolicy.cost}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.attachPolicy"/>:&nbsp;</label>
										<br>
										<button><spring:message code="view.viewfile"/></button>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.policyNumber"/>:&nbsp;</label>
										<strong>${asset.assetInsurance.insuranceTypePolicyLink.insurancePolicy.policyNumber}</strong>
									</div>
	
								</div>
								
							</div>

						</div>
						
						<div class="col-md-12 padding-bottom pad-top ">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.indemnitiesTab.warranty"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
									
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantyType"/>:&nbsp;</label>
										<strong>${asset.warranty.assetWarrantyType.name}</strong>
									</div>
										
 									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantySupplier"/>:&nbsp;</label>
										<strong>${asset.warranty.warrantyPolicy.supplierName}</strong>
									</div>
	
									<div class="word-wrap col-md-4">
 										<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantyCommences"/>:&nbsp;</label> 
										<strong><spring:eval expression="asset.warranty?.warrantyPolicy?.commencementDate"/></strong>
									</div> 
									
 									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantyExpires"/>:&nbsp;</label>
										<strong><spring:eval expression="asset.warranty?.warrantyPolicy?.expiryDate"/></strong>
									</div> 
	
 									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.cost"/>:&nbsp;</label>
										<strong>£${asset.warranty.warrantyPolicy.cost}</strong>
									</div> 
	
 									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.attachWarranty"/>:&nbsp;</label>
										<br>
										<button><spring:message code="view.viewfile"/></button>
									</div>
	
									<div class="word-wrap col-md-4">
										<label><spring:message code="assetRegister.asset.indemnitiesTab.policyNumber"/>:&nbsp;</label>
										<strong>${asset.warranty.warrantyPolicy.policyNumber}</strong>
									</div>
	
								</div>
	
							</div>
							
						</div>
						
						<div class="col-md-12 pad-top  padding-bottom">
						
							<div class="border">

								<div class="panel-heading-separate col-md-12">
									<spring:message code="assetRegister.asset.maintenance.coreSkills"/>
								</div>
	
								<div class="panel-body-thin col-md-12">
	
									<div class="word-wrap col-md-2 padding-bottom">
										<label><spring:message code="assetRegister.asset.maintenance.industry"/>:&nbsp;</label>
										<strong></strong>
									</div>
	
									<div class="word-wrap col-md-2 padding-bottom">
										<label><spring:message code="assetRegister.asset.maintenance.coreSkillQualification"/>:&nbsp;</label>
										<strong></strong>
									</div>
								</div>
							</div>
						</div>
					</div>					
					</div>	
			</div>

		</div>
	
	</div>
	
</div>
	

