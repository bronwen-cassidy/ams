<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="das" uri="das-tags" %>


<div class="tab-content">
	<div id="general_tab" class="tab-pane active">

		<form:form action="${pageContext.request.contextPath}/das/assetAvailabilitySearch/searchAvailability" modelAttribute="assetAvailabilitySearch" class="form-horizontal droppable-form" role="form" id="assetAvailabilitySearch" method="post">

			<das:droppable bean="${assetAvailabilitySearch}" formId="assetAvailabilitySearch" />
			
			<div class="form-group clearfix">

				<div class="col-sm-4">

					<form:hidden path="clientId" cssClass="droppable" data-dnd-accepts="clientDraggable" data-dnd-field-to-copy="id"/>
			        <label for="clientName"><spring:message code="scratchpad.finance.client"/></label>
					<form:input id="clientName" path="clientName" cssClass="form-control scratchpad-trigger droppable" data-dnd-accepts="clientDraggable" data-dnd-field-to-copy="name" data-scratchpad-trigger-id="client"/>
							
				</div>
			</div>
			<div class="form-group clearfix">

				<div class="col-sm-4">
			        <label for="fromDate" class="">Date From:</label>
			        <div class="input-group">
						<label for="fromDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
						<input id="fromDate" name="fromDate" class="form-control date-picker" type="text" value="">
					</div>
				</div>
				<div class="col-sm-4">
				
					<label for="toDate" class="">Date To:</label>
			        <div class="input-group">
						<label for="toDate" class="input-group-addon"> <i class="fa fa-calendar"></i> </label>
						<input id="toDate" name="toDate" class="form-control date-picker" type="text" value="">
					</div>
				</div>
				
				<div class="col-sm-4">
			        <label for="" class="">Asset Name</label>
		            <form:input path="availableName" class="form-control asset-search-field" />
				</div>

			</div>

		</form:form>
		
		<div class="panel-group accordion-custom accordion-teal" id="accordion">

			<div class="available-table inner-form-panel panel panel-default" style="display:none;">

				<div class="panel-heading">

					<h4 class="panel-title">
						<a class="accordion-toggle" data-toggle="collapse"
							data-parent="#accordion" href="#supplier_info_panel"> <i
							class="icon-arrow"></i>
							Available 
						</a>
						<a class="unavailable-toggle unavailableTableToggle-show accordion-toggle collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#custodian_part_panel">Show Unavailable</a>
					</h4>

				</div>

				<div id="supplier_info_panel" class="panel-collapse in">

					<div class="panel-body">

						<div class="form-group clearfix">
							
							<div class="available-label sales-table-container" style="display:none;">
								<table class="table-container context-opener sales-order-table table multi-table table-hover scroll-content available-asset-table" data-type="ASSETS">
								</table>
							</div>

						</div>

					</div>

				</div>

			</div>
			
			

			<div class="unavailable inner-form-panel panel panel-default" style="display:none;">

				<div class="panel-heading">

					<h4 class="panel-title">
						<a class="accordion-toggle collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#custodian_part_panel"> <i
							class="icon-arrow"></i>Unavailable
						</a><a class="unavailable-toggle unavailableTableToggle-close accordion-toggle" data-toggle="collapse"
							data-parent="#accordion" href="#supplier_info_panel">Close Unavailable </a>
					</h4>

				</div>

				<div id="custodian_part_panel" class=" panel-collapse collapse" style="height: 0px;">

					<div class="panel-body">

						<div class="form-group clearfix">
							<div class="sales-table-container " style="display:none !important;">
								<!-- Table data set by class .available-asset-table -->
								<table class="table-container context-opener sales-order-table table multi-table table-hover scroll-content not-available-asset-table scratchpad-trigger" data-type="ASSETS" data-scratchpad-trigger-id="assetProposal">
								</table>
							</div>

						</div>

					</div>

				</div>

			</div>
			
		</div>
		
		<div class="footer sales-table-container" style="display:none;">
		<hr>
			<input value="Create Proposal" class="pull-right btn btn-success disabled" disabled type="submit" id="createProposalButton"/>
		</div>
	</div>
</div>
