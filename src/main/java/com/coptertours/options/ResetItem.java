package com.coptertours.options;

import java.util.ArrayList;
import java.util.List;

import com.coptertours.common.KeyValue;

public enum ResetItem {

	HOBBS("Hobbs"), ENGINE("Engine");

	private String name;

	private ResetItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static ResetItem byName(String name) {
		for (ResetItem resetItem : ResetItem.values()) {
			if (name.toLowerCase().equals(resetItem.getName().toLowerCase())) {
				return resetItem;
			}
		}
		return null;
	}

	public static List<KeyValue> asKeyValueList() {
		List<KeyValue> resetItemList = new ArrayList<KeyValue>();
		for (ResetItem resetItem : ResetItem.values()) {
			resetItemList.add(new KeyValue(resetItem.name(), resetItem.getName()));
		}
		return resetItemList;
	}
}
