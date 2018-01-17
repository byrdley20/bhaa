package com.coptertours.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

@Entity
public class RepairLog extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;

	@Expose
	private Date reportDate;

	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "PILOT_ID")
	@Expose
	private User pilot;

	@ManyToOne(targetEntity = Aircraft.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "AIRCRAFT_ID")
	@Expose
	private Aircraft aircraft;

	@Expose
	@Column(columnDefinition = "varchar(500)")
	private String issueNotes;
	@Expose
	private Date repairDate;

	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "MECHANIC_ID")
	@Expose
	private User mechanic;

	@Expose
	@Column(columnDefinition = "varchar(500)")
	private String repairNotes;

	public Long getId() {
		return id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "GMT-06:00")
	public Date getReportDate() {
		return reportDate;
	}

	public User getPilot() {
		return pilot;
	}

	public String getIssueNotes() {
		return issueNotes;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "GMT-06:00")
	public Date getRepairDate() {
		return repairDate;
	}

	public String getRepairNotes() {
		return repairNotes;
	}

	public User getMechanic() {
		return mechanic;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}
}
