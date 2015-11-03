// TODO This should be configurable from the server depending on the locale see DAS-242
$.fn.datepicker.defaults.format = "dd/mm/yyyy";

var dasSearch = function(url, divClass)
{
	$.get( url, function( response ) {
		$("." + divClass).html(response);
	});
};

/* A function that will duplicate the fields inside a div that passed to it from the 
createAssetPage1 screen */
function duplicateField(divTag)
{
	var aTag = divTag.children().first();
	var length = divTag.children('#aTag').length;
	
	if (length < 3){
		
		$( "<br />" ).insertBefore(aTag.clone(true).appendTo(divTag));
		aTag.children().first().val("from SP");
		length++;
	}
	
	else if(length < 15){
		
		divTag.css({ 'width': '300px', 'height': '100px', 'overflow': 'scroll' });
		$( "<br />").insertBefore(aTag.clone(true).appendTo(divTag));
		aTag.children().first().val("from SP");
		length++;
	}
}

$(document).ready(function(){
	
    $('.date-range').daterangepicker();
    $('.date-picker').datepicker();
	
	/*
	 * Editing Lists
	 */ 
	
	$('.sub-menu-heading').click(function() {	
		
		$(this).toggleClass('open').closest('.has-submenu').find('.list-menu-submenu').first().slideToggle();

	});

	$('.list-form').sortable({
		axis: "y",
		handle: ".move-list-item",
		update: function(event, ui) {
			console.log('update');
			recalculate_list_order();
			updateListItems();
		},
		stop: function(event, ui) {
			console.log('stop');
		}
	});	





	//
	// Event Listeners
	// 


	var adding = false;
	var current_list_id = 0;


	$('.is-list').click(function() {
		
		current_list_id = $(this).data('list-id');
		
		getList(current_list_id);
		
		$('.add-list-item').removeClass('disabled');
		
		adding = false;
	});

	$(document.body).on('click', '.add-list-item', function() {

		if(!adding && current_list_id !== 0) {
			
			clearAlerts();

			getNewListItem();

			$('.new-item').find('input[type=text]').focus();
			
			adding = true;
		}

	});

	$(document.body).on('click', '.save-list-item-btn', function() {

		var form_group = $(this).parents('.form-group');
		var input = form_group.find('input[type=text]');

		if(input.val() !== '') {

			$(this).addClass('hide');
			
			form_group.removeClass('has-error').removeClass('new-item');
			form_group.find('.edit-list-item-btn').removeClass('hide');
			
			input.attr('readonly', 'readonly');
			
			// Update all list items if editing, otherwise add the new list item
			(!adding) ? updateListItem(form_group) : addListItem(form_group);
			
			adding = false;

		} else {

			form_group.addClass('has-error');

		}

	});

	$(document.body).on('click', '.edit-list-item-btn', function() {

		$(this).addClass('hide');
		
		var form_group = $(this).parents('.form-group');
		
		form_group.find('.save-list-item-btn').removeClass('hide');
		form_group.find('input[type=text]').removeAttr('readonly');
		
	});

	$(document.body).on('click', '.remove-list-item-btn', function() {

		var form_group = $(this).parents('.form-group');
		
		if(form_group.hasClass('new-item')) {	
			adding = false;
		} else {
			deleteListItem(form_group);
		}

		form_group.fadeOut(function() {
			$(this).remove();
		});
		
	});

	/* Selects which have options that depend on the value of another select. ie If the parent
	 * select has a particular value, then the list of options in the child select changes to match.
	 * Driven by user data and the user data links.
	 * If the resultant list of options is of size 1, then select it and trigger the next dynamic load
	 * If the parent select is set to blank, then re-enable all the child options
	 */
	$('.auto-loading-user-data').change( function(){
		
		var parentSelect = $(this);
		var childSelectName = $(this).data('child-user-data');
		
		// If set the parent select to blank then enable all child select options
		if(parentSelect.val() == ""){
			$('#' + childSelectName + ' option').attr("disabled",null);
		}
		else{
			// Call server to get the child user data for the parent select value
			$.getJSON(base_url + "/das/systemAdmin/userData/childData/" + parentSelect.val(), function(data) {
		        var foundCount = 0;
		        var singleOption = null;
		        console.log("Looping around options");
		        // Loop around all child select options
				$('#' + childSelectName + ' option').each(function() {
					var option = $(this);
				    console.log("Option:" + option.val() + "  text:"+ option.text());
					var found = false;
					// Check each option to see if it is in the list of child UD items
				    $.each(data, function(index) {
				    	console.log("JSON: " + data[index].id + "  " + data[index].name);
			            if(option.val() == data[index].id && option.text() == data[index].name)
					    {
			            	console.log("Found: " + data[index].name);
					    	found = true;
					    	foundCount++;
					    	singleOption = option;
					    	option.attr("disabled",null);
					    }
				    });
				    // If the option is not found then disable it
				    if(!found){
		            	console.log("disabling :" + option.text());
				    	option.attr("disabled","disabled");
		            }
				});
				// If there is only one available child select option, then select it
				if(foundCount == 1)
					singleOption.attr("selected","selected");
		    });
		}
	});


	//
	//	AJAX Methods
	//

  
	/*
		Grabs the contents of a list
	 */
	function getList(userDataTypeId) {
		
		console.log('working');

		$.ajax({
			method: 'GET',
			url: base_url + "/das/systemAdmin/listItems/" + userDataTypeId,
			beforeSend: function() {
				$('.edit-list').html("<div class='alert alert-info'><strong>Info: </strong>Grabbing list data</div>");
			},
			success: function(response) {
				$('.edit-list').html(response);
			},
			complete: function() {
				recalculate_list_order();
			},
			error: function() {
				$('.edit-list').html("<div class='alert alert-danger'><strong>Error: </strong>Could not get list data</div>");
			}
		});
				
	}
  
	/*
		Grabs a blank list item field 
	 */
	function getNewListItem() {
	
		$.ajax({
			method: 'GET',
			url: base_url + "/das/systemAdmin/listItem",
			success: function(response) {
				console.log(response);
				$('.list-form').prepend(response);
			},
			complete: function() {
				recalculate_list_order();
			},
			error: function() {
				$('.list-form').html("<div class='alert alert-danger'><strong>Error: </strong>Could not get list data</div>");
			}
		});		
			
	}
	
	/*
		Adds a list item over AJAX
	 */
	function addListItem(listItem) {
		
		console.log("Adding list item: " + listItem);
		
		$.ajax({
			method: 'POST',
			url: base_url + "/das/systemAdmin/listItem",
			contentType: "application/json",
			data: JSON.stringify({ 
				"userDataTypeId": current_list_id,
				"name": listItem.find('input[name=listItemValue]').val(),
				"active": Boolean(true),
				"udOrder": get_next_order(),
				"versionNumber": 1
			}),
			dataType: "json",
			success: function(data) {
				$('.list-form').prepend("<div class='alert alert-success'><strong>Success: </strong>List item saved</div>");
				console.log("Updating new list item data with response from server;");
				console.log(data);
				updateListItemData(data, listItem);
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			},
			error: function() {
				$('.list-form').prepend("<div class='alert alert-danger'><strong>Error: </strong>Could not save list item</div>");
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			}
		});
		
	}

	/*
		Deletes a list item over AJAX
	 */
	function deleteListItem(listItem) {
		
		console.log("Deleting list item: " + listItem);
		
		$.ajax({
			method: 'DELETE',
			url: base_url + "/das/systemAdmin/listItem",
			contentType: "application/json",
			data: JSON.stringify({ 
				"id": parseInt(listItem.find('input[name=listItemId]').val()),
				"userDataTypeId": parseInt(listItem.find('input[name=listItemDataTypeId]').val()),
				"name": listItem.find('input[name=listItemValue]').val(),
				"active": Boolean(listItem.find('input[name=listItemActive]').val()),
				"udOrder": parseInt(listItem.find('input[name=listItemOrder]').val()),
				"versionNumber": parseInt(listItem.find('input[name=listItemVersion]').val())
			}),
			success: function() {
				$('.list-form').prepend("<div class='alert alert-success'><strong>Success:</strong> List item deleted</div>");
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			},
			complete: function() {
				recalculate_list_order();			
			},
			error: function() {
				$('.list-form').prepend("<div class='alert alert-danger'><strong>Error: </strong>Could not delete list item</div>");
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			}
		});

	}

	/*
		Updates an individual list item over AJAX
	 */
	function updateListItem(listItem) {
		
		console.log("Updating list item: " + listItem);
			
		$.ajax({
			method: 'PUT',
			url: base_url + "/das/systemAdmin/listItem",
			contentType: "application/json",
			data: JSON.stringify({ 
				"id": parseInt(listItem.find('input[name=listItemId]').val()),
				"userDataTypeId": parseInt(listItem.find('input[name=listItemDataTypeId]').val()),
				"name": listItem.find('input[name=listItemValue]').val(),
				"active": Boolean(listItem.find('input[name=listItemActive]').val()),
				"udOrder": parseInt(listItem.find('input[name=listItemOrder]').val()),
				"versionNumber": parseInt(listItem.find('input[name=listItemVersion]').val())
			}),
			dataType: "json",
			success: function(data) {
				$('.list-form').prepend("<div class='alert alert-success'><strong>Success: </strong>List item updated</div>");
				console.log("Updating list item data with response from server;");
				console.log(data);
				updateListItemData(data, listItem);
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			},
			error: function() {
				$('.list-form').prepend("<div class='alert alert-danger'><strong>Error: </strong>Could not update list items</div>");
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			}
		});			
		
	}

	/*
		Updates the entire list over AJAX
	 */
	function updateListItems() {
		
		var data = getListData();
			
		$.ajax({
			method: 'PUT',
			url: base_url + "/das/systemAdmin/listItems",
			contentType: "application/json",
			data: JSON.stringify(data),
			dataType: "json",
			success: function() {
				$('.list-form').prepend("<div class='alert alert-success'><strong>Success: </success>List item updated</div>");
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			},
			error: function() {
				$('.list-form').prepend("<div class='alert alert-danger'><strong>Error: </strong>Could not update list items</div>");
				setTimeout(function(){
					clearAlerts();
				}, 1000);
			}
		});			
		
	}

	/*
		Takes a server response and updates a list item
		
		Called after a successful add or update of a list item
	 */
	function updateListItemData(data, listItem) {
		
		listItem.find('input[name=listItemId]').val(data.id),
		listItem.find('input[name=listItemDataTypeId]').val(data.userDataTypeId),
		listItem.find('input[name=listItemValue]').val(data.name),
		listItem.find('input[name=listItemActive]').val(data.active),
		listItem.find('input[name=listItemOrder]').val(data.udOrder),
		listItem.find('input[name=listItemVersion]').val(data.versionNumber);
		
	}	


	//
	// Helper Functions
	// 

	/*
		Recalculates the order values of the list items
	 */
	function recalculate_list_order() {
		
		console.log('reordering');
		
		var order = 1;
		
		$('.form-group').each(function() {
		
			$(this).find('input[name=listItemOrder]').val(order); order++;
			
		});
		
	}
	
	/*
		Returns the next order for a new list item
		@return Int
	 */
	function get_next_order() {
		
		return ($('.list-form').children('.form-group').length + 1);
		
	}
	
	/*
		Generates an array of list field objects for updating
		@return Array[List objects]
	 */
	function getListData() {
		
		var data = [];
		
		$('.list-form').find('.form-group').each(function() {
			
			data.push({
				"userDataId": parseInt($(this).find('input[name=listItemId]').val()),
				"userDataTypeId": parseInt($(this).find('input[name=listItemDataTypeId]').val()),
				"userData": $(this).find('input[name=listItemValue]').val(),
				"active": Boolean($(this).find('input[name=listItemActive]').val()),
				"userDataOrder": parseInt($(this).find('input[name=listItemOrder]').val()),
				"versionNumber": parseInt($(this).find('input[name=listItemVersion]').val())
			});
			
		});
		
		return data;
		
	}

	/*
		Clears the screen of alert boxes
	 */
	function clearAlerts() {

		$('.alert').remove();

	}

	/*
		Context Menu
	 */
	var context_menu = false;		
	
	$(document.body).on('contextmenu', '.context-opener tbody tr', function(event) {
		event.preventDefault();
		var url = $(this).find('td:eq(0) a').attr('href');
		var positionOfTable = $('.table-container').position();
		var bottomOfTable = positionOfTable.top + $('.table-container').height();
		var widthofTable = positionOfTable.left + $('.table-container').width();
		
		//finds the position of the mouse and applys the correct placement of the context menu.
		if(($('.table-container').height() < $('.contextmenu').height()) && ((widthofTable - event.pageX)< $('.contextmenu').width())){
			$(".contextmenu").css({"top": event.pageY + "px", "left": event.pageX - $('.contextmenu').width() + "px"}).show().find('.context-open').attr('href', url);	
		}
		else if($('.table-container').height() < $('.contextmenu').height()){
			$(".contextmenu").css({"top": event.pageY + "px", "left": event.pageX + "px"}).show().find('.context-open').attr('href', url);	
		}
		else if (((bottomOfTable - event.pageY) < $('.contextmenu').height())&&(event.pageX - positionOfTable.left) < $('.contextmenu').width()){
			$(".contextmenu").css({"top": event.pageY - $(".contextmenu").height()+ "px", "left": event.pageX + "px"}).show().find('.context-open').attr('href', url);
		}
		else if ((bottomOfTable - event.pageY) < $('.contextmenu').height()){
			$(".contextmenu").css({"top": event.pageY - $(".contextmenu").height()+ "px", "left": event.pageX - $(".contextmenu").width() + "px"}).show().find('.context-open').attr('href', url);
		}
		
		else if((widthofTable - event.pageX)< $('.contextmenu').width()){
			$(".contextmenu").css({"top": event.pageY + "px", "left": event.pageX - $(".contextmenu").width() + "px"}).show().find('.context-open').attr('href', url);
		}
		
		else{
			$(".contextmenu").css({"top": event.pageY + "px", "left": event.pageX + "px"}).show().find('.context-open').attr('href', url);
		}
	});
	
	$('.contextmenu').on('contextmenu', function(event) {
		event.preventDefault();
	});
	
	$(document).on('click', function() {
		$('.contextmenu').hide();
	});

	/* All links are selected by the selected-row-dependant class when clicked 
	 * We get the selected row from the data
	 * We put the url of the actual row onto the url of the page we are going
	 * The seleced row is appended to the link
	 * 
	 * @see amendSelectedRowDataToLink
	 */
	$("a.selected-row-dependant").click(function(event){amendSelectedRowDataToLink($(this));});
	
	/*This deals with the delete button on the myList page
	 * The button needs to get id and version number of the selected rows
	 * "selected-row-attrs" allows for it to find the id and version number onn the jsp
	 * Then the id and version number are put into the link
	 * This then allows for select row to be deleted
	 * 
	 * Cant multiple delete-this will be looked at
	 */
	$("a.selected-rows-dependant").click(function(event){
 		amendSelectedRowsDataToLink($(this));		
	});
	
	/*This deals with the delete button on the myList page
	 * The button needs to get id and version number of the selected rows
	 * "selected-row-attrs" allows for it to find the id and version number onn the jsp
	 * Then the id and version number are put into the link
	 * This then allows for select row to be deleted
	 * 
	 * Cant multiple delete-this will be looked at
	 */
	$("a.selected-rows-dependant").click(function(event){
		// get space separated list of attribute names
		var selectedRowAttrNames = $(this).data("selected-row-attrs");
		var urlsAttrs = {};
		$(selectedRowAttrNames.split(" ")).each(function(index, value){
			urlsAttrs[value] = bucket.rows.selected_row[value];
		});
		var href = $(this).attr('href');
		$(this).attr('href', href + $.param(urlsAttrs));
	});
	
	/*
	* Ajax call that will save any changes made to form fields into the drafts table and simply return to the screen without a refresh.
	* A user will be totally unaware that the data they have input is being saved as a draft.
	* 
	* @Possible improvements TODO:
	* 		 1) Instead of using the form ID to execute this ajax call, we should change to use a style, this is because a screen may have multiple forms.
	* 		 2) Instead of using the 'action' to submit the url possibly pass in url so that other buttons on screen can submit elsewhere?
	*
	*/
	//jquery for asset draft
	$("form.draftForm").change(saveDraft);
	/* 
	 * The bootstrap checkboxes do not fire the change event on the form, so
	 * add onclick handler on all elements within a checkbox div and save the draft.
	 */
	$('form.draftForm div.checkbox *').click(saveDraft);
	
	//jquery for company draft
	$("form.draftCompanyForm").change(saveSupplierDraft);
	
	
	//jquery for supplier draft
	$("form.draftSupplierForm").change(saveSupplierDraft);
	function saveSupplierDraft(event){
		var draftForm = $(this).closest("form");
		
		delay(function(){
			$.ajax({
			url : base_url + "/das/financial/supplier/saveDraftSupplier",
			data : draftForm.serialize(),
			type : "POST",
			success : function(response) {
//			     $('input[name="id"]').val(response);//The response is basically the draft ID so we can perform an update when next field altered.
			     //bucket.draftId = response;
			},
			//Below used for debugging any faults:-
			error : function(xhr, status, error) {console.log(xhr.responseText);} 
			});
		}, 1000);
	}
	
	//jquery for client draft
	$("form.draftClientForm").change(saveClientDraft);
	function saveClientDraft(event){
		var draftForm = $(this).closest("form");
		
		delay(function(){
			$.ajax({
			url : base_url + "/das/financial/client/saveDraftSupplier",
			data : draftForm.serialize(),
			type : "POST",
			success : function(response) {
//			     $('input[name="id"]').val(response);//The response is basically the draft ID so we can perform an update when next field altered.
			     //bucket.draftId = response;
			},
			//Below used for debugging any faults:-
			error : function(xhr, status, error) {console.log(xhr.responseText);} 
			});
		}, 1000);
	}
	
	$('#listGroupForm').submit(
		function(event) {
			$.ajax({
				url : $("#listGroupForm").attr("action"),
				data : $('#listGroupForm').serialize(),
				type : "POST",
				success : function(response) {
	  		    $('input[name="id"]').val(response); 
	  		    bucket.draftId = response;
				},
 				//Below used for debugging any faults:-
				error : function(xhr, status, error) {console.log(xhr.responseText);} 
			});
			event.preventDefault();
		});
	
	
	/*
	 * Event listener for form tabs
	 * 
	 * When a tab is clicked it checks for the existence of a draftId in the bucket var.
	 * If a draft id exists the user is forwarded to the tab URL with the draft id as a
	 * get variable.

	$('.form-tabs li a').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		if(bucket.draftId !== undefined)
			location.replace(href + '?draftId=' + bucket.draftId);
		else
			location.replace(href);
	});
		 */
	/*
	 * Gets a URL query parameter from string

	function getQueryVariable(variable)
	{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(undefined);
	}
	*/
		 
});

var delay = (function(){
	var timer = 0;
	return function(callback, ms){
		clearTimeout (timer);
		timer = setTimeout(callback, ms);
	};
})();

/**
 * Runs on click of the save asset button
 * 
 * Prevents the draft from not being saved, i.e. when the user does not
 * click out of the current field before clicking the save button
 */
function saveAsset(url) {
	
	saveDraft();
	
	window.location.href = base_url + url;
	
}

function saveDraft(event){
	var draftForm = $(this).closest("form");
	console.log(draftForm);
	delay(function(){
		$.ajax({
		url : draftForm.attr("action"),
		data : draftForm.serialize(),
		type : "POST",
		success : function(response) {
//		     $('input[name="id"]').val(response);//The response is basically the draft ID so we can perform an update when next field altered.
		     //bucket.draftId = response;
		},
		//Below used for debugging any faults:-
		error : function(xhr, status, error) {console.log(xhr.responseText);} 
		});
	}, 1000);
}

function saveSupplierDraft(event){
	var draftForm = $(this).closest("form");
	
	delay(function(){
		$.ajax({
		url : base_url + "/das/financial/supplier/saveDraftSupplier",
		data : draftForm.serialize(),
		type : "POST",
		success : function(response) {
//		     $('input[name="id"]').val(response);//The response is basically the draft ID so we can perform an update when next field altered.
		     //bucket.draftId = response;
		},
		//Below used for debugging any faults:-
		error : function(xhr, status, error) {console.log(xhr.responseText);} 
		});
	}, 1000);
}

