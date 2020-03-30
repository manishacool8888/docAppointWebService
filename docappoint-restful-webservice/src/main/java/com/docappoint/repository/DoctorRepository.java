package com.docappoint.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.docappoint.constants.DbQueryConstant;

@Repository
public class DoctorRepository {

	private static final Logger logger = LoggerFactory.getLogger(DoctorRepository.class);
	
	public String fetchDoctorFirstName(String doctor_id) {
		logger.info("fetching Doctor First name");
		logger.info("queryFetchStates:"+DbQueryConstant.queryFetchDoctorFirstName);
		String doctor_fist_name="";
		
		
		return doctor_fist_name;
	}
}
