package com.yoon.standard.dto;


import com.yoon.standard.domain.Dept;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDeptDto {

	private Integer deptNo;
	private String dname;
	private	String loc;
	
	@Builder
	public RequestDeptDto(Integer deptNo,String dname,String loc) {
		this.deptNo = deptNo;
		this.dname = dname;
		this.loc = loc;
	}
	
	public Dept toEntity() {
		return Dept.builder().deptNo(deptNo).dname(dname).loc(loc).build();
	}
}
