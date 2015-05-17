package com.coptertours.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

@Entity
public class AdComplianceLog extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;

	@Expose
	private Date complyWithDate;

	@Expose
	private BigDecimal complyWithHobbs;

	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "PILOT_ID")
	@Expose
	private User pilot;
	
	@Expose
	private Long aircraftId;

	@Expose
	private Long adComplianceId;

	public Long getId() {
		return id;
	}

	private static final SimpleDateFormat AD_COMPLIANCE_LOG_DATE_FORMAT = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

	public String getAdComplianceLogDateDisplay() {
		if (getComplyWithDate() == null) {
			return null;
		}
		return AD_COMPLIANCE_LOG_DATE_FORMAT.format(getComplyWithDate());
	}

	public String getDateDisplay() {
		if (getComplyWithDate() == null) {
			return null;
		}
		return DATE_FORMAT.format(getComplyWithDate());
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "GMT-06:00")
	public Date getComplyWithDate() {
		return complyWithDate;
	}

	public User getPilot() {
		return pilot;
	}



	public Long getAircraftId() {
		return aircraftId;
	}

	public Long getAdComplianceId() {
		return adComplianceId;
	}

	public BigDecimal getComplyWithHobbs() {
		return complyWithHobbs;
	}
}
