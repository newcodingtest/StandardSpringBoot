package com.yoon.standard.service.dept;

import java.util.List;

import com.yoon.standard.core.dto.ResponseDeptDto;
import com.yoon.standard.core.exception.BusinessException;
import com.yoon.standard.domain.dept.Dept;


public interface DeptService {
	public List<ResponseDeptDto> deptList() throws BusinessException;
	public ResponseDeptDto deptDetail(Integer deptno) throws BusinessException;
	public ResponseDeptDto deptInsert(Dept dept) throws BusinessException;
	public ResponseDeptDto deptUpdate(Dept dept) throws BusinessException;
	public void deptDelete(Integer deptno) throws BusinessException;
}
