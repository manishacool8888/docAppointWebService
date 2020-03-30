package com.docappoint.constants;

public class DbQueryConstant {

	public static final String queryFetchLoginDtls="select usr.username,usr.enabled, auth.authority from users usr,authorities auth where usr.username=? and auth.username=?";
	public static final String queryFetchPatientFirstName="select first_name from patient_details where patient_id=?";
	public static final String queryFetchDoctorFirstName="select first_name from doctor_details where doctor_id=?";
	
	public static final String queryInsertUsers="insert into users (USERNAME,PASSWORD,ENABLED) values (?,?,?)";
	public static final String queryInsertAuthorities="insert into authorities (USERNAME,AUTHORITY) values (?,?)";
	public static final String queryInsetPatientDetails="insert into patient_details (patient_id,first_name,last_name,date_of_birth,gender) values (?,?,?,?,?)";
	public static final String queryInsertPatientContact="insert into patient_contact (patient_id,address_line_one,address_line_two,state,city,pincode,primary_mobile_number,alternate_mobile_number,alternate_email_id) values (?,?,?,?,?,?,?,?,?)";
	
	public static final String queryFetchStates="select state_name from states";
	public static final String queryFetchCity="select city_name from city where state_name=?";
	public static final String queryFetchLocality="select locality_name from city_locality where city_name=?";
	
}
