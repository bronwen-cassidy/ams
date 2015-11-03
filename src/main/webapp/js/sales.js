function searchAvailableAssets(data) {
	
	$.ajax({
		url: base_url + '/das/assetAvailability/searchAvailabileAssets',
		type: 'POST',
		data: data,
		beforeSend: function() {
			$('.available-asset-table').html('Searching...');
		},
		success: function(response) {
			$('.available-asset-table').html(response);
			bucket.rows.page_variables.clientId = data.client_id;
			bucket.rows.page_variables.fromDate = data.from_date;
			bucket.rows.page_variables.toDate = data.to_date;
		},
		error: function(response) {
			
		}
	});
	
}
function searchNotAvailableAssets(data) {
	
	$.ajax({
		url: base_url + '/das/assetAvailability/searchNotAvailableAssets',
		type: 'POST',
		data: data,
		success: function(response) {
			$('.not-available-asset-table').html(response);
			bucket.rows.page_variables.clientId = data.client_id;
			bucket.rows.page_variables.fromDate = data.from_date;
			bucket.rows.page_variables.toDate = data.to_date;
		},
		error: function(response) {
		}
	});
	
}

$(document).ready(function() {
	$("#proposalContactDetail").change(function(){
		
		var contactId = $("select#proposalContactDetail option:selected").data("personId");
		
		console.log("contactId " + contactId);
		
		$("#clientContactDetails").load(base_url + '/das/proposal/updateAssetProposalContact?contactId='+contactId+' #clientContactDetails');
	});
	
	$('.asset-search-field').keyup(function() {
		
		var client = $('input[name=clientId]'),
			from  = $('input[name=fromDate]'),
			to 	  = $('input[name=toDate]'),
			data = {
				query: $(this).val(),
				client_Id: client.val(),
				from_date: from.val(),
				to_date: to.val()
			};
		
		if(from.val() == '' || to.val() == '') {
			
			$('.available-asset-table').html('Please select a to and from date');
			
		} else {

			delay(function() {
				
				searchAvailableAssets(data);
				searchNotAvailableAssets(data);
				
			}, 500);
			
		}
		
	});
	
});


$("input[name='availableName']").keypress(function(){
	$('.available-table').show();
	$('.available-label').show();
	$('.footer').show();
	$('.unavailable').hide();
	tableResize();
});

$(document).ready(tableResize);
$(window).on('resize',tableResize);

function tableResize() {
	
	if($('.unavailable').is(":visible")){
		if($(window).height() > 960) {
			$('.available-asset-table').attr({style: 'max-height:23vh !important'});
			}
			else {
				$('.available-asset-table').attr({style: 'max-height:10vh !important'});
			};
	}
	else{
		if ($(window).height() > 960) {
			$('.available-asset-table').attr({style: 'max-height:45vh'});
			}
			else {
				$('.available-asset-table').attr({style: 'max-height:29vh'});
			};
		};
	};
	
$('.unavailableTableToggle-show').click(function(){
	$('.unavailableTableToggle-close').show();
	$('.unavailable').show();
	$('.sales-table-container').show();
	$('#available').removeClass('in').addClass('collapse'); 
	$('.accordion-available').addClass('collapsed');
	$('.accordion-unavailable').removeClass('collapsed');
	$('#unavailable').show();
	$('#unavailable').addClass('removeHeight');
	$('#unavailable').addClass('in').removeClass('collapse');
	$('.unavailableTableToggle-show').hide();
});

$('.unavailableTableToggle-close').click(function(){
	$('.unavailable').hide();
	$('.unavailableTableToggle-show').show();
});


if($(window).height() > 960) {
	$('.available-asset-table').attr({style: 'max-height:45vh !important'});
}
else {
	$('.available-asset-table').attr({style: 'max-height:29vh !important'});
};

$('#creatProposalButton').click(function(){
	window.location.href = base_url + "/das/proposal?clientId=" + bucket.rows.selected_row['clientId'] + "&" + "assetId=" + bucket.rows.selected_row['assetId'] + "&" + "fromDate=" + bucket.rows.selected_row['fromDate'] + "&toDate=" + bucket.rows.selected_row['toDate'];
});

$('.checkBoxValue').click(setupExtras);
/*
 * Function to call controller with selected lease out extra id's which it will then setup
 * the required set of details.
 */
function setupExtras(event){
   	var checkboxSelection = $(this).is(':checked');
   	console.log(checkboxSelection);
   	
	var extraIdString = $(this).parents('.newcheckbox').parents('.col-sm-3').parents('.clearfix').find('input#extraId').val();
   	
   	var assetId = $('#assetId');
   	var assetIdString = assetId.val();
  
   	var extraCostVal = $(this).parents('.newcheckbox').parents('.col-sm-3').prev('.col-sm-3').find('input').val();
   	console.log(extraCostVal);
		$.ajax({
			url: base_url + '/das/proposal/addProposalExtras',
	        data: {extraId: extraIdString, assetId: assetIdString, checkBoxValue: checkboxSelection, extraCost: extraCostVal},
	        type : "POST",
	        success : function(response) {
	        	calculateTotalCost();
	        },
			//Below used for debugging any faults:-
			error : function(xhr, status, error) {
				console.log(xhr.responseText);
			} 
		});
};

$(function(){
	if($('div').is('#createProposalPage')){
		calculateTotalCost();
	}
});

function calculateTotalCost()
{
	$.ajax({
	    type: 'POST',
	    url: base_url + '/das/proposal/calculateProposedPrice',
	}).done(function(response){
		//send values to jsp
		document.getElementById('costBeforeExtras').innerHTML = response.costBeforeExtras;
		document.getElementById('extrasCost').innerHTML = response.extrasCost;
		document.getElementById('proposalGrandTotal').innerHTML = response.proposalGrandTotal;
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed: " + textStatus);
	});
}

$(document).ready(function(){
	checkCheckbox()
	$(document.body).on('click', '.multi-table tbody tr', function(){
		checkCheckbox()
	});
});

function checkCheckbox(){
	if ($("th.checkbox-holder-size input:checkbox:checked").length > 0)
	{
		$('#creatProposalButton').prop('disabled',false);
	}
	else
	{
		$('#creatProposalButton').prop('disabled',true);
	}
};
