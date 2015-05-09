package com.coptertours.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coptertours.options.Role;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "APP_USER")
public class User extends BaseDomain {
	@Id
	@GeneratedValue
	@Expose
	private Long id;
	@Expose
	private String userId;
	@Expose
	private String firstName;
	@Expose
	private String lastName;
	@ManyToOne(targetEntity = Rating.class, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "RATING_ID")
	@Expose
	private Rating rating;
	@Expose
	private String username;
	@Expose
	private String password;

	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "ROLE")
	@Expose
	private Role role;

	@Override
	public User clone() {
		try {
			// shallow copy
			return (User) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Rating getRating() {
		return rating;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDisplayName() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", {rating=" + rating + "}, username=" + username + ", password=" + password + "]";
	}

	public Role getRole() {
		return role;
	}

	public void clearRole() {
		this.role = null;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
