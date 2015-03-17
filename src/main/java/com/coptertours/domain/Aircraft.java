package com.coptertours.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	@Expose
	private int modelId;
	@Expose
	private String serialNum;
	@Expose
	private String imagePath;
	@Expose
	private String engine;
	@Expose
	private int showStarts;
	@Expose
	private int ordering;

	public Long getId() {
		return id;
	}

	public String getAircraftNumber() {
		return aircraftNumber;
	}

	public String getName() {
		return name;
	}

	public int getModelId() {
		return modelId;
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

	public int getShowStarts() {
		return showStarts;
	}

	public int getOrdering() {
		return ordering;
	}

}
