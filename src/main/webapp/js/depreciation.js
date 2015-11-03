$("document").ready(function(){
	
	/*	Function that on change of any of the applicable fields below will
	 * 	perform an ajax call to a controller to return a calculated depreciated 
	 * 	amount for the end of life of value the asset*/
	$("#cost, #dateOfPurchase, #endOfLifeDate, #depreciationCodeId").change(function(){
		//Gets the values for the end of life value from the fields by the given field ID
		var	totalCost = $("#cost").val();
		var purchaseDate = $("#dateOfPurchase").val();
		var endOfLifeDate= $("#endOfLifeDate") .val();
		var depreciationCode = $("#depreciationCodeId").val();
		
		//Condition to stop ajax call if any of the fields are null
		if((totalCost != null) && (purchaseDate != null) && (endOfLifeDate != null) && (depreciationCode != null))
		{
			ajaxCallEndOfLife(totalCost, purchaseDate, endOfLifeDate, depreciationCode);
		}
	
	});	
	
	/*	Function that on change of any of the applicable fields below will
	 * 	perform an ajax call to a controller to return the current calculated 
	 * 	depreciated amount for todays date*/
	$("#cost, #dateOfPurchase, #depreciationCodeId").change(function(){
		var	totalCost = $("#cost").val();
		var purchaseDate = $("#dateOfPurchase").val();
		var depreciationCode = $("#depreciationCodeId").val();
		
		//Condition to stop ajax call if any of the fields are null
		if ((totalCost != null) && (purchaseDate != null) && (depreciationCode != null))
		{
			ajaxCallCurrentValue(totalCost, depreciationCode, purchaseDate);
		}
	});
		
		
		
	
	
	//Ajax call for the predicted end of life value of an asset
	var ajaxCallEndOfLife = function(cost, purcDate, endDate, depeciation){
		$.ajax({
			type: "POST",
			//URL that the ajax call will be passing the values to
			url: base_url + "/das/depreciation/endOfLife", 
			//The data that will be sent to the controller as request parameters
			data: { totalCost: cost, purchaseDate: purcDate, endOfLifeDate: endDate, depreciationCode: depeciation}
		}) .success(function(responseMsg) { //If successful print response to screen and to input field
			console.log("AJAX Call Successful - " + responseMsg);
			$("#predictedEndOfLifeValue").val(responseMsg);
			$("#predictedEndOfLifeValue").trigger('change');
		}) .fail(function(textStatus) { //If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	};
	
	//Ajax call for the current depreciated value of an asset on the current day from the date of purchase
	var ajaxCallCurrentValue = function (cost, depreciation, purcDate){
		$.ajax({
			type: "POST",
			//URL that the ajax call will be passing the values to
			url: base_url + "/das/depreciation/currentValue",
			//The data that will be sent to the controller as request parameters
			data: {totalCost: cost, depreciationCode: depreciation, purchaseDate: purcDate}
		})	.success(function(responseMsg) { //If successful print response to screen and to input field
			console.log("AJAX Call Successful - " + responseMsg);
			$("#currentDepreciatedValue").val(responseMsg);
			$("#currentDepreciatedValue").trigger('change');
		}) .fail(function(textStatus) { //If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	};
});