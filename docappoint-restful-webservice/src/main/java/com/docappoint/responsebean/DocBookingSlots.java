package com.docappoint.responsebean;

public class DocBookingSlots {

	private int    slot_id;
	private String start_time;
	private String end_time;
	private String meridiem_indicator;
	
	public int getSlot_id() {
		return slot_id;
	}
	
	public void setSlot_id(int slot_id) {
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
	
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("slot_id:"+getSlot_id()+",");
    	sb.append("start_time:"+getStart_time()+",");
    	sb.append("end_time"+getEnd_time()+",");
    	sb.append("meridiem_indicator"+getMeridiem_indicator());
    	sb.append("|");
   	
    	return sb.toString();
    }
}
