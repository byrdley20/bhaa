package com.coptertours.options;

import java.util.ArrayList;
import java.util.List;

import com.coptertours.common.KeyValue;

public enum Role {

	ADMIN("Admin"), PILOT("Pilot"), MECHANIC("Mechanic");

	private String name;

	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static List<KeyValue> asKeyValueList() {
		List<KeyValue> roleList = new ArrayList<KeyValue>();
		for (Role role : Role.values()) {
			roleList.add(new KeyValue(role.name(), role.getName()));
		}
		return roleList;
	}
}
