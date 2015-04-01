package com.coptertours.options;

import java.util.ArrayList;
import java.util.List;

import com.coptertours.common.KeyValue;

public enum MaintenanceCategory {

	FLIGHT_HOURS("Flight Hours"), MONTHS("Months");

	private String name;

	private MaintenanceCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static List<KeyValue> asKeyValueList() {
		List<KeyValue> maintenanceCategoryList = new ArrayList<KeyValue>();
		for (MaintenanceCategory maintenanceCategory : MaintenanceCategory.values()) {
			maintenanceCategoryList.add(new KeyValue(maintenanceCategory.name(), maintenanceCategory.getName()));
		}
		return maintenanceCategoryList;
	}
}
