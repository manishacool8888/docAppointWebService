package com.docappoint.rest.webservices.connections;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;
import java.sql.DatabaseMetaData;

public class DataSourceSample {  
  // The recommended format of a connection URL is the long format with the
  // connection descriptor.
  //final static String DB_URL= "jdbc:oracle:thin:@myhost:1521/myorcldbservicename";
  // For ATP and ADW - use the TNS Alias name along with the TNS_ADMIN when using 18.3 JDBC driver
  // final static String DB_URL="jdbc:oracle:thin:@wallet_dbname?TNS_ADMIN=/Users/test/wallet_dbname";
  // In case of windows, use the following URL  //D:\wallet_DocAppointDB
  //final static String DB_URL="jdbc:oracle:thin:@wallet_dbname?TNS_ADMIN=D:\\wallet_DocAppointDB";
  
  //final static String DB_URL="jdbc:oracle:thin:@DocAppointDB_high?TNS_ADMIN=D:\\wallet_DocAppointDB";
  final static String DB_URL="jdbc:oracle:thin:@DocAppointDB_high?TNS_ADMIN=.\\wallet_DocAppointDB";
  
  ///docappoint-restful-webservice/wallet_DocAppointDB
  
  final static String DB_USER = "ADMIN";
  final static String DB_PASSWORD = "Sukhoi30@mki";

  // wallet_DocAppointDB  adb.ap-mumbai-1.oraclecloud.com:1522/trlizhsph0ksnal_docappointdb_high.atp.oraclecloud.com
  
 /*
  * The method gets a database connection using 
  * oracle.jdbc.pool.OracleDataSource. It also sets some connection 
  * level properties, such as,
  * OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH,
  * OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES, etc.,
  * There are many other connection related properties. Refer to 
  * the OracleConnection interface to find more. 
  */
  public static void main(String args[]) throws SQLException {
    Properties info = new Properties();     
    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
    info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    

    OracleDataSource ods = new OracleDataSource();
    ods.setURL(DB_URL);    
    ods.setConnectionProperties(info);

    // With AutoCloseable, the connection is closed automatically.
    try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
      // Get the JDBC driver name and version 
      DatabaseMetaData dbmd = connection.getMetaData();       
      System.out.println("Driver Name: " + dbmd.getDriverName());
      System.out.println("Driver Version: " + dbmd.getDriverVersion());
      // Print some connection properties
      System.out.println("Default Row Prefetch Value is: " + 
         connection.getDefaultRowPrefetch());
      System.out.println("Database Username is: " + connection.getUserName());
      System.out.println();
      // Perform a database operation 
      printEmployees(connection);
    }   
  }
 /*
  * Displays first_name and last_name from the employees table.
  */
  public static void printEmployees(Connection connection) throws SQLException {
	  
//	  if(null!=connection) {
//		  System.out.println("DB connection success");
//		  
//		  try {
//			  connection.close();
//		  }catch(Exception e) {
//			  e.printStackTrace();
//		  }
//	  }
	  
    // Statement and ResultSet are AutoCloseable and closed automatically. 
    try (Statement statement = connection.createStatement()) {      
      try (ResultSet resultSet = statement
          .executeQuery("select name, city from test_table")) {
        System.out.println("NAME" + "         " + "CITY");
        System.out.println("---------------------");
        while (resultSet.next())
          System.out.println(resultSet.getString(1) + " "
              + resultSet.getString(2) + " ");       
      }
    }   
    
  } 
}