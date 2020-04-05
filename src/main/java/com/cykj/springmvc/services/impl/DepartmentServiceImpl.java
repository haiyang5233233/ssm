package com.cykj.springmvc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cykj.springmvc.base.BaseServiceImpl;
import com.cykj.springmvc.entity.Department;
import com.cykj.springmvc.mapper.DepartmentMapper;
import com.cykj.springmvc.services.DepartmentService;

@SuppressWarnings("all")
@Transactional
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	@Autowired
	public DepartmentMapper departmentMapper;
	
	public List<Department> query(){
		return departmentMapper.query();
	}
}