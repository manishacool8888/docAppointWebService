package com.docappoint.bean;

public class UserAuthData {

	private String username;
	private String isEnabled;
	private String user_role;

	public UserAuthData(String username,String isEnabled,String user_role) {
		this.username=username;
		this.user_role=user_role;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

}
