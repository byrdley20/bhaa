package com.coptertours.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.coptertours.common.AppConstants;
import com.google.gson.annotations.Expose;

@Entity
public class Aircraft extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;
	@Expose
	private String aircraftNumber;
	@Expose
	private String name;
	@ManyToOne(targetEntity = Model.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "MODEL_ID")
	@Expose
	private Model model;
	@Expose
	private String serialNum;
	@Expose
	private String imagePath = AppConstants.PLACEHOLDER_IMAGE;
	@Expose
	private int ordering;
	@Expose
	private BigDecimal hobbs = BigDecimal.ZERO;
	@Expose
	private BigDecimal hobbsOffset = BigDecimal.ZERO;
	@Expose
	private BigDecimal engineTotalTime = BigDecimal.ZERO;
	@Expose
	private BigDecimal engineTotalTimeOffset = BigDecimal.ZERO;
	@Transient
	@Expose
	private int totalStarts;

	@Transient
	@Expose
	private List<MaintenanceType> maintenanceTypes;

	@Transient
	@Expose
	private List<MaintenanceType> flightHourMaintenanceTypes;

	@Transient
	@Expose
	private List<MaintenanceType> monthMaintenanceTypes;

	public Long getId() {
		return id;
	}

	public String getAircraftNumber() {
		return aircraftNumber;
	}

	public String getName() {
		return name;
	}

	public Model getModel() {
		return model;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getOrdering() {
		return ordering;
	}

	public BigDecimal getHobbs() {
		return hobbs;
	}

	public BigDecimal getHobbsOffset() {
		return hobbsOffset;
	}

	public BigDecimal getEngineTotalTime() {
		return engineTotalTime;
	}

	public BigDecimal getEngineTotalTimeOffset() {
		return engineTotalTimeOffset;
	}

	public List<MaintenanceType> getFlightHourMaintenanceTypes() {
		return flightHourMaintenanceTypes;
	}

	public void setFlightHourMaintenanceTypes(List<MaintenanceType> flightHourMaintenanceTypes) {
		this.flightHourMaintenanceTypes = flightHourMaintenanceTypes;
	}

	public List<MaintenanceType> getMonthMaintenanceTypes() {
		return monthMaintenanceTypes;
	}

	public void setMonthMaintenanceTypes(List<MaintenanceType> monthMaintenanceTypes) {
		this.monthMaintenanceTypes = monthMaintenanceTypes;
	}

	public List<MaintenanceType> getMaintenanceTypes() {
		return maintenanceTypes;
	}

	public void setMaintenanceTypes(List<MaintenanceType> maintenanceTypes) {
		this.maintenanceTypes = maintenanceTypes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getTotalStarts() {
		return totalStarts;
	}

	public void setTotalStarts(int totalStarts) {
		this.totalStarts = totalStarts;
	}

}
