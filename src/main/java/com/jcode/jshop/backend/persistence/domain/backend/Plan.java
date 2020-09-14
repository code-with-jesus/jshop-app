package com.jcode.jshop.backend.persistence.domain.backend;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.jcode.jshop.enums.PlansEnum;

@Entity
public class Plan implements Serializable {

	/** The serial version UID for serializable classes. */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	private String name;
	
	/** Default constructor. */
	public Plan() {
		
	}
	
	public Plan(PlansEnum plansEnum) {
		this.id = plansEnum.getId();
		this.name = plansEnum.getPlanName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Plan other = (Plan) obj;
		return id == other.id;
	}

	
}
