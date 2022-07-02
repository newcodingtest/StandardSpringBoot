package com.yoon.standard.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Dept {
	
	@Id
	private Integer deptNo;
	
	private String dname;
	
	private	String loc; 
	
	@Builder
	public Dept(Integer deptNo, String dname, String loc) {
		this.deptNo = deptNo;
		this.dname = dname;
		this.loc = loc;
	}
	
	

}
