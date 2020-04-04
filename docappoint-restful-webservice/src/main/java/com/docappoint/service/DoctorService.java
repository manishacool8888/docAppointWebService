package com.docappoint.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.docappoint.bean.DoctorProfileBean;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.repository.DoctorRepository;
import com.docappoint.requestbean.NewSlotDetails;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.ProfileUpdateResponse;
import com.docappoint.responsebean.ServiceResponse;
import com.docappoint.responsebean.SlotDetails;

@Service
public class DoctorService {
	
	@Autowired DoctorRepository docRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
	
	public ProfileUpdateResponse updateDoctorProfile(DoctorProfileBean doctorProfile) {
		ProfileUpdateResponse profileUpdateResponse= new ProfileUpdateResponse();
		profileUpdateResponse.setUsername(doctorProfile.getDoctor_id());
		try {
			
			 if(docRepo.updateDoctorProfile(doctorProfile)) {
				 
				 profileUpdateResponse.setProfileUpdated("Y");
			 }else {
				 profileUpdateResponse.setProfileUpdated("N");
			 }
			 
		}catch(Exception ex) {
			logger.error("Exception while updating profile for doctorId:{}, message:{}",doctorProfile.getDoctor_id(),ex.getMessage());
		}
		
		return profileUpdateResponse;
	}
	
	public DoctorProfileBean getDoctorProfile(String doctorId) {
		DoctorProfileBean doctorProfile = null;
		
		try {
			doctorProfile = docRepo.fetchDoctorProfile(doctorId);
		}catch(Exception ex) {
			logger.error("Exception while fetching profile for doctorId:{}, message:{}",doctorId,ex.getMessage());
			ex.printStackTrace();
		}
		
		return doctorProfile;
	}
	
	public List<DocAppointBookings> getAllBookings(String doctorId){
		List<DocAppointBookings> docBookingList = null;
		
		try {
			docBookingList = docRepo.fetchAllBookings(doctorId);
		}catch(Exception ex) {
			logger.error("Exception while fetching bookings for doctorId:{} ,message{} :",doctorId,ex.getMessage());
		}
		return docBookingList;
	}
	
	public ServiceResponse cancelBooking(String doctorId,long bookingId) {
		ServiceResponse response = new ServiceResponse();
		
		try {
			if(docRepo.cancelBooking(doctorId, bookingId)) {
				response.setMessage(ApplicationConstants.SUCCESS);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
			}
		}catch(Exception ex){
			logger.error("Exception while deleting booking for doctorId:{}, BookingId:{}, message:{}",doctorId,bookingId,ex.getMessage());
		}
		return response;
	}
	
	public List<SlotDetails> getAllSlots(String doctorId){
		List<SlotDetails> slotList =null;
		
		try {
			slotList = docRepo.fetchAllSlots(doctorId);
		}catch(Exception ex) {
			logger.error("Exception while fetching all flots for doctorId:{}, message:{}",doctorId,ex.getMessage());
		}
		return slotList;
	}
	
	public ServiceResponse addSlot(NewSlotDetails newSlotDetails) {
		ServiceResponse response = new ServiceResponse();
		
		try {
			if(docRepo.addSlot(newSlotDetails)) {
				response.setMessage(ApplicationConstants.SUCCESS);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
			}
		}catch(Exception ex) {
			logger.error("Exception while adding new slot for doctorId:{}, message:{}",newSlotDetails.getDoctor_id(),ex.getMessage());
		}
		return response;
	}
	
	public ServiceResponse deleteSlot(String doctorId,long slotId) {
		ServiceResponse response = new ServiceResponse();
		
		try {
			if(docRepo.deleteSlot(doctorId, slotId)) {
				response.setMessage(ApplicationConstants.SUCCESS);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
			}
		}catch(Exception ex) {
			logger.error("Exception while deleting slot for doctorId:{},slotId:{}, message:{}",doctorId,slotId,ex.getMessage());
		}
		return response;
	}
	
	public ServiceResponse disableAccount(String doctorId) {
		ServiceResponse response = new ServiceResponse();
		
		try {
			if(docRepo.disableAccount(doctorId)) {
				response.setMessage(ApplicationConstants.SUCCESS);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
			}
		}catch(Exception ex) {
			logger.error("Exception while desabling account for doctorId:{},message:{}",doctorId,ex.getMessage());
		}
		return response;
	}

}
