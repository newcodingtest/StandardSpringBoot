package com.yoon.standard.domain.dept;

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

import com.yoon.standard.domain.dept.Dept;
import com.yoon.standard.domain.dept.DeptRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles({"dev", "db-h2"}) //테스트에 적용할 설정파일 적용
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 순서 적용 어노테이션
public class TestDeptRepository {
	
	@Autowired
	DeptRepository deptRepository;
	
	@Test
	@Order(1)
	@Commit
	public void A001_DEPT_TABLE_입력() {
		//GIVEN
		List<Dept> deptList = new ArrayList<Dept>();
		deptList.add(Dept.builder().deptNo(10).dname("ACCOUNTING").loc("NEW_YORK").build());
		deptList.add(Dept.builder().deptNo(20).dname("RESEARCH").loc("DALLAS").build());
		deptList.add(Dept.builder().deptNo(30).dname("SALES").loc("NEW_YORK").build());
		deptList.add(Dept.builder().deptNo(40).dname("OPERATIONS").loc("BOSTON").build());
		
		//WHEN
		deptRepository.saveAll(deptList);
		
		//THEN
		Assertions.assertThat(deptRepository.findById(10).isPresent()).isEqualTo(true);
		Assertions.assertThat(deptRepository.findById(20).isPresent()).isEqualTo(true);
		Assertions.assertThat(deptRepository.findById(30).isPresent()).isEqualTo(true);
		Assertions.assertThat(deptRepository.findById(40).isPresent()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	@Commit
	public void A002_DEPT_TABLE_수정() {
		//GIVEN
		String changeDname = "ACCOUINTING002";
		
		//WHEN
		deptRepository.save(Dept.builder().deptNo(10).dname(changeDname).loc("NEW YORK").build());
		Dept dept = deptRepository.findById(10).get();
		
		log.debug("결과: "+dept.toString());
		
		//THEN
		Assertions.assertThat(changeDname).isEqualTo(dept.getDname());

	}
	
	@Test
	@Order(3)
	@Commit
	public void A003_DEPT_TABLE_삭제() {
		//GIVEN
		Integer[] deptnos = {10,20,30,40};
		
		for(Integer deptno : deptnos) {
			//WHEN
			deptRepository.delete(Dept.builder().deptNo(deptno).build());
			boolean isPresent = deptRepository.findById(deptno).isPresent();
			log.debug(String.valueOf(isPresent));
			
			//THEN
			Assertions.assertThat(false).isEqualTo(isPresent);
		}
		
	}
	
	
}
