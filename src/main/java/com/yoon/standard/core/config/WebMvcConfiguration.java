package com.yoon.standard.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yoon.standard.core.handler.InterceptorHandler;

import lombok.RequiredArgsConstructor;

/**
 * 모든 request, response에 InterceptorHandler 적용
 * */
@RequiredArgsConstructor
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final InterceptorHandler interceptorHandler;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorHandler);
	}
}
