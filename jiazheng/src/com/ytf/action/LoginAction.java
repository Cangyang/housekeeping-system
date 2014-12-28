package com.ytf.action;

import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.ytf.actionSuper.MySuperAction;
import com.ytf.actionForm.LoginForm;
import com.ytf.core.OpDB;

/**
 * 用户登录Action
 * @version 1.0
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends MySuperAction  {	
	//登录实体
	private LoginForm user;
	public LoginForm getUser() {
		return user;
	}
	public void setUser(LoginForm user) {
		this.user = user;
	}
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 * 功能：默认验证是否输入用户名和密码 
	 */
	public String execute() {
		String type=user.getUserType();
		String name=user.getUsername();
		String password=user.getPassword();
		String securityCode=user.getSecurityCode();
		if(type==null||type.equals("")){
			addFieldError("user.userType","请选择登录类型");
			return INPUT;
		} 
		if(name==null||name.equals("")){
			addFieldError("user.username","请输入用户名!");
			return INPUT;
		}
		if(password==null||password.equals("")){
			addFieldError("user.password","请输入密码!");
			return INPUT;
		}
		if(securityCode==null||securityCode.equals("")){
			addFieldError("user.securityCode","请输入验证码!");
			return INPUT;
		}
		if(!Login()) {
			return INPUT;
		}
		return SUCCESS;
	}	
	/**
	 * 用户登录
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public boolean Login () {
		//判断用户类型
		String userType;
		if("employee".equals(user.getUserType())) {
			userType = "tb_employee";
			session.put("userType", "employee");
		} else if ("employer".equals(user.getUserType())) {
			userType = "tb_employer";
			session.put("userType", "employer");
		} else if ("company".equals(user.getUserType())) {
			userType = "tb_company";
			session.put("userType", "company");
		} else {
			userType = "tb_manager";
		}	
		//Struts2中Map类型的session
		//接收客户端传来的验证码
		String serverCode = (String)session.get("SESSION_SECURITY_CODE");
		//继续判断用户名和密码
		OpDB myOp = new OpDB();
		boolean checkResult = myOp.LogOn(userType,user.getUsername(),user.getPassword());
		if(!checkResult) {
			addActionMessage("用户名或密码错误!");
			return false;
		} 
		if(!serverCode.equals(user.getSecurityCode().toUpperCase())) {
			addActionMessage("验证码不正确，请仔细看看再填入！");
			return false;
		}
		   session.put("username",user.getUsername());
		   return true;
	}
	
	/** 功能：退出登录 */
	public String Logout(){		
		session.clear();	
		return "logout";
	}
	
	
	
}
