package com.coptertours.domain;

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
	private Date date;

	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "PILOT_ID")
	@Expose
	private User pilot;

	@ManyToOne(targetEntity = Aircraft.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "AIRCRAFT_ID")
	@Expose
	private Aircraft aircraft;

	@ManyToOne(targetEntity = AdCompliance.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "AD_COMPLIANCE_ID")
	@Expose
	private AdCompliance adCompliance;

	public Long getId() {
		return id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "GMT-06:00")
	public Date getDate() {
		return date;
	}

	public User getPilot() {
		return pilot;
	}

	public AdCompliance getAdCompliance() {
		return adCompliance;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}
}
