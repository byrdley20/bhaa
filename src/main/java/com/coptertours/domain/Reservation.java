package com.coptertours.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	private final int groupSize = 1;
	private Date dateAndTime;
	private String familyName;

	@Override
	public String toString() {
		return "Reservation{" +
				"groupSize=" + groupSize +
				", dateAndTime=" + dateAndTime +
				", id=" + id +
				", familyName='" + familyName + '\'' +
				'}';
	}

	public int getGroupSize() {
		return groupSize;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public Long getId() {
		return id;
	}

	public String getFamilyName() {
		return familyName;
	}
}
