package com.yoon.standard.service.dept;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yoon.standard.controller.dto.ResponseDeptDto;
import com.yoon.standard.core.exception.BusinessException;
import com.yoon.standard.domain.dept.Dept;
import com.yoon.standard.domain.dept.DeptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

	private final DeptRepository deptRepository;
	
	@Override
	public List<ResponseDeptDto> deptList() throws BusinessException {
		List<ResponseDeptDto> responseDeptDtoList = new ArrayList();
		deptRepository.findAll().forEach(dept -> {
			log.debug(dept.toString());
			responseDeptDtoList.add(new ResponseDeptDto(dept));
		});
		return responseDeptDtoList;
	}

	@Override
	public ResponseDeptDto deptDetail(Integer deptno) throws BusinessException {
		Optional<Dept> optionalDept = deptRepository.findById(deptno);
		Dept dept = optionalDept.orElseThrow(() -> new BusinessException("해당 부서 번호에 대한 정보가 없습니다." + deptno));
		return new ResponseDeptDto(dept);
	}

	@Transactional
	@Override
	public ResponseDeptDto deptInsert(Dept dept) throws BusinessException {
		Optional<Dept> optionalDept = deptRepository.findById(dept.getDeptno());
		if(!optionalDept.isPresent()) {
			return new ResponseDeptDto(deptRepository.save(dept));
		}else {
			throw new BusinessException("해당 부서가 이미 존재합니다."+dept.getDname());
		}
	}

	@Transactional
	@Override
	public ResponseDeptDto deptUpdate(Dept dept) throws BusinessException {
		Optional<Dept> optionalDept = deptRepository.findById(dept.getDeptno());
		if(optionalDept.isPresent()) {
			return new ResponseDeptDto(deptRepository.save(dept));
		}else {
			throw new BusinessException("해당 부서가 존재하지 않습니다."+dept.getDname());
		}
	}

	@Transactional
	@Override
	public void deptDelete(Integer deptno) throws BusinessException {
		deptRepository.deleteById(deptno);
	}
}
