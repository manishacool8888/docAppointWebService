package com.docappoint.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.docappoint.bean.DoctorProfileBean;
import com.docappoint.requestbean.NewSlotDetails;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.ProfileUpdateResponse;
import com.docappoint.responsebean.ServiceResponse;
import com.docappoint.responsebean.SlotAvailability;
import com.docappoint.responsebean.SlotDetails;
import com.docappoint.service.DoctorService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/api/doctor")
public class DoctorController {
	
	@Autowired
	DoctorService docService;

	@GetMapping(path="/doctorProfile/{username}")
	public ResponseEntity<?> getDoctorProfile(@PathVariable String username){
		
		DoctorProfileBean doctorProfile = docService.getDoctorProfile(username);
		return new ResponseEntity<DoctorProfileBean>(doctorProfile,HttpStatus.OK);
	}
	
	@PostMapping(path="/updateProfile", produces="application/json")
	public ResponseEntity<?> updateDoctorProfile(@RequestBody DoctorProfileBean patientProfile) {
		
		ProfileUpdateResponse profileUpdateResponse = docService.updateDoctorProfile(patientProfile);
		return new ResponseEntity<ProfileUpdateResponse>(profileUpdateResponse,HttpStatus.OK);
	}
	
	@GetMapping(path="/bookings/{username}")
	public ResponseEntity<?> getAllBookings(@PathVariable String username){
		
		List<DocAppointBookings> bookingList = docService.getAllBookings(username);
		return new ResponseEntity<List<DocAppointBookings>>(bookingList,HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}/cancelBooking/{booking_id}")
	public ResponseEntity<?> cancelBooking(@PathVariable String username, @PathVariable long booking_id){
		
		ServiceResponse response = docService.cancelBooking(username, booking_id);
		
		return new ResponseEntity<ServiceResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(path="/slots/{username}")
	public ResponseEntity<?> getAllSlots(@PathVariable String username){
		List<SlotDetails> slotDetailList = docService.getAllSlots(username);
		
		return new ResponseEntity<List<SlotDetails>>(slotDetailList,HttpStatus.OK);
	}
	
//	@GetMapping(path="/slotsAvl/{username}/date/{date}")
//	public ResponseEntity<?> getSlotAvailability(@PathVariable String username
//			                                    ,@PathVariable Date date){
//		
//		List<SlotAvailability> avlSlotList = docService.getDoctorProfile(username);
//		return new ResponseEntity<List<SlotAvailability>>(avlSlotList,HttpStatus.OK);
//	}
	
	@PostMapping(path="/addSlot", produces="application/json")
	public ResponseEntity<?> addSlot(@RequestBody NewSlotDetails newSlotDetails) {
		
		ServiceResponse response  = docService.addSlot(newSlotDetails);
		return new ResponseEntity<ServiceResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}/deleteSlot/{slot_id}")
	public ResponseEntity<?> deleteSlot(@PathVariable String username, @PathVariable long slot_id){
		
		ServiceResponse response  = docService.deleteSlot(username, slot_id);
		return new ResponseEntity<ServiceResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(path="/disableAccount/{username}")
	public ResponseEntity<?> disableAccount(@PathVariable String username){
		
		ServiceResponse response  = docService.disableAccount(username);
		return new ResponseEntity<ServiceResponse>(response,HttpStatus.OK);
	}
	
}
