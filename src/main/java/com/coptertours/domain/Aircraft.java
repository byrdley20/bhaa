package com.coptertours.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
	private boolean inService;

	@Lob
	@Column(name = "IMAGE_PATH", columnDefinition = "clob")
	@Expose
	private String imagePath = AppConstants.PLACEHOLDER_IMAGE;
	@Expose
	private int ordering;

	@Transient
	@Expose
	private BigDecimal hobbs = BigDecimal.ZERO;

	@Transient
	@Expose
	private BigDecimal hobbsOffset = BigDecimal.ZERO;

	@Transient
	@Expose
	private BigDecimal engineTotalTimeOffset = BigDecimal.ZERO;

	@OneToMany(mappedBy = "aircraftId")
	@OrderBy("resetItem, date")
	@Expose
	private List<ResetLog> resetLogs;

	@Transient
	@Expose
	private int totalStarts;
	@Transient
	@Expose
	private boolean adComplied = true;

	@Transient
	@Expose
	private List<MaintenanceType> maintenanceTypes;

	@Transient
	@Expose
	private List<MaintenanceType> flightHourMaintenanceTypes;

	@Transient
	@Expose
	private List<MaintenanceType> monthMaintenanceTypes;

	@Transient
	@Expose
	private BigDecimal yearlyHours;

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

	public boolean isAdComplied() {
		return adComplied;
	}

	public void setAdComplied(boolean adComplied) {
		this.adComplied = adComplied;
	}

	public List<ResetLog> getResetLogs() {
		return resetLogs;
	}

	public BigDecimal getHobbsOffset() {
		return hobbsOffset;
	}

	public void setHobbsOffset(BigDecimal hobbsOffset) {
		this.hobbsOffset = hobbsOffset;
	}

	public BigDecimal getEngineTotalTimeOffset() {
		return engineTotalTimeOffset;
	}

	public void setEngineTotalTimeOffset(BigDecimal engineTotalTimeOffset) {
		this.engineTotalTimeOffset = engineTotalTimeOffset;
	}

	public void setHobbs(BigDecimal hobbs) {
		this.hobbs = hobbs;
	}

	public BigDecimal getYearlyHours() {
		return yearlyHours;
	}

	public void setYearlyHours(BigDecimal yearlyHours) {
		this.yearlyHours = yearlyHours;
	}

	public boolean isInService() {
		return inService;
	}

}
