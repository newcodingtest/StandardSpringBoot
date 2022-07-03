package com.yoon.standard.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controller {
	
	@GetMapping
	public String getIndex(String data, ModelMap modelMap) {
		
		modelMap.addAttribute("data","GET 테스트 " + data);
		return "index";
	}
	
	@PostMapping
	public String postIndex(String data, ModelMap modelMap) {
		
		modelMap.addAttribute("data","POST 테스트 " + data);
		return "index";
	}
}
