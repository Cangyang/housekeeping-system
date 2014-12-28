package com.ytf.actionForm;

import java.io.*;;

public class UserInfoForm {
	//保姆状态
	private String status;
	//保姆编号
	private int id;
	//保姆姓名
	private String surname;
	//保姆籍贯
	private String hometown;
	//保姆性别
	private int sex;
	//保姆出生日期
	private String birthDate;
	//保姆联系方式
	private String phone;
	//保姆婚姻状况
	private int marriage;
	//保姆学历
	private int education;
	//保姆住址
	private String address;
	//保姆证书
	private String [] cert;
	//保姆技能
	private String [] skills;
	//保姆语言
	private String [] lang;
	//保姆口味
	private String [] flavor;
	//保姆头像
	private File avatar;
	private String avatarFileName;
	private String avatarContentType;
	//保姆简介
	private String profile;
	//上传时间
	private String uptime;
	//保姆工时
	private int worktime;
	//食宿
	private int accomm;
	//保姆工作类型
	private int worktype;
	//供应信息标题
	private String headLine;
	//供应信息内容
	private String content;
	//发布时间
	private String postTime;
	//薪资
	private String pay;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param userId
	 */
	public void setId(int userId) {
		this.id = userId;
	}
	/**
	 * @return uptime
	 */
	public String getUptime() {
		return uptime;
	}
	/**
	 * @param uptime
	 */
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	/**
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return hometown
	 */
	public String getHometown() {
		return hometown;
	}
	/**
	 * @param hometown
	 */
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	/**
	 * @return sex
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * @param sex
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * @return birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return marriage
	 */
	public int getMarriage() {
		return marriage;
	}
	/**
	 * @param marriage
	 */
	public void setMarriage(int marriage) {
		this.marriage = marriage;
	}
	/**
	 * @return education
	 */
	public int getEducation() {
		return education;
	}
	/**
	 * @param education
	 */
	public void setEducation(int education) {
		this.education = education;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return cert
	 */
	public String[] getCert() {
		return cert;
	}
	/**
	 * @param cert
	 */
	public void setCert(String[] cert) {
		this.cert = cert;
	}
	/**
	 * @return skills
	 */
	public String[] getSkills() {
		return skills;
	}
	/**
	 * @param skills
	 */
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	/**
	 * @return lang
	 */
	public String[] getLang() {
		return lang;
	}
	/**
	 * @param lang
	 */
	public void setLang(String[] lang) {
		this.lang = lang;
	}
	/**
	 * @return flavor
	 */
	public String[] getFlavor() {
		return flavor;
	}
	/**
	 * @param flavor
	 */
	public void setFlavor(String[] flavor) {
		this.flavor = flavor;
	}
	/**
	 * @return avatar
	 */
	public File getAvatar() {
		return avatar;
	}
	/**
	 * @param avatar
	 */
	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}
	/**
	 * @return avatarFileName
	 */
	public String getAvatarFileName() {
		return avatarFileName;
	}
	/**
	 * @param avatarFileName
	 */
	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}
	/**
	 * @return avatarContentType
	 */
	public String getAvatarContentType() {
		return avatarContentType;
	}
	/**
	 * @param avatarContentType
	 */
	public void setAvatarContentType(String avatarContentType) {
		this.avatarContentType = avatarContentType;
	}
	/**
	 * @return profile
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * @param profile
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * @return the worktime
	 */
	public int getWorktime() {
		return worktime;
	}
	/**
	 * @param worktime the worktime to set
	 */
	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}
	/**
	 * @return the accomm
	 */
	public int getAccomm() {
		return accomm;
	}
	/**
	 * @param accomm the accomm to set
	 */
	public void setAccomm(int accomm) {
		this.accomm = accomm;
	}
	/**
	 * @return the worktype
	 */
	public int getWorktype() {
		return worktype;
	}
	/**
	 * @param worktype the worktype to set
	 */
	public void setWorktype(int worktype) {
		this.worktype = worktype;
	}
	/**
	 * @return the headLine
	 */
	public String getHeadLine() {
		return headLine;
	}
	/**
	 * @param headLine the headLine to set
	 */
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the postTime
	 */
	public String getPostTime() {
		return postTime;
	}
	/**
	 * @param postTime the postTime to set
	 */
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	/**
	 * @return the pay
	 */
	public String getPay() {
		return pay;
	}
	/**
	 * @param pay the pay to set
	 */
	public void setPay(String pay) {
		this.pay = pay;
	}
	
	

}
