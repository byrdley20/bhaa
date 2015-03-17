var baseUrl = "http://localhost:9999";
var dialog, allFields, form, tips, addUpdatePath, deletePath;

function createDialog(saveButtonName){
	
	var buttonsOpts = {}
	buttonsOpts[saveButtonName] = saveRecord;
	buttonsOpts["Cancel"] = function() {
		dialog.dialog( "close" );
	};
	
	dialog = $( "#dialog-form" ).dialog({
    	autoOpen: false,
    	height: 680,
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

function createPasswordValidationObject(field1Obj, field2Obj){
	var passwordValidationObject = {
		field1: field1Obj,
		field2: field2Obj,
	};
	return passwordValidationObject;
}

function saveRecord() {
	var formData = {};
	var valid = true;
	
	clearFormValidation();
	$.each(lengthValidation, function(){
		valid = checkLength( this.field, this.name, this.min, this.max ) && valid;
	});
	$.each(passwordValidation, function(){
		valid = checkPasswordsMatch( this.field1, this.field2 ) && valid;
	});
	
	
	if(valid) {
		$.each(allFields, function(i, v){
			if(v.nodeName == "SELECT") {
				setSelectValue(formData, v);
			} else {
				formData[v.name] = v.value;
			}
		});
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
	}
	return valid;
	
}
function clearFormValidation(){
	tips.empty();
	allFields.removeClass( "ui-state-error" );
	tips.removeClass( "ui-state-highlight" );
}
function updateTips( t ) {
	if(tips.text().length > 0) {
		tips.append("<br>");
	}
	tips.append(t);
	tips.addClass( "ui-state-highlight" );
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
function checkPasswordsMatch( p1, p2 ) {
	if( p1.val() == p2.val() ) {
		return true;	
	}
	p1.addClass( "ui-state-error" );
	updateTips( "The passwords do not match" );
	return false;
}

$(function() {
	tips = $( ".validateTips" );
	
	buildOptions();
	
	function deleteRecord(id){
        $.ajax({
            url: baseUrl+deletePath+'/'+id,
            type:'DELETE',
            success:function(response){
            },
            error:function(response){
                alert("Error! Unable to delete record");
            }
       	});	
	}
	$( "#create-record" ).button().on( "click", function() {
		clearFormValidation();
		$('#addEditForm').trigger("reset");
		dialog.dialog( "open" );
	});
	$(document).on("click", ".editLink", function(){
		clearFormValidation();
		$('#addEditForm').trigger("reset");
		populateEditForm(this);
		dialog.dialog( "open" );
	});
	$(document).on("click", ".deleteLink", function(){
		var deleteName = findDeleteName(this);
		if(window.confirm("Are you sure you want to delete '"+deleteName+"'?")) {
			$(this).closest('div.records-rows').remove();
			deleteRecord($(this).attr('data-id'));
		}
	});
});