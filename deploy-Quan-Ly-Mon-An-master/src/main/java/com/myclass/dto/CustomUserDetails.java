package com.myclass.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private String fullname;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		fullname = username;
	}

	// ThÃªm vÃ o má»™t sá»‘ thÆ°á»£c tÃ­nh á»Ÿ Ä‘Ã¢y
	// VÃ­ dá»¥: fullname, avatar,...
	
}
