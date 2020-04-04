package com.docappoint.requestbean;

public class NewSlotDetails {
	
	private String doctor_id;
	private String start_time;
    private String end_time;
    private String meridiem_indicator;
    
	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
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
    
    
    
}
