package com.docappoint.repository;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.docappoint.bean.UserAuthData;
import com.docappoint.constants.DbConstants;
import com.docappoint.constants.DbQueryConstant;
import com.docappoint.dao.TestDataDao;
import com.docappoint.responsebean.AuthResponseBean;

@Repository
public class CommonRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonRepository.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<TestDataDao> testMethod() {
		
		String query="select name,city from test_table";
		ArrayList<TestDataDao> testDataList = new ArrayList<TestDataDao>();
		testDataList = (ArrayList<TestDataDao>) jdbcTemplate.query(query, new BeanPropertyRowMapper(TestDataDao.class));
		
		return testDataList;
	}
	
	public UserAuthData fetchUserRoleData(String username) {
		logger.info("Entering getLoginData with username:{}",username);
		
		RowMapper<UserAuthData> rowMapper = (rs, rowNum) -> {
			UserAuthData authResponse = new UserAuthData(rs.getString(DbConstants.USER_USERNAME)
														,rs.getString(DbConstants.USER_ENABLED)
														,rs.getString(DbConstants.AUTH_AUTHORITY));
			return authResponse;
		};
		
		return jdbcTemplate.queryForObject(DbQueryConstant.queryFetchLoginDtls
										  ,new Object[] {username,username}
		                                  ,rowMapper);
	}
	
	public List<String> fetchStates(){
		logger.info("fetching state list");
		logger.info("queryFetchStates:"+DbQueryConstant.queryFetchStates);
		
		List<String> stateList=new ArrayList<String>();
		
		stateList=jdbcTemplate.queryForList(DbQueryConstant.queryFetchStates, String.class);
		
		logger.info("state List fetched from DB is:"+stateList);
		
		return stateList;
	}
	
	public List<String> fetchCity(String state){
		logger.info("fetching city list");
		logger.info("queryFetchCity:"+DbQueryConstant.queryFetchCity);
		
		List<String> cityList=new ArrayList<String>();
		
		cityList=jdbcTemplate.queryForList(DbQueryConstant.queryFetchCity
										  ,new Object[] {state}
										  ,String.class);
		return cityList;
	}
	
	public List<String> fetchCityLocality(String city){
		logger.info("fetching city locality list");
		logger.info("queryFetchLocality:"+DbQueryConstant.queryFetchLocality);
		
		List<String> cityLocalityList = new ArrayList<String>();
		
		cityLocalityList = jdbcTemplate.queryForList(DbQueryConstant.queryFetchLocality
													,new Object[] {city}
													,String.class);
		return cityLocalityList;
	}
}
