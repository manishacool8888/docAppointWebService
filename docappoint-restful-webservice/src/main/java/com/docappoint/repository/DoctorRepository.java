package com.docappoint.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.docappoint.bean.DoctorProfileBean;
import com.docappoint.bean.PatientProfileBean;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.constants.DbConstants;
import com.docappoint.constants.DbQueryConstant;
import com.docappoint.requestbean.RegisterDoctorBean;
import com.docappoint.requestbean.RegisterPatientBean;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.ProfileUpdateResponse;
import com.docappoint.responsebean.RegistrationResponse;

@Repository
public class DoctorRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorRepository.class);
	
	public String fetchDoctorFirstName(String doctor_id) {
		logger.info("fetching Patient First name");
		logger.info("queryFetchStates:"+DbQueryConstant.queryFetchDoctorFirstName);
		
		String patient_fist_name="";
		patient_fist_name = (String) jdbcTemplate.queryForObject(DbQueryConstant.queryFetchPatientFirstName
																,new Object[] {doctor_id}
																,String.class);
		
		return patient_fist_name;
	}
	
	@Transactional
	public RegistrationResponse registerDoctor(RegisterDoctorBean doctorDetails) {
		
		logger.info("Entering registerDoctor for user:{}",doctorDetails.getDoctor_id());
		logger.info("queryInsertUsers:{}",DbQueryConstant.queryInsertUsers);
		logger.info("queryInsertAuthorities:{}",DbQueryConstant.queryInsertAuthorities);
		logger.info("queryInsetDoctorDetails:{}",DbQueryConstant.queryInsetDoctorDetails);
		logger.info("queryInsertDoctorContact:{}",DbQueryConstant.queryInsertDoctorContact);
		logger.info("queryInsertDoctorQualification:{}",DbQueryConstant.queryInsertDoctorQualification);
		logger.info("queryInsertDoctorSpeciality:{}",DbQueryConstant.queryInsertDoctorSpeciality);
		
		RegistrationResponse registrationResponse=null;
		
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
			
			registrationResponse = new RegistrationResponse();
			registrationResponse.setUsername(doctorDetails.getDoctor_id());
			registrationResponse.setUser_role(ApplicationConstants.ROLE_DOCTOR);
			registrationResponse.setRegistrationSuccess(true);
		}
		
		return registrationResponse;
	}
	
	@Transactional
	public ProfileUpdateResponse updateDoctorProfile(DoctorProfileBean doctorDetails) {
		
		logger.info("Entering update profile for doctor:{}",doctorDetails.getDoctor_id());
		
		logger.info("queryUpdateDoctorDetails:{}",DbQueryConstant.queryUpdateDoctorDetails);
		logger.info("queryUpdateDoctorContact:{}",DbQueryConstant.queryUpdateDoctorContact);
		logger.info("queryUpdateDoctorQualification:{}",DbQueryConstant.queryUpdateDoctorQualification);
		logger.info("queryUpdateDoctorSpeciality:{}",DbQueryConstant.queryUpdateDoctorSpeciality);
		
		ProfileUpdateResponse profileUpdateResponse= new ProfileUpdateResponse();
		
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
			
			profileUpdateResponse.setUsername(doctorDetails.getDoctor_id());
			profileUpdateResponse.setProfileUpdated("Y");
		}else {
			profileUpdateResponse.setUsername(doctorDetails.getDoctor_id());
			profileUpdateResponse.setProfileUpdated("N");
		}
		
		return profileUpdateResponse;
	}
	
	public DoctorProfileBean fetchDoctorProfile(String doctorId) {
		DoctorProfileBean doctorProfileBean = null;
		
		logger.info("Entering get Doctor Profile for user:{}",doctorId);
		logger.info("queryFetchPatientProfile:{}",DbQueryConstant.queryFetchDoctorProfile);
		
		/*
		 * RowMapper<PatientProfileBean> rowMapper = (rs, rowNum) -> {
		 * 
		 * PatientProfileBean patientProfile = new PatientProfileBean();
		 * patientProfile.setPatient_id(rs.getString(DbConstants.PT_DTLS_PATIENT_ID));
		 * patientProfile.setFirst_name(rs.getString(DbConstants.PT_DTLS_FIRST_NAME));
		 * patientProfile.setLast_name(rs.getString(DbConstants.PT_DTLS_LAST_NAME));
		 * patientProfile.setDate_of_birth(rs.getDate(DbConstants.PT_DTLS_DOB));
		 * patientProfile.setGender(rs.getString(DbConstants.PT_DTLS_GENDER));
		 * patientProfile.setAddress_line_one(rs.getString(DbConstants.
		 * PT_CONT_ADD_LINE_ONE));
		 * patientProfile.setAddress_line_two(rs.getString(DbConstants.
		 * PT_CONT_ADD_LINE_TWO));
		 * patientProfile.setState(rs.getString(DbConstants.PT_CONT_STATE));
		 * patientProfile.setCity(rs.getString(DbConstants.PT_CONT_CITY));
		 * patientProfile.setPincode(rs.getString(DbConstants.PT_CONT_PINCODE));
		 * patientProfile.setPrimary_mobile_number(rs.getString(DbConstants.
		 * PT_CONT_PRIMARY_MOBILE));
		 * patientProfile.setAlternate_mobile_number(rs.getString(DbConstants.
		 * PT_CONT_ALT_MOBILE));
		 * patientProfile.setAlternate_email_id(rs.getString(DbConstants.
		 * PT_CONT_ALT_EMAIL));
		 * 
		 * return patientProfile; };
		 */

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
			bookingList = jdbcTemplate.queryForList(DbQueryConstant.queryFetchAllDoctorBookings
					   ,new Object[] {doctorId}
					   ,DocAppointBookings.class);
			
		}catch(DataAccessException de) {
			logger.error("the query failed for fetchAllBookings, message:{}",de.getMessage());
		}
		
		return bookingList;
	}
}
