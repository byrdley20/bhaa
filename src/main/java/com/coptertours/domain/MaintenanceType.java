package com.coptertours.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.coptertours.options.Action;
import com.coptertours.options.MaintenanceCategory;
import com.google.gson.annotations.Expose;

@Entity
public class MaintenanceType extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;
	@Expose
	private String name;

	@ManyToOne(targetEntity = Model.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "MODEL_ID")
	@Expose
	private Model model;

	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "MAINTENANCE_CATEGORY")
	@Expose
	private MaintenanceCategory maintenanceCategory;

	@Expose
	private BigDecimal timeBeforeAction;
	@Expose
	private String manualNumber;
	@Expose
	private String partNumber;
	@Expose
	private String serialNumber;

	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "ACTION")
	@Expose
	private Action action;

	@Expose
	private boolean showOnDashboard;
	@Expose
	private boolean active;

	@Transient
	@Expose
	private MaintenanceLog maintenanceLog;
	
	public void clearModel() {
		this.model = null;
	}
	
	@Override
	public MaintenanceType clone() {
		try {
			// shallow copy
			return (MaintenanceType) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public MaintenanceCategory getMaintenanceCategory() {
		return maintenanceCategory;
	}

	public BigDecimal getTimeBeforeAction() {
		return timeBeforeAction;
	}

	public String getManualNumber() {
		return manualNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public Action getAction() {
		return action;
	}

	public Model getModel() {
		return model;
	}

	public MaintenanceLog getMaintenanceLog() {
		return maintenanceLog;
	}

	public void setMaintenanceLog(MaintenanceLog maintenanceLog) {
		this.maintenanceLog = maintenanceLog;
	}

	public boolean getShowOnDashboard() {
		return showOnDashboard;
	}

	public boolean isActive() {
		return active;
	}
}
