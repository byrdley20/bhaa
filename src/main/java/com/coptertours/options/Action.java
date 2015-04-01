package com.coptertours.options;

import java.util.ArrayList;
import java.util.List;

import com.coptertours.common.KeyValue;

public enum Action {

	OVERHAUL("Overhaul"), RETIRE("Retire");

	private String name;

	private Action(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static List<KeyValue> asKeyValueList() {
		List<KeyValue> actionList = new ArrayList<KeyValue>();
		for (Action action : Action.values()) {
			actionList.add(new KeyValue(action.name(), action.getName()));
		}
		return actionList;
	}
}
