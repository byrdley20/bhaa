package com.coptertours.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

@Entity
public class MaintenanceLog extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;

	@Expose
	private Long aircraftId;
	@Expose
	private Long maintenanceTypeId;
	@Expose
	private BigDecimal complyWithHobbs;
	@Expose
	private Date complyWithDate;
	@Expose
	private String partNumber;
	@Expose
	private String serialNumber;

	public String getComplyWithDateDisplay() {
		if (getComplyWithDate() == null) {
			return "";
		}
		return DATE_FORMAT.format(getComplyWithDate());
	}

	public Long getAircraftId() {
		return aircraftId;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "GMT-06:00")
	public Date getComplyWithDate() {
		return complyWithDate;
	}

	public Long getMaintenanceTypeId() {
		return maintenanceTypeId;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getComplyWithHobbs() {
		return complyWithHobbs;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setComplyWithHobbs(BigDecimal complyWithHobbs) {
		this.complyWithHobbs = complyWithHobbs;
	}

	public void setComplyWithDate(Date complyWithDate) {
		this.complyWithDate = complyWithDate;
	}
}
