package com.coptertours.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class ExcludedAdCompliance extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;

	@Expose
	private Long aircraftId;

	@ManyToOne(targetEntity = AdCompliance.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "AD_COMPLIANCE_ID")
	@Expose
	private AdCompliance adCompliance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Long aircraftId) {
		this.aircraftId = aircraftId;
	}

	public AdCompliance getAdCompliance() {
		return adCompliance;
	}

	public void setAdCompliance(AdCompliance adCompliance) {
		this.adCompliance = adCompliance;
	}
}