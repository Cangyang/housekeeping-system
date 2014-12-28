package com.ytf.action;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.ytf.core.*;
import org.apache.log4j.Logger; 
import com.ytf.actionSuper.*;
import com.ytf.actionForm.*;
public class BookBm extends MySuperAction{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3005168808853396441L;
	private ConnDB db = new ConnDB();
	private Connection conn = ConnDB.getConnection();
	private static Logger logger = Logger.getLogger(BookBm.class);
	//ԤԼʵ��
	private BookBmForm bookBm;
	/**
	 * @return the bookBm
	 */
	public BookBmForm getBookBm() {
		return bookBm;
	}

	/**
	 * @param bookBm the bookBm to set
	 */
	public void setBookBm(BookBmForm bookBm) {
		this.bookBm = bookBm;
	}
	//������Ա����
	private String bmName ;
	//������ԱId
	private int bmId;
	//����Id
	private int gzId;
	//�����˺�
    private String gzUsername;
	//�����ƺ�
	private String gzName;
	//������ϵ��ʽ
	private String gzPhone;
	//�����ṩн��
	private String gzPay;
	//��ʼʱ��
	private String startDate;
	//����ʱ��
	private String endDate;
	//��������
	private String wkPeriod;
	//����ԤԼ��Ϣ
	private String gzbkContent;
	//��ķ��������
	private int servType;
	//��ķÿ�칤��ʱ��
	private int servDailyTime;
	//��ķ��ʳ��Ҫ��
	private int isAccomm;
	//�ͻ���֤��
	private String securityCode ; 
	//ԤԼ����
	private String bkDate;
	/*
	 * ����:��������ԤԼ
	 */
	public String bookBm() {
		bmId = cutID(bookBm.getBmId());
		bmName = bookBm.getBmName();
		gzUsername = (String)session.get("username");
		gzId = queryID(gzUsername,"tb_employer");
		gzName = bookBm.getGzName();
		gzPhone = bookBm.getGzPhone();
		gzPay = bookBm.getGzPay();
		startDate = bookBm.getStartDate();
		wkPeriod = bookBm.getWkPeriod();
		endDate = calEndDate(startDate, wkPeriod);
		gzbkContent = bookBm.getGzbkContent();
		bkDate = bookBm.getBkDate();
//		System.out.println(endDate);
		if(check()&&canBook(bmId,gzId)) {
			logger.info("������ID"+gzId+"���ɹ�ԤԼ��ķ��ID"+bmId+"��!");
			String sql_querySupply = "select worktime, accomm, workType from tb_info where user_id="+bmId+" and type='1'";
			String sql_insertBook = "insert into tb_book (bmID,bmName,gzID,gzName,gzPhone,startDate,endDate,wkPeriod,gzPay,servType,servDailyTime,isAccomm,gzbkContent,bookTime,node,isSuccess) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				ResultSet rs = db.executeQuery(sql_querySupply);
				logger.info("ִ��SQL:"+sql_querySupply);
				PreparedStatement ps = conn.prepareStatement(sql_insertBook);
				if(rs.next()) {
					servType = rs.getInt("workType");
					isAccomm = rs.getInt("accomm");
					servDailyTime = rs.getInt("worktime");
				}
				ps.setInt(1, bmId);
				ps.setString(2, bmName);
				ps.setInt(3, gzId);
				ps.setString(4, gzName);
				ps.setString(5, gzPhone);
				ps.setString(6, startDate);
				ps.setString(7, endDate);
				ps.setString(8, wkPeriod);
				ps.setString(9, gzPay);
				ps.setInt(10, servType);
				ps.setInt(11, servDailyTime);
				ps.setInt(12, isAccomm);
				ps.setString(13, gzbkContent);
				ps.setString(14, bkDate);
				ps.setString(15, "gz");
				ps.setString(16, "0");
				ps.executeUpdate();
				ps.close();
				logger.info("ִ��SQL:"+sql_insertBook);
				//���±�ķ״̬
				String sql_updateStatus = "update tb_employee set status ='1' where id="+bmId;
				db.executeUpdate(sql_updateStatus);
				logger.info("���±�ķ״̬'��ԤԼ',ִ��SQL:"+sql_updateStatus);
				
			} catch(SQLException e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			addActionMessage("�ɹ��ύԤԼ��Ϣ�����ǽ����촦����������!");
		} else {
			logger.info("������ID"+gzId+"��ԤԼ��ķ��ID"+bmId+"��ʧ��!");
		}
		
		db.close();
		return SUCCESS;
	}
	/*
	 * ������:�����֤�� 
	 */
	public boolean check() {
		boolean flag = false;
		//����session��securityCode��
		String sessionSecurityCode = (String)session.get("SESSION_SECURITY_CODE");
		securityCode = bookBm.getSecurityCode();
		if(!securityCode.equals(sessionSecurityCode)) {
		    addFieldError("bookBm.securityCode","�ף���֤�벻��Ŷ!!");
		} else {
		    flag = true;
		}
		return flag;
	}
	/*
	 * ������:���ݿ�ʼ���ں�ʱ��μ����������
	 */
	public String calEndDate(String startDate, String period) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = null;
		Date sDate = null;
		try {
			sDate = format.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		//����ʼʱ�丳������ʵ��  
		Calendar sCalendar = Calendar.getInstance();
		sCalendar.setTime(sDate);
		//�������ʱ��  
        sCalendar.add(Calendar.MONTH, Integer.valueOf(period));
        endDate = format.format(sCalendar.getTime());
		return endDate;
	}
	/*
	 * ������:�����û�����ѯID
	 */
	public int queryID (String surname,String type) {
		int ID = 0;
		String sql_query = "select id from " + type + " where name='" + surname + "'";
		ResultSet rs = db.executeQuery(sql_query);
		logger.info("��ѯID,ִ��SQL: "+sql_query);
		try {
			if(rs.next()){
				ID = rs.getInt("id");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			db.close();
		}
		return ID;
	}
	/*
	 * ������:��ѯ��ǰ�����Ƿ��ܹ�ԤԼ��ǰ��������
	 */
	public boolean canBook(int bmId, int gzId) {
		boolean flag = false;
		//�������̵�״̬
		String status = null;
		//ԤԼ�ڵ�
		String node = null;
		//���������Ƿ�ѡ��
		String isSuccess = null;
		//���ݱ�ķID��ѯ��ķ״̬
		String sql_queryBmStatus = "select status from tb_employee where id=" + bmId;
		//��ԤԼ���в�ѯ�����Ƿ��ܹ�ԤԼ
		String sql_isBooked = "select node, isSuccess  from tb_book where gzId =" + gzId + " and isOutdated ='0'";
		ResultSet rs1 = db.executeQuery(sql_queryBmStatus);
		logger.info("ִ��SQL: "+sql_queryBmStatus);
		ResultSet rs2 = db.executeQuery(sql_isBooked);
		logger.info("ִ��SQL: "+sql_isBooked);
		try {
			if(rs1.next()) {
				status = rs1.getString("status");
			}
			rs1.close();
			if(status.equals("2")) {
				addActionMessage("�����������ڸ�,��������ԤԼ��������,�Դ���ɲ����������Ǹ��!");
				flag = false; 
			} else {
			    while(rs2.next()) {
				    node = rs2.getString("node");
				    isSuccess = rs2.getString("isSuccess");
				    if(node.equals("gz")) {
					    addActionMessage("�ף����Ѿ�ԤԼ���ˣ��������ڻ��ٴ����У�!");
					    flag = false;
					    break;
				    } else if(isSuccess.equals("1")) {
					    addActionMessage("����ԤԼ�˼�������,һ���˺�ֻ��ԤԼһλ��������!");
					    flag = false;
					    break;
				    } else {
					    flag = true;
				    }
			    } 
			    if((node==null&&isSuccess==null)) {
					flag = true;
				}
			}
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			db.close();
		}
		return flag;
	}
	/*
	 * ������:����ǰ̨��ID��ȡԭʼ�û�ID
	 */
	public static int cutID(int id) {
		int ok_id = 0;
		String temp = null;
		temp = String.valueOf(id).substring(4);
		ok_id = Integer.parseInt(temp);
		return ok_id;
	}

}
