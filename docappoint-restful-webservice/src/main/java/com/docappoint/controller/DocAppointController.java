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
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.requestbean.RegisterDoctorBean;
import com.docappoint.requestbean.RegisterPatientBean;
import com.docappoint.requestbean.TestPostRequest;
import com.docappoint.responsebean.RegistrationResponse;
import com.docappoint.responsebean.SpecialityList;
import com.docappoint.service.DocAppointPublicServices;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/api/public")
public class DocAppointController {
	
	@Autowired
	DocAppointPublicServices publicServices;
	
	@PostMapping(path="/register_patient", produces="application/json")
	public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientBean patientDetails) {
		
		RegistrationResponse registrationResponse = publicServices.registerPatient(patientDetails);
		return new ResponseEntity<RegistrationResponse>(registrationResponse,HttpStatus.OK);
	}
	
	@PostMapping(path="/register_doctor", produces="application/json")
	public ResponseEntity<?> registerDoctor(@RequestBody RegisterDoctorBean doctorDetails) {
		
		RegistrationResponse registrationResponse = publicServices.registerDoctor(doctorDetails);
		return new ResponseEntity<RegistrationResponse>(registrationResponse,HttpStatus.OK);
	}
	
	@GetMapping(path="/states")
	public ResponseEntity<?> getStates(){
		
		List<String> stateList = publicServices.getStateList();
		return new ResponseEntity<List<String>>(stateList,HttpStatus.OK);
	}
	
	@GetMapping(path="/city/{state}")
	public ResponseEntity<?> getcity(@PathVariable String state){
		
		List<String> cityList = publicServices.getCityList(state);
		return new ResponseEntity<List<String>>(cityList,HttpStatus.OK);
	}
	
	@GetMapping(path="/locality/{city}")
	public ResponseEntity<?> getLocality(@PathVariable String city){
		
		List<String> localityList = publicServices.getLocalityList(city);
		return new ResponseEntity<List<String>>(localityList,HttpStatus.OK);
	}
	
	@GetMapping(path="/speciality")
	public ResponseEntity<?> getSpeciality(){
		
		List<SpecialityList> specialityList = publicServices.getSpecialityList();
		return new ResponseEntity<List<SpecialityList>>(specialityList,HttpStatus.OK);
	}
	
	@PostMapping(path="/testPost", produces="application/json")
	public ResponseEntity<?> login(@RequestBody TestPostRequest testPost) {
		
		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setUsername(testPost.getName());
		registrationResponse.setUser_role(ApplicationConstants.ROLE_PATIENT);
		registrationResponse.setRegistrationSuccess(true);
		return new ResponseEntity<RegistrationResponse>(registrationResponse,HttpStatus.OK);
		
	}
	
}
