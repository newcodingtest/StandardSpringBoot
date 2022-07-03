package com.yoon.standard.service.dept;

import java.util.ArrayList;  
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.standard.controller.dto.ResponseDeptDto;
import com.yoon.standard.domain.dept.Dept;
import com.yoon.standard.domain.dept.DeptRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles({"dev", "db-h2"}) //테스트에 적용할 설정파일 적용
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 순서 적용 어노테이션
public class TestDeptService {

	@Autowired
	private DeptService deptService;
	@Autowired
	private DeptRepository deptRepository;
	
	@Test
	@Order(1)
	@Commit
	@Transactional
	public void A001_DEPT_TABLE_입력() {
		//GIVEN
		List<Dept> deptList = new ArrayList<Dept>();
		deptList.add(Dept.builder().deptno(10).dname("ACCOUNTING").loc("NEW_YORK").build());
		deptList.add(Dept.builder().deptno(20).dname("RESEARCH").loc("DALLAS").build());
		deptList.add(Dept.builder().deptno(30).dname("SALES").loc("NEW_YORK").build());
		deptList.add(Dept.builder().deptno(40).dname("OPERATIONS").loc("BOSTON").build());
		
		//WHEN
		for(Dept dept: deptList) {
			deptService.deptInsert(dept);
		}
		
		//THEN
		for(Dept dept: deptList) {
			Integer deptno = dept.getDeptno();
			ResponseDeptDto responseDeptDto = deptService.deptDetail(deptno);
			Assertions.assertThat(responseDeptDto.getDeptno()).isEqualTo(deptno);
		}
	}
		
		@Test
		@Order(2)
		@Commit
		@Transactional
		public void A002_DEPT_TABLE_수정() {
			//GIVEN
			String changeDname = "ACCOUINTING002";
			
			//WHEN
			deptService.deptUpdate(Dept.builder().deptno(10).dname(changeDname).loc("NEW_YORK").build());
			ResponseDeptDto responseDeptDto = deptService.deptDetail(10);
			log.debug("결과: "+responseDeptDto.toString());
			
			//THEN
			Assertions.assertThat(changeDname).isEqualTo(responseDeptDto.getDname());
		}
		
		
		@Test
		@Order(3)
		@Commit
		public void A003_DEPT_TABLE_삭제() {
			//GIVEN
			Integer[] deptnos = {10,20,30,40};
			
			for(Integer deptno : deptnos) {
				//WHEN
				deptService.deptDelete(deptno);
				boolean isPresent = deptRepository.findById(deptno).isPresent();
				log.debug(String.valueOf(isPresent));
				
				//THEN
				Assertions.assertThat(false).isEqualTo(isPresent);
			}
			
		}
		
	
	
}
