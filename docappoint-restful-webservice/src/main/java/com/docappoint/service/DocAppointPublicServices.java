package com.docappoint.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docappoint.constants.ApplicationConstants;
import com.docappoint.repository.CommonRepository;
import com.docappoint.repository.DoctorRepository;
import com.docappoint.repository.PatientRepository;
import com.docappoint.requestbean.RegisterDoctorBean;
import com.docappoint.requestbean.RegisterPatientBean;
import com.docappoint.responsebean.RegistrationResponse;
import com.docappoint.responsebean.SpecialityList;

@Service
public class DocAppointPublicServices{
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	DoctorRepository docRepo;
	
	@Autowired
	CommonRepository commonRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(DocAppointPublicServices.class);
	
	public  RegistrationResponse registerPatient(RegisterPatientBean patientDetails) {
		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setUsername(patientDetails.getPatient_id());
		
		try {
			
			if(patientRepo.registerPatient(patientDetails)) {
				registrationResponse.setUser_role(ApplicationConstants.ROLE_PATIENT);
				registrationResponse.setRegistrationSuccess(true);
			}else {
				registrationResponse.setUser_role("");
				registrationResponse.setRegistrationSuccess(false);
			}
			
		}catch(Exception ex) {
			logger.error("Exception while registering patient with username:{}, message:{}"
					,patientDetails.getPatient_id(),ex.getMessage());
		}
		
		return registrationResponse;
	}
	
	public RegistrationResponse registerDoctor(RegisterDoctorBean doctorDetails) {
		
		RegistrationResponse registrationResponse = new RegistrationResponse();
		try {	
			
			registrationResponse.setUsername(doctorDetails.getDoctor_id());
			
			if(docRepo.registerDoctor(doctorDetails)) {
				registrationResponse.setUser_role(ApplicationConstants.ROLE_DOCTOR);
				registrationResponse.setRegistrationSuccess(true);
			}else {
				registrationResponse.setUser_role("");
				registrationResponse.setRegistrationSuccess(true);
			}
			
		}catch(Exception ex) {
			logger.error("Exception while registering doctor with username:{}, message:{}"
					,doctorDetails.getDoctor_id(),ex.getMessage());
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
	
	public List<SpecialityList> getSpecialityList(){
		List<SpecialityList> specialityList = null;
		
		try {
			specialityList = commonRepo.fetchSpeciality();
		}catch(Exception ex) {
			logger.error("Exception while fetching specialityList, message:"+ex.getMessage());
		}
		
		return specialityList;
	}
	
}
