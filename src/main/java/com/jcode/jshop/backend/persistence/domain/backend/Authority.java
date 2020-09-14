package com.jcode.jshop.backend.persistence.domain.backend;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	/** The serial version UID for serializable classes. */
	private static final long serialVersionUID = 1L;
	
	private final String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}

}
