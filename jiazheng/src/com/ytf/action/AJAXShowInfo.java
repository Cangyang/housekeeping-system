package com.ytf.action;

import net.sf.json.JSONArray;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger; 
import com.ytf.actionSuper.MySuperAction;
import com.ytf.actionForm.CompanyCardForm;
import com.ytf.actionForm.GQInfoForm;
import com.ytf.actionForm.NewsForm;
import com.ytf.core.ConnDB;
import java.util.LinkedList;
import java.util.List;

/*
 * �๦��:��ǰ̨AJAX������ʾ��Ϣ
 */
public class AJAXShowInfo extends MySuperAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AJAXShowInfo.class);
	private ConnDB db = new ConnDB();
	//ǰ̨������Ϣurl����
	private String type;
	private String infoID;
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
	/**
	 * @return the infoID
	 */
	public String getInfoID() {
		return infoID;
	}
	/**
	 * @param infoID the infoID to set
	 */
	public void setInfoID(String infoID) {
		this.infoID = infoID;
	}
	//ǰ̨������Ϣurl����
	private String newsID;
	/**
	 * @return the newsID
	 */
	public String getNewsID() {
		return newsID;
	}
	/**
	 * @param newsID the newsID to set
	 */
	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}
	/*
	 * ����:ǰ̨��ʾ������Ƭ
	 */
	public String showCompanyCard() throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =  response.getWriter();
		List<CompanyCardForm> list = new LinkedList<CompanyCardForm>();
		String sql_queryCompany = "select companyName, companyDesc, companyLinkman, companyContacts from tb_company where companyUpTime is not null";
		try {
			ResultSet rs_queryCompany = db.executeQuery(sql_queryCompany);
			logger.info("��ѯ������Ƭ,ִ��SQL:"+sql_queryCompany);
			while(rs_queryCompany.next()) {
				CompanyCardForm company = new CompanyCardForm();
				company.setName(rs_queryCompany.getString("companyName"));
				company.setDesc(rs_queryCompany.getString("companyDesc"));
				company.setLinkman(rs_queryCompany.getString("companyLinkman"));
				company.setContacts(rs_queryCompany.getString("companyContacts"));
				list.add(company);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		JSONArray company = JSONArray.fromObject(list);
		out.print(company);
		out.flush();
		out.close();
		db.close();
		return "company";
	}
	/*
	 * ����:ǰ̨��ʾ��ʾ���ű�������
	 */
	public String showNews () throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =  response.getWriter();
		List<NewsForm> list = new LinkedList<NewsForm>();
		String sql_queryNews = "select id, title, content, issuedate, type from tb_news ORDER BY issuedate DESC";
		try {
			ResultSet rs_queryNews = db.executeQuery(sql_queryNews);
			logger.info("��ѯ������Ϣ,ִ��SQL:"+sql_queryNews);
			while (rs_queryNews.next()) {
				NewsForm news = new NewsForm();
				news.setId(rs_queryNews.getInt("id"));
				news.setTitle(rs_queryNews.getString("title"));
				news.setContent(rs_queryNews.getString("content"));
				news.setIssuedate(rs_queryNews.getString("issuedate"));
				news.setType(rs_queryNews.getString("type"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		JSONArray news = JSONArray.fromObject(list);
		out.print(news);
		out.flush();
		out.close();
		db.close();
		return "news";
	}
	/*
	 * ��ʾǰ̨������Ϣ
	 */
	public String showGQInfo() throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =  response.getWriter();
		List<GQInfoForm> list = new LinkedList<GQInfoForm>();
		String sql_queryInfo = "select user_id, worktime, accomm, workType, headline, content, posttime, pay, phone, type from tb_info where isLock='1' order by posttime desc";
		try {
			ResultSet rs_queryInfo = db.executeQuery(sql_queryInfo);
			logger.info("��ѯ������Ϣ,ִ��SQL:"+sql_queryInfo);
			while (rs_queryInfo.next()) {
				GQInfoForm gqInfo = new GQInfoForm();
				gqInfo.setUserId(rs_queryInfo.getInt("user_id"));
				gqInfo.setWorkTime(rs_queryInfo.getInt("worktime"));
				gqInfo.setWorkType(rs_queryInfo.getInt("workType"));
				gqInfo.setIsAccomm(rs_queryInfo.getInt("accomm"));
				gqInfo.setHeadLine(rs_queryInfo.getString("headline"));
				gqInfo.setContent(rs_queryInfo.getString("content"));
				gqInfo.setPostTime(rs_queryInfo.getString("posttime"));
				gqInfo.setPay(rs_queryInfo.getString("pay"));
				gqInfo.setPhone(rs_queryInfo.getString("phone"));
				gqInfo.setType(rs_queryInfo.getString("type"));
				list.add(gqInfo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		JSONArray gqInfo = JSONArray.fromObject(list);
		out.print(gqInfo);
		out.flush();
		out.close();
		db.close();
		return "gqInfo";
	}
	/*
	 * ���ܣ���ʾ������Ϣ��ϸ����
	 */
	public String showGQDetail() throws IOException {
		type = request.getParameter("type");
		infoID = request.getParameter("infoID");
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<GQInfoForm> list = new LinkedList<GQInfoForm>();
		String sql_queryInfo = "select worktime, accomm, workType, headline, content, posttime, pay, phone, type from tb_info where user_id ="+infoID+" and type ="+type;
		try {
			ResultSet rs_queryInfo = db.executeQuery(sql_queryInfo);
			logger.info("��ѯ������ϸ,ִ��SQL:"+sql_queryInfo);
			if(rs_queryInfo.next()) {
				GQInfoForm gqInfo = new GQInfoForm();
				gqInfo.setWorkTime(rs_queryInfo.getInt("worktime"));
				gqInfo.setWorkType(rs_queryInfo.getInt("workType"));
				gqInfo.setIsAccomm(rs_queryInfo.getInt("accomm"));
				gqInfo.setHeadLine(rs_queryInfo.getString("headline"));
				gqInfo.setContent(rs_queryInfo.getString("content"));
				gqInfo.setPostTime(rs_queryInfo.getString("posttime"));
				gqInfo.setPay(rs_queryInfo.getString("pay"));
				gqInfo.setPhone(rs_queryInfo.getString("phone"));
				gqInfo.setType(rs_queryInfo.getString("type"));
				list.add(gqInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		JSONArray gqDetail = JSONArray.fromObject(list);
		out.print(gqDetail);
		out.flush();
		out.close();
		db.close();
		return "gqDetail";
	}
	/*
	 * ���ܣ���ʾǰ̨����������Ϣ
	 */
	public String showNewsDetail() throws IOException {
		newsID = request.getParameter("newsID");
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<NewsForm> list = new LinkedList<NewsForm>();
		String sql_queryNews = "select id, title, content, issuedate, type from tb_news where id="+newsID;
		try {
			ResultSet rs_queryNews = db.executeQuery(sql_queryNews);
			logger.info("��ѯ������ϸ,ִ��SQL:"+sql_queryNews);
			if(rs_queryNews.next()) {
				NewsForm news = new NewsForm();
				news.setId(rs_queryNews.getInt("id"));
				news.setTitle(rs_queryNews.getString("title"));
				news.setContent(rs_queryNews.getString("content"));
				news.setIssuedate(rs_queryNews.getString("issuedate"));
				news.setType(rs_queryNews.getString("type"));
				list.add(news);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		JSONArray newsDetail = JSONArray.fromObject(list);
		out.print(newsDetail);
		out.flush();
		out.close();
		db.close();
		return "newsDetail";
	}

}
