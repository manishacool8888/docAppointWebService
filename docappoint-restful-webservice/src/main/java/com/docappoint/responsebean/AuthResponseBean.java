package com.docappoint.responsebean;

public class AuthResponseBean {

	private String username;
	private String user_role;

	public AuthResponseBean(String username,String user_role) {
		this.username=username;
		this.user_role=user_role;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
}
