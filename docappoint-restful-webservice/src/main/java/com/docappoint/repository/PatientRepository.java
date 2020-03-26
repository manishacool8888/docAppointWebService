package com.docappoint.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.docappoint.constants.ApplicationConstants;
import com.docappoint.constants.DbQueryConstant;
import com.docappoint.requestbean.RegisterPatientBean;
import com.docappoint.responsebean.PatientRegistrationResponse;

@Repository
public class PatientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(PatientRepository.class);
	
	@Transactional
	public PatientRegistrationResponse registerPatient(RegisterPatientBean patientDetails) {
		
		logger.info("Entering registerPatient for user:{}",patientDetails.getPatient_id());
		logger.info("queryInsertUsers:{}",DbQueryConstant.queryInsertUsers);
		logger.info("queryInsertAuthorities:{}",DbQueryConstant.queryInsertAuthorities);
		logger.info("queryInsetPatientDetails:{}",DbQueryConstant.queryInsetPatientDetails);
		logger.info("queryInsertPatientContact:{}",DbQueryConstant.queryInsertPatientContact);
		
		PatientRegistrationResponse registrationResponse=null;
		
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
			
			registrationResponse = new PatientRegistrationResponse();
			registrationResponse.setUsername(patientDetails.getPatient_id());
			registrationResponse.setUser_role(ApplicationConstants.ROLE_PATIENT);
			registrationResponse.setRegistrationSuccess(true);
		}
		
		return registrationResponse;
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
	