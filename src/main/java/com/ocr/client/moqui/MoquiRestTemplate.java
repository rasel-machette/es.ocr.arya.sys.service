package com.ocr.client.moqui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MoquiRestTemplate {
	
	
	@Value("${moqui.userName}")
	String moquiUserName;
	@Value("${moqui.password}")
	String moquiPassword;
	//TODO: if possible use API_KEY
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.basicAuthentication(moquiUserName, moquiPassword).build();
	}

}
