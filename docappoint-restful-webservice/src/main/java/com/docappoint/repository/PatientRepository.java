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
import com.docappoint.bean.PatientProfileBean;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.constants.DbConstants;
import com.docappoint.constants.DbQueryConstant;
import com.docappoint.requestbean.RegisterPatientBean;
import com.docappoint.responsebean.DocAppointBookings;
import com.docappoint.responsebean.DoctorSearchDetails;

@Repository
public class PatientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(PatientRepository.class);
	
	public String fetchPatientFirstName(String patient_id) {
		logger.info("fetching Patient First name for patient:"+patient_id);
		logger.info("queryFetchStates:"+DbQueryConstant.queryFetchPatientFirstName);
		
		String patient_fist_name="";
		patient_fist_name = (String) jdbcTemplate.queryForObject(DbQueryConstant.queryFetchPatientFirstName
																,new Object[] {patient_id}
																,String.class);
		
		return patient_fist_name;
	}
	
	@Transactional
	public boolean registerPatient(RegisterPatientBean patientDetails) {
		
		boolean isPatientRegistered = false;
		
		logger.info("Entering registerPatient for user:{}",patientDetails.getPatient_id());
		logger.info("queryInsertUsers:{}",DbQueryConstant.queryInsertUsers);
		logger.info("queryInsertAuthorities:{}",DbQueryConstant.queryInsertAuthorities);
		logger.info("queryInsetPatientDetails:{}",DbQueryConstant.queryInsetPatientDetails);
		logger.info("queryInsertPatientContact:{}",DbQueryConstant.queryInsertPatientContact);
		
		int usersInsertSuccess = jdbcTemplate.update(DbQueryConstant.queryInsertUsers
				                                   ,patientDetails.getPatient_id()
				                                   ,patientDetails.getPassword()
				                                   ,"Y");
		
		int authoritiesInsertSuccess = jdbcTemplate.update(DbQueryConstant.queryInsertAuthorities
														  ,patientDetails.getPatient_id()
														  ,ApplicationConstants.ROLE_PATIENT);
		
		int patientDetailsInsertSuccess = jdbcTemplate.update(DbQueryConstant.queryInsetPatientDetails
															 ,patientDetails.getPatient_id()
															 ,patientDetails.getFirst_name()
															 ,patientDetails.getLast_name()
															 ,patientDetails.getDate_of_birth()
															 ,patientDetails.getGender());
		
		int patientContactInsertSuccess = jdbcTemplate.update(DbQueryConstant.queryInsertPatientContact
															 ,patientDetails.getPatient_id()
															 ,patientDetails.getAddress_line_one()
															 ,patientDetails.getAddress_line_two()
															 ,patientDetails.getState()
															 ,patientDetails.getCity()
															 ,patientDetails.getPincode()
															 ,patientDetails.getPrimary_mobile_number()
															 ,patientDetails.getAlternate_mobile_number()
															 ,patientDetails.getAlternate_email_id());
		
		if(usersInsertSuccess>0 && authoritiesInsertSuccess>0 
				&& patientDetailsInsertSuccess>0  && patientContactInsertSuccess>0) {
			
			isPatientRegistered = true;
		}
		
		return isPatientRegistered;
	}
	
	@Transactional
	public boolean updatePatientProfile(PatientProfileBean patientProfile) {
		
		logger.info("Entering update profile for user:{}",patientProfile.getPatient_id());
		
		logger.info("queryUpdatePatientDetails:{}",DbQueryConstant.queryUpdatePatientDetails);
		logger.info("queryUpdatePatientContact:{}",DbQueryConstant.queryUpdatePatientContact);
		
		boolean isPatientProfileupdated = false;
		
		int patientProfileUpdateSuccess = jdbcTemplate.update(DbQueryConstant.queryUpdatePatientDetails
						
															 ,patientProfile.getFirst_name()
															 ,patientProfile.getLast_name()
															 ,patientProfile.getDate_of_birth()
															 ,patientProfile.getGender()
															 ,patientProfile.getPatient_id());
		
		int patientContactUpdateSuccess = jdbcTemplate.update(DbQueryConstant.queryUpdatePatientContact
															 
															 ,patientProfile.getAddress_line_one()
															 ,patientProfile.getAddress_line_two()
															 ,patientProfile.getState()
															 ,patientProfile.getCity()
															 ,patientProfile.getPincode()
															 ,patientProfile.getPrimary_mobile_number()
															 ,patientProfile.getAlternate_mobile_number()
															 ,patientProfile.getAlternate_email_id()
															 ,patientProfile.getPatient_id());
		
		if(patientProfileUpdateSuccess>0  && patientContactUpdateSuccess>0) {
			isPatientProfileupdated = true;	
		}
		
		return isPatientProfileupdated;
	}
	
	public PatientProfileBean fetchPatientProfile(String patientId) {
		PatientProfileBean patientProfileBean = null;
		
		logger.info("Entering get Patient Profile for user:{}",patientId);
		
		logger.info("queryFetchPatientProfile:{}",DbQueryConstant.queryFetchPatientProfile);
		
		RowMapper<PatientProfileBean> rowMapper = (rs, rowNum) -> {
			
			PatientProfileBean patientProfile = new PatientProfileBean();
			patientProfile.setPatient_id(rs.getString(DbConstants.PT_DTLS_PATIENT_ID));
			patientProfile.setFirst_name(rs.getString(DbConstants.PT_DTLS_FIRST_NAME));
			patientProfile.setLast_name(rs.getString(DbConstants.PT_DTLS_LAST_NAME));
			patientProfile.setDate_of_birth(rs.getDate(DbConstants.PT_DTLS_DOB));
			patientProfile.setGender(rs.getString(DbConstants.PT_DTLS_GENDER));
			patientProfile.setAddress_line_one(rs.getString(DbConstants.PT_CONT_ADD_LINE_ONE));
			patientProfile.setAddress_line_two(rs.getString(DbConstants.PT_CONT_ADD_LINE_TWO));
			patientProfile.setState(rs.getString(DbConstants.PT_CONT_STATE));
			patientProfile.setCity(rs.getString(DbConstants.PT_CONT_CITY));
			patientProfile.setPincode(rs.getString(DbConstants.PT_CONT_PINCODE));
			patientProfile.setPrimary_mobile_number(rs.getString(DbConstants.PT_CONT_PRIMARY_MOBILE));
			patientProfile.setAlternate_mobile_number(rs.getString(DbConstants.PT_CONT_ALT_MOBILE));
			patientProfile.setAlternate_email_id(rs.getString(DbConstants.PT_CONT_ALT_EMAIL));
			
			return patientProfile;
		};
		
		patientProfileBean =  jdbcTemplate.queryForObject(DbQueryConstant.queryFetchPatientProfile
														 ,new Object[] {patientId,patientId}
														 ,rowMapper);
		
		return patientProfileBean;
	}
	
	public List<DoctorSearchDetails> fetchDoctorDetailsByState(String state){
	
		logger.info("Entering fetch All Doctors by state:{}",state);
		logger.info("queryFetchDoctorDtlsByState:{}",DbQueryConstant.queryFetchDoctorDtlsByState);
		List<DoctorSearchDetails> doctorDetailsList = new ArrayList<DoctorSearchDetails>();
		
		try {
			doctorDetailsList = (ArrayList<DoctorSearchDetails>) jdbcTemplate.query
					(DbQueryConstant.queryFetchDoctorDtlsByState
					,new Object[] {state}
                    ,new BeanPropertyRowMapper<DoctorSearchDetails>(DoctorSearchDetails.class));
			
		}catch(DataAccessException de) {
			logger.error("the query failed for fetchAllBookings, message:{}",de.getMessage());
		}
		
		return doctorDetailsList;
	}
	
	public List<DoctorSearchDetails> fetchDoctorsByStateCity(String state,String city){
		logger.info("Entering fetch All Doctors by state and city. state:{},city:{}",state,city);
		logger.info("queryFetchDoctorDtlsByStateCity:{}",DbQueryConstant.queryFetchDoctorDtlsByStateCity);
		List<DoctorSearchDetails> doctorDetailsList = new ArrayList<DoctorSearchDetails>();
		
		try {
			doctorDetailsList = (ArrayList<DoctorSearchDetails>) jdbcTemplate.query
					(DbQueryConstant.queryFetchDoctorDtlsByStateCity
					,new Object[] {state,city}
                    ,new BeanPropertyRowMapper<DoctorSearchDetails>(DoctorSearchDetails.class));
			
		}catch(DataAccessException de) {
			logger.error("the query failed for fetchAllBookings, message:{}",de.getMessage());
		}
		
		return doctorDetailsList;
	}

	public List<DoctorSearchDetails> fetchDoctorsByStateCityLocality(String state,String city,String locality){
		logger.info("Entering fetch All Doctors by state,city and locality. state:{},city:{},locality:{}",state,city,locality);
		logger.info("queryFetchDoctorDtlsByStateCityLoc:{}",DbQueryConstant.queryFetchDoctorDtlsByStateCityLoc);
		List<DoctorSearchDetails> doctorDetailsList = new ArrayList<DoctorSearchDetails>();
		
		try {
			doctorDetailsList = (ArrayList<DoctorSearchDetails>) jdbcTemplate.query
					(DbQueryConstant.queryFetchDoctorDtlsByStateCityLoc
					,new Object[] {state,city,locality}
                    ,new BeanPropertyRowMapper<DoctorSearchDetails>(DoctorSearchDetails.class));
			
		}catch(DataAccessException de) {
			logger.error("the query failed for fetchAllBookings, message:{}",de.getMessage());
		}
		
		return doctorDetailsList;
	}
	
	
	/*public AuthResponseBean getLoginData(String username) {
		logger.info("Entering getLoginData with username:{}",username);
		
		String query="select usr.username, auth.authority from users usr, authorities auth where usr.username=? and auth.username=?";
		AuthResponseBean authResponse = new AuthResponseBean();
		
		ResultSet rs=null;
		try(Connection con = datasource.getConnection();
			PreparedStatement preStmt = con.prepareStatement(query);){
			
			preStmt.setString(1, username);
			preStmt.setString(2, username);
			rs = preStmt.executeQuery();
			
			while(rs.next()) {
				
				authResponse.setUsername(rs.getString(DbConstants.USER_USERNAME));
				authResponse.setUser_role(rs.getString(DbConstants.AUTH_AUTHORITY));
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Exception while getting login details for user:{} , message:{}",username,ex.getMessage());
		}finally {
			if(null!=rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					// nothing to do here 
				}
			}
		}
		return authResponse;
	}*/
	
}
	