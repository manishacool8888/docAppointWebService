package com.docappoint.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.docappoint.bean.PatientProfileBean;
import com.docappoint.repository.PatientRepository;
import com.docappoint.responsebean.ProfileUpdateResponse;

@Service
public class PatientService {

	@Autowired 
	PatientRepository patientRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
	
	public ProfileUpdateResponse updatePatientProfile(PatientProfileBean patientProfile) {
		ProfileUpdateResponse profileUpdateResponse = new ProfileUpdateResponse();
		profileUpdateResponse.setUsername(patientProfile.getPatient_id());
		
		try {
			if(patientRepo.updatePatientProfile(patientProfile)) {
				profileUpdateResponse.setProfileUpdated("Y");
			}else {
				profileUpdateResponse.setProfileUpdated("N");
			}
		}catch(Exception ex) {
			logger.error("Exception while updating patient profile {}",ex.getMessage());
		}
		
		return profileUpdateResponse;
	}
	
	public PatientProfileBean getPatientProfile(String patientId) {
		PatientProfileBean patientProfile = null;
		
		try {
			patientProfile = patientRepo.fetchPatientProfile(patientId);
		}catch(Exception ex) {
			logger.error("Exception while fetching patient profile :"+ex.getMessage());
			ex.printStackTrace();
		}
		
		return patientProfile;
	}
	
}
