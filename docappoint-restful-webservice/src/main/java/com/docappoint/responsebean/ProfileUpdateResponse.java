package com.docappoint.responsebean;

public class ProfileUpdateResponse {

	private String username;
    private String profileUpdated; // Y or N
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfileUpdated() {
		return profileUpdated;
	}
	public void setProfileUpdated(String profileUpdated) {
		this.profileUpdated = profileUpdated;
	}
}
