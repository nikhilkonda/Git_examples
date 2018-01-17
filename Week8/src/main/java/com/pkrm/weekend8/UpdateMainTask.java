package com.pkrm.weekend8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class UpdateMainTask {
	private static org.apache.log4j.Logger logger=Logger.getLogger(Main.class);

	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@172.16.203.151:1521:traindb";
    static final String USER = "knikhil";
    static final String PASS = "prokarma";
    public static void update() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
      String sold="";
      String descnew="";
        Scanner sc=new Scanner(System.in);
        try {
        	logger.info("Enter the existing maintask name which needs to be replaced");
        	 sold=sc.nextLine();
        	logger.info("Enter the new maintask title");
        	String snew=sc.nextLine();
            logger.info("Enter the main task details");
             descnew=sc.nextLine();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "update maintaskdetails set mname = ?, mdesc=? where mname = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,snew);
            preparedStmt.setString(2,descnew);
            preparedStmt.setString(3,sold );
            preparedStmt.executeUpdate();
            
           
        	}
        catch(SQLException se)
        {
        	logger.error("Error message"+se);
        }
        finally
        {
        	conn.close();
        }
    }
}
