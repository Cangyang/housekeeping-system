package com.ytf.actionForm;

public class RegisterForm {
	private String type;
	private String name;
	private String email;
	private String pwd;
	private String repwd;
	private String securityCode;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String passWord) {
		this.pwd = passWord;
	}
	
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String rePwd) {
		this.repwd = rePwd;
	}
	
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
