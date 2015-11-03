<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="das" uri="das-tags"%>

<script src="${pageContext.request.contextPath}/js/depreciation.js"></script>
<script src="${pageContext.request.contextPath}/js/finance.js"></script>

<div class="tab-content">

<form:form  action="${pageContext.request.contextPath}/das/assetController/saveDraftAsset" modelAttribute="asset" id="assetForm" class="form-horizontal draftForm droppable-form" role="form" method="post">

		<font color="red">
			<b><form:errors title="Errors" path="*" cssClass="validation-error"/></b> <!-- exception error handling! -->
		</font>
			
 		<das:droppable bean="${asset}" formId="assetForm"/>		
 		
		<div class="panel-group accordion-custom accordion-teal" id="accordion">

			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title">
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#purchase_panel">
						<i class="icon-arrow"></i>
						<spring:message code="assetRegister.asset.financeTab.purchaseDetails"/>
					</a></h4>

				</div>

				<div id="purchase_panel" class="panel-collapse in">

					<div class="panel-body">

						<div class="form-group clearfix">

							<div class="col-sm-3">	
							<label for="PURCHASE_PRICE"><spring:message code="assetRegister.asset.financeTab.purchasePrice"/></label>
								<div class="input-group">
									<label for="purchasePrice" class="input-group-addon">£</label>
									<fmt:formatNumber value="${asset.purchasePrice}" minFractionDigits="2" var="price"/> 
									<form:input id="purchasePrice" path="purchasePrice" value="${price}" class="form-control " type="text" />
								</div>						
							</div>

							<div class="col-sm-3">
								<label for="vatCodeId"><spring:message code="assetRegister.asset.financeTab.vatCode"/></label>
								<das:userdata var="VATCode" userDataType="VAT_CODE"/>
								<form:select id="vatCodeId" path="vatCodeId" class="form-control scratchpad-trigger" data-scratchpad-trigger-id="vatCodes">
									<option></option>
									<form:options items="${VATCode}" itemValue="id" itemLabel="name" data-field-to-copy="id" />
				                </form:select>
							</div>

							<div class="col-sm-3">
								<label for="cost"><spring:message code="assetRegister.asset.financeTab.totalCost"/></label>
								<div class="input-group">
									<label for="cost" class="input-group-addon">£</label>
									<form:input id="cost" path="cost" cssClass="form-control total-cost-calc" data-vat-code_calc="vatCodeId" data-cost_calc="purchasePrice"/>
								</div>	
							</div>

							<div class="col-sm-3">
								<label for="dateOfPurchase"><spring:message code="assetRegister.asset.financeTab.dateOfPurchase"/></label>
								<div class="input-group">
									<label for="dateOfPurchase" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
									<form:input id="dateOfPurchase" path="dateOfPurchase" cssClass="form-control date-picker"/>
								</div>
							</div>

						</div>
						
						<div class="form-group clearfix">

							<div class="col-sm-3">
								<label for="purchaseLeadTime"><spring:message code="assetRegister.asset.financeTab.purchaseLeadTime"/></label>
								<das:userdata var="purchaseLeadTimes" userDataType="PURCHASE_LEAD_TIMES"/>
								<form:select id="purchaseLeadTime"  path="purchaseLeadTime" cssClass="form-control">
									<option></option>
									<form:options items="${purchaseLeadTimes}" itemValue="id" itemLabel="name" data-field-to-copy="PURCHASE_LEAD_TIME" />
								</form:select>
							</div>
										
							<div class="col-sm-3">
								<label for="budgetLimit"><spring:message code="assetRegister.asset.financeTab.budgetLimit"/></label>
								<div class="input-group">
									<label for="budgetLimit" class="input-group-addon">£</label>
									<form:input path="budgetLimit" cssClass="form-control scratchpad-trigger" data-scratchpad-trigger-id="budget"/>
								</div>
							</div>

						</div>

					</div>

				</div>

			</div>

			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#installation_panel">
						<i class="icon-arrow"></i>
						<spring:message code="assetRegister.asset.financeTab.commissioning"/>
					</a></h4>

				</div>

				<div id="installation_panel" class="panel-collapse collapse" style="height: 0px;">

					<div class="panel-body">

						<div class="form-group clearfix">

							<div class="col-sm-3">
								<label for="installationDate"><spring:message code="assetRegister.asset.financeTab.installationDate"/></label>
								<div class="input-group">
									<label for="installationDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
									<form:input id="installationDate" path="installationDate" cssClass="form-control date-picker" />
								</div>
							</div>

							<div class="col-sm-3">
								<label for="commissioningDate"><spring:message code="assetRegister.asset.financeTab.commissioningDate"/></label>
								<div class="input-group">
									<label for="commissioningDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
									<form:input id="commissioningDate" path="commissioningDate" cssClass="form-control date-picker" />
								</div>
							</div>

							<div class="col-sm-3">
								<label for="decommissioningDate"><spring:message code="assetRegister.asset.financeTab.decommissioningDate"/></label>
								<div class="input-group">
									<label for="decommissioningDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
									<form:input id="decommissioningDate" path="decommissioningDate" cssClass="form-control date-picker" />
								</div>
							</div>

							<div class="col-sm-3">
								<label for="endOfLifeDate"><spring:message code="assetRegister.asset.financeTab.endOfLifeDate"/></label>
								<div class="input-group">
									<label for="endOfLifeDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
									<form:input id="endOfLifeDate" path="endOfLifeDate" cssClass="form-control date-picker" />
								</div>
							</div>

						</div>

						<div class="form-group clearfix">

							<div class="col-sm-3">
								<label for="lifeExpectancy"><spring:message code="assetRegister.asset.financeTab.lifeExpectancy"/></label>
								<form:input id="lifeExpectancy" path="lifeExpectancy" cssClass="form-control"/>
							</div>

						</div>

					</div>

				</div>

			</div>
			
			<div class="inner-form-panel panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#depreciation_panel">
						<i class="icon-arrow"></i>
						<spring:message code="assetRegister.asset.financeTab.depreciation"/>
					</a></h4>

				</div>

				<div id="depreciation_panel" class="panel-collapse collapse" style="height: 0px;">

					<div class="panel-body">

						<div class="form-group clearfix">

							<div class="col-sm-3">
								<label for="depreciationCodeId"><spring:message code="assetRegister.asset.financeTab.depreciationCode"/></label>
								<das:userdata var="depreciationCodes" userDataType="DEPRECIATION_CODE"/>
								<form:select id="depreciationCodeId" name="depreciationCodeId" path="depreciationCodeId" class="form-control">
									<option></option>
									<form:options items="${depreciationCodes}" itemValue="value" itemLabel="name" data-field-to-copy="DEPRECIATION_CODE" />
								</form:select>
							</div>

							<div class="col-sm-3">
								<label for="currentDepreciatedValue"><spring:message code="assetRegister.asset.financeTab.currentDepreciatedValue"/></label>
								<div class="input-group">
									<label for="currentDepreciatedValue" class="input-group-addon">£</label>
									<form:input id="currentDepreciatedValue" path="currentDepreciatedValue" cssClass="form-control" />
								</div>
							</div>

							<div class="col-sm-3">
								<label for="predictedEndOfLifeValue"><spring:message code="assetRegister.asset.financeTab.predictedEndOfLifeValue"/></label>
								<div class="input-group">
									<label for="predictedEndOfLifeValue" class="input-group-addon">£</label>
									<form:input id="predictedEndOfLifeValue" path="predictedEndOfLifeValue" cssClass="form-control"/>
								</div>
							</div>

						</div>

					</div>

				</div>

			</div>

		</div>

	</form:form>

</div>
