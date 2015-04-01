package com.coptertours.controller.mvc;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.coptertours.domain.BaseDomain;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {

	public String toJson(List<? extends BaseDomain> domainList) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(domainList);
	}

	protected Sort sortByNameAsc() {
		return new Sort(Sort.Direction.ASC, "name");
	}

	protected Sort sortByLastNameAsc() {
		return new Sort(Sort.Direction.ASC, "lastName");
	}

	protected Sort sortByModelAsc() {
		return new Sort(Sort.Direction.ASC, "model.name");
	}

	protected Sort sortByMaintCategoryThenName() {
		return new Sort(new Order(Sort.Direction.ASC, "maintenanceCategory"), new Order(Sort.Direction.ASC, "name"));
	}
}
