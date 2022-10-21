package com.services.fastmart.conifg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class StateCodesConfig {
	

	@Bean(name = "state-code-map")
	public Map<String, String> stateCodeMap() throws IOException {
		final Map<String, String> stateCodes = new HashMap<>();
		
		InputStream ioStream = this.getClass()
	            .getClassLoader()
	            .getResourceAsStream("stateCodes.txt");
		
		InputStreamReader isr = new InputStreamReader(ioStream);
	    
		//ClassPathResource resource =  new ClassPathResource("stateCodes.txt");
		//File file = resource.getFile();
		BufferedReader bf = new BufferedReader(isr);
		String line = bf.readLine();
		while(line!=null) {
			String[] sArr = line.split("=");
			String key = sArr[0];
			String value = sArr[1];
			stateCodes.put(key, value);			
			line = bf.readLine();
		}
		return stateCodes;
	}
}
