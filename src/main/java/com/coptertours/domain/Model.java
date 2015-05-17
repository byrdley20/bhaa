package com.coptertours.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

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
	@Transient
	@Expose
	private boolean hasAdCompliances;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean getShowStarts() {
		return showStarts;
	}

	public boolean isHasAdCompliances() {
		return hasAdCompliances;
	}

	public void setHasAdCompliances(boolean hasAdCompliances) {
		this.hasAdCompliances = hasAdCompliances;
	}
}