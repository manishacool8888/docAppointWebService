package com.docappoint.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docappoint.repository.CommonRepository;
import com.docappoint.responsebean.AuthResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoginService {
	
	@Autowired
	CommonRepository commonRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	public AuthResponseBean login(String username) {
		AuthResponseBean authResponse=null;
		try {
			
			authResponse = commonRepo.getLoginData(username);
			
		}catch(Exception e) {
			logger.error("Excepion while fetching login details for user: {} , exception message:{}"
					,username,e.getMessage());
		}
		return authResponse;
	}
}
