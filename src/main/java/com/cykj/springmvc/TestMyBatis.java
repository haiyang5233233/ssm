package com.cykj.springmvc;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.cykj.springmvc.entity.Department;
import com.cykj.springmvc.services.DepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})

public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	@Resource
	private DepartmentService departmentService = null;
	@Test
	public void test1() {
		Department dep = departmentService.selectByPrimaryKey(40);
		
		logger.info(dep.toString());
	}

}
