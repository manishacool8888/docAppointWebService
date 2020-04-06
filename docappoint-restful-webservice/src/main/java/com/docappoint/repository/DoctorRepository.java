package com.docappoint.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.docappoint.bean.DoctorProfileBean;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.constants.DbQueryConstant;
import com.docappoint.requestbean.NewSlotDetails;
import com.docappoint.requestbean.RegisterDoctorBean;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.SlotDetails;
import com.docappoint.responsebean.SpecialityList;

@Repository
public class DoctorRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorRepository.class);
	
	public String fetchDoctorFirstName(String doctor_id) {
		logger.info("fetching Doctor First name for doctorId:"+doctor_id);
		logger.info("queryFetchStates:"+DbQueryConstant.queryFetchDoctorFirstName);
		
		String doctor_fist_name="";
		doctor_fist_name = (String) jdbcTemplate.queryForObject(DbQueryConstant.queryFetchDoctorFirstName
																,new Object[] {doctor_id}
																,String.class);
		
		return doctor_fist_name;
	}
	
	@Transactional
	public boolean registerDoctor(RegisterDoctorBean doctorDetails) {
		
		logger.info("Entering registerDoctor for user:{}",doctorDetails.getDoctor_id());
		logger.info("queryInsertUsers:{}",DbQueryConstant.queryInsertUsers);
		logger.info("queryInsertAuthorities:{}",DbQueryConstant.queryInsertAuthorities);
		logger.info("queryInsetDoctorDetails:{}",DbQueryConstant.queryInsetDoctorDetails);
		logger.info("queryInsertDoctorContact:{}",DbQueryConstant.queryInsertDoctorContact);
		logger.info("queryInsertDoctorQualification:{}",DbQueryConstant.queryInsertDoctorQualification);
		logger.info("queryInsertDoctorSpeciality:{}",DbQueryConstant.queryInsertDoctorSpeciality);
		
		boolean isDoctorRegistered = false;
		
		int usersInsertSuccess = jdbcTemplate.update(DbQueryConstant.queryInsertUsers
				                                   ,doctorDetails.getDoctor_id()
				                                   ,doctorDetails.getPassword()
				                                   ,"Y");
		
		int authoritiesInsert = jdbcTemplate.update(DbQueryConstant.queryInsertAuthorities
														  ,doctorDetails.getDoctor_id()
														  ,ApplicationConstants.ROLE_DOCTOR);
		
		int doctorDetailsInsert = jdbcTemplate.update(DbQueryConstant.queryInsetDoctorDetails
															 ,doctorDetails.getDoctor_id()
															 ,doctorDetails.getFirst_name()
															 ,doctorDetails.getLast_name()
															 ,doctorDetails.getDate_of_birth()
															 ,doctorDetails.getGender()
															 ,doctorDetails.getPracticing_from()
															 ,doctorDetails.getConsultation_fee());
		
		int doctorContactInsert = jdbcTemplate.update(DbQueryConstant.queryInsertDoctorContact
															 ,doctorDetails.getDoctor_id()
															 ,doctorDetails.getAddress_line_one()
															 ,doctorDetails.getAddress_line_two()
															 ,doctorDetails.getState()
															 ,doctorDetails.getCity()
															 ,doctorDetails.getLocality()
															 ,doctorDetails.getPincode()
															 ,doctorDetails.getPrimary_mobile_number()
															 ,doctorDetails.getAlternate_mobile_number()
															 ,doctorDetails.getAlternate_email_id());
		
		int doctorQualificationInsert = jdbcTemplate.update(DbQueryConstant.queryInsertDoctorQualification
															,doctorDetails.getDoctor_id()
															,doctorDetails.getInstitute_name()
															,doctorDetails.getProcurement_date());
		
		int doctorSpecialityInsert = jdbcTemplate.update(DbQueryConstant.queryInsertDoctorSpeciality
														,doctorDetails.getDoctor_id()
														,doctorDetails.getSpeciality_name());
		
		if(usersInsertSuccess>0 && authoritiesInsert>0 
				&& doctorDetailsInsert>0  && doctorContactInsert>0
				&& doctorQualificationInsert>0 && doctorSpecialityInsert>0) {
			
			isDoctorRegistered = true;
		}
		
		return isDoctorRegistered;
	}
	
	@Transactional
	public boolean updateDoctorProfile(DoctorProfileBean doctorDetails) {
		
		logger.info("Entering update profile for doctor:{}",doctorDetails.getDoctor_id());
		
		logger.info("queryUpdateDoctorDetails:{}",DbQueryConstant.queryUpdateDoctorDetails);
		logger.info("queryUpdateDoctorContact:{}",DbQueryConstant.queryUpdateDoctorContact);
		logger.info("queryUpdateDoctorQualification:{}",DbQueryConstant.queryUpdateDoctorQualification);
		logger.info("queryUpdateDoctorSpeciality:{}",DbQueryConstant.queryUpdateDoctorSpeciality);
		
		boolean isProfileUpdated = false;
		
		int doctorDetailsUpdate = jdbcTemplate.update(DbQueryConstant.queryUpdateDoctorDetails
													 ,doctorDetails.getFirst_name()
													 ,doctorDetails.getLast_name()
													 ,doctorDetails.getDate_of_birth()
													 ,doctorDetails.getGender()
													 ,doctorDetails.getPracticing_from()
													 ,doctorDetails.getConsultation_fee()
													 ,doctorDetails.getDoctor_id());

		int doctorContactUpdate = jdbcTemplate.update(DbQueryConstant.queryUpdateDoctorContact
													 ,doctorDetails.getAddress_line_one()
													 ,doctorDetails.getAddress_line_two()
													 ,doctorDetails.getState()
													 ,doctorDetails.getCity()
													 ,doctorDetails.getLocality()
													 ,doctorDetails.getPincode()
													 ,doctorDetails.getPrimary_mobile_number()
													 ,doctorDetails.getAlternate_mobile_number()
													 ,doctorDetails.getAlternate_email_id()
													 ,doctorDetails.getDoctor_id());
		
		int doctorQualificationUpdate = jdbcTemplate.update(DbQueryConstant.queryUpdateDoctorQualification
														   ,doctorDetails.getInstitute_name()
														   ,doctorDetails.getProcurement_date()
														   ,doctorDetails.getDoctor_id());
		
		int doctorSpecialityUpdate = jdbcTemplate.update(DbQueryConstant.queryUpdateDoctorSpeciality
														,doctorDetails.getSpeciality_name()
														,doctorDetails.getDoctor_id());
		
		if(doctorDetailsUpdate>0  && doctorContactUpdate>0 
			&& doctorQualificationUpdate>0 && doctorSpecialityUpdate>0) {
			
			isProfileUpdated = true;	
		}
		
		return isProfileUpdated;
	}
	
	public DoctorProfileBean fetchDoctorProfile(String doctorId) {
		DoctorProfileBean doctorProfileBean = null;
		
		logger.info("Entering get Doctor Profile for user:{}",doctorId);
		logger.info("queryFetchDoctorProfile:{}",DbQueryConstant.queryFetchDoctorProfile);

		doctorProfileBean =  (DoctorProfileBean) jdbcTemplate.queryForObject(DbQueryConstant.queryFetchDoctorProfile
														 ,new Object[] {doctorId,doctorId,doctorId,doctorId}
														 ,new BeanPropertyRowMapper<DoctorProfileBean>(DoctorProfileBean.class));
		
//		testDataList = (ArrayList<TestDataDao>) jdbcTemplate.query(query, new BeanPropertyRowMapper(TestDataDao.class));	
		
		return doctorProfileBean;
	}
	
	public List<DocAppointBookings> fetchAllBookings(String doctorId){
		logger.info("Entering fetch All Booking for doctorId:{}",doctorId);
		logger.info("queryFetchAllDoctorBookings:{}",DbQueryConstant.queryFetchAllDoctorBookings);
		List<DocAppointBookings> bookingList = new ArrayList<DocAppointBookings>();
		
		try {
			bookingList = (ArrayList<DocAppointBookings>) jdbcTemplate.query
					(DbQueryConstant.queryFetchAllDoctorBookings
					,new Object[] {doctorId}
                    ,new BeanPropertyRowMapper<DocAppointBookings>(DocAppointBookings.class));
			
		}catch(DataAccessException de) {
			logger.error("the query failed for fetchAllBookings, message:{}",de.getMessage());
		}
		
		return bookingList;
	}
	
	public boolean cancelBooking(String doctorId,long bookingId) {
		boolean isBookingDeleted = false;
		
		logger.info("Entering cancal Booking for doctorId:{}",doctorId);
		logger.info("queryCancelBooking:{}",DbQueryConstant.queryCancelBooking);
		
		int bookingCancelled = jdbcTemplate.update(DbQueryConstant.queryCancelBooking
				 								   ,"Y"
				 								   ,ApplicationConstants.DOCTOR
				 								   ,bookingId
				 								   ,doctorId);
		
		if(bookingCancelled>0) {
			isBookingDeleted = true;
		}
		
		return isBookingDeleted;
	}
	
	public List<SlotDetails> fetchAllSlots(String doctorId){
		
		logger.info("Entering fetch All Booking for doctorId:{}",doctorId);
		logger.info("queryFetchAllDoctorBookings:{}",DbQueryConstant.queryFetchAllDoctorSlots);
		List<SlotDetails> slotDetailsList = new ArrayList<SlotDetails>();
		
		try {
			
			slotDetailsList = (ArrayList<SlotDetails>) jdbcTemplate.query
					(DbQueryConstant.queryFetchAllDoctorSlots
					,new Object[] {doctorId}
                    ,new BeanPropertyRowMapper<SlotDetails>(SlotDetails.class));
			
		}catch(DataAccessException de) {
			logger.error("the query failed for fetchAllBookings, message:{}",de.getMessage());
		}
		
		return slotDetailsList;
	}
	
	public boolean addSlot(NewSlotDetails newSlotDetails) {
		boolean isSlotAdded = false;
		
		logger.info("Entering addSlot for doctorId:{}",newSlotDetails.getDoctor_id());
		logger.info("queryAddBookingSlot:{}",DbQueryConstant.queryAddBookingSlot);
		
		int doctorDetailsInsert = jdbcTemplate.update(DbQueryConstant.queryAddBookingSlot
													 ,newSlotDetails.getDoctor_id()
													 ,newSlotDetails.getStart_time()
													 ,newSlotDetails.getEnd_time()
													 ,newSlotDetails.getMeridiem_indicator());
		if(doctorDetailsInsert>0) {
			isSlotAdded = true;
		}
		
		return isSlotAdded;
	}
	
	public boolean deleteSlot(String doctorId,long slotId) {
		boolean isSlotDeleted = false;
		
		logger.info("Entering addSlot for doctorId:{}",doctorId);
		logger.info("queryAddBookingSlot:{}",DbQueryConstant.queryDeleteBookingSlot);
		
		int doctorDetailsInsert = jdbcTemplate.update(DbQueryConstant.queryDeleteBookingSlot
													 ,slotId
													 ,doctorId);
		
		if(doctorDetailsInsert>0) {
			isSlotDeleted = true;
		}
		
		return isSlotDeleted;
	}
	
	public boolean disableAccount(String doctorId) {
		boolean isAccountDisabled = false;
		
		logger.info("Entering disableAccount for doctorId:{}",doctorId);
		logger.info("queryDisableAccount:{}",DbQueryConstant.queryDisableAccount);
		
		int accountDisabled = jdbcTemplate.update(DbQueryConstant.queryDisableAccount,"N",doctorId);
		
		if(accountDisabled>0) {
			isAccountDisabled = true;
		}
		
		return isAccountDisabled;
	}
	
}
