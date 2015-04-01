package com.coptertours.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
public class Model extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;
	@Expose
	private String name;

	@Transient
	private List<MaintenanceType> maintenanceTypes;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<MaintenanceType> getMaintenanceTypes() {
		return maintenanceTypes;
	}

	public void setMaintenanceTypes(List<MaintenanceType> maintenanceTypes) {
		this.maintenanceTypes = maintenanceTypes;
	}
}
