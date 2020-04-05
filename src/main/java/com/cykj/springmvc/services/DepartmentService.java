package com.cykj.springmvc.services;
import java.util.List;

import com.cykj.springmvc.entity.Department;

public interface DepartmentService {
	
	/**
	 * 插入部门
	 * @param dep
	 */
	void insertDep(Department dep);
	
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	Department selectByPrimaryKey(int id);
}


