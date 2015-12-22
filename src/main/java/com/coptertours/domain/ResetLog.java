package com.coptertours.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.coptertours.options.ResetItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

@Entity
public class ResetLog extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;

	@Expose
	private Date date;

	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "RESET_ITEM")
	@Expose
	private ResetItem resetItem;

	@Expose
	private BigDecimal hobbs;
	@Expose
	private Long aircraftId;

	public String getDateDisplay() {
		return DATE_FORMAT.format(getDate());
	}

	public Long getId() {
		return id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "GMT-06:00")
	public Date getDate() {
		return date;
	}

	public ResetItem getResetItem() {
		return resetItem;
	}

	public BigDecimal getHobbs() {
		return hobbs;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AIRCRAFT_ID")
	public Long getAircraftId() {
		return aircraftId;
	}
}
