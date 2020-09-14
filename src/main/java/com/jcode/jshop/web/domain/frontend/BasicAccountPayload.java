package com.jcode.jshop.web.domain.frontend;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class BasicAccountPayload implements Serializable {

	/** The serial version UID for Serializable classes. */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String confirmPassword;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	private String description;
	
	@NotNull
	private String phoneNumber;
	
	@NotNull
	private String country;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		BasicAccountPayload other = (BasicAccountPayload) obj;
		return username != null ? username.equals(other.username) : other.username == null;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("BasicAccountPayload{");
		sb.append("email='").append(email).append('\'');
		sb.append(", username='").append(username).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", confirmPassword='").append(confirmPassword).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", phoneNumber='").append(phoneNumber).append('\'');
		sb.append(", country='").append(country).append('\'');
		sb.append("}");
		return sb.toString();
	}

	
}
