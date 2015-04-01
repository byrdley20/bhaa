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

import com.google.gson.annotations.Expose;

@Entity
public class FlightLog extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;
	// @ManyToOne(targetEntity = Aircraft.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	// @JoinColumn(name = "AIRCRAFT_ID")
	// @Expose
	// private Aircraft aircraft;
	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "USER_ID")
	@Expose
	private User user;
	@Expose
	private Date date;
	@Expose
	private int starts;
	@ManyToOne(targetEntity = Location.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "LOCATION_ID")
	@Expose
	private Location location;
	@Expose
	private BigDecimal hobbsBegin;
	@Expose
	private BigDecimal hobbsEnd;
	@ManyToOne(targetEntity = Operation.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "OPERATION_ID")
	@Expose
	private Operation operation;

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

	public String getDateDisplay() {
		return DATE_FORMAT.format(getDate());
	}

	public Long getId() {
		return id;
	}

	// public Aircraft getAircraft() {
	// return aircraft;
	// }

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public int getStarts() {
		return starts;
	}

	public Location getLocation() {
		return location;
	}

	public BigDecimal getHobbsBegin() {
		return hobbsBegin;
	}

	public BigDecimal getHobbsEnd() {
		return hobbsEnd;
	}

	public Operation getOperation() {
		return operation;
	}
}
