package com.docappoint.responsebean;

public class RegistrationResponse {

	private String username;
	private String user_role;
	private boolean isRegistrationSuccess;
	
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
	
	public boolean isRegistrationSuccess() {
		return isRegistrationSuccess;
	}
	public void setRegistrationSuccess(boolean isRegistrationSuccess) {
		this.isRegistrationSuccess = isRegistrationSuccess;
	}
	
}
