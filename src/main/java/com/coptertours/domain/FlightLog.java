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

import com.coptertours.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

@Entity
public class FlightLog extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;

	@ManyToOne(targetEntity = Aircraft.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "AIRCRAFT_ID")
	@Expose
	private Aircraft aircraft;

	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "USER_ID")
	@Expose
	private User user;

	@Expose
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "GMT-06:00")
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

	private static final SimpleDateFormat FLIGHT_LOG_DATE_FORMAT = new SimpleDateFormat("EEE, MM/dd/yyyy");

	public String getFlightLogDateDisplay() {
		return FLIGHT_LOG_DATE_FORMAT.format(getDate());
	}

	public void setDateToStartOfDay() {
		setDate(DateUtil.findDayStart(date));
	}

	public String getDateDisplay() {
		return DATE_FORMAT.format(getDate());
	}

	public Long getId() {
		return id;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

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

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStarts(int starts) {
		this.starts = starts;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setHobbsBegin(BigDecimal hobbsBegin) {
		this.hobbsBegin = hobbsBegin;
	}

	public void setHobbsEnd(BigDecimal hobbsEnd) {
		this.hobbsEnd = hobbsEnd;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
