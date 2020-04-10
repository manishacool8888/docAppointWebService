package com.docappoint.controller;

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
import com.docappoint.bean.PatientProfileBean;
import com.docappoint.requestbean.BookingDetails;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.DoctorSearchDetails;
import com.docappoint.responsebean.ProfileUpdateResponse;
import com.docappoint.responsebean.ServiceResponse;
import com.docappoint.service.PatientService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/api/patient")
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@GetMapping(path="/patientProfile/{username}")
	public ResponseEntity<?> getPatientProfile(@PathVariable String username){
		
		PatientProfileBean patientProfile = patientService.getPatientProfile(username);
		return new ResponseEntity<PatientProfileBean>(patientProfile,HttpStatus.OK);
	}
	
	@PostMapping(path="/updateProfile", produces="application/json")
	public ResponseEntity<?> updatePatientProfile(@RequestBody PatientProfileBean patientProfile) {
		
		ProfileUpdateResponse profileUpdateResponse = patientService.updatePatientProfile(patientProfile);
		return new ResponseEntity<ProfileUpdateResponse>(profileUpdateResponse,HttpStatus.OK);
	}
	
	@GetMapping(path="/Doctors/state/{state}/city/{city}/locality/{locality}/speciality/{speciality}/search")
	public ResponseEntity<?> searchDoctors(@PathVariable String state
			                              ,@PathVariable String city
			                              ,@PathVariable String locality
			                              ,@PathVariable String speciality){
		
		List<DoctorSearchDetails> doctorSearchDetails = patientService.searchDoctors(state, city, locality,speciality);
		return new ResponseEntity<List<DoctorSearchDetails>>(doctorSearchDetails,HttpStatus.OK);
	}
	
//	@PostMapping(path="/bookAppointment", produces="application/json")
//	public ResponseEntity<?> bookAppointment(@RequestBody BookingDetails bookingDetails) {
//		
//		ServiceResponse serviceResponse = patientService.updatePatientProfile(patientProfile);
//		return new ResponseEntity<ServiceResponse>(serviceResponse,HttpStatus.OK);
//	}
	
	@GetMapping(path="/bookings/{username}")
	public ResponseEntity<?> getAllBookings(@PathVariable String username){
		
		List<DocAppointBookings> bookingList = patientService.getAllBookings(username);
		return new ResponseEntity<List<DocAppointBookings>>(bookingList,HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}/cancelBooking/{booking_id}")
	public ResponseEntity<?> cancelBooking(@PathVariable String username, @PathVariable long booking_id){
		
		ServiceResponse response = patientService.cancelBooking(username, booking_id);
		
		return new ResponseEntity<ServiceResponse>(response,HttpStatus.OK);
	}
	
}
