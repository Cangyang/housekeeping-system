package com.ytf.action;
/*
 * ����Ա����Action
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
	
	//����ҳ��url����
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
	//ǩ���ͬҳ��url����
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
	//�û�����ҳ��url����
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
	//��˹�����Ϣҳ��url����
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
	//����Ա��Ϣ
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
	//����̨info
	private static Logger logger = Logger.getLogger(ManageAction.class);	
	//���ݿ���ʶ���
	ConnDB mydb=new ConnDB();
	Connection conn = ConnDB.getConnection();
	ResultSet rs = null;
	PreparedStatement ps =null;
	//��¼ʵ��
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
	//����ʵ��
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
	 * ����Ա��¼����
	 */
	@SuppressWarnings("unchecked")
	public String Login()  {
		String name=user.getUsername();
		String password=user.getPassword();
		String sql = "select * from tb_manager where name= '"+name+"' and password ='"+password+"'";
		rs = mydb.executeQuery(sql);
		logger.info("��ѯ����Ա,ִ��SQL:"+sql);
		try {
			if(rs.next()) {
				session.put("userType", "admin");
				return SUCCESS;
			} else {
				addActionMessage("�û������������!");
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
	 * ����Ա�������
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
			logger.info("����Ա�������,ִ��SQL:"+sql);
			addActionMessage("������ųɹ���");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/*
	 * ����Աɾ������
	 */
	public String deleNews() {
		newsID = request.getParameter("newsID");
		String sql = "delete from tb_news where id ="+newsID;
		mydb.executeUpdate(sql);
		logger.info("ɾ������,ִ��SQL:"+sql);
		mydb.close();
		return SUCCESS;
	}
	/*
	 *����Ա����Ա�����ۿ��� 
	 */
	public String rateBm() {
		String bmID = request.getParameter("bmID");
		String bmRank = request.getParameter("bmRank");
		String sql = "update tb_employee set rank =" +bmRank+ " where id ="+bmID;
		mydb.executeUpdate(sql);
		logger.info("���¼���Ա�������,ִ��SQL:"+sql);
		mydb.close();
		return SUCCESS;
	}
	/*
	 * ����Աǩ���ͬ
	 */
	public String confirmCont() throws IOException {
		PrintWriter out =  response.getWriter();
		contID = request.getParameter("contID");
		confirmDate = request.getParameter("confirmDate");
		String sql = "update tb_contract set sigDate ='" +confirmDate+ "' where id="+contID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("����Աǩ���ͬ,ִ��SQL:"+sql);
			out.print("1");
		}
		mydb.close();
		out.close();
		return SUCCESS;
	}
	/*
	 * ����Աɾ������Ա
	 */
	public String deleBm() throws IOException {
		PrintWriter out = response.getWriter();
		bmID = request.getParameter("bmID");
		String sql = "delete from tb_employee where id="+bmID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("ɾ������Ա��Ϣ��tb_employee,ִ��SQL:"+sql);
			out.print("1");
		}
		String sqlBook = "delete from tb_book where bmID="+bmID;
		mydb.executeUpdate(sqlBook);
		logger.info("ɾ������ԱԤԼ��tb_book,ִ��SQL:"+sqlBook);
		String sqlSupply = "delete from tb_info where type='1' and user_id="+bmID;
		mydb.executeUpdate(sqlSupply);
		logger.info("ɾ������Ա��Ӧ��Ϣ��tb_info,ִ��SQL:"+sqlSupply);
		String sqlCert = "delete from tb_cert where user_id="+bmID;
		mydb.executeUpdate(sqlCert);
		logger.info("ɾ������Ա֤���tb_cert,ִ��SQL:"+sqlCert);
		String sqlSkills = "delete from tb_skills where user_id="+bmID;
		mydb.executeUpdate(sqlSkills);
		logger.info("ɾ������Ա���ܱ�tb_skills,ִ��SQL:"+sqlSkills);
		String sqlLang = "delete from tb_lang where user_id="+bmID;
		mydb.executeUpdate(sqlLang);
		logger.info("ɾ������Ա���Ա�tb_lang,ִ��SQL:"+sqlLang);
		String sqlFlavor = "delete from tb_flavor where user_id="+bmID;
		mydb.executeUpdate(sqlFlavor);
		logger.info("ɾ������Ա��ζ��tb_flavor,ִ��SQL:"+sqlLang);
		mydb.close();
		out.close();
		return SUCCESS;
	}
	/*
	 * ����Ա��˹�Ӧ��Ϣ
	 */
	public String reviewGYInfo() throws IOException {
		PrintWriter out = response.getWriter();
		gyInfoID = request.getParameter("gyInfoID");
		String sql = "update tb_info set isLock = '1' where type= '1' and user_id ="+gyInfoID;
		int num = mydb.executeUpdate(sql);
		if(num>0){
			logger.info("���ͨ������Ա��������Ϣ,ִ��SQL:"+sql);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * ����Աɾ������
	 */
	public String deleGz() throws IOException {
		PrintWriter out = response.getWriter();
		gzID = request.getParameter("gzID");
		String sql = "delete from tb_employer where id="+gzID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("ɾ��������Ϣ��tb_employer,ִ��SQL:"+sql);
		    String sqlBook = "delete from tb_book where gzID="+gzID;
		    mydb.executeUpdate(sqlBook);
		    logger.info("ɾ������ԤԼ��tb_book,ִ��SQL:"+sqlBook);
		    String sqlDemand = "delete from tb_info where type='2' and user_id="+gzID;
		    mydb.executeUpdate(sqlDemand);
		    logger.info("ɾ��������Ϣ��tb_info,ִ��SQL:"+sqlDemand);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * ����Ա���������Ϣ
	 */
	public String reviewXQInfo() throws IOException {
		PrintWriter out = response.getWriter();
		xqInfoID = request.getParameter("xqInfoID");
		String sql = "update tb_info set isLock = '1' where type = '2' and user_id="+xqInfoID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("���������Ϣ,ִ��SQL:"+sql);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * ����Աɾ����ҵ�û�
	 */
	public String deleQy() throws IOException {
		PrintWriter out = response.getWriter();
		qyID = request.getParameter("qyID");
		String sql = "delete from tb_company where id="+qyID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("����Աɾ����ҵ�û���tb_compamy,ִ��SQL:"+sql);
			out.print("1");
		}
		out.close();
		mydb.close();
		return SUCCESS;
	}
	/*
	 * ��ӹ���Ա
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
				logger.info("��ӹ���Ա,ִ��SQL:"+sql);
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
	 * ɾ������Ա
	 */
	public String deleAdmin() throws IOException {
		PrintWriter out = response.getWriter();
		adminID = request.getParameter("adminID");
		String sql = "delete from tb_manager where type ='0' and id="+adminID;
		int num = mydb.executeUpdate(sql);
		if(num>0) {
			logger.info("ɾ������Ա,ִ��SQL:"+sql);
			out.print("1");
		}
		mydb.close();
		out.close();
		return SUCCESS;
	}

}
