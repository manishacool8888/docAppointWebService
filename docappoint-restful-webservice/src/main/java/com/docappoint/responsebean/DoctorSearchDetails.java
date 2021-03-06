package com.docappoint.responsebean;

import java.util.Date;

public class DoctorSearchDetails {

	private String doctor_id;
	private String first_name;
    private String gender;
    private  Date practicing_from;
    private int consultation_fee;
    private String speciality_name;
    private String state;
    private String city;
    private String locality;
    
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getPracticing_from() {
		return practicing_from;
	}
	public void setPracticing_from(Date practicing_from) {
		this.practicing_from = practicing_from;
	}
	public int getConsultation_fee() {
		return consultation_fee;
	}
	public void setConsultation_fee(int consultation_fee) {
		this.consultation_fee = consultation_fee;
	}
	public String getSpeciality_name() {
		return speciality_name;
	}
	public void setSpeciality_name(String speciality_name) {
		this.speciality_name = speciality_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("doctor_id:"+getDoctor_id()+",");
    	sb.append("first_name:"+getFirst_name()+",");
    	sb.append("gender"+getGender()+",");
    	sb.append("practicing_from"+getPracticing_from()+",");
    	sb.append("consultation_fee"+getConsultation_fee()+",");
    	sb.append("speciality_name"+getSpeciality_name()+",");
    	sb.append("state"+getState()+",");
    	sb.append("city"+getCity()+",");
    	sb.append("locality"+getLocality());
    	sb.append("|");
    	
    	return sb.toString();
    }
    
}
