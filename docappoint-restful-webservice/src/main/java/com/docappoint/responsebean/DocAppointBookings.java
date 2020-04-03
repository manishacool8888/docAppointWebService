package com.docappoint.responsebean;

import java.util.Date;

public class DocAppointBookings {

	private int booking_id;
    private String first_name;
    private String speciality_name;
    private Date booking_date;
    private String start_time;
    private String end_time;
    private String meridiem_indicator;
    private String symptom_desc;
    private String cancelled;
    private String cancelled_by;
    
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getSpeciality_name() {
		return speciality_name;
	}
	public void setSpeciality_name(String speciality_name) {
		this.speciality_name = speciality_name;
	}
	public Date getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getMeridiem_indicator() {
		return meridiem_indicator;
	}
	public void setMeridiem_indicator(String meridiem_indicator) {
		this.meridiem_indicator = meridiem_indicator;
	}
	public String getSymptom_desc() {
		return symptom_desc;
	}
	public void setSymptom_desc(String symptom_desc) {
		this.symptom_desc = symptom_desc;
	}
	public String getCancelled() {
		return cancelled;
	}
	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}
	public String getCancelled_by() {
		return cancelled_by;
	}
	public void setCancelled_by(String cancelled_by) {
		this.cancelled_by = cancelled_by;
	}
    
}
