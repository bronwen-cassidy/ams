<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="print-inner-container">
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.assetdetails.assetDetails"/>
			</div>
			<hr>
			<div class="print-img">
				<img src="${pageContext.request.contextPath}/images/${asset.uid}.jpg" style="height:auto; width:auto; max-width:90px; max-height:100px;">
			</div>
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.assetNumber"/>:&nbsp;</label>
				<strong>${asset.assetNumber}</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.uid"/>:&nbsp;</label>
				<strong>${asset.uid}</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.status"/>:&nbsp;</label>
				<strong>${asset.assetStatus.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.assetNumber"/>:&nbsp;</label>
				<strong>${asset.assetNumber}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.serialNumber"/>:&nbsp;</label>
				<strong>${asset.serialNumber}</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.createdBy"/>:&nbsp;</label>
				<strong>${asset.createdBy}</strong>
			</div>
				
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.category"/>:&nbsp;</label>
				<strong>${asset.category.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.name"/>:&nbsp;</label>
				<strong>${asset.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.otherSystemName"/>:&nbsp;</label>
				<strong></strong>
			</div>
			
				<div class="print-details">
					<label class=""><spring:message code="assetRegister.asset.generalTab.otherSystemID"/>:&nbsp;</label>
					<strong></strong>
				</div>
			
				<div class="print-details">
					<label class=""><spring:message code="assetRegister.asset.generalTab.description"/>:&nbsp;</label>
					<strong>${asset.description}</strong>
				</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.generalTab.custodianAndLocation"/>
			</div>
			<hr>
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.custodian"/>:&nbsp;</label>
				<strong>${asset.custodian.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.company"/>:&nbsp;</label>
				<strong>${asset.company.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.division"/>:&nbsp;</label>
				<strong>${asset.division.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.site"/>:&nbsp;</label>
				<strong>${asset.site.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.department"/>:&nbsp;</label>
				<strong>${asset.department.name}</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.country"/>:&nbsp;</label>
				<strong>${asset.country.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.location"/>:&nbsp;</label>
				<strong>${asset.location.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.trackingDevice"/>:&nbsp;</label>
				<strong>${asset.trackingDevice}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.criticalAsset"/>:&nbsp;</label>
				<strong>${asset.criticalAsset}</strong>
			</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.financeTab.purchaseDetails"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.purchasePrice"/>:&nbsp;</label>
				<strong>${asset.purchasePrice}</strong>
			</div>
		
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.vatCode"/>:&nbsp;</label>
				<strong>${asset.vatCode.name}</strong>
			</div>
		
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.totalCost"/>:&nbsp;</label>
				<strong>${asset.cost}</strong>
			</div>
		
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.purchaseLeadTime"/>:&nbsp;</label>
				<strong>${asset.purchaseLeadTime}</strong>
			</div>
		
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.budgetLimit"/>:&nbsp;</label>
				<strong>${asset.budgetLimit}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.dateOfPurchase"/>:&nbsp;</label>
				<strong><spring:eval expression="asset.dateOfPurchase"/></strong>
			</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.financeTab.commissioning"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.installationDate"/>:&nbsp;</label>
				<strong><spring:eval expression="asset.installationDate"/></strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.commissioningDate"/>:&nbsp;</label>
				<strong><spring:eval expression="asset.commissioningDate"/></strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.decommissioningDate"/>:&nbsp;</label>
				<strong><spring:eval expression="asset.decommissioningDate"/></strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.endOfLifeDate"/>:&nbsp;</label>
				<strong><spring:eval expression="asset.endOfLifeDate"/></strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.lifeExpectancy"/>:&nbsp;</label>
				<strong>${asset.lifeExpectancy}</strong>
			</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.generalTab.supplierInformation"/>
			</div>
			<hr>
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.supplier"/>:&nbsp;</label>
				<strong>${asset.supplier.name}</strong>
			</div>
		
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.supplierPN"/>:&nbsp;</label>
				<strong>${asset.supplierPn}</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.manufacturer"/>:&nbsp;</label>
				<strong>${asset.manufacturer.name}</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.manufacturerPN"/>:&nbsp;</label>
				<strong>${asset.manufacturerPn}</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="address.telephoneNumber"/>:&nbsp;</label>
				<strong>${telNo}</strong>
			</div>		
			
			<div class="print-details">
				<label class=""><spring:message code="finance.supplier.supplierDetailsTab.email"/>:&nbsp;</label>
				<strong>${email}</strong>
			</div>
		</div>
		<div class="print-box">
		
			<div class="print-heading">
				<spring:message code="assetRegister.asset.tenureTab.leaseIn"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.type"/>:&nbsp;</label>
				<strong>${asset.leaseIn.leaseType.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.leasedFrom"/>:&nbsp;</label>
				<strong>${asset.leaseIn.partyName}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.leaseInCharge"/>:&nbsp;</label>
				<strong>${asset.leaseIn.leaseCharge}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.vatCode"/>:&nbsp;</label>
				<strong>${asset.leaseIn.vatCode.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.maintenance"/>:&nbsp;</label>
				<strong>${asset.leaseIn.maintenanceIncluded}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.totalLeaseValue"/>:&nbsp;</label>
				<strong>${asset.leaseIn.leaseValue}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.leasePeriod"/>:&nbsp;</label>
				<strong>${asset.leaseIn.leasePeriod}</strong>
			</div>
		
		</div>
		
		<div class="print-box">
		
			<div class="print-heading">
				<spring:message code="assetRegister.asset.tenureTab.leaseOut"/>
			</div>
			<hr>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.type"/>:&nbsp;</label>
				<strong>${asset.leaseOut.leaseType.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.leasedTo"/>:&nbsp;</label>
				<strong>${asset.leaseOut.partyName}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.leaseOutCharge"/>:&nbsp;</label>
				<strong>${asset.leaseOut.leaseCharge}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.vatCode"/>:&nbsp;</label>
				<strong>${asset.leaseOut.vatCode.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.maintenance"/>:&nbsp;</label>
				<strong>${asset.leaseOut.maintenanceIncluded}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.warrenty"/>:&nbsp;</label>
				<strong>${asset.leaseOut.warrantyIncluded}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.totalLeaseValue"/>:&nbsp;</label>
				<strong>${asset.leaseOut.leaseValue}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.leasePeriod"/>:&nbsp;</label>
				<strong>${asset.leaseOut.leasePeriod}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.tenureTab.leaseOutMargin"/>:&nbsp;</label>
				<strong>${asset.leaseOut.leaseOutMargin}</strong>
			</div>
		</div>
	
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.generalTab.groupParts"/>
			</div>
			<hr>
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.partOfGroup"/>:&nbsp;</label>
				<strong>${asset.partOfGroup}<br/>
				<c:forEach items="${asset.groups}" var="group">${group.name}<br/></c:forEach>
				</strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.childOf"/>:&nbsp;</label>
				<strong></strong>
			</div>
			
			<div class="print-details">
				<label class=""><spring:message code="assetRegister.asset.generalTab.parts"/>:&nbsp;</label>
				<strong>${asset.parts}</strong>
			</div>		
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.financeTab.depreciation"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.generalTab.riskAssessment"/>:&nbsp;</label>
				<strong>${asset.riskAssessment}&nbsp</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.generalTab.bcp"/>:&nbsp;</label>
				<strong>${asset.bcp}&nbsp</strong>
			</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.generalTab.riskManagement"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.depreciationCode"/>:&nbsp;</label>
				<strong>${asset.depreciationCode.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.currentDepreciatedValue"/>&nbsp;</label>
				<strong></strong><%--TODO--%>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.financeTab.predictedEndOfLifeValue"/>:&nbsp;</label>
				<strong>${asset.predictedEndOfLifeValue}</strong>
			</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.indemnitiesTab.insurance"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.insuranceType"/>:&nbsp;</label>
				<strong>${asset.assetInsurance.insuranceType.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.insuranceSupplier"/>:&nbsp;</label>
				<strong>${asset.assetInsurance.insuranceTypePolicyLink.insurancePolicy.insuranceSupplier.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.cost"/>:&nbsp;</label>
				<strong>£ ${asset.assetInsurance.insuranceTypePolicyLink.insurancePolicy.cost}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.policyNumber"/>:&nbsp;</label>
				<strong>${asset.assetInsurance.insuranceTypePolicyLink.insurancePolicy.policyNumber}</strong>
			</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.indemnitiesTab.warranty"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantyType"/>:&nbsp;</label>
				<strong>${asset.warranty.type.name}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantySupplier"/>:&nbsp;</label>
				<strong>${asset.warranty.supplierName}</strong>
			</div>
			
			<div class="print-details">
					<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantyCommences"/>:&nbsp;</label> 
				<strong><spring:eval expression="asset.warranty?.commencementDate"/></strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.warrantyExpires"/>:&nbsp;</label>
				<strong><spring:eval expression="asset.warranty?.expiryDate"/></strong>
			</div> 
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.cost"/>:&nbsp;</label>
				<strong>${asset.warranty.cost}</strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.indemnitiesTab.policyNumber"/>:&nbsp;</label>
				<strong>${asset.warranty.policyNumber}</strong>
			</div>
		</div>
		
		<div class="print-box">
			<div class="print-heading">
				<spring:message code="assetRegister.asset.maintenance.coreSkills"/>
			</div>
			<hr>
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.maintenance.industry"/>:&nbsp;</label>
				<strong></strong>
			</div>
			
			<div class="print-details">
				<label><spring:message code="assetRegister.asset.maintenance.coreSkillQualification"/>:&nbsp;</label>
				<strong></strong>
			</div>
		</div>
</div>
	