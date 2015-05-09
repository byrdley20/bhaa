var baseUrl = "http://localhost:9999";
var uploadImageUrl = baseUrl+"/aircrafts/uploadImage";
var dialog, allFields, form, tips, addUpdatePath, deletePath;

function createDialog(){
	
	var buttonsOpts = {}
	buttonsOpts["Cancel"] = function() {
		dialog.dialog( "close" );
	};
	buttonsOpts["Save"] = saveRecord;
	
	dialog = $( "#dialog-form" ).dialog({
    	autoOpen: false,
    	width: 350,
    	modal: true,
    	buttons : buttonsOpts,
		close: function() {
			allFields.removeClass( "ui-state-error" );
		}
	});
}

function createLengthValidationObject(fieldObj, fieldName, minimum, maximum){
	var lengthValidationObject = {
		field: fieldObj,
		name: fieldName,
		min: minimum,
		max: maximum
	};
	return lengthValidationObject;
}

function createNumberValidationObject(fieldObj, fieldName){
	var numberValidationObject = {
		field: fieldObj,
		name: fieldName
	};
	return numberValidationObject;
}

function createDateValidationObject(fieldObj, fieldName){
	var dateValidationObject = {
		field: fieldObj,
		name: fieldName
	};
	return dateValidationObject;
}

function createPasswordValidationObject(field1Obj, field2Obj){
	var passwordValidationObject = {
		field1: field1Obj,
		field2: field2Obj,
	};
	return passwordValidationObject;
}

function saveRecord() {
	var fileFormData = {};
	var valid = validateForm();
	
	if(valid) {
		var formData = populateFormData();
		
		var editRecord = (formData["id"] > 0);
        $.ajax({
            url: baseUrl+addUpdatePath,
            type:'POST',
            data: JSON.stringify(formData),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success:function(resObj, status, fullResponse){
            	var savedRecord = JSON.parse(fullResponse.responseText);
            	if(editRecord){
            		writeEditRecord(savedRecord);
            	}
            	else{
            		writeCreatedRecord(savedRecord);
            	}
		    	dialog.dialog( "close" );
            },
            error:function(res){
            	alert("Error! Unable to add record");
            }
       	});
        if(!$.isEmptyObject(fileFormData)) {
        	var uploadFileFormData = new FormData();
        	uploadFileFormData.append("imageFile", fileFormData.imagePath);
        	$.ajax({
                url: uploadImageUrl,  //Server script to process data
                type: 'POST',
                xhr: function() {  // Custom XMLHttpRequest
                    var myXhr = $.ajaxSettings.xhr();
                    if(myXhr.upload){ // Check if upload property exists
                        //myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // For handling the progress of the upload
                    }
                    return myXhr;
                },
                //Ajax events
                success: function() {
                	//alert('upload success');
                },
                error: function(data) {
                	alert('Error uploading file!');
                },
                // Form data
                data: uploadFileFormData,
                //Options to tell jQuery not to process data or worry about content-type.
                cache: false,
                contentType: false,
                processData: false
            });
        }
	}
	return valid;
}
function validateForm() {
	var valid=true;
	
	clearFormValidation();
	if (typeof preValidation == 'function') { 
		valid = preValidation() && valid;
	}
	if (typeof lengthValidation !== 'undefined') {
		$.each(lengthValidation, function(){
			valid = checkLength( this.field, this.name, this.min, this.max ) && valid;
		});
	}
	if (typeof numberValidation !== 'undefined') {
		$.each(numberValidation, function(){
			valid = checkNumber( this.field, this.name ) && valid;
		});
	}
	if (typeof dateValidation !== 'undefined') {
		$.each(dateValidation, function(){
			valid = checkDate( this.field, this.name ) && valid;
		});
	}
	if (typeof passwordValidation !== 'undefined') {
		$.each(passwordValidation, function(){
			valid = checkPasswordsMatch( this.field1, this.field2 ) && valid;
		});
	}
	if (typeof extraValidation == 'function') { 
		valid = extraValidation() && valid;
	}
	return valid;
}

function clearFormValidation(){
	tips.empty();
	allFields.removeClass( "ui-state-error" );
	tips.removeClass( "ui-state-highlight" );
	tips.hide();
}
function populateFormData() {
	var formData = {};
	
	$.each(allFields, function(i, v){
		var shortName = findShortName(v.name);
		if(v.nodeName == "SELECT") {
			setSelectValue(formData, v);
		} else if (v.type == "checkbox") {
			if(v.checked) {
				formData[shortName] = true;
			} else {
				formData[shortName] = false;
			}
		} else if (v.type == "file") {
			if (typeof v.files[0] !== 'undefined') {
				formData[shortName] = v.files[0].name;
				fileFormData[shortName] = v.files[0];
			}
		} else {
			formData[shortName] = v.value;
		}
	});
	if (typeof setExtraValues == 'function') { 
		setExtraValues(formData); 
	}
	return formData;
}
function findShortName(name) {
	var underscoreIndex = name.indexOf('_');
	var shortName;
	if(underscoreIndex >= 0) {
		shortName = name.substring(underscoreIndex+1);
	} else {
		shortName = name;
	}
	return shortName;
}

function updateTips( t ) {
	if(tips.text().length > 0) {
		tips.append("<br>");
	}
	tips.append(t);
	tips.addClass( "ui-state-highlight" );
	tips.show();
	//setTimeout(function() {
	//	tips.removeClass( "ui-state-highlight", 1500 );
	//}, 500 );
}
function checkLength( o, n, min, max ) {
	if ( o.val().length > max || o.val().length < min ) {
		o.addClass( "ui-state-error" );
		updateTips( "Length of " + n + " must be between " + min + " and " + max + "." );
		return false;
	} else {
		return true;
	}
}
function checkNumber( o, n ) {
	if ( !$.isNumeric(o.val()) ) {
		o.addClass( "ui-state-error" );
		updateTips( n + " must be a valid number." );
		return false;
	} else {
		return true;
	}
}
function checkPasswordsMatch( p1, p2 ) {
	if( p1.val() == p2.val() ) {
		return true;	
	}
	p1.addClass( "ui-state-error" );
	updateTips( "The passwords do not match" );
	return false;
}
function checkDate( o, n ){
	var t = o.val().match(/^(\d{2})\/(\d{2})\/(\d{4})$/);
	if(t!==null) {
		var m=+t[1], d=+t[2], y=+t[3];
		var date = new Date(y,m-1,d);
		if(date.getFullYear()===y && date.getMonth()===m-1) {
			return true;
	    }
	}
	o.addClass( "ui-state-error" );
	updateTips( n + " must be a valid date in the mm/dd/yyyy format." );
	return false;
}

function getNewCheckboxValue(booleanValue) {
	if(booleanValue) {
		return 'checked';
	}
	return '';
}

var _MS_PER_DAY = 1000 * 60 * 60 * 24;
//a and b are javascript Date objects
function dateDiffInDays(a, b) {
	// Discard the time and time-zone information.
	var utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
	var utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());
	
	return Math.floor((utc2 - utc1) / _MS_PER_DAY);
}

$(function() {
	tips = $( ".validateTips" );
	
	if (typeof buildOptions == 'function') {
		buildOptions();
	}
	function deleteRecord(id){
        $.ajax({
            url: baseUrl+deletePath+'/'+id,
            type:'DELETE',
            success:function(response){
        		if (typeof postDeleteAction == 'function') {
        			postDeleteAction(response);
        		}
            },
            error:function(response){
            	// show the record again, since it couldn't be deleted
            	$('div.records-rows.id'+id).toggle();
            	alert('The record could not be deleted. This is likely because this record is being used by another element (ie. User, Aircraft, etc).')
            }
       	});	
	}
	$( "#create-record" ).button().on( "click", function() {
		clearFormValidation();
		$('#addEditForm').trigger("reset");
		$('#addEditForm').show();
		if (typeof populateCreateForm == 'function') {
			populateCreateForm(this);
		}
		dialog.dialog( "open" );
	});
	$(document).on("click", ".editLink", function(){
		clearFormValidation();
		$('#addEditForm').trigger("reset");
		$('#addEditForm').show();
		populateEditForm(this);
		dialog.dialog( "open" );
	});
	$(document).on("click", ".deleteLink", function(){
		var deleteName = findDeleteName(this);
		if(window.confirm("Are you sure you want to delete '"+deleteName+"'?")) {
			deleteRecord($(this).attr('data-id'));
			$(this).closest('div.records-rows').toggle();
		}
	});
	$("#addEditForm").submit(function(e){
		e.preventDefault();
		saveRecord();
	});
	$( "#open-squawks" ).button();
	$( "#completed-squawks" ).button();
});