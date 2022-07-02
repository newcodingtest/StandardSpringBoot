package com.yoon.standard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class StandardApplication implements ApplicationListener<ApplicationEvent> {

	public static void main(String[] args) {
		SpringApplication.run(StandardApplication.class, args);
	}

	/**
	 * 
	 * */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
	}

}