/**
 * Event handler for links marked with the class selected-row-dependant
 * 
 * Appends params to the link with the values of the last selected row
 * 
 * Expects the link to have a data attribute of selected-row-attrs that
 * details the attrbiutes required from the last seleced row. The name of
 * the attribute is used as the name of the param.
 * 
 * TODO Allow for different param names for a given attribute name. Eg id=listId
 * which will get the id from the selected row and set this to the param listId
 * @param event
 */
function amendSelectedRowDataToLink(link){
	console.log("amendSelectedRowDataToLink");
	// get space separated list of attribute names
	var selectedRowAttrNames = link.data("selected-row-attrs");
	var urlsAttrs = {};
	$(selectedRowAttrNames.split(" ")).each(function(index, value){
		urlsAttrs[value] = bucket.rows.selected_row[value];
	});
	console.log(bucket.rows.selected_row);
	var href = link.attr('href');
	console.log("amendSelectedRowDataToLink : " + link.attr('href'));
	link.attr('href', href + $.param(urlsAttrs));
	console.log("amendSelectedRowDataToLink : " + link.attr('href'));
}

/**
 * Event handler for links marked with the class selected-rows-dependant
 * 
 * Similar to above but allows for all selected rows rather than just the last one.
 * 
 * Currently just appends entityIds to the URL and ignore the required attribute names.
 * This is because the bucket does not store multiple data attributes for each row, just 
 * the asset id. So this needs to change
 * @param link
 */
function amendSelectedRowsDataToLink(link){
	// get space separated list of attribute names
	var selectedRowAttrNames = link.data("selected-row-attrs");
	var urlsAttrs = {};
	$(selectedRowAttrNames.split(" ")).each(function(index, value){
		var reqName, sendName;
		var bits = value.split("=");
		reqName=bits[0];
		if(bits[1])sendName=bits[1];
		else sendName=reqName;
		urlsAttrs[sendName] = bucket.rows.getSelectedRowsAttr(reqName).toString();
	}); 
	var href =link.attr('href');
	console.log("amendSelectedRowsDataToLink : " + link.attr('href'));
	link.attr('href',href + $.param(urlsAttrs) + "&") ;
	console.log("amendSelectedRowsDataToLink : " + link.attr('href'));
}
//Function that will trigger on any file uploads that have the auto-upload class
$('.auto-upload').change(function() {
	
		var fileInputField = $(this);
		
		$(".auto-file-upload").submit(function(e)
		{    
			//The form data to be sent to the controller
		    var fileFormData = new FormData();
		    
		    //Apending the file to the form data
		    fileFormData.append("file", docFile.files[0]);
		    
	    	/*
	    	 * Ajax call to call controller that uploads document returns
	    	 * a document object then will submit file automatically	
		     */    
		    	$.ajax({
		            type: 'POST',
		            url: base_url + "/das/document/upload",
		            data:  fileFormData,
		            mimeType:"multipart/form-data",
		            contentType: false,
		            cache: false,
		            processData:false,
	            })	.success(function(response){
		            	console.log("AJAX Call Successful - " + response);
		            	var destDivId = fileInputField.data("doc-list-div");
		            	var json = jQuery.parseJSON(response);
		            	
		            	//Dynamic creation of text field containing document name
		            	$('<input>').attr({
		            	    type: 'text',
		            	    id: 'hiddenFileName',
		            	    name: 'hiddenFileName',
		            	    disabled: 'true',
		            	    value: json.objectKey,  
		            	}).appendTo("#" + destDivId);
		            	
		            	//Dynamic creation of text field containing document id
		            	$('<input>').attr({
		            	    type: 'hidden',
		            	    id: 'hiddenDocumentId',
		            	    name: 'hiddenDocumentId',
		            	    value: json.id,  
		            	}).appendTo("#" + destDivId);
		            	
		            	//Dynamic creation of text field containing version number
		            	$('<input>').attr({
		            	    type: 'hidden',
		            	    id: 'hiddenDocumentId',
		            	    name: 'hiddenDocumentId',
		            	    value: json.versionNumber,
		            	}).appendTo("#" + destDivId);
		            	
	            }) .fail(function(textStatus){
	    		    	 //If unsuccessful print AJAX failed.
			    		console.log("AJAX Call Failed");
				});
		e.preventDefault();
		//Wraps form around the element then resets the form to stop it from uploading multiple times
		e.wrap('<form>').closest('form').get(0).reset();
	    e.unwrap();
		});    
		
	$(".auto-file-upload").submit();		
	
});

var fieldCount = 0;

$('#addOtherSystemId').click(function(e){
	fieldCount++;

	var inputFieldId = $('#otherSystemId');
	var sysRefIdString = inputFieldId.val();
	
	var inputFieldName = $('#otherSystemName');
	var sysRefNameString = inputFieldName.val();
	
	var destDivId = inputFieldId.data("sys-ref-id-list-div");
	var destDivName = inputFieldName.data("sys-ref-name-list-div");
	
	$.ajax({
        type: 'POST',
        url: base_url + '/das/assetController/setOtherSystemRef',
        data: {otherSystemId: sysRefIdString, otherSystemNameId: sysRefNameString},
	}).success(function(response){
		
			$('<input>').attr({
			    type: 'text',
			    id: 'otherSysId' + fieldCount,
			    name: 'otherSysId',
			    disabled: 'true',
			    value: inputFieldId.val(),
			}).appendTo('#' + destDivId);
			
			$('<input>').attr({
			    type: 'text',
			    id: 'otherSysName' + fieldCount,
			    name: 'otherSysName',
			    disabled: 'true',
			    value: inputFieldName.val(),
			}).appendTo('#' + destDivName);
			
			inputFieldId.val("");
			inputFieldName.val("");
		
		
	}).fail(function(textStatus){
   	 //If unsuccessful print AJAX failed.
		console.log("AJAX Call Failed");
	});
	
	$('#removeOtherSystemId').click(function(){
		
		var nameToRemove = $('#otherSysName'+ fieldCount);
		var idToRemove = $('#otherSysId'+ fieldCount);
		
		var otherSysNameRemove = nameToRemove.val();
		var otherSysIdRemove = idToRemove.val();
		
		$.ajax({
			type:'POST',
			url: base_url + '/das/assetController/removeOtherSystemRef',
	        data: {otherSystemId: otherSysIdRemove, otherSystemName: otherSysNameRemove},
		}).success(function(){
			$('#otherSysName'+ fieldCount).remove();
			$('#otherSysId'+ fieldCount).remove();
		}).fail(function(textStatus){
	   	 //If unsuccessful print AJAX failed.
			console.log("AJAX Call Failed");
		});
		
		fieldCount = fieldCount - 1;
});

});






