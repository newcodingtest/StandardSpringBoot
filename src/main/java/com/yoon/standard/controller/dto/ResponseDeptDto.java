package com.yoon.standard.controller.dto;

import com.yoon.standard.core.dto.ShareDTO; 
import com.yoon.standard.domain.dept.Dept;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseDeptDto extends ShareDTO {

	private Integer deptno;
	private String dname;
	private	String loc; 
	
	public ResponseDeptDto(Dept entity) {
		this.deptno = entity.getDeptno();
		this.dname = entity.getDname();
		this.loc = entity.getLoc();
	}
}
