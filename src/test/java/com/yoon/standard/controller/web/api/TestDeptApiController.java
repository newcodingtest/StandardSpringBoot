package com.yoon.standard.controller.web.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoon.standard.controller.dto.ResponseDeptDto;
import com.yoon.standard.core.dto.ShareDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
@ActiveProfiles({"dev", "db-maria"}) //테스트에 적용할 설정파일 적용
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 순서 적용 어노테이션
public class TestDeptApiController {

	@Autowired
	private MockMvc mockMvc;
	
	private String version = "v1";
	
	@Test
	@Order(1)
	public void A001_deptInsert()throws Exception{
		//GIVEN
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("deptno", "10");
		params.add("dname", "ACCOUNTING 회계");
		params.add("loc", "NEW YORK 뉴욕");
		
		//WHEN
		MvcResult result = mockMvc.perform(post("/api/"+ version + "/dept").params(params))
			.andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		Gson gson = new Gson();
		
		ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class);
		
		//THEN
		Assertions.assertThat(responseDeptDto.getDeptno()).isEqualTo(10);
	}
	
	@Test
	@Order(2)
	public void A001_deptUpdate()throws Exception{
		//GIVEN
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("deptno", "10");
		params.add("dname", "ACCOUNTING2");
		params.add("loc", "NEW YORK");
		
		//WHEN
		MvcResult result = mockMvc.perform(put("/api/"+ version + "/dept").params(params))
			.andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		Gson gson = new Gson();
		
		ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class);
		
		//THEN
		Assertions.assertThat(responseDeptDto.getDname()).isEqualTo("ACCOUNTING2");
	}
	
	@Test
	@Order(3)
	public void A001_deptList()throws Exception{
		//WHEN
		MvcResult result = mockMvc.perform(get("/api/"+ version + "/dept"))
			.andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		Gson gson = new Gson();
		
		List<ResponseDeptDto> responseDeptDto = gson.fromJson(content, new TypeToken<List<ResponseDeptDto>>(){}.getType());
		
		//THEN
		responseDeptDto.forEach(dept -> {
			log.debug(dept.toString());
		});
	}
	
	@Test
	@Order(4)
	public void A001_deptDetail()throws Exception{
		//GIVEN
		int deptno = 10;
		
		//WHEN
		MvcResult result = mockMvc.perform(get("/api/"+ version + "/dept/"+deptno))
			.andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		Gson gson = new Gson();
		
		ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class);
		
		//THEN
		Assertions.assertThat(responseDeptDto.getDeptno()).isEqualTo(deptno);
	}
	
	@Test
	@Order(5)
	public void A001_deptDelete()throws Exception{
		//GIVEN
		int deptno = 10;
		
		//WHEN
		MvcResult result = mockMvc.perform(delete("/api/"+ version + "/dept/"+deptno))
			.andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		Gson gson = new Gson();
		
		ShareDTO shareDTO = gson.fromJson(content, ShareDTO.class);
		
		//THEN
		log.debug(shareDTO.toString());
	}
}
