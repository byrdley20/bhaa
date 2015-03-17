package com.coptertours.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseDomain {

	public String toJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}
}
