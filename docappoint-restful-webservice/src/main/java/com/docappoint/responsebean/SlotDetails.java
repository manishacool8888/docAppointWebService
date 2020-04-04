package com.docappoint.responsebean;

public class SlotDetails {

	private String slot_id;
	private String start_time;
	private String end_time;
	private String meridiem_indicator;
	
	public String getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(String slot_id) {
		this.slot_id = slot_id;
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
