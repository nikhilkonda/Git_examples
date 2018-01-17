package com.pkrm.weekend8;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class SubTask extends Abstract_Task {
	private static org.apache.log4j.Logger logger=Logger.getLogger(Main.class);

	static Scanner sc=new Scanner(System.in);
	private String subtasktitle;
	private String subtaskdescription;
	Person p1;
	int estimatehours;
	private static int sub_a;
	public static String subname;
	public static int subage;
	public SubTask(int age, String name) {
		super(age, name);
		this.age=age;
		this.name=name;
	}
public SubTask(String subtasktitle,String subtaskdescription,int estimatehours,Person p1)
{
	super(age,name);
	this.estimatehours=estimatehours;
	this.subtaskdescription=subtaskdescription;
	this.subtasktitle=subtasktitle;
	this.p1=p1;
	
}
public String title() {
	logger.info("Enter the subtask title:");
	subtasktitle=sc.next();

		return subtasktitle;
	}
	public String getSubtasktitle() {
	return subtasktitle;
}

public String getSubtaskdescription() {
	return subtaskdescription;
}

	public String description() {
		logger.info("Enter the description subtask");
		subtaskdescription=sc.next();
		return subtaskdescription;
	}
	
	static int estimateHours() {
		logger.info("Enter the no.of estimated hours:");
		int ehours=sc.nextInt();
		return ehours;
	}

	public static void subTaskAssignment() throws IOException, SubTaskException, MainTaskException, SQLException
	{
		DataCapture.dataCaptureMethod("SubTask");
		//DataCapture.dataWrite();
					
	}
	/*public int subTaskPersonAssignment(int n) throws SubTaskException {
		 int assignhours=0;
		 int checktest=n;
				 
		 if(checktest>20&&checktest<30) {
		 assignhours=estimateHours();
		 
		}
		else {
			throw new SubTaskException("Age is below 20 or above 30 which is not allowed");
		}
		return assignhours;*/
	//}
}
