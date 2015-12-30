package com.coptertours.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
public class Component extends BaseDomain {

	@Id
	@GeneratedValue
	@Expose
	private Long id;
	@Expose
	private String componentName;
	@Expose
	private String partNumber;
	@Expose
	private String serialNumber;

	@ManyToOne(targetEntity = Aircraft.class, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "AIRCRAFT_ID")
	@Expose
	private Aircraft aircraft;

	@ManyToOne(targetEntity = MaintenanceType.class, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "MAINTENANCE_TYPE_ID")
	@Expose
	private MaintenanceType maintenanceType;

	@Transient
	@Expose
	private Long linkedMaintenanceTypeId;

	@Override
	public Component clone() {
		try {
			// shallow copy
			return (Component) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public void clearMaintenanceTypeEnums() {
		if (maintenanceType != null) {
			this.maintenanceType.clearAction();
			this.maintenanceType.clearMaintenanceCategory();
		}
	}

	public Long getId() {
		return id;
	}

	public String getComponentName() {
		return componentName;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public MaintenanceType getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(MaintenanceType maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public Long getLinkedMaintenanceTypeId() {
		return linkedMaintenanceTypeId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setLinkedMaintenanceTypeId(Long linkedMaintenanceTypeId) {
		this.linkedMaintenanceTypeId = linkedMaintenanceTypeId;
	}
}
