package com.docappoint.controller;

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
import com.docappoint.bean.PatientProfileBean;
import com.docappoint.responsebean.ProfileUpdateResponse;
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
}
