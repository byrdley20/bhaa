package com.coptertours.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private String imagePath;
	@Expose
	private String engine;
	@Expose
	private boolean showStarts;
	@Expose
	private int ordering;
	@OneToMany(targetEntity = FlightLog.class, cascade = {
			CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "AIRCRAFT_ID")
	@Expose
	private List<FlightLog> flightLogs;

	public List<FlightLog> getFlightLogs() {
		return flightLogs;
	}

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

	public String getEngine() {
		return engine;
	}

	public boolean getShowStarts() {
		return showStarts;
	}

	public int getOrdering() {
		return ordering;
	}

}
