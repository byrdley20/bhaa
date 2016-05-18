package com.coptertours.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;

@Entity
public class Model extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;
	@Expose
	private String name;
	@Expose
	private boolean showStarts;
	@Expose
	private boolean showMajorComponents;
	@Expose
	private boolean active;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean getShowStarts() {
		return showStarts;
	}

	public boolean isActive() {
		return active;
	}

	public boolean getShowMajorComponents() {
		return showMajorComponents;
	}
}