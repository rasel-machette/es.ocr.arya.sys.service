package com.ocr;

import com.ocr.client.moqui.MoquiConnector;
import com.ocr.client.moqui.Moqui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrApplication.class, args);
	}

	@Bean
	public Moqui moquiBean(){
		return new MoquiConnector();
	}
}
