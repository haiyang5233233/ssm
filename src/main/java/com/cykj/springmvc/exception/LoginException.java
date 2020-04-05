package com.cykj.springmvc.exception;
/**
 * 登陆异常类,全局异常对此类进行重定向login页面
 * @author zyj
 *
 */
public class LoginException extends RuntimeException{

	private static final long serialVersionUID = -2716772129006528389L;
	private String message   = null;

	public LoginException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
