package com.pkrm.weekend8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class GenerateMultipleTasks implements Runnable {
	private static org.apache.log4j.Logger logger=Logger.getLogger(Main.class);

	public void run() {
		
	
		List<MainTask> maintasklist = new ArrayList<MainTask>();
		FileWriter fw = null;

		try {String filePath="C:\\Desktop\\workspace\\week4";
		String filename="testcheckmultiple-";
		 File file = new File(filePath + "\\" + filename+GetCurrentTimeStamp().replace(":","-")+".csv");
		 try {
		 	if (!file.exists()) {
		         file.createNewFile();
		         //System.out.println("File is created; file name is " + file.getName());
		         
		 }
		 	
		 }
		 catch(Exception e)
		 {
		 	logger.error(e);
		 }
			fw = new FileWriter(file,true);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		for (int i = 1; i <= 100; ++i) {
			MainTask maintask = new MainTask(i+",Company Construction-" + i, "Constructing a building",
					new SubTask("Wooden Work-" + i, "All kinds of wood",7, new Person(",John-"+i, 27)));
			maintasklist.add(maintask);
		}
		BufferedWriter buffer=new BufferedWriter(fw);
		try {
			buffer.write("S.no,MainTaskTitle,MainTaskDescription,SubTaskTitle,SubTaskDescription,EstimatedHours,AssignedPerson,Age");
			buffer.newLine();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		for(MainTask mt : maintasklist)
		{
			try {
				buffer.write(mt.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		try {
			buffer.flush();
			buffer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public static String GetCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(
	            "MMM-dd-yyyy-HH:mm:ss");
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

}
