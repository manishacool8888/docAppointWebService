package com.docappoint.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docappoint.bean.UserAuthData;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.repository.CommonRepository;
import com.docappoint.repository.DoctorRepository;
import com.docappoint.repository.PatientRepository;
import com.docappoint.responsebean.AuthResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoginService {
	
	@Autowired
	CommonRepository commonRepo;
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	DoctorRepository doctorRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	public AuthResponseBean login(String username) {
		AuthResponseBean authResponse=null;
		String user_first_name="";
		
		try {
			UserAuthData userAuthData = commonRepo.fetchUserRoleData(username);
			
			if(ApplicationConstants.ROLE_PATIENT.equals(userAuthData.getUser_role())) {
				user_first_name = patientRepo.fetchPatientFirstName(username);
			}else {
				user_first_name = doctorRepo.fetchDoctorFirstName(username);
			}
			
			authResponse = new AuthResponseBean(username
											   ,userAuthData.getUser_role()
											   ,userAuthData.getIsEnabled()
											   ,user_first_name);
			
		}catch(Exception e) {
			logger.error("Excepion while fetching login details for user: {} , exception message:{}"
					,username,e.getMessage());
		}
		return authResponse;
	}
}
