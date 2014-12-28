package com.ytf.action;
/*
 * 管理员管理Action
 * @version 1.0
 * @author Administrator
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.ytf.actionSuper.MySuperAction;
import com.ytf.actionForm.LoginForm;
import com.ytf.actionForm.NewsForm;
import com.ytf.core.ConnDB;
public class ManageAction extends MySuperAction {
	
	//新闻页面url参数
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
	//签署合同页面url参数
	private String contID;
	private String confirmDate;
	public String getContID() {
		return contID;
	}

	public void setContID(String contID) {
		this.contID = contID;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	//用户管理页面url参数
	private String bmID;
	private String gzID;
	private String qyID;
	public String getBmID() {
		return bmID;
	}

	public void setBmID(String bmID) {
		this.bmID = bmID;
	}
	
	public String getGzID() {
		return gzID;
	}

	public void setGzID(String gzID) {
		this.gzID = gzID;
	}

	public String getQyID() {
		return qyID;
	}

	public void setQyID(String qyID) {
		this.qyID = qyID;
	}
	//审核供求信息页面url参数
	private String gyInfoID;
	private String xqInfoID;
	public String getGyInfoID() {
		return gyInfoID;
	}

	public void setGyInfoID(String gyInfoID) {
		this.gyInfoID = gyInfoID;
	}

	public String getXqInfoID() {
		return xqInfoID;
	}

	public void setXqInfoID(String xqInfoID) {
		this.xqInfoID = xqInfoID;
	}
	//管理员信息
	private String adminAccount;
	private String adminPass;
	private String adminID;
	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	//控制台info
	private static Logger logger = Logger.getLogger(ManageAction.class);	
	//数据库访问对象
	ConnDB mydb=new ConnDB();
	Connection conn = ConnDB.getConnection();
	ResultSet rs = null;
	PreparedStatement ps =null;
	//登录实体
	private LoginForm user;
	/**
	 * @return the user
	 */
	public LoginForm getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(LoginForm user) {
		this.user = user;
	}
	//新闻实体
	private String newsTitle;
	private String newsContent;
	private String newsType;
	private String newsIssuedate;
	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsIssuedate() {
		return newsIssuedate;
	}

	public void setNewsIssuedate(String newsIssuedate) {
		this.newsIssuedate = newsIssuedate;
	}
	/*
	 * 管理员登录方法
	 */
	@SuppressWarnings("unchecked")
	public String Login()  {
		String name=user.getUsername();
		String password=user.getPassword();
		String sql = "select * from tb_manager where name= '"+name+"' and password ='"+password+"'";
		rs = mydb.executeQuery(sql);
		logger.info("查询管理员,执行SQL:"+sql);
		try {
			if(rs.next()) {
				session.put("userType", "admin");
				return SUCCESS;
			} else {
				addActionMessage("用户名或密码错误!");
				return INPUT;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		mydb.close();
		return INPUT;
	}
	/*
	 * 管理员添加新闻
	 */
	public String addNews() {
		newsTitle = request.getParameter("newsTitle");
		newsContent = request.getParameter("newsContent");
		newsType= request.getParameter("newsType");
		newsIssuedate = request.getParameter("newsIssuedate");
		String sql = "insert into tb_news (title, content, issuedate, type) values(?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newsTitle);
			ps.setString(2, newsContent);
			ps.setString(3, newsIssuedate);
			ps.setString(4, newsType);
			ps.executeUpdate();
			ps.close();
			logger.info("管理员添加新闻,执行SQL:"+sql);
			addActionMessage("添加新闻成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/*
	 * 管理员删除新闻
	 */
	public String deleNews() {
		newsID = request.getParameter("newsID");
		String sql = "delete from tb_news where id ="+newsID;
		mydb.executeUpdate(sql);
		logger.info("删除新闻,执行SQL:"+sql);
		mydb.close();
		return SUCCESS;
	}
	/*
	 *管理员进行员工评价考核 
	 */
	public String rateBm() {
		String bmID = request.getParameter("bmID");
		String bmRank = request.getParameter("bmRank");
		String sql = "update tb_employee set rank =" +bmRank+ " where id ="+bmID;
		mydb.executeUpdate(sql);
		logger.info("更新家政员评级结果,执行SQL:"+sql);
		mydb.close();
		return SUCCESS;
	}
	/*
	 * 管理员签署合同
	 */
	public String confirmCont() throws IOException {
		PrintWriter out =  response.getWriter();
		contID = request.getParameter("contID");
		confirmDate = request.getParameter("confirmDate");
		String sql = "update tb_contract set sigDate ='" +confirmDate+ "' where id="+contID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("管理员签署合同,执行SQL:"+sql);
			out.print("1");
		}
		mydb.close();
		out.close();
		return SUCCESS;
	}
	/*
	 * 管理员删除家政员
	 */
	public String deleBm() throws IOException {
		PrintWriter out = response.getWriter();
		bmID = request.getParameter("bmID");
		String sql = "delete from tb_employee where id="+bmID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("删除家政员信息表tb_employee,执行SQL:"+sql);
			out.print("1");
		}
		String sqlBook = "delete from tb_book where bmID="+bmID;
		mydb.executeUpdate(sqlBook);
		logger.info("删除家政员预约表tb_book,执行SQL:"+sqlBook);
		String sqlSupply = "delete from tb_info where type='1' and user_id="+bmID;
		mydb.executeUpdate(sqlSupply);
		logger.info("删除家政员供应信息表tb_info,执行SQL:"+sqlSupply);
		String sqlCert = "delete from tb_cert where user_id="+bmID;
		mydb.executeUpdate(sqlCert);
		logger.info("删除家政员证书表tb_cert,执行SQL:"+sqlCert);
		String sqlSkills = "delete from tb_skills where user_id="+bmID;
		mydb.executeUpdate(sqlSkills);
		logger.info("删除家政员技能表tb_skills,执行SQL:"+sqlSkills);
		String sqlLang = "delete from tb_lang where user_id="+bmID;
		mydb.executeUpdate(sqlLang);
		logger.info("删除家政员语言表tb_lang,执行SQL:"+sqlLang);
		String sqlFlavor = "delete from tb_flavor where user_id="+bmID;
		mydb.executeUpdate(sqlFlavor);
		logger.info("删除家政员口味表tb_flavor,执行SQL:"+sqlLang);
		mydb.close();
		out.close();
		return SUCCESS;
	}
	/*
	 * 管理员审核供应信息
	 */
	public String reviewGYInfo() throws IOException {
		PrintWriter out = response.getWriter();
		gyInfoID = request.getParameter("gyInfoID");
		String sql = "update tb_info set isLock = '1' where type= '1' and user_id ="+gyInfoID;
		int num = mydb.executeUpdate(sql);
		if(num>0){
			logger.info("审核通过家政员发布的信息,执行SQL:"+sql);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * 管理员删除雇主
	 */
	public String deleGz() throws IOException {
		PrintWriter out = response.getWriter();
		gzID = request.getParameter("gzID");
		String sql = "delete from tb_employer where id="+gzID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("删除雇主信息表tb_employer,执行SQL:"+sql);
		    String sqlBook = "delete from tb_book where gzID="+gzID;
		    mydb.executeUpdate(sqlBook);
		    logger.info("删除雇主预约表tb_book,执行SQL:"+sqlBook);
		    String sqlDemand = "delete from tb_info where type='2' and user_id="+gzID;
		    mydb.executeUpdate(sqlDemand);
		    logger.info("删除需求信息表tb_info,执行SQL:"+sqlDemand);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * 管理员审核需求信息
	 */
	public String reviewXQInfo() throws IOException {
		PrintWriter out = response.getWriter();
		xqInfoID = request.getParameter("xqInfoID");
		String sql = "update tb_info set isLock = '1' where type = '2' and user_id="+xqInfoID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("审核需求信息,执行SQL:"+sql);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * 管理员删除企业用户
	 */
	public String deleQy() throws IOException {
		PrintWriter out = response.getWriter();
		qyID = request.getParameter("qyID");
		String sql = "delete from tb_company where id="+qyID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("管理员删除企业用户表tb_compamy,执行SQL:"+sql);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * 添加管理员
	 */
	public String addAdmin() throws IOException {
		PrintWriter out = response.getWriter();
		adminAccount = request.getParameter("adminAccount");
		adminPass = request.getParameter("adminPass");
		String sql = "insert into tb_manager (name, password, type) values (?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminAccount);
			ps.setString(2, adminPass);
			ps.setString(3, "0");
			int num = ps.executeUpdate();
			if(num>0) {
				logger.info("添加管理员,执行SQL:"+sql);
				out.print("1");
			}
			ps.close();
			conn.close();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/*
	 * 删除管理员
	 */
	public String deleAdmin() throws IOException {
		PrintWriter out = response.getWriter();
		adminID = request.getParameter("adminID");
		String sql = "delete from tb_manager where type ='0' and id="+adminID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("删除管理员,执行SQL:"+sql);
			out.print("1");
		}
		mydb.close();
		out.close();
		return SUCCESS;
	}

}
