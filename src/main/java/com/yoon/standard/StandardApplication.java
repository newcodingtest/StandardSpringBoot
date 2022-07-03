package com.yoon.standard;
 
import java.nio.charset.Charset; 

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class StandardApplication implements ApplicationListener<ApplicationReadyEvent> {

	@Value("${custom.message}")
	String message;
	
	public static void main(String[] args) {
		SpringApplication.run(StandardApplication.class, args);
	}
	
	/**
	 * yml test
	 * */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.debug("####");
		log.info(message);
		log.debug(message);
		log.warn(message);
		log.trace(message);
		log.debug("####");
		
	}

	/**
	 * Http body Response 확인 시 한글 깨지는 문제 해결
	 * */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2CborHttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		jsonConverter.setObjectMapper(objectMapper);
		jsonConverter.setDefaultCharset(Charset.forName("UTF-8"));
		return jsonConverter;
	}



}
