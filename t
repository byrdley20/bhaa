[1mdiff --git a/src/main/java/com/coptertours/controller/mvc/ComponentController.java b/src/main/java/com/coptertours/controller/mvc/ComponentController.java[m
[1mindex 4b631c3..2ff1e62 100755[m
[1m--- a/src/main/java/com/coptertours/controller/mvc/ComponentController.java[m
[1m+++ b/src/main/java/com/coptertours/controller/mvc/ComponentController.java[m
[36m@@ -11,6 +11,7 @@[m [mimport org.springframework.web.bind.annotation.RequestParam;[m
 import com.coptertours.domain.Aircraft;[m
 import com.coptertours.repository.AircraftRepository;[m
 import com.coptertours.repository.ComponentRepository;[m
[32m+[m[32mimport com.coptertours.repository.MaintenanceTypeRepository;[m
 [m
 @Controller[m
 public class ComponentController extends BaseController {[m
[36m@@ -18,6 +19,8 @@[m [mpublic class ComponentController extends BaseController {[m
 	private ComponentRepository componentRepository;[m
 	@Autowired[m
 	private AircraftRepository aircraftRepository;[m
[32m+[m	[32m@Autowired[m
[32m+[m	[32mprivate MaintenanceTypeRepository maintenanceTypeRepository;[m
 [m
 	@RequestMapping("/admin/components.html")[m
 	String components(Model model) {[m
[36m@@ -31,6 +34,7 @@[m [mpublic class ComponentController extends BaseController {[m
 		Aircraft aircraft = this.findAircraftById(id);[m
 		model.addAttribute("components", this.componentRepository.findByAircraft(aircraft, sortByComponentNameAsc()));[m
 		model.addAttribute("aircraft", aircraft);[m
[32m+[m		[32mmodel.addAttribute("maintenanceTypes", this.maintenanceTypeRepository.findByModelAndActiveTrue(aircraft.getModel(), sortByNameAsc()));[m
 		return "admin/aircraftComponents";[m
 	}[m
 }[m
[1mdiff --git a/src/main/java/com/coptertours/domain/Component.java b/src/main/java/com/coptertours/domain/Component.java[m
[1mindex 0717301..fc08aa7 100755[m
[1m--- a/src/main/java/com/coptertours/domain/Component.java[m
[1m+++ b/src/main/java/com/coptertours/domain/Component.java[m
[36m@@ -28,6 +28,11 @@[m [mpublic class Component extends BaseDomain {[m
 	@Expose[m
 	private Aircraft aircraft;[m
 [m
[32m+[m	[32m@ManyToOne(targetEntity = MaintenanceType.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })[m
[32m+[m	[32m@JoinColumn(name = "MAINTENANCE_TYPE_ID")[m
[32m+[m	[32m@Expose[m
[32m+[m	[32mprivate MaintenanceType maintenanceType;[m
[32m+[m
 	public Long getId() {[m
 		return id;[m
 	}[m
[36m@@ -51,4 +56,12 @@[m [mpublic class Component extends BaseDomain {[m
 	public void setAircraft(Aircraft aircraft) {[m
 		this.aircraft = aircraft;[m
 	}[m
[32m+[m
[32m+[m	[32mpublic MaintenanceType getMaintenanceType() {[m
[32m+[m		[32mreturn maintenanceType;[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32mpublic void setMaintenanceType(MaintenanceType maintenanceType) {[m
[32m+[m		[32mthis.maintenanceType = maintenanceType;[m
[32m+[m	[32m}[m
 }[m
[1mdiff --git a/src/main/resources/templates/admin/aircraftComponents.html b/src/main/resources/templates/admin/aircraftComponents.html[m
[1mindex b5abd8b..f1b02cd 100755[m
[1m--- a/src/main/resources/templates/admin/aircraftComponents.html[m
[1m+++ b/src/main/resources/templates/admin/aircraftComponents.html[m
[36m@@ -13,9 +13,14 @@[m
     	/*<![CDATA[*/[m
     	           [m
 		var aircraft = /*[[${aircraft}]]*/; [m
[32m+[m		[32mvar maintenanceTypes = {};[m
 [m
 		function buildOptions(){[m
[31m-		[m
[32m+[m			[32mvar allModelMaintenanceTypes = /*[[${maintenanceTypes}]]*/;[m
[32m+[m			[32mfor(var i=0; i < allModelMaintenanceTypes.length; i++) {[m
[32m+[m				[32mvar maintenanceType = allModelMaintenanceTypes[i];[m
[32m+[m				[32mmaintenanceTypes[maintenanceType.id] = maintenanceType;[m
[32m+[m			[32m}[m
 		}[m
 		/*]]>*/[m
     </script>[m
[36m@@ -29,9 +34,10 @@[m
     		var id = $( "#id" ),[m
     		componentName = $( "#componentName" ),[m
         	serialNumber = $( "#serialNumber" ),[m
[31m-        	partNumber = $( "#partNumber" );[m
[32m+[m[41m        [m	[32mpartNumber = $( "#partNumber" ),[m
[32m+[m[41m        [m	[32mmaintenanceType = $( "#maintenanceType" );[m
     		[m
[31m-        	allFields = $( [] ).add( id ).add( componentName ).add( serialNumber ).add( partNumber );[m
[32m+[m[41m        [m	[32mallFields = $( [] ).add( id ).add( componentName ).add( serialNumber ).add( partNumber ).add( maintenanceType );[m
         	addUpdatePath = "/admin/components";[m
         	deletePath = "/admin/components";[m
     		[m
[36m@@ -40,7 +46,9 @@[m
     		lengthValidation[lengthValidation.length] = createLengthValidationObject(componentName, "'Name'", 1, 50);[m
     	});[m
     	function setSelectValue(formData, element) {[m
[31m-[m
[32m+[m[41m    [m		[32mif(element.name == 'maintenanceType'){[m
[32m+[m				[32mformData[element.name] = maintenanceTypes[element.value];[m
[32m+[m			[32m}[m
     	}[m
     	function setExtraValues(formData) {[m
     		formData["aircraft"] = aircraft;[m
[36m@@ -61,7 +69,9 @@[m
 			    	"<span class='componentName dataCell'>"+savedRecord.componentName+"</span> " +[m
 			    	"<span class='dataCell serialNumber'>"+savedRecord.serialNumber+"</span> " +[m
 			    	"<span class='dataCell partNumber'>"+savedRecord.partNumber+"</span> " +[m
[32m+[m			[41m    [m	[32m"<span class='maintenanceType dataCell'>"+savedRecord.maintenanceType.name+"</span> " +[m
 			    	"<span class='id cell hidden'>"+savedRecord.id+"</span> " +[m
[32m+[m			[41m    [m	[32m"<span class='maintenanceTypeId cell hidden'>"+savedRecord.maintenanceType.id+"</span> " +[m
 		    	"</div>" );    		[m
     	}[m
     	function writeEditRecord(savedRecord){[m
[36m@@ -69,6 +79,7 @@[m
     		editedRow.find('.componentName').html(savedRecord.componentName);[m
     		editedRow.find('.serialNumber').html(savedRecord.serialNumber);[m
     		editedRow.find('.partNumber').html(savedRecord.partNumber);[m
[32m+[m[41m    [m		[32meditedRow.find('.maintenanceType').html(savedRecord.maintenanceType.name);[m
     		editedRow.find('.id').html(savedRecord.id);[m
     		editedRow.addClass('new-row');[m
     	}[m
[36m@@ -77,6 +88,11 @@[m
     		$('#addEditForm').find('#componentName').val(editSpan.nextAll('span.componentName').first().html());[m
     		$('#addEditForm').find('#serialNumber').val(editSpan.nextAll('span.serialNumber').first().html());[m
     		$('#addEditForm').find('#partNumber').val(editSpan.nextAll('span.partNumber').first().html());[m
[32m+[m[41m    		[m
[32m+[m[41m    [m		[32mvar maintenanceTypeIdSpan = editSpan.nextAll('span.maintenanceTypeId');[m
[32m+[m[41m    [m		[32mvar maintenanceTypeOptionStr = "select option[value='"+maintenanceTypeIdSpan.html()+"']";[m
[32m+[m[41m    [m		[32m$('#addEditForm').find(maintenanceTypeOptionStr).prop('selected','selected');[m
[32m+[m[41m    		[m
     		$('#addEditForm').find('#id').val(editSpan.nextAll('span.id').first().html());[m
     	}[m
     	function findDeleteName(deleteLink){[m
[36m@@ -100,6 +116,11 @@[m
 					<input type="text" name="serialNumber" id="serialNumber" class="text ui-widget-content ui-corner-all"/>[m
 					<label for="name">Part Number</label>[m
 					<input type="text" name="partNumber" id="partNumber" class="text ui-widget-content ui-corner-all"/>[m
[32m+[m					[32m<label for="maintenanceType">Link to Maintenance Type</label>[m
[32m+[m					[32m<select id="maintenanceType" name="maintenanceType" class="ui-widget-content ui-corner-all">[m
[32m+[m						[32m<option value="-1">-- None --</option>[m
[32m+[m						[32m<option th:each="maintenanceType : ${maintenanceTypes}" th:text="${maintenanceType.name}" th:value="${maintenanceType.id}">${maintenanceType.name}</option>[m
[32m+[m					[32m</select>[m
 					<input type="text" name="id" id="id" class="hidden"/>[m
 					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px"/>[m
 				</fieldset>[m
[36m@@ -112,8 +133,9 @@[m
 			    <span class="headerCell dataCell">Name</span>[m
 			    <span class="headerCell dataCell">Serial Num</span>[m
 			    <span class="heade