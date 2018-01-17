package com.coptertours.controller.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.domain.MaintenanceType;
import com.coptertours.options.Action;
import com.coptertours.options.MaintenanceCategory;
import com.coptertours.repository.MaintenanceTypeRepository;
import com.coptertours.repository.ModelRepository;

@Controller
public class MaintenanceTypeController extends BaseController {
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;
	@Autowired
	private ModelRepository modelRepository;

	@RequestMapping("/admin/maintenanceTypes.html")
	String maintenanceTypes(Model model) {
		List<com.coptertours.domain.Model> models = this.modelRepository.findAllByActiveTrue(sortByNameAsc());
		model.addAttribute("models", models);
		return "admin/maintenanceTypes";
	}

	@RequestMapping("/admin/modelMaintenanceTypes.html")
	String modelMaintenanceTypes(Model model, @RequestParam Long id) {
		com.coptertours.domain.Model aircraftModel = this.modelRepository.findById(id);
		Map<MaintenanceCategory, List<MaintenanceType>> maintCategoryToMaintTypes = new HashMap<MaintenanceCategory, List<MaintenanceType>>();
		List<MaintenanceType> maintenanceTypes = this.maintenanceTypeRepository.findByModel(aircraftModel, sortByMaintCategoryThenName());
		for (MaintenanceType maintenanceType : maintenanceTypes) {
			List<MaintenanceType> maintenanceTypeList = maintCategoryToMaintTypes.get(maintenanceType.getMaintenanceCategory());
			if (CollectionUtils.isEmpty(maintenanceTypeList)) {
				maintenanceTypeList = new ArrayList<MaintenanceType>();
			}
			maintenanceTypeList.add(maintenanceType);
			maintCategoryToMaintTypes.put(maintenanceType.getMaintenanceCategory(), maintenanceTypeList);
		}
		// add empty list for any categories not included
		for (MaintenanceCategory maintenanceCategory : MaintenanceCategory.values()) {
			if(!maintCategoryToMaintTypes.containsKey(maintenanceCategory)){
				maintCategoryToMaintTypes.put(maintenanceCategory, new ArrayList<MaintenanceType>());
			}
		}
		model.addAttribute("model", aircraftModel);
		model.addAttribute("maintCategoryToMaintTypes", maintCategoryToMaintTypes);
		model.addAttribute("maintenanceCategoryList", MaintenanceCategory.asKeyValueList());
		model.addAttribute("actionList", Action.asKeyValueList());
		return "admin/modelMaintenanceTypes";
	}

	// UNUSED RIGHT NOW
	@RequestMapping("/allModelMaintenanceTypes.html")
	String allModelMaintenanceTypes(Model model) {
		model.addAttribute("maintenanceTypes", this.maintenanceTypeRepository.findAll(sortByModelAsc()));
		List<com.coptertours.domain.Model> allModels = this.modelRepository.findAllByActiveTrue(sortByNameAsc());

		Map<String, Map<MaintenanceCategory, List<MaintenanceType>>> modelToMaintTypes = new HashMap<String, Map<MaintenanceCategory, List<MaintenanceType>>>();
		for (com.coptertours.domain.Model maintTypeModel : allModels) {
			Map<MaintenanceCategory, List<MaintenanceType>> maintCategoryToMaintTypes = new HashMap<MaintenanceCategory, List<MaintenanceType>>();
			List<MaintenanceType> maintenanceTypes = this.maintenanceTypeRepository.findByModel(maintTypeModel, sortByMaintCategoryThenName());
			for (MaintenanceType maintenanceType : maintenanceTypes) {
				List<MaintenanceType> maintenanceTypeList = maintCategoryToMaintTypes.get(maintenanceType.getMaintenanceCategory());
				if (CollectionUtils.isEmpty(maintenanceTypeList)) {
					maintenanceTypeList = new ArrayList<MaintenanceType>();
				}
				maintenanceTypeList.add(maintenanceType);
				maintCategoryToMaintTypes.put(maintenanceType.getMaintenanceCategory(), maintenanceTypeList);
			}
			modelToMaintTypes.put(maintTypeModel.getName(), maintCategoryToMaintTypes);
		}

		for (com.coptertours.domain.Model maintTypeModel : allModels) {
			List<MaintenanceType> maintenanceTypes = this.maintenanceTypeRepository.findByModel(maintTypeModel, sortByMaintCategoryThenName());
			// maintTypeModel.setMaintenanceTypes(maintenanceTypes);
			for (MaintenanceType maintenanceType : maintenanceTypes) {
				maintenanceType.clearModel();
			}
		}
		model.addAttribute("modelToMaintTypes", modelToMaintTypes);
		model.addAttribute("allModels", allModels);
		model.addAttribute("maintenanceCategoryList", MaintenanceCategory.asKeyValueList());
		model.addAttribute("actionList", Action.asKeyValueList());
		return "allModelMaintenanceTypes";
	}
}
