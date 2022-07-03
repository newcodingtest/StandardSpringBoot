package com.yoon.standard.domain.dept;

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
	private Integer deptno;
	
	private String dname;
	
	private	String loc; 
	
	@Builder
	public Dept(Integer deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	
	

}
