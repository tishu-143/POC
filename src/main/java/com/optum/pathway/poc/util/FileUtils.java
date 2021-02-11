package com.optum.pathway.poc.util;


import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class FileUtils {

	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(FileUtils.class);

	
	public static Properties getExternalizedProperties(){
		// load external properties file and read the encryption key
		Properties props = null;
		try {
				props = PropertiesLoaderUtils
						.loadProperties(new ClassPathResource("/externalized-properties.properties"));

		} catch (IOException e) {
			logger.error("Error occured while loading externalized properties file: %s ", e);
		}
		return props;
	}






}
