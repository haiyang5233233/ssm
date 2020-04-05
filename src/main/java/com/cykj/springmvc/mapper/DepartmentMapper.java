package com.cykj.springmvc.mapper;

import java.util.List;

import com.cykj.springmvc.base.BaseDao;
import com.cykj.springmvc.entity.Department;

public interface DepartmentMapper extends BaseDao<Department>{
    List<Department> query();
}