package com.yoon.standard.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.yoon.standard.service.dept.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeptController {

	private final DeptService deptService;
	
	@GetMapping(value ="/dept")
	public String deptList(ModelMap modelMap)throws Exception {
		modelMap.addAttribute("deptList", deptService.deptList());
		return "deptList";
	}
}
