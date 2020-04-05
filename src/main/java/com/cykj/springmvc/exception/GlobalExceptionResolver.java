package com.cykj.springmvc.exception;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class GlobalExceptionResolver implements HandlerExceptionResolver {
	protected final Logger logger = Logger.getLogger(GlobalExceptionResolver.class);

	/**
	 * 全局异常处理，返回JSON格式信息<br/>
	 * {"code":"00","message":"操作成功"}
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			Writer w = new StringWriter();
			ex.printStackTrace(new PrintWriter(w));
			if (ex.getClass().toString().indexOf("ClientAbortException") < 0) {
				logger.error("\n" + w.toString());
			}
			w.close();
		} catch (Exception e) {
			logger.error("\n" + e.toString());
		}

		if (ex instanceof LoginException) {// 登陆异常重定向
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // 如果是ajax请求响应头会有，x-requested-with
				MappingJackson2JsonView view = new MappingJackson2JsonView();
				view.setPrettyPrint(true);
				ModelAndView modelAndView = new ModelAndView(view);
				Map<String, Object> map = modelAndView.getModelMap();
				map.put("code", "timeout");
				map.put("message", "请重试");
				return modelAndView;

			} else {
				return new ModelAndView("redirect:/pages/yylogin.jsp");
			}
		}
		if (ex instanceof AppTokenException) {
			ModelAndView mv = new ModelAndView();
			response.setStatus(HttpStatus.OK.value()); // 设置状态码
			response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			response.setCharacterEncoding("UTF-8"); // 避免乱码
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			try {
				response.getWriter().write("{\"code\":15,\"message\":\"" + ex.getMessage() + "\",\"resultData\":\"\"}");
			} catch (IOException e) {
				logger.error("与客户端通讯异常:" + e.getMessage(), e);
			}

			return mv;
		}
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		ModelAndView modelAndView = new ModelAndView(view);
		Map<String, Object> map = modelAndView.getModelMap();
		map.put("code", "-9999");
		map.put("message", "服务器异常");
		PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(ex.getClass());

		List<String> includeProperties = new ArrayList<>();
		includeProperties.add("message");
		includeProperties.add("code");
		includeProperties.add("error");

		for (PropertyDescriptor descriptor : descriptors) {
			String name = descriptor.getName();
			if (includeProperties.contains(name)) {
				Method method = descriptor.getReadMethod();
				if (method != null) {
					try {
						boolean readAble = Modifier.isPublic(method.getDeclaringClass().getModifiers());
						if (readAble) {
							Object value = method.invoke(ex);
							if (map.containsKey(name)) {
								if (value != null)
									map.put(name, value);
							} else {
								map.put(name, value);
							}
						}
					} catch (Throwable e) {
						logger.error("\n" + e.getMessage());
					}
				}
			}
		}
		return modelAndView;
	}

}
