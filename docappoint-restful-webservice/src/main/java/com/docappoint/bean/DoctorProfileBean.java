package com.docappoint.bean;

import java.util.Date;

public class DoctorProfileBean {

	private String doctor_id;
	private String first_name;
	private String last_name;
    public  Date   date_of_birth;
    private String gender;
    public  Date   practicing_from;
    public  String speciality_name;
    public  int    consultation_fee;
    private String address_line_one;
    private String address_line_two;
    private String state;
    private String city;
    private String locality;
    private String pincode;
    private String primary_mobile_number;
    private String alternate_mobile_number;
    private String alternate_email_id;
    private String institute_name;
    private Date   procurement_date;
    
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
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
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
	
	public String getSpeciality_name() {
		return speciality_name;
	}
	
	public void setSpeciality_name(String speciality_name) {
		this.speciality_name = speciality_name;
	}
	
	public int getConsultation_fee() {
		return consultation_fee;
	}
	
	public void setConsultation_fee(int consultation_fee) {
		this.consultation_fee = consultation_fee;
	}
	
	public String getAddress_line_one() {
		return address_line_one;
	}
	
	public void setAddress_line_one(String address_line_one) {
		this.address_line_one = address_line_one;
	}
	
	public String getAddress_line_two() {
		return address_line_two;
	}
	
	public void setAddress_line_two(String address_line_two) {
		this.address_line_two = address_line_two;
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
	
	public String getPincode() {
		return pincode;
	}
	
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getPrimary_mobile_number() {
		return primary_mobile_number;
	}
	
	public void setPrimary_mobile_number(String primary_mobile_number) {
		this.primary_mobile_number = primary_mobile_number;
	}
	
	public String getAlternate_mobile_number() {
		return alternate_mobile_number;
	}
	
	public void setAlternate_mobile_number(String alternate_mobile_number) {
		this.alternate_mobile_number = alternate_mobile_number;
	}
	
	public String getAlternate_email_id() {
		return alternate_email_id;
	}
	
	public void setAlternate_email_id(String alternate_email_id) {
		this.alternate_email_id = alternate_email_id;
	}
	
	public String getInstitute_name() {
		return institute_name;
	}
	
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	
	public Date getProcurement_date() {
		return procurement_date;
	}
	
	public void setProcurement_date(Date procurement_date) {
		this.procurement_date = procurement_date;
	}
    
}
