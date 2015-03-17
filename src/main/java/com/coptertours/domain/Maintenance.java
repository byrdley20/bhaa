package com.coptertours.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;

@Entity
public class Maintenance extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;
	@Expose
	private String name;
	@Expose
	private Long maintenanceTypeId;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getMaintenanceTypeId() {
		return maintenanceTypeId;
	}

}
