$("document").ready(function(){
	/*
	 * Compares the from and to date fields to get the time period.
	 * changes the days from the time period calculation to days, weeks, months or years. 
	 */
	$(".date-period-calc").each(function(){
		var $this = $(this);
		// Get the IDs of the from and to date fields
		var fromDateFieldId =  $(this).data("from-date_calc");
		var toDateFieldId = $(this).data("to-date_calc");
		var chargePeriodFieldId = $(this).data("charge-period_calc");
		
		var fromDateField =  $("[id='" + fromDateFieldId + "']");   
		var toDateField = $("[id='" + toDateFieldId + "']");   
		var chargePeriodField = $("[id='" + chargePeriodFieldId + "']"); 
		
		// for each of above. Add an on change event listener
		fromDateField.change(function(){
			console.log("lease commences input" + fromDateFieldId);
			calculate();
		});
		toDateField.change(function() {
			console.log("lease expires input" + toDateFieldId);
			calculate();
		});
		chargePeriodField.change(function() {
			console.log("charge period input" + chargePeriodFieldId);
			calculate();
		});

		
		function calculate() {
			var fromDateVal = fromDateField.val(); //Get Lease Commences date (Req)
			var toDateVal = toDateField.val(); //Get Lease Expires date (Req)
			var chargePeriodVal = $(":selected", chargePeriodField).text(); //Get Charge Period for lease (Opt)

			ajaxCallCalculatePeriod(fromDateVal, toDateVal, chargePeriodVal, $this);
		}
	});
	
	var ajaxCallCalculatePeriod = function(from, to, charge, returnField) {
		$.ajax({ //Post data to calculator and get response
			type: "POST",
			url: base_url + "/das/utility/datePeriodCalc",
			data: { leaseStart: from, leaseEnd: to, chargePeriod: charge }
		}) .done(function(responseMsg) { //If successful print response to screen and to input field
			console.log("AJAX Call Successful - " + responseMsg);
			returnField.val(responseMsg);
		}) .fail(function(textStatus) { //If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	};
	
	/*
	 * Calculates the cost from the start and end date period with the lease charge. 
	 */
	$(".cost-calc").each(function(){
		var $this = $(this);
		// Get the IDs of the from and to date fields
		var fromDateFieldId =  $(this).data("from-date_calc");
		var toDateFieldId = $(this).data("to-date_calc");
		var chargePeriodFieldId = $(this).data("charge-period_calc");
		var leaseChargeFieldId = $(this).data("lease-charge_calc");
		
		var fromDateField =  $("[id='" + fromDateFieldId + "']");   
		var toDateField = $("[id='" + toDateFieldId + "']");   
		var chargePeriodField = $("[id='" + chargePeriodFieldId + "']"); 
		var leaseChargeField = $("[id='" + leaseChargeFieldId + "']"); 
		
		// for each of above. Add an on change event listener
		fromDateField.change(function(){
			console.log("lease commences input" + fromDateFieldId);
			calculate();
		});
		toDateField.change(function() {
			console.log("lease expires input" + toDateFieldId);
			calculate();
		});
		chargePeriodField.change(function() {
			console.log("charge period input" + chargePeriodFieldId);
			calculate();
		});
		
		leaseChargeField.change(function() {
			console.log("lease in charge input" + leaseChargeFieldId);
			calculate();
		});

		
		function calculate() {
			var fromDateVal = fromDateField.val(); //Get Lease Commences date (Req)
			var toDateVal = toDateField.val(); //Get Lease Expires date (Req)
			var chargePeriodVal = $(":selected", chargePeriodField).text(); //Get Charge Period for lease (Opt)
			var leaseChargeVal = leaseChargeField.val();//get charge amount for lease.
			console.log($(this));
			
			ajaxCallCalculateCost(fromDateVal, toDateVal, chargePeriodVal, leaseChargeVal, $this);	
		}
	});
	var ajaxCallCalculateCost = function(from, to, chargePeriod, charge, returnField) {
		console.log("calc cost ajax. charge:" + charge);
		$.ajax({ //Post data to calculator and get response
			type: "POST",
			url: base_url + "/das/utility/costCalc",
			data: { leaseStart: from, leaseEnd: to, chargePeriod: chargePeriod, leaseCharge: charge}
		}) .done(function(responseMsg) { //If successful print response to screen and to input field
			console.log("AJAX Call Successful - " + responseMsg);
			returnField.val(responseMsg);
		}) .fail(function(textStatus) { //If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	};
	
	/*
	 * Calculates the total cost off the lease in/out with a calculation from the VAT code.
	 */
	$(".total-cost-calc").each(function(){
		var $this = $(this);
		// Get the IDs of the from and to date fields
		var vatCodeFieldId =  $this.data("vat-code_calc");
		var leaseCostFieldId =  $this.data("cost_calc");
		var vatField = $("[id='" + vatCodeFieldId + "']");
		var costField = $("[id='" + leaseCostFieldId + "']");
		
		// for each of above. Add an on change event listener
		vatField.change(function(){
			console.log("VAT Code Input" + vatCodeFieldId);
			calculate();
		});
		
		costField.change(function(){
			console.log("Lease Cost Input" + leaseCostFieldId);
			calculate();
		});
		
		function calculate() {
			var vatCodeVal = vatField.val(); //Get VAT Code Input(Req)
			var leaseCostVal = costField.val(); //Get VAT Code Input(Req)
			ajaxCallCalculateTotalCost(vatCodeVal,leaseCostVal, $this);	
		}
	});
	/*
	 * Calculates the lease out margin if leaseIn is present
	 */
	
	$('input[id="leaseOut.leaseValue"]').change(function(){
			console.log('Testing123');
			
			if($('input[id="leaseIn.leaseValue"]').val() != null){
				var leaseOut = $('input[id="leaseOut.leaseValue"]').val();
				var leaseIn = $('input[id="leaseIn.leaseValue"]').val();
				var leaseOutMargine = leaseOut - leaseIn;
				console.log("dsfsdfsdfsdfs ");
				$('input[id="leaseOut.leaseOutMargin"]').val(leaseOutMargine);
			}
			else if(!$('input[id="leaseIn.leaseValue"]').val()){
				$('input[id="leaseOut.leaseOutMargin"]').val(1);
			}
	});
	
	

	var ajaxCallCalculateTotalCost = function(vatCode, leaseCost, returnField) {
		console.log("Total cost calc, VAT:" + vatCode);
		console.log("Total lease cost:" + leaseCost);
		$.ajax({ //Post data to calculator and get response
			type: "POST",
			url: base_url + "/das/utility/totalCost",
			data: { vatCode: vatCode, leaseCost: leaseCost}
		}) .done(function(responseMsg) { //If successful print response to screen and to input field
			returnField.trigger('change');
			console.log("AJAX Call Successful - " + responseMsg);
			
			returnField.val(responseMsg);
		}) .fail(function(textStatus) { //If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	};
	
	
	
//	$(".lease-margin-calc").each(function(){
//		var $this = $(this);
//		// Get the IDs of the from and to date fields
//		var totalInFieldId =  $(this).data("total-in_calc");
//		var totalOutFieldId =  $(this).data("total-out_calc");
//		
//		console.log("lease Margin. " + totalOutFieldId + "  "  + totalInFieldId);
//		
//		// for each of above. Add an on change event listener
//		$("#" + totalOutFieldId).change(function(){
//			console.log("Total Lease Out Input" + totalOutFieldId);
//			calculate();
//		});
//		
//		$("#" + totalInFieldId).change(function(){
//			console.log("Total Lease In Input" + totalInFieldId);
//			calculate();
//		});
//		
//		function calculate() {
//			
//			var totalInVal = $("#" + totalInFieldId).val(); //Get VAT Code Input(Req)
//			var totalOutVal = $("#" + totalOutFieldId).val(); //Get VAT Code Input(Req)
//			
//			console.log($(this));
//			
//			ajaxCallCalculateMargin(totalInVal,totalOutVal, $this);
//			
//		}
//	});
//	var ajaxCallCalculateMargin = function(leaseInTotal, leaseOutTotal, returnField) {
//		console.log("Total lease in value:" + leaseInTotal);
//		console.log("Total lease out value:" + leaseOutTotal);
//		$.ajax({ //Post data to calculator and get response
//			type: "POST",
//			url: base_url + "/das/utility/leaseMargin",
//			data: { totalIn: leaseInTotal, totalOut: leaseOutTotal}
//		}) .done(function(responseMsg) { //If successful print response to screen and to input field
//			console.log("AJAX Call Successful - " + responseMsg);
//			returnField.val(responseMsg);
//		}) .fail(function(textStatus) { //If unsuccessful print AJAX failed.
//			console.log("AJAX Call Failed");
//		});
//	};
	
});

//Add lease out extras on tenure tab
$('#addOptionalExtra').click(function(e){

	var inputFieldId = $('#extraCost');
	var extraCostVal  = inputFieldId.val();
	
	var inputFieldName = $('#optionalExtra');
	var extraNameVal = inputFieldName.val();
	
	$.ajax({
      type: 'POST',
      url: base_url + '/das/assetController/addLeaseOutExtra',
      data: {extraCost: extraCostVal , optionalExtra: extraNameVal},
	}).done(function(response){
		//this refreshes the div containing the added extras so to show the newly added extra
		$('#refreshExtras').load(base_url +'/das/assetController/tenure #refreshExtras');
		//set the fields back to empty
		$('#extraCost').val('');
		$('#optionalExtra').val('');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
});

/*
 * Method to remove a lease out extra from the Asset. Accepts and requires an ID which is then passed to the controller
 * which then removes the element from the SortedSet.
 */ 
function removeLeaseOutExtra(id) {
	$.ajax({
	      type: 'POST',
	      url: base_url + '/das/assetController/removeLeaseOutExtra',
	      data: {removableElementIndex: id},
		}).done(function(response){
			//this refreshes the div containing the added extras so to show the newly added extra
			$('#refreshExtras').load(base_url +'/das/assetController/tenure #refreshExtras');
		}).fail(function(textStatus){
			//If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
}