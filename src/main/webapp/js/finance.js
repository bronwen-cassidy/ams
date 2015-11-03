$("document").ready(function(){
	
	$("#commissioningDate, #endOfLifeDate").change(function(){
		var commissioningDate = $("#commissioningDate").val();
		var endOfLifeDate= $("#endOfLifeDate") .val();
		
		ajaxCallLifeExpectancy(commissioningDate, endOfLifeDate);
	});
	
	
	var ajaxCallLifeExpectancy = function (comissionDate, endDate){
		$.ajax({
			type: "POST",
			//URL that the ajax call will be passing the values to
			url: base_url + "/das/utility/calcLifeExpectancy", 
			//The data that will be sent to the controller as request parameters
			data: {commissioningDate: comissionDate, endOfLifeDate: endDate}
		})	.done(function(responseMsg) { //If successful print response to screen and to input field
			console.log("AJAX Call Successful - " + responseMsg);
			$("#lifeExpectancy").val(responseMsg) ;
		}) 	.fail(function(textStatus) { //If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	};
});

//supplier
$("#add-new-website").click( function() {
	$website = $("#new-website");
	
	$.ajax({
		type: 'POST',
		url: base_url + '/das/financial/supplier/addExtraWebsite',
		data: {websiteUrl: $website.val()},
	}).done(function(response){
		//this refreshes the div containing the added emails to show the newly added emails
		$('#refreshWebsites').load(base_url + '/das/financial/supplier/create/supplierTab #refreshWebsites');
		//set the fields back to empty
		$website.val('');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
});


$("#add-new-email").click( function() {
	$email = $("#new-email");
	console.log($email.val());
	$emailType = $("#new-email-type");
	
	$.ajax({
		type: 'POST',
		url: base_url + '/das/financial/supplier/addExtraEmailAddress',
		data: {emailAddress: $email.val(), emailType: $emailType.val()},
	}).done(function(response){
		//this refreshes the div containing the added emails to show the newly added emails
		$('#refreshEmails').load(base_url + '/das/financial/supplier/create/supplierTab #refreshEmails');
		//set the fields back to empty
		$email.val('');
		$emailType.val('');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
});

 $("#add-new-telephone").click( function() {
	 $telNo = $("#new-telephone-number");
	 $telNoType = $("#new-telephone-type");
	 
	 $.ajax({
		 type: 'POST',
		 url: base_url + '/das/financial/supplier/addExtraTelephoneNumber',
		 data: {telephoneNumber: $telNo.val(), telephoneNumberType: $telNoType.val()},
	 }).done(function(response){
		 //this refreshes the div containing the added telephone numbers to show the newly added telephone numbers
		 $('#refreshTelephone').load(base_url + '/das/financial/supplier/create/supplierTab #refreshTelephone');
		 //set the fields back to empty
		 $telNo.val('');
		 $telNoType.val('');
	 }).fail(function(textStatus){
		 //If unsuccessful print AJAX failed.
		 console.log("AJAX Call Failed");
	 });
 });
 
 $("#add-new-address").click( function() {

	 $addressLine1 = $("#new-address-line-1");
	 $addressLine2 = $("#new-address-line-2");
	 $city = $("#new-city");
	 $country = $("#new-country");
	 $postZipCode = $("#new-post-zipcode");
	 $addressType = $("#new-address-type");
	 
	 $.ajax({
		 type: 'POST',
		 url: base_url + '/das/financial/supplier/addExtraAddress',
		 data: {addressLine1: $addressLine1.val(), addressLine2: $addressLine2.val(), city: $city.val(), country: $country.val(), postZipCode: $postZipCode.val(), addressType: $addressType.val()},
	 }).done(function(response){
		 //this refreshes the div containing the added addresses to show the newly added addresses
		 $('#refreshAddress').load(base_url + '/das/financial/supplier/create/supplierTab #refreshAddress');
		 //set the fields back to empty
		 $addressLine1.val('');
		 $addressLine2.val('');
		 $city.val('');
		 $country.val('');
		 $postZipCode.val('');
		 $addressType.val('');
	 }).fail(function(textStatus){
		 //If unsuccessful print AJAX failed.
		 console.log("AJAX Call Failed");
	 });
 });
 
 $("#add-new-bank-detail").click( function() {
		$name = $("#new-bank-name");
		$address = $("#new-bank-address");
		$accountNumber = $("#new-bank-account-number");
		$sortCode = $("#new-bank-sort-code");
		
		$.ajax({
			type: 'POST',
			url: base_url + '/das/financial/supplier/addExtraBankDetail',
			data: {bankName: $name.val(), bankAddress: $address.val(), bankAccountNumber: $accountNumber.val(), bankSortCode: $sortCode.val() },
		}).done(function(response){
			//this refreshes the div containing the added emails to show the newly added emails
			$('#refreshBankDetails').load(base_url + '/das/financial/supplier/create/financialTab #refreshBankDetails');
			//set the fields back to empty
			$name.val('');
			$address.val('');
			$accountNumber.val('');
			$sortCode.val('');
		}).fail(function(textStatus){
			//If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	});
 
function updatePrimaryEmail(id) {
	$.ajax({
		type: 'POST',
		url: base_url + '/das/financial/supplier/updatePrimaryEmail',
		data: {setIndex: id},
	}).done(function(response){
		//this refreshes the div containing the updated emails to show the newly updated emails
		$('#refreshEmails').load(base_url + '/das/financial/supplier/create/supplierTab #refreshEmails');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
}

function updatePrimaryTelephoneNumber(id) {
	$.ajax({
		type: 'POST',
		url: base_url + '/das/financial/supplier/updatePrimaryTelephoneNumber',
		data: {setIndex: id},
	}).done(function(response){
		//this refreshes the div containing the updated telephone numbers to show the newly updated telephone numbers
		$('#refreshTelephone').load(base_url + '/das/financial/supplier/create/supplierTab #refreshTelephone');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
}

function updatePrimaryAddress(id) {
	$.ajax({
		type: 'POST',
		url: base_url + '/das/financial/supplier/updatePrimaryAddress',
		data: {setIndex: id},
	}).done(function(response){
		//this refreshes the div containing the updated addresses to show the newly updated addresses
		$('#refreshAddress').load(base_url + '/das/financial/supplier/create/supplierTab #refreshAddress');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
}

$(document).ready( function(){
	   primaryEmail();
	   primaryTelephone();
});

$('#add-new-email').click(function(){
	   $('.addNewEmail').show();
	   $('#refreshEmailInput').hide();
	   $('.emails').show();
	   primaryEmail();
});

$('.addNewEmail').click(function(){
	   $('.addNewEmail').hide();
	   $('#refreshEmailInput').show();
});

$('#add-new-telephone').click(function(){
	   $('.addNewTelephone').show();
	   $('#refreshTelephoneInput').hide();
	   primaryTelephone();
});

$('.addNewTelephone').click(function(){
	   $('.addNewTelephone').hide();
	   $('#refreshTelephoneInput').show();
});

$('#add-new-address').click(function(){
	   $('.addNewAddress').show();
	   $('#refreshAddressInput').hide();
});

$('.addNewAddress').click(function(){
	   $('.addNewAddress').hide();
	   $('#refreshAddressInput').show();
});



function primaryEmail(){
	   var a = 0;
	   $('.emails').each(function(){
		   a = a + 1;
	   });
	   if(a > 0){
		   $('#primaryEmail').show();
	   }
	   else{
		   $('#primaryEmail').hide();
	   }
	   console.log(a);
};

function primaryTelephone(){
	   var a = 0;
	   $('.telephoneNumbers').each(function(){
		   a = a + 1;
	   });
	   if(a > 0){
		   $('#primaryTelephone').show();
	   }
	   else{
		   $('#primaryTelephone').hide();
	   }
	   console.log(a);
};

/*
 * Method for passing through 'set' value fields from input params to the controller, these are then used when persisting the record.
 */
$("#add-new-person-contact").click( function() {

	 $addressLine1 = $("#new-per-address-line-1");
	 $addressLine2 = $("#new-per-address-line-2");
	 $city = $("#new-per-city");
	 $country = $("#new-per-country");
	 $emailAddress = $("#new-per-emailAddress");
	 $telNo = $("#new-per-tel-no");
	 $zipPostCode = $("#new-per-zipPostCode"); 
	 $parentPartyId = $("#parentPartyId");
	 $view = $("#view");

	 $.ajax({
		 type: 'POST',
		 url: base_url + '/das/contact/create?parentPartyId=${parentPartyId}&view=${view}',
		 data: {addressLine1: $addressLine1.val(), addressLine2: $addressLine2.val(), city: $city.val(), country: $country.val(), emailAddress: $emailAddress.val(), telNo: $telNo.val(), zipPostCode: $zipPostCode.val(), $parentPartyId: $parentPartyId.val(), view: view.val()},
	 }).done(function(response){

	 }).fail(function(textStatus){
		 //If unsuccessful print AJAX failed.
		 console.log("AJAX Call Failed");
	 });
});











$(document).ready( function(){
	   var test = document.getElementById('COMPANY_NAME').value;
	   console.log(test);
	  if (test != ""){
	  }
	  else{
		  $('#contactsInformationAccordion').removeAttr("data-toggle");
		  $('#contactsInformationAccordion').append("<p style='display:inline;color:red;'>- Company name is required to view contact information.</p>")
		  $('#contactsInformation').hide();
		  console.log("fine");
	  }
});





//client
$("#add-new-client-website").click( function() {
	$website = $("#new-client-website");
	
	$.ajax({
		type: 'POST',
		url: base_url + '/das/financial/client/addExtraWebsite',
		data: {websiteUrl: $website.val()},
	}).done(function(response){
		//this refreshes the div containing the added emails to show the newly added emails
		$('#refreshClientWebsites').load(base_url + '/das/financial/client/create/clientTab #refreshClientWebsites');
		//set the fields back to empty
		$website.val('');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
});

$("#add-new-client-email").click( function() {
	$email = $("#new-client-email");
	$emailType = $("#new-client-email-type");
	
	$.ajax({
		type: 'POST',
		url: base_url + '/das/financial/client/addExtraEmailAddress',
		data: {emailAddress: $email.val(), emailType: $emailType.val()},
	}).done(function(response){
		//this refreshes the div containing the added emails to show the newly added emails
		$('#refreshClientEmails').load(base_url + '/das/financial/client/create/clientTab #refreshClientEmails');
		//set the fields back to empty
		$email.val('');
		$emailType.val('');
	}).fail(function(textStatus){
		//If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
});

 $("#add-new-client-telephone").click( function() {
	 $telNo = $("#new-client-telephone-number");
	 $telNoType = $("#new-client-telephone-type");
	 
	 $.ajax({
		 type: 'POST',
		 url: base_url + '/das/financial/client/addExtraTelephoneNumber',
		 data: {telephoneNumber: $telNo.val(), telephoneNumberType: $telNoType.val()},
	 }).done(function(response){
		 //this refreshes the div containing the added telephone numbers to show the newly added telephone numbers
		 $('#refreshClientTelephone').load(base_url + '/das/financial/client/create/clientTab #refreshClientTelephone');
		 //set the fields back to empty
		 $telNo.val('');
		 $telNoType.val('');
	 }).fail(function(textStatus){
		 //If unsuccessful print AJAX failed.
		 console.log("AJAX Call Failed");
	 });
 });
 
 $("#add-new-client-address").click( function() {

	 $addressLine1 = $("#new-client-address-line-1");
	 $addressLine2 = $("#new-client-address-line-2");
	 $city = $("#new-client-city");
	 $country = $("#new-client-country");
	 $postZipCode = $("#new-client-post-zipcode");
	 $addressType = $("#new-client-address-type");
	 
	 $.ajax({
		 type: 'POST',
		 url: base_url + '/das/financial/client/addExtraAddress',
		 data: {addressLine1: $addressLine1.val(), addressLine2: $addressLine2.val(), city: $city.val(), country: $country.val(), postZipCode: $postZipCode.val(), addressType: $addressType.val()},
	 }).done(function(response){
		 //this refreshes the div containing the added addresses to show the newly added addresses
		 $('#refreshClientAddress').load(base_url + '/das/financial/client/create/clientTab #refreshClientAddress');
		 //set the fields back to empty
		 $addressLine1.val('');
		 $addressLine2.val('');
		 $city.val('');
		 $country.val('');
		 $postZipCode.val('');
		 $addressType.val('');
	 }).fail(function(textStatus){
		 //If unsuccessful print AJAX failed.
		 console.log("AJAX Call Failed");
	 });
 });
 
 $("#add-new-client-bank-detail").click( function() {
		$name = $("#new-client-bank-name");
		$address = $("#new-client-bank-address");
		$accountNumber = $("#new-client-bank-account-number");
		$sortCode = $("#new-client-bank-sort-code");
		
		$.ajax({
			type: 'POST',
			url: base_url + '/das/financial/client/addExtraBankDetail',
			data: {bankName: $name.val(), bankAddress: $address.val(), bankAccountNumber: $accountNumber.val(), bankSortCode: $sortCode.val() },
		}).done(function(response){
			//this refreshes the div containing the added emails to show the newly added emails
			$('#refreshClientBankDetails').load(base_url + '/das/financial/client/create/financialTab #refreshClientBankDetails');
			//set the fields back to empty
			$name.val('');
			$address.val('');
			$accountNumber.val('');
			$sortCode.val('');
		}).fail(function(textStatus){
			//If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	});
 
 	function updateClientPrimaryEmail(id) {
		$.ajax({
			type: 'POST',
			url: base_url + '/das/financial/client/updatePrimaryEmail',
			data: {setIndex: id},
		}).done(function(response){
			//this refreshes the div containing the updated emails to show the newly updated emails
			$('#refreshClientEmails').load(base_url + '/das/financial/client/create/clientTab #refreshClientEmails');
		}).fail(function(textStatus){
			//If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	}

	function updateClientPrimaryTelephoneNumber(id) {
		$.ajax({
			type: 'POST',
			url: base_url + '/das/financial/client/updatePrimaryTelephoneNumber',
			data: {setIndex: id},
		}).done(function(response){
			//this refreshes the div containing the updated telephone numbers to show the newly updated telephone numbers
			$('#refreshClientTelephone').load(base_url + '/das/financial/client/create/clientTab #refreshClientTelephone');
		}).fail(function(textStatus){
			//If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	}

	function updateClientPrimaryAddress(id) {
		$.ajax({
			type: 'POST',
			url: base_url + '/das/financial/client/updatePrimaryAddress',
			data: {setIndex: id},
		}).done(function(response){
			//this refreshes the div containing the updated addresses to show the newly updated addresses
			$('#refreshClientAddress').load(base_url + '/das/financial/client/create/clientTab #refreshClientAddress');
		}).fail(function(textStatus){
			//If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
	}
