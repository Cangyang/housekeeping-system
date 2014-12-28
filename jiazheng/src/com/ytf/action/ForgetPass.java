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
		//��֤�û�����
		
		
		try {
			//��Properties�ļ���ȡ��InputStream������
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
	
	//ͨ��ǰ̨ҳ���ȡ�û���Ϣ��������������
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
	
	//�������뵽�û�����
	public String SendPass() {
	
	if( validateGetPass()){
	
	String Type = "tb_" + userType;
	mydb = new ConnDB();

	try {
	String sql = "select password from "+Type+ " where name ='"+userName+"' and email like '" +toAddress+"'";
	ResultSet rs = mydb.executeQuery(sql);
	if(!rs.next()) {
		addActionMessage("���ݲ�ѯʧ�ܣ������������δע�ᣡ");
		return INPUT;
	} else {
		
		userPass = rs.getString("password");
		
		
		
	}
	} catch (SQLException e) {
		e.printStackTrace();
		mydb.close();
	}
	

	String content = userName + ",����!��������Ϊ"+userPass + ",�����Ʊ���.";
	MailSendInfo mailInfo = new MailSendInfo();
	mailInfo.setMailServerHost(emailServerHost);
	mailInfo.setMailServerPort(emailServerPort);
	mailInfo.setValidate(true);
	mailInfo.setUserName(emailUser);
	mailInfo.setPassword(emailPass);
	mailInfo.setFromAddress(emailUser);
	mailInfo.setToAddress(toAddress);
	mailInfo.setSubject("ȡ������");
	mailInfo.setContent(content);
	SimpleMailSender sms = new SimpleMailSender();
    
    boolean flag = sms.sendTextMail(mailInfo) ;
    
	    if(flag) {
	    	addActionMessage("�ʼ����ͳɹ�����ע�����!");
		    return SUCCESS;
	    } else {
	    	addActionMessage("����ǰ���粻�ȶ�����ʧ�ܣ������������Ӻ�����!");
	    	return INPUT;
	    }
	}
	 return INPUT;   
	
	}
	/**
	 * ���� ��֤�û�������
	 */
	public boolean validateGetPass() {
	
		if(userType==null||userType.equals("")) {
			addFieldError("userType","��ѡ��ע����û�����");
		    return false;
		}else if (userName==null||userName.equals("")) {
			addFieldError("userName","������ע����û���!");
			return false;
		} else if(toAddress==null||toAddress.equals("")) {
			addFieldError("toAddress","������ע����û�����");
			return false;
		} else {
			return true;
		}
	}

}
