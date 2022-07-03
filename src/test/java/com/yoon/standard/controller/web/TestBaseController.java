package com.yoon.standard.controller.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
@ActiveProfiles({"dev", "db-h2"}) //테스트에 적용할 설정파일 적용
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 순서 적용 어노테이션
public class TestBaseController {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	@Order(1)
	public void getIndex() throws Exception {
		//GIVEN
		String data = "GET";
		
		//WHEN
		MultiValueMap<String, String>params = new LinkedMultiValueMap<>();
		params.add("data", data);
		
		MvcResult mvcResult = mockMvc.perform(get("/").params(params))
				.andDo(print())
				.andExpect(status().is(HttpStatus.OK.value()))
				.andReturn();
		
		//THEN
		String content = mvcResult.getResponse().getContentAsString();
		Assertions.assertThat(content).contains(data);
	}
	
	@Test
	@Order(2)
	public void postIndex() throws Exception {
		//GIVEN
		String data = "POST";
		
		//WHEN
		MultiValueMap<String, String>params = new LinkedMultiValueMap<>();
		params.add("data", data);
		
		MvcResult mvcResult = mockMvc.perform(post("/").params(params))
				.andDo(print())
				.andExpect(status().is(HttpStatus.OK.value()))
				.andReturn();
		
		//THEN
		String content = mvcResult.getResponse().getContentAsString();
		Assertions.assertThat(content).contains(data);
	}
}
