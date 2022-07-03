package com.yoon.standard.controller.api;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.standard.controller.dto.RequestDeptDto;
import com.yoon.standard.controller.dto.ResponseDeptDto;
import com.yoon.standard.core.dto.ShareDTO;
import com.yoon.standard.service.dept.DeptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor //코틀린 사용시 해당 어노테이션은 사용 안하기 때문에 추후 전환시에 삭제하기 좋게 Top 에 위치
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DeptApiController {
	
	private final DeptService deptService;
	
	@GetMapping(value ="/dept")
	public List<ResponseDeptDto> deptList()throws Exception {
		return deptService.deptList();
	}
	
	@GetMapping(value ="/dept/{deptno}")
	public ResponseDeptDto deptDetail(@PathVariable("deptno")int deptno)throws Exception {
		return deptService.deptDetail(deptno);
	}
	
	@PostMapping(value ="/dept")
	public ResponseDeptDto deptInsert(RequestDeptDto requestDeptDto)throws Exception {
		return deptService.deptInsert(requestDeptDto.toEntity());
	}
	
	@PutMapping(value ="/dept")
	public ResponseDeptDto deptUpdate(RequestDeptDto requestDeptDto)throws Exception {
		return deptService.deptUpdate(requestDeptDto.toEntity());
	}
	
	@DeleteMapping(value ="/dept/{deptno}")
	public ShareDTO deptDelete(@PathVariable("deptno")int deptno)throws Exception {
		deptService.deptDelete(deptno);
		return new ShareDTO(true, "삭제완료");
	}

}