//$('.menu-toggle').click (function() {
//	$('.main-navigation').toggleClass('menu-open');
//	$('.main-content').toggleClass('main-open');
//	$('.main-navigation').toggleClass('menu-closed');
//	$('.main-content').toggleClass('main-closed');
//    return false;
//});


//This method will resize the table dependent on the screen resolution.
$(document).ready(tableResize);
$(window).on('resize',tableResize);

function tableResize() {
	if ($(window).height() > 960) {
		$('.scroll-content').attr({style: 'max-height:76vh'});
		$('.inner-scroll').attr({style: 'max-height:81vh'});
		}
		else {
			$('.scroll-content').attr({style: 'max-height:65vh'});
			$('.inner-scroll').attr({style: 'max-height:73vh'});
		}};


//This should prevent the scrolling sideways with the middle mouse button
$(function() {
	$('body').mousedown(function(e){
		if(e.button==1)
			return false;
		});
	});

//Indemnities page functionality.
/*This is the create new policy functionality which allows the 
create policy div to open and close witht he use off buttons.*/
$('#warranty_policy_toggle').click(function(){
	$('.warranty_policy').show();
	$('#warranty_policy_toggle').hide();
});

$('.accordion-toggle').click(function(){
	$('#warranty_policy_toggle').show();
	$('.warranty_policy').hide();
});

$('#insurance_policy_toggle').click(function(){
	$('.insurance_policy').show();
	$('#insurance_policy_toggle').hide();
});

$('.accordion-toggle').click(function(){
	$('#insurance_policy_toggle').show();
	$('.insurance_policy').hide();
});

$('.warrantyCancel').click(function(){
	$('.warranty_policy').hide();
	$('#warranty_policy_toggle').show();
});

$('#insuranceCancel').click(function(){
	$('.insurance_policy').hide();
	$('#insurance_policy_toggle').show();
});

$('#warrantyCancel').click(function(){
	$('.warranty_policy').hide();
	$('#warranty_policy_toggle').show();
});

//Create supplier page functionality
$('#add_address_toggle').click(function(){
	$('.new_address').show();
});

/*
 * This function creates an iframe which contains a selected selected div,
 * this then is sent to the bowsers print preview when the print button is click. 
 */
	function printPage(assetId){
      var div = document.getElementById("printerDiv");
      console.log(assetId);
      div.innerHTML = '<iframe src="print/assetPrint?assetId=' + assetId + '" onload="this.contentWindow.print();"></iframe>';
	};


