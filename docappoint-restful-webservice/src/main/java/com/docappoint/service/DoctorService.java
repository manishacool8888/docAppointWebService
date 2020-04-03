package com.docappoint.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.docappoint.bean.DoctorProfileBean;
import com.docappoint.repository.DoctorRepository;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.ProfileUpdateResponse;

@Service
public class DoctorService {
	
	@Autowired DoctorRepository docRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
	
	public ProfileUpdateResponse updateDoctorProfile(DoctorProfileBean doctorProfile) {
		ProfileUpdateResponse profileUpdateResponse=null;
		
		try {
			profileUpdateResponse = docRepo.updateDoctorProfile(doctorProfile);
		}catch(Exception ex) {
			logger.error("Exception while updating doctor profile {}",ex.getMessage());
		}
		
		return profileUpdateResponse;
	}
	
	public DoctorProfileBean getDoctorProfile(String doctorId) {
		DoctorProfileBean doctorProfile = null;
		
		try {
			doctorProfile = docRepo.fetchDoctorProfile(doctorId);
		}catch(Exception ex) {
			logger.error("Exception while fetching profile for doctor:{}, message:{}",doctorId,ex.getMessage());
			ex.printStackTrace();
		}
		
		return doctorProfile;
	}
	
	public List<DocAppointBookings> getAllBookings(String doctorId){
		List<DocAppointBookings> docBookingList = null;
		
		try {
			docBookingList = docRepo.fetchAllBookings(doctorId);
		}catch(Exception ex) {
			logger.error("Exception while fetching bookings for doctor:{} ,message{} :",doctorId,ex.getMessage());
		}
		return docBookingList;
	}

}
