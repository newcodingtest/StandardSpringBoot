package com.yoon.standard.core.dto;

import com.yoon.standard.domain.dept.Dept;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseDeptDto extends ShareDTO {

	private Integer deptNo;
	private String dname;
	private	String loc; 
	
	public ResponseDeptDto(Dept entity) {
		this.deptNo = entity.getDeptNo();
		this.dname = entity.getDname();
		this.loc = entity.getLoc();
	}
}
