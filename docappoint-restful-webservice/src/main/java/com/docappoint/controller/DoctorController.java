package com.docappoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.docappoint.bean.DoctorProfileBean;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.ProfileUpdateResponse;
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
	public ResponseEntity<?> getAllBookings(@PathVariable String doctorId){
		
		List<DocAppointBookings> bookingList = docService.getAllBookings(doctorId);
		return new ResponseEntity<List<DocAppointBookings>>(bookingList,HttpStatus.OK);
	}
	
}
