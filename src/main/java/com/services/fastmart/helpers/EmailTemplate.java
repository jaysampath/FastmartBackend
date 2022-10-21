package com.services.fastmart.helpers;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class EmailTemplate {
	
	private String templateId;
	
	private String template;
	
	private Map<String,String> replacementParams;
	
	public EmailTemplate(String templateId) {
		this.templateId = templateId;
		try {
			this.template = loadTemplateMethod(templateId);
		}catch(Exception e) {
			this.template = "Empty";
		}
	}

	private String loadTemplateMethod(String templateId) throws Exception {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(templateId).getFile());
		String content = "Empty";
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		}catch(Exception e) {
			throw new Exception("Could not read template with ID = " + templateId);
		}
		return content;
	}
	
	public String getTemplate(Map<String,String> replacementParams) {
		String newTemplate = this.template;
		
		for(Map.Entry<String,String> entry: replacementParams.entrySet()) {
			newTemplate = newTemplate.replace("{{"+entry.getKey()+"}}", entry.getValue());
		}
		return newTemplate;
	}

}
