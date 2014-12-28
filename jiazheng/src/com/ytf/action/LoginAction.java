package com.ytf.action;

import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.ytf.actionSuper.MySuperAction;
import com.ytf.actionForm.LoginForm;
import com.ytf.core.OpDB;

/**
 * �û���¼Action
 * @version 1.0
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends MySuperAction  {	
	//��¼ʵ��
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
	 * ���ܣ�Ĭ����֤�Ƿ������û��������� 
	 */
	public String execute() {
		String type=user.getUserType();
		String name=user.getUsername();
		String password=user.getPassword();
		String securityCode=user.getSecurityCode();
		if(type==null||type.equals("")){
			addFieldError("user.userType","��ѡ���¼����");
			return INPUT;
		} 
		if(name==null||name.equals("")){
			addFieldError("user.username","�������û���!");
			return INPUT;
		}
		if(password==null||password.equals("")){
			addFieldError("user.password","����������!");
			return INPUT;
		}
		if(securityCode==null||securityCode.equals("")){
			addFieldError("user.securityCode","��������֤��!");
			return INPUT;
		}
		if(!Login()) {
			return INPUT;
		}
		return SUCCESS;
	}	
	/**
	 * �û���¼
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public boolean Login () {
		//�ж��û�����
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
		//Struts2��Map���͵�session
		//���տͻ��˴�������֤��
		String serverCode = (String)session.get("SESSION_SECURITY_CODE");
		//�����ж��û���������
		OpDB myOp = new OpDB();
		boolean checkResult = myOp.LogOn(userType,user.getUsername(),user.getPassword());
		if(!checkResult) {
			addActionMessage("�û������������!");
			return false;
		} 
		if(!serverCode.equals(user.getSecurityCode().toUpperCase())) {
			addActionMessage("��֤�벻��ȷ������ϸ���������룡");
			return false;
		}
		   session.put("username",user.getUsername());
		   return true;
	}
	
	/** ���ܣ��˳���¼ */
	public String Logout(){		
		session.clear();	
		return "logout";
	}
	
	
	
}
