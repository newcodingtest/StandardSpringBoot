package com.yoon.standard.core.exception;

import javax.servlet.http.HttpServletResponse; 
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.yoon.standard.core.dto.ShareDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.yoon.standard.controller.api")
public class RestControllerExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public ShareDto Exception(HttpServletResponse response, Exception e) {
		return new ShareDto(false, e.getMessage());
	}
}
