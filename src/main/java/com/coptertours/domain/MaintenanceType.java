package com.coptertours.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MaintenanceType {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
