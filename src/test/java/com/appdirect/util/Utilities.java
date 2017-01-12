package com.appdirect.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;






public class Utilities {
	
	static String className = Utilities.class.getName();
	static FileInputStream input;
	public static String getDataFromConfig(String configDataKey) {
		String configData = null;
		Properties currentProperties = new Properties();
		LogUtility.setLoggerForClass(className);

		try {
			input = new FileInputStream("resources/config.properties");
			currentProperties.load(input);
			configData = currentProperties.getProperty(configDataKey);
			input.close();

		} catch (FileNotFoundException e) {
			LogUtility.logger.info("file not found at path:-{}", "/src/resources/config.properties", e);

		} catch (IOException e) {
			LogUtility.logger.info("unable to get input from file path:-{}", "src/resources/config.properties", e);

		} catch (Exception e) {
			LogUtility.logger.info("unable to get data from property file at file path:-{}",
					"src/resources/config.properties", e);

		}

		// LogUtility.logger.info("exiting from method with fetching data as {}
		// for key {}", configData, configDataKey);

		return configData;
	}
	

}
