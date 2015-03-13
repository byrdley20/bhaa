package com.coptertours.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Aircraft {
	@Id
	@GeneratedValue
	private Long id;
	private String aircraftNumber;
	private String name;
	private int modelId;
	private String serialNum;
	private String imagePath;
	private String engine;
	private int showStarts;
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
