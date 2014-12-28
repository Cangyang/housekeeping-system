package com.ytf.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.util.Properties;
import com.ytf.core.ConnDB;
import com.ytf.mail.*;
import java.sql.*;

import javax.mail.MessagingException;

@SuppressWarnings("serial")
public class ForgetPass extends ActionSupport{
	private ConnDB mydb;
	private static String propFileName = "/com/connDB.properties";
	private static Properties prop = new Properties();
	private static String emailUser = null;
	private static String emailPass = null;
	private static String emailServerHost = null;
	private static String emailServerPort = null;
	private String toAddress = null;
	private String userName = null;
	private String userType = null;
	private String userPass = null;
	
	public ForgetPass() {
		//验证用户输入
		
		
		try {
			//将Properties文件读取到InputStream对象中
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in);
			emailUser = prop.getProperty("USER_NAME");
			emailPass= prop.getProperty("USER_PASSWORD");
			emailServerHost = prop.getProperty("USER_SERVER_HOST");
			emailServerPort = prop.getProperty("USER_SERVER_PORT");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//通过前台页面获取用户信息用来发送其密码
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	//发送密码到用户邮箱
	public String SendPass() {
	
	if( validateGetPass()){
	
	String Type = "tb_" + userType;
	mydb = new ConnDB();

	try {
	String sql = "select password from "+Type+ " where name ='"+userName+"' and email like '" +toAddress+"'";
	ResultSet rs = mydb.executeQuery(sql);
	if(!rs.next()) {
		addActionMessage("数据查询失败，您的邮箱可能未注册！");
		return INPUT;
	} else {
		
		userPass = rs.getString("password");
		
		
		
	}
	} catch (SQLException e) {
		e.printStackTrace();
		mydb.close();
	}
	

	String content = userName + ",您好!您的密码为"+userPass + ",请妥善保管.";
	MailSendInfo mailInfo = new MailSendInfo();
	mailInfo.setMailServerHost(emailServerHost);
	mailInfo.setMailServerPort(emailServerPort);
	mailInfo.setValidate(true);
	mailInfo.setUserName(emailUser);
	mailInfo.setPassword(emailPass);
	mailInfo.setFromAddress(emailUser);
	mailInfo.setToAddress(toAddress);
	mailInfo.setSubject("取回密码");
	mailInfo.setContent(content);
	SimpleMailSender sms = new SimpleMailSender();
    
    boolean flag = sms.sendTextMail(mailInfo) ;
    
	    if(flag) {
	    	addActionMessage("邮件发送成功，请注意查收!");
		    return SUCCESS;
	    } else {
	    	addActionMessage("您当前网络不稳定发送失败，请检查网络连接后重试!");
	    	return INPUT;
	    }
	}
	 return INPUT;   
	
	}
	/**
	 * 功能 验证用户的输入
	 */
	public boolean validateGetPass() {
	
		if(userType==null||userType.equals("")) {
			addFieldError("userType","请选择注册的用户类型");
		    return false;
		}else if (userName==null||userName.equals("")) {
			addFieldError("userName","请输入注册的用户名!");
			return false;
		} else if(toAddress==null||toAddress.equals("")) {
			addFieldError("toAddress","请输入注册的用户邮箱");
			return false;
		} else {
			return true;
		}
	}

}
