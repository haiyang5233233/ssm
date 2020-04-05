package com.cykj.springmvc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cykj.springmvc.entity.Department;
import com.cykj.springmvc.mapper.DepartmentMapper;
import com.cykj.springmvc.services.DepartmentService;

@SuppressWarnings("all")
@Transactional
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	public DepartmentMapper departmentMapper;
	

	@Override
	public void insertDep(Department dep) {
		 departmentMapper.insert(dep);
	}

	@Override
	public Department selectByPrimaryKey(int id) {
		return departmentMapper.selectByPrimaryKey(id);
	}
}