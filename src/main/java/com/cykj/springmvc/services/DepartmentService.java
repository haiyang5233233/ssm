package com.cykj.springmvc.services;
import java.util.List;

import com.cykj.springmvc.base.BaseService;
import com.cykj.springmvc.entity.Department;

public interface DepartmentService extends BaseService<Department>{
	List<Department> query();
}


