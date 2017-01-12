package com.appdirect.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtility {
	
	public static Logger logger;

	public static void setLoggerForClass(String className) {
		logger=LoggerFactory.getLogger(className);
		}

}
