package com.docappoint.requestbean;

import java.util.Date;

public class BookingDetails {
	
	private String patient_id;
    private String doctor_id;
    private String slot_id;
    private Date booking_date;
    private String symptom_desc;
    
	public String getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getDoctor_id() {
		return doctor_id;
	}
	
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	public String getSlot_id() {
		return slot_id;
	}
	
	public void setSlot_id(String slot_id) {
		this.slot_id = slot_id;
	}
	
	public Date getBooking_date() {
		return booking_date;
	}
	
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	
	public String getSymptom_desc() {
		return symptom_desc;
	}
	
	public void setSymptom_desc(String symptom_desc) {
		this.symptom_desc = symptom_desc;
	}
    
}
