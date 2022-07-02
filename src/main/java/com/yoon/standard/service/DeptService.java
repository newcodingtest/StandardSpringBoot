package com.yoon.standard.service;

import java.util.List;

import com.yoon.standard.domain.Dept;
import com.yoon.standard.dto.ResponseDeptDto;


public interface DeptService {
	public List<ResponseDeptDto> deptList() throws BusinessException;
	public ResponseDeptDto deptDetail(Integer deptno) throws BusinessException;
	public ResponseDeptDto deptInsert(Dept dept) throws BusinessException;
	public ResponseDeptDto deptUpdate(Dept dept) throws BusinessException;
	public void deptDelete(Integer deptno) throws BusinessException;
}
