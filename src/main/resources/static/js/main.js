	/*
	$('<div/>', {
	    id: 'dialog-form',
	    title: 'Create new'
	}).appendTo('body');
	$('<p/>', {
		'class': 'validateTips'
	}).appendTo('#dialog-form');
	$('<form/>', {
		id: 'addEditForm',
		action: formAction,
		method: 'post'
	}).appendTo('#dialog-form');
	$('<fieldset/>', {
		id: 'addEditFieldset'
	}).appendTo('#addEditForm');
	$('<label/>', {
		'for': 'name',
		text: 'ID' //var
	}).appendTo('#addEditFieldset');
	
	

	<div id="dialog-form" title="Create new">
	<p class="validateTips"></p>
	<form id="addEditForm" action="/users" method="post">
		<fieldset>
			<label for="name">ID</label>
			<input type="text" name="userId" id="userId" value="5555" class="text ui-widget-content ui-corner-all"/>
			<label for="name">First Name</label>
			<input type="text" name="firstName" id="firstName" value="Jane" class="text ui-widget-content ui-corner-all"/>
			<label for="name">Last Name</label>
			<input type="text" name="lastName" id="lastName" value="Smith" class="text ui-widget-content ui-corner-all"/>
			<label for="email">Rating</label>
			<select id="rating" name="rating" class="ui-widget-content ui-corner-all">
				<option th:each="rating : ${allRatings}" th:text="${rating.name}" th:value="${rating.id}">${rating.name}</option>
			</select>
			<!--<input type="text" name="rating" id="rating" value="Captain" class="text ui-widget-content ui-corner-all"/>-->
			<label for="email">Username</label>
			<input type="text" name="username" id="username" value="jsmith" class="text ui-widget-content ui-corner-all"/>
			<label for="password">Password</label>
			<input type="password" name="password" id="password" value="xxxxxxx" class="text ui-widget-content ui-corner-all"/>
			<input type="password" name="password2" id="password2" value="xxxxxxx" class="text ui-widget-content ui-corner-all"/>
			<input type="text" name="id" id="id" class="hidden"/>
			<!-- Allow form submission with keyboard without duplicating the dialog button -->
			<input type="submit" tabindex="-1" style="position:absolute; top:-1000px"/>
		</fieldset>
	</form>
</div>
*/

var baseUrl = "http://localhost:9999";
var dialog, allFields, form, tips;

function createDialog(saveButtonName, allFormFields){
	allFields = allFormFields;
	
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
			if(v.name == 'rating'){
				$.ajax({
		            url: baseUrl+'/ratings/'+v.value,
		            type:'GET',
		            async: false,
		            dataType: 'json',
		            contentType: "application/json; charset=utf-8",
		            success:function(ratingResponse){
		            	formData[v.name]=ratingResponse;
		            }
				});
			} else {
				formData[v.name] = v.value;
			}
		});
		var editUser = (formData["id"] > 0);
        $.ajax({
            url: baseUrl+'/users',
            type:'POST',
            data: JSON.stringify(formData),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success:function(resObj, status, fullResponse){
            	var savedUser = JSON.parse(fullResponse.responseText);
            	if(editUser){
            		var editedRow = $(".id"+savedUser.id);
            		editedRow.find('.userId').html(savedUser.userId);
            		editedRow.find('.name').html(savedUser.firstName + ' ' + savedUser.lastName);
            		editedRow.find('.rating').html(savedUser.rating.name);
            		editedRow.find('.username').html(savedUser.username);
            		editedRow.find('.id').html(savedUser.id);
            		editedRow.find('.firstName').html(savedUser.firstName);
            		editedRow.find('.lastName').html(savedUser.lastName);
            	}
            	else{				            	
			    	$( ".users-rows" ).last().after( "<div class='users-rows id"+savedUser.id+"'>" +
						    	"<span class='edit'><a href='#' class='editLink' data-id='"+savedUser.id+"'><img border='0' alt='Edit' src='images/edit.png' width='15' height='15'/></a></span> " +
						    	"<span class='delete'><a href='#' class='deleteLink' data-id='"+savedUser.id+"'><img border='0' alt='Delete' src='images/delete.png' width='15' height='15'/></a></span> " +
						    	"<span class='userId cell'>"+savedUser.userId+"</span> " +
						    	"<span class='name cell'>"+savedUser.firstName+" "+savedUser.lastName+"</span> " +
						    	"<span class='rating cell'>"+savedUser.rating.name+"</span> " +
						    	"<span class='username cell'>"+savedUser.username+"</span> " +
						    	"<span class='id cell hidden'>"+savedUser.id+"</span> " +
						    	"<span class='firstName cell hidden'>"+savedUser.firstName+"</span> " +
						    	"<span class='lastName cell hidden'>"+savedUser.lastName+"</span> " +
						    	"<span class='ratingId cell hidden'>"+savedUser.rating.id+"</span> " +
					    	"</div>" );
            	}
		    	dialog.dialog( "close" );
            },
            error:function(res){
            	alert("Error! Unable to add user");
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
	
	function deleteUser(id){
        $.ajax({
            url: baseUrl+'/users/'+id,
            type:'DELETE',
            success:function(response){
            },
            error:function(response){
                alert("Error! Unable to delete user");
            }
       	});	
	}
	$( "#create-user" ).button().on( "click", function() {
		clearFormValidation();
		$('#addEditForm').trigger("reset");
		dialog.dialog( "open" );
	});
	$(document).on("click", ".editLink", function(){
		clearFormValidation();
		$('#addEditForm').trigger("reset");
		$('#addEditForm').find("option:selected").removeAttr("selected");
		var editSpan = $(this).closest('span');
		$('#addEditForm').find('#userId').val(editSpan.nextAll('span.userId').first().html());
		$('#addEditForm').find('#firstName').val(editSpan.nextAll('span.firstName').first().html());
		$('#addEditForm').find('#lastName').val(editSpan.nextAll('span.lastName').first().html());
		var ratingIdSpan = editSpan.nextAll('span.ratingId');
		var ratingOptionStr = "select option[value='"+ratingIdSpan.html()+"']";
		$('#addEditForm').find(ratingOptionStr).prop('selected','selected');
		$('#addEditForm').find('#username').val(editSpan.nextAll('span.username').first().html());
		$('#addEditForm').find('#id').val(editSpan.nextAll('span.id').first().html());
		dialog.dialog( "open" );
	});
	$(document).on("click", ".deleteLink", function(){
		var name = $(this).closest('span').nextAll('span.name').first().html();
		if(window.confirm("Are you sure you want to delete '"+name+"'?")) {
			$(this).closest('div.users-rows').remove();
			deleteUser($(this).attr('data-id'));
		}
	});
});