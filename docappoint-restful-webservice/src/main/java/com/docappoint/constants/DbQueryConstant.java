package com.docappoint.constants;

public class DbQueryConstant {

	public static final String queryInsertUsers="insert into users (USERNAME,PASSWORD,ENABLED) values (?,?,?)";
	public static final String queryInsertAuthorities="insert into authorities (USERNAME,AUTHORITY) values (?,?)";
	public static final String queryFetchLoginDtls="select usr.username,usr.enabled, auth.authority from users usr,authorities auth where usr.username=? and auth.username=?";
	
	public static final String queryDisableAccount="update users set enabled=? where username=?";
	
	/**********************************************************************************************/
	
	public static final String queryFetchStates="select state_name from states";
	public static final String queryFetchCity="select city_name from city where state_name=?";
	public static final String queryFetchLocality="select locality_name from city_locality where city_name=?";
	
	public static final String queryFetchSpeciality="select speciality_name, description from speciality";
	
	/**********************************************************************************************/
	
	public static final String queryFetchPatientFirstName="select first_name from patient_details where patient_id=?";
	public static final String queryFetchPatientProfile="select pd.patient_id, pd.first_name,pd.last_name,pd.date_of_birth,pd.gender, pc.address_line_one,pc.address_line_two,pc.state,pc.city,pc.pincode,pc.primary_mobile_number,pc.alternate_mobile_number,pc.alternate_email_id from patient_details pd , patient_contact pc where pc.patient_id=? and pd.patient_id=?";
	
	public static final String queryInsetPatientDetails="insert into patient_details (patient_id,first_name,last_name,date_of_birth,gender) values (?,?,?,?,?)";
	public static final String queryInsertPatientContact="insert into patient_contact (patient_id,address_line_one,address_line_two,state,city,pincode,primary_mobile_number,alternate_mobile_number,alternate_email_id) values (?,?,?,?,?,?,?,?,?)";
	
	public static final String queryUpdatePatientDetails="update patient_details set first_name=?, last_name=?, date_of_birth=? ,gender=? where patient_id=?";
	public static final String queryUpdatePatientContact="update patient_contact set address_line_one=?, address_line_two=?, state=?, city=?, pincode=?, primary_mobile_number=?, alternate_mobile_number=?,alternate_email_id=? where patient_id=?";

	public static final String queryFetchDoctorDtlsByStateCityLoc="select dd.doctor_id, dd.first_name, dd.gender, dd.practicing_from, dd.consultation_fee, ds.speciality_name, dc.state, dc.city, dc.locality from doctor_details dd, doctor_speciality ds ,doctor_contact dc where dc.state=? and dc.city=? and dc.locality=? and dd.doctor_id=dc.doctor_id and dd.doctor_id=ds.doctor_id";
	public static final String queryFetchDoctorDtlsByStateCityLocSpec="select dd.doctor_id, dd.first_name, dd.gender, dd.practicing_from, dd.consultation_fee, ds.speciality_name, dc.state, dc.city, dc.locality from doctor_details dd, doctor_speciality ds ,doctor_contact dc where dc.state=? and dc.city=? and dc.locality=? and ds.speciality_name=? and dd.doctor_id=dc.doctor_id and dd.doctor_id=ds.doctor_id";
	
	public static final String queryFetchDoctorDtlsByStateCity = "select dd.doctor_id, dd.first_name, dd.gender, dd.practicing_from, dd.consultation_fee, ds.speciality_name, dc.state, dc.city, dc.locality from doctor_details dd, doctor_speciality ds ,doctor_contact dc where dc.state=? and dc.city=? and dd.doctor_id=dc.doctor_id and dd.doctor_id=ds.doctor_id";
	public static final String queryFetchDoctorDtlsByStateCitySpec = "select dd.doctor_id, dd.first_name, dd.gender, dd.practicing_from, dd.consultation_fee, ds.speciality_name, dc.state, dc.city, dc.locality from doctor_details dd, doctor_speciality ds ,doctor_contact dc where dc.state=? and dc.city=? and ds.speciality_name=? and dd.doctor_id=dc.doctor_id and dd.doctor_id=ds.doctor_id";
	
	public static final String queryFetchDoctorDtlsByState="select dd.doctor_id, dd.first_name, dd.gender, dd.practicing_from, dd.consultation_fee, ds.speciality_name, dc.state, dc.city, dc.locality from doctor_details dd, doctor_speciality ds ,doctor_contact dc where dc.state=? and dd.doctor_id=dc.doctor_id and dd.doctor_id=ds.doctor_id";
	public static final String queryFetchDoctorDtlsByStateSpec="select dd.doctor_id, dd.first_name, dd.gender, dd.practicing_from, dd.consultation_fee, ds.speciality_name, dc.state, dc.city, dc.locality from doctor_details dd, doctor_speciality ds ,doctor_contact dc where dc.state=? and ds.speciality_name=? and dd.doctor_id=dc.doctor_id and dd.doctor_id=ds.doctor_id";
	
	public static final String queryFetchAllPatientBookings ="select bd.booking_id, dd.first_name, ds.speciality_name, bd.booking_date, bs.start_time, bs.end_time, bs.meridiem_indicator, bd.symptom_desc, bd.cancelled, bd.cancelled_by  from booking_details bd, doctor_details dd, doctor_speciality ds, booking_slots bs where bd.patient_id=? and bd.doctor_id=dd.doctor_id and bd.doctor_id=ds.doctor_id and bd.slot_id=bs.slot_id";
	public static final String queryCancelPatientBooking="update booking_details set cancelled=? cancelled_by=? where booking_id=? and patient_id=?";
	
	/**********************************************************************************************/
	
	public static final String queryFetchDoctorFirstName="select first_name from doctor_details where doctor_id=?";	
	public static final String queryFetchDoctorProfile="select dd.doctor_id, dd.first_name, dd.last_name, dd.date_of_birth,dd.gender, dd.practicing_from,ds.speciality_name, dd.consultation_fee, dc.address_line_one, dc.address_line_two, dc.state, dc.city, dc.locality, dc.pincode,dc.primary_mobile_number, dc.alternate_mobile_number,dc.alternate_email_id, dq.institute_name, dq.procurement_date from doctor_details dd, doctor_contact dc, doctor_qualification dq, doctor_speciality ds where dd.doctor_id=? and dc.doctor_id=? and dq.doctor_id=? and ds.doctor_id=?";
	public static final String queryFetchAllDoctorBookings = "select bd.booking_id, pd.first_name, ds.speciality_name, bd.booking_date, bs.start_time, bs.end_time, bs.meridiem_indicator, bd.symptom_desc, bd.cancelled, bd.cancelled_by from booking_details bd, patient_details pd, doctor_speciality ds, booking_slots bs where bd.doctor_id=? and bd.patient_id=pd.patient_id and bd.doctor_id=ds.doctor_id and bd.slot_id=bs.slot_id";
	
	
	public static final String queryInsetDoctorDetails="insert into doctor_details (doctor_id,first_name,last_name,date_of_birth,gender,practicing_from,consultation_fee) values (?,?,?,?,?,?,?)";
	public static final String queryInsertDoctorContact="insert into doctor_contact (doctor_id,address_line_one,address_line_two,state,city,locality,pincode,primary_mobile_number,alternate_mobile_number,alternate_email_id) values (?,?,?,?,?,?,?,?,?,?)";
	public static final String queryInsertDoctorQualification="insert into doctor_qualification (doctor_id,institute_name,procurement_date) values (?,?,?)";
	public static final String queryInsertDoctorSpeciality="insert into doctor_speciality (doctor_id,speciality_name) values (?,?)";
	
	public static final String queryUpdateDoctorDetails="update doctor_details set first_name=?, last_name=?, date_of_birth=?, gender=?, practicing_from=?, consultation_fee=? where doctor_id=?";
	public static final String queryUpdateDoctorContact="update doctor_contact set address_line_one=?, address_line_two=?, state =?, city=?, locality=?, pincode=?, primary_mobile_number=?, alternate_mobile_number=?, alternate_email_id=? where doctor_id=?";
	public static final String queryUpdateDoctorQualification="update doctor_qualification set institute_name=?, procurement_date=? where doctor_id=?";
	public static final String queryUpdateDoctorSpeciality="update doctor_speciality set speciality_name=? where doctor_id=?";
	
	public static final String queryCancelBooking="update booking_details set cancelled=? cancelled_by=? where booking_id=? and doctor_id=?";
	
	public static final String queryFetchAllDoctorSlots="select slot_id, start_time, end_time, meridiem_indicator from booking_slots where doctor_id=?";
	public static final String queryAddBookingSlot="insert into booking_slots (doctor_id,start_time,end_time, meridiem_indicator) values (?,?,?,?)";
	public static final String queryDeleteBookingSlot="delete from booking_slots where slot_id=? and doctor_id=?";
}
