package com.docappoint.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.docappoint.bean.DoctorProfileBean;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.repository.DoctorRepository;
import com.docappoint.requestbean.NewSlotDetails;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.ProfileUpdateResponse;
import com.docappoint.responsebean.ServiceResponse;
import com.docappoint.responsebean.SlotAvailability;
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
				 logger.info("profile updated successfully for doctorId:"+doctorProfile.getDoctor_id());
			 }else {
				 profileUpdateResponse.setProfileUpdated("N");
				 logger.info("profile update failed for doctorId:"+doctorProfile.getDoctor_id());
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
				logger.info("booking cancelled for bookingId:{}, doctorId:{}",bookingId,doctorId);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
				logger.info("booking cancel failed for bookingId:{}, doctorId:{}",bookingId,doctorId);
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
	
	 public List<SlotAvailability> getAvlSlots(String doctorId,Date date){
		 List<SlotAvailability> SlotAvlList = new ArrayList<SlotAvailability>();
		 
		 try {
			   
			 List<SlotDetails> allSlots = getAllSlots(doctorId);
			 List<Integer> bookedSlots = docRepo.fetchBookedSlots(doctorId, date);
			 
			 for(SlotDetails allSlotDtls : allSlots) {
				 
				 SlotAvailability slotAvl = new SlotAvailability();
				 
				 slotAvl.setSlot_id(allSlotDtls.getSlot_id());
				 slotAvl.setStart_time(allSlotDtls.getStart_time());
				 slotAvl.setEnd_time(allSlotDtls.getEnd_time());
				 slotAvl.setMeridiem_indicator(allSlotDtls.getMeridiem_indicator());
				 
				 if(bookedSlots.contains(allSlotDtls.getSlot_id())){
					 
					 slotAvl.setIsAvailable("N");
				 }else {
					 
					 slotAvl.setIsAvailable("Y");
				 }
				 
				 SlotAvlList.add(slotAvl);
			 }
		} catch (Exception ex) {
			logger.error("Exception while fetching available slots, message:{}",ex.getMessage());
		}
		 
		 return SlotAvlList;
	 }
	
	public ServiceResponse addSlot(NewSlotDetails newSlotDetails) {
		ServiceResponse response = new ServiceResponse();
		
		try {
			if(docRepo.addSlot(newSlotDetails)) {
				response.setMessage(ApplicationConstants.SUCCESS);
				logger.info("slot added successfully for doctorId:{}",newSlotDetails.getDoctor_id());
			}else {
				response.setMessage(ApplicationConstants.FAIL);
				logger.info("slot add failed for doctorId:{}",newSlotDetails.getDoctor_id());
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
				logger.info("slot deleted for slotId:{}, doctorId:{}",slotId,doctorId);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
				logger.info("slot delete failed for slotId:{}, doctorId:{}",slotId,doctorId);
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
				logger.info("account disabled for doctorId:"+doctorId);
			}else {
				response.setMessage(ApplicationConstants.FAIL);
				logger.info("account disable failed for doctorId:"+doctorId);
			}
		}catch(Exception ex) {
			logger.error("Exception while desabling account for doctorId:{},message:{}",doctorId,ex.getMessage());
		}
		return response;
	}

}
