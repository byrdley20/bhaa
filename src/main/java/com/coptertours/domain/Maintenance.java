package com.coptertours.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Maintenance {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
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
