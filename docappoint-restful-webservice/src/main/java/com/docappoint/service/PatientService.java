package com.docappoint.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.docappoint.bean.PatientProfileBean;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.repository.PatientRepository;
import com.docappoint.requestbean.BookingDetails;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.DoctorSearchDetails;
import com.docappoint.responsebean.ProfileUpdateResponse;
import com.docappoint.responsebean.ServiceResponse;

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
	
	public List<DoctorSearchDetails> searchDoctors(String state,String city,String locality,String speciality){
		List<DoctorSearchDetails> doctorSearchDetails = null;
		
		if(StringUtils.isNotBlank(state) && StringUtils.isNotBlank(city) 
				&& StringUtils.isNotBlank(locality) && StringUtils.isNotBlank(speciality)) {
			
			logger.info("fetchDoctorsByStateCityLocSpec is called");
			doctorSearchDetails = patientRepo.fetchDoctorsByStateCityLocSpec(state, city, locality, speciality);
			
			logger.info("DoctorSearchDetails from repo:"+doctorSearchDetails);
			
		}else if(StringUtils.isNotBlank(state) && StringUtils.isNotBlank(city) 
				&& StringUtils.isNotBlank(locality) && StringUtils.isBlank(speciality)) {
			
			logger.info("fetchDoctorsByStateCityLocality is called");
			doctorSearchDetails = patientRepo.fetchDoctorsByStateCityLocality(state, city, locality);
			
			logger.info("DoctorSearchDetails from repo:"+doctorSearchDetails);
			
		}else if(StringUtils.isNotBlank(state) && StringUtils.isNotBlank(city) 
				    && StringUtils.isBlank(locality) && StringUtils.isNotBlank(speciality)) {
			
			logger.info("fetchDoctorsByStateCitySpec is called");
			doctorSearchDetails = patientRepo.fetchDoctorsByStateCitySpec(state, city, speciality);
			
			logger.info("DoctorSearchDetails from repo:"+doctorSearchDetails);
			
		}else if(StringUtils.isNotBlank(state) && StringUtils.isNotBlank(city) 
			    && StringUtils.isBlank(locality) && StringUtils.isBlank(speciality)) {
			
			logger.info("fetchDoctorsByStateCity is called");
			doctorSearchDetails = patientRepo.fetchDoctorsByStateCity(state, city);
			
			logger.info("DoctorSearchDetails from repo:"+doctorSearchDetails);
			
		}else if(StringUtils.isNotBlank(state) && StringUtils.isBlank(city) 
				    && StringUtils.isBlank(locality) && StringUtils.isNotBlank(speciality)) {
			
			logger.info("fetchDoctorDetailsByStateSpec is called");
			doctorSearchDetails = patientRepo.fetchDoctorDetailsByStateSpec(state, speciality);
			
			logger.info("DoctorSearchDetails from repo:"+doctorSearchDetails);
			
		}else if(StringUtils.isNotBlank(state) && StringUtils.isBlank(city) 
			    && StringUtils.isBlank(locality) && StringUtils.isBlank(speciality)) {
			
			logger.info("fetchDoctorDetailsByState is called");
			doctorSearchDetails = patientRepo.fetchDoctorDetailsByState(state);
			
			logger.info("DoctorSearchDetails from repo:"+doctorSearchDetails);
		}
		
		return doctorSearchDetails;
	}
	
	public ServiceResponse bookAppointment(BookingDetails bookingDetails) {
		ServiceResponse response = new ServiceResponse();
		
		try {
			if(patientRepo.bookAppointment(bookingDetails)) {
				response.setMessage(ApplicationConstants.SUCCESS);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
			}
			
		}catch(Exception ex) {
			logger.error("Exception while booking appointment for doctor_id:{},patient_id:{},slot_id:{}, mesage:{}"
					,bookingDetails.getDoctor_id()
					,bookingDetails.getPatient_id()
					,bookingDetails.getSlot_id()
					,ex.getMessage());
		}
		
		return response;
	}
	
	public List<DocAppointBookings> getAllBookings(String patientId){
		List<DocAppointBookings> docBookingList = null;
		
		try {
			docBookingList = patientRepo.fetchAllBookings(patientId);
		}catch(Exception ex) {
			logger.error("Exception while fetching bookings for PatientId:{} ,message{} :",patientId,ex.getMessage());
		}
		return docBookingList;
	}
	
	public ServiceResponse cancelBooking(String patientId,long bookingId) {
		ServiceResponse response = new ServiceResponse();
		
		try {
			if(patientRepo.cancelBooking(patientId, bookingId)) {
				response.setMessage(ApplicationConstants.SUCCESS);
				logger.info("booking cancelled for bookingId:{}, PatientId:{}",bookingId,patientId);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
				logger.info("booking cancel failed for bookingId:{}, PatientId:{}",bookingId,patientId);
			}
		}catch(Exception ex){
			logger.error("Exception while cancelling booking for PatientId:{}, BookingId:{}, message:{}",patientId,bookingId,ex.getMessage());
		}
		return response;
	}
	
}
