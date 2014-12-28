package com.ytf.actionForm;
//新闻实体
public class NewsForm {
	//新闻编号
	private int id;
	//新闻标题
	private String title;
	//新闻内容
	private String content;
	//发布日期
	private String issuedate;
	//新闻类别
	private String type;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the issuedate
	 */
	public String getIssuedate() {
		return issuedate;
	}
	/**
	 * @param issuedate the issuedate to set
	 */
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	

}
