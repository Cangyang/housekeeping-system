package com.ytf.mail;
/**
 * 
 * @author Administrator
 *�����ʼ���Ҫʹ�õĻ�����Ϣ
 */

import java.util.Properties;

public class MailSendInfo {
	private String mailServerHost;
	private String mailServerPort = "25";
	private String fromAddress;
	private String toAddress;
	private String userName;
	private String password;
	//�Ƿ���Ҫ�����֤
	private boolean validate = false;
	private String subject;
	private String content;
	public String getMailServerHost() {
		return mailServerHost;
	}
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	public String getMailServerPort() {
		return mailServerPort;
	}
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	//�ʼ��������ļ���
	private String[] attachFileNames;
	/**
	 * ����ʼ��Ự����
	 */
    public Properties getProperties() {
    	Properties p = new Properties();
    	p.put("mail.smtp.host", this.mailServerHost);
    	p.put("mail.smto.port", this.mailServerPort);
    	p.put("mail.smtp.auth", validate ? "true" : "false");
    	return p;
    }
    
}
