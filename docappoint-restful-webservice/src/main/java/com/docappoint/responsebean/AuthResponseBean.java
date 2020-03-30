package com.docappoint.responsebean;

public class AuthResponseBean {

	private String username;
	private String user_role;
	private String isEnabled;
	private String user_first_name;
	
	public AuthResponseBean(String username,String user_role,String isEnabled,String user_first_name) {
		this.username=username;
		this.user_role=user_role;
		this.isEnabled=isEnabled;
		this.user_first_name=user_first_name;
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

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
}
