package com.yoon.standard.core.exception;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice("com.yoon.standard.controller.web")
public class ControllerExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public String Exception(ModelMap modelMap, Exception e) {
		modelMap.addAttribute("message",e.getMessage());
		return "error";
	}
}
