package com.cykj.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cykj.springmvc.TestMyBatis;
import com.cykj.springmvc.entity.Department;
import com.cykj.springmvc.entity.LayUIData;
import com.cykj.springmvc.mapper.DepartmentMapper;
import com.cykj.springmvc.services.DepartmentService;

@SuppressWarnings("all")
@Controller
@RequestMapping("/departmentControl/")
public class DepartmentControl {

	@Autowired(required = true)
	// @Qualifier("departmentService")//@Qualifier的参数名称必须为我们之前定义@Service注解的名称之一
	DepartmentService departmentService;
	private Logger logger = Logger.getLogger(DepartmentControl.class);

	/**
	 * 显示分页列表 返回值以流的形式返回去//@ResponseBody--api使用
	 */
	@RequestMapping(value = "get")
	public String getDepartment(HttpServletRequest request, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		Department dep = departmentService.get(id);

		model.addAttribute("department", dep);
		return "showDepartment";
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public Object list() {
		List<Department> data=departmentService.query();
		LayUIData<Department> list=new LayUIData<Department>();
		list.setCode("0");
		list.setCount(data.size()+"");
		list.setData(data);
		list.setMsg("");
		return list;
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request) {
		String name=request.getParameter("name");
		String remark=request.getParameter("remark");
		Department data=new Department();
		data.setName(name);
		data.setRemark(remark);
		departmentService.save(data);
		return "OK";
	}
	@RequestMapping(value = "/showView", method = RequestMethod.GET)
	public ModelAndView showDepartment(HttpServletRequest request) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Department dep = departmentService.get(Integer.parseInt(request.getParameter("id")));
			logger.info("查询结果:" + dep.getName());
			return new ModelAndView("showDepartment", "department", dep);// 返回登录页面
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			return null;
		}
	}
}
