package com.coptertours.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class AdCompliance extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;

	@Expose
	private String name;

	@Expose
	@Column(columnDefinition = "varchar(800)")
	String description;

	@ManyToOne(targetEntity = Model.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "MODEL_ID")
	@Expose
	private Model model;

	@Expose
	private BigDecimal timeBeforeAction;

	@Expose
	private Boolean daily;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Model getModel() {
		return model;
	}

	public BigDecimal getTimeBeforeAction() {
		return timeBeforeAction;
	}

	public Boolean getDaily() {
		return daily;
	}
}
