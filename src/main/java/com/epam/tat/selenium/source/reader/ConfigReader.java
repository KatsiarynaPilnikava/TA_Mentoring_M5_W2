package com.epam.tat.selenium.source.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static final ConfigReader INSTANCE = new ConfigReader();

	public static ConfigReader getInstance() {
		return INSTANCE;
	}
	private ConfigReader(){
		
	}
	
	public String getPropValues(String propFileName, String property) throws IOException {
		InputStream inputStream = null;
		Properties prop = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop.getProperty(property);
	}
}
