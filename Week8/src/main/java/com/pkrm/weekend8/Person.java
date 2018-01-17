package com.pkrm.weekend8;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Person {
	private static org.apache.log4j.Logger logger=Logger.getLogger(Main.class);
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@172.16.203.151:1521:traindb";
	static final String USER = "knikhil";
	static final String PASS = "prokarma";
	static Connection conn = null;
	static CallableStatement call = null;

	protected static int age;
	protected static String name;
	private static int pinsage;

	public static int getPinsage() {
		return pinsage;
	}

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public Person(String name, int age) {
		this.name = name;

		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	private static Connection dbConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (final ClassNotFoundException e) {
			logger.error("Driver not found.Please check the driver configuration");
			e.printStackTrace();

		}

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;

		} catch (SQLException se) {
			logger.error(se);
		}
		return conn;

	}

	public static int personnextid() {
		Statement stmt1 = null;
		int pid = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt1 = conn.createStatement();
			String sqll = "select person1_seq.nextval from dual";
			ResultSet resultSet = stmt1.executeQuery(sqll);
			logger.info("Displaying");
			if (resultSet.next()) {
				pid = resultSet.getInt(1);
			}
			resultSet.close();
			stmt1.close();
			// conn.close();
		} catch (final SQLException se) {
			se.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt1 != null) {
					stmt1.close();
				}
			} catch (final SQLException se2) {
				logger.error("Exception ex" + se2);
			}
			/*
			 * try { if (conn != null) { conn.close(); } } catch (final SQLException se) {
			 * se.printStackTrace(); }
			 */
		}
		return pid;

	}

	public static Person personDetails(String str) throws MainTaskException, SubTaskException 
{
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		if(str.equalsIgnoreCase("MainTask"))
			{
			System.out.println("Enter the person's name FOR MAINTASK");
			String name=sc.nextLine();
			logger.info("Enter the person's age for maintask");
			int age=sc.nextInt();
			pinsage=personnextid();
				if(age<30)
					{
					throw new MainTaskException("Age is not allowed for maintasks");
					}
					String insertpersondetails= "{call insert1_persondetails(?,?,?)}";
					try 
					{
						conn=dbConnection();
						System.out.println("----------------------------------------------------\n-------------------------------"+pinsage);
						call=conn.prepareCall(insertpersondetails);
						call.setInt(1,pinsage);
						call.setString(2, name);
						call.setInt(3, age);
						call.executeUpdate();
						logger.info("Person values are inserted");
					}
					catch(SQLException se)
					{
						logger.error(se);			
					}finally
					{
						try {
							call.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
		if(str.equalsIgnoreCase("SubTask"))
			{
			logger.info("Enter the person's name for subtask");
			String name=sc.nextLine();
			logger.info("Enter the person's age for subtask");
			int age=sc.nextInt();
			 pinsage=personnextid();
			if(age>20&&age>30)
			{
			throw new SubTaskException("Age is not allowed for subtasks");
			}
			String insertpersondetails= "{call insert1_persondetails(?,?,?)}";
			try 
			{
			conn=dbConnection();
			System.out.println("----------------------------------------------------\n-------------------------------"+pinsage);
			call=conn.prepareCall(insertpersondetails);
			call.setInt(1,pinsage);
			call.setString(2, name);
			call.setInt(3, age);
			call.executeUpdate();
			logger.info("Person values are inserted");
			}	
			catch(SQLException se)
			{
				logger.error(se);			
			}
			finally
			{
				try {
					call.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				finally
				{
					try {
						conn.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
			}
			}
		return new Person(age,name);
}
}

	