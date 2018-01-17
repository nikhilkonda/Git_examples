package com.pkrm.weekend8;

import org.apache.log4j.Logger;

public class SubTaskException extends Exception {
	private static org.apache.log4j.Logger logger=Logger.getLogger(Main.class);

	public SubTaskException(String s)
	{
		logger.info(s);
	}
}
