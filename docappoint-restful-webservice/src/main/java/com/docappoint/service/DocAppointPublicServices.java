package com.docappoint.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docappoint.repository.CommonRepository;
import com.docappoint.repository.PatientRepository;
import com.docappoint.requestbean.RegisterPatientBean;
import com.docappoint.responsebean.PatientRegistrationResponse;

@Service
public class DocAppointPublicServices{
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	CommonRepository commonRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(DocAppointPublicServices.class);
	
	public  PatientRegistrationResponse registerPatient(RegisterPatientBean patientDetails) {
		PatientRegistrationResponse registrationResponse=null;
		
		try {
			
			registrationResponse = patientRepo.registerPatient(patientDetails);
			
		}catch(Exception ex) {
			logger.error("Exception while registering patient with username:{}, message:{}"
					,patientDetails.getPatient_id(),ex.getMessage());
		}
		
		return registrationResponse;
	}
	
	public List<String> getStateList() {
		List<String> StateList=null;
		
		try {
			StateList = commonRepo.fetchStates();
		}catch(Exception ex){
			logger.error("Exception while fetching state list, message:"+ex.getMessage());
		}
		return StateList;
	}
	
	public List<String> getCityList(String state) {
		List<String> cityList=null;
		
		try {
			cityList = commonRepo.fetchCity(state);
		}catch(Exception ex){
			logger.error("Exception while fetching city list, message:"+ex.getMessage());
		}
		return cityList;
	}
	
	public List<String> getLocalityList(String city) {
		List<String> localityList=null;
		
		try {
			localityList = commonRepo.fetchCityLocality(city);
		}catch(Exception ex){
			logger.error("Exception while fetching city locality list, message:"+ex.getMessage());
		}
		return localityList;
	}
	
}
