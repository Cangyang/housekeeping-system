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
	//预约实体
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
	//家政人员姓名
	private String bmName ;
	//家政人员Id
	private int bmId;
	//雇主Id
	private int gzId;
	//雇主账号
    private String gzUsername;
	//雇主称呼
	private String gzName;
	//雇主联系方式
	private String gzPhone;
	//雇主提供薪资
	private String gzPay;
	//开始时间
	private String startDate;
	//结束时间
	private String endDate;
	//工作周期
	private String wkPeriod;
	//雇主预约信息
	private String gzbkContent;
	//保姆服务类型
	private int servType;
	//保姆每天工作时间
	private int servDailyTime;
	//保姆对食宿要求
	private int isAccomm;
	//客户验证码
	private String securityCode ; 
	//预约日期
	private String bkDate;
	/*
	 * 功能:雇主在线预约
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
			logger.info("雇主【ID"+gzId+"】成功预约保姆【ID"+bmId+"】!");
			String sql_querySupply = "select worktime, accomm, workType from tb_info where user_id="+bmId+" and type='1'";
			String sql_insertBook = "insert into tb_book (bmID,bmName,gzID,gzName,gzPhone,startDate,endDate,wkPeriod,gzPay,servType,servDailyTime,isAccomm,gzbkContent,bookTime,node,isSuccess) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				ResultSet rs = db.executeQuery(sql_querySupply);
				logger.info("执行SQL:"+sql_querySupply);
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
				logger.info("执行SQL:"+sql_insertBook);
				//更新保姆状态
				String sql_updateStatus = "update tb_employee set status ='1' where id="+bmId;
				db.executeUpdate(sql_updateStatus);
				logger.info("更新保姆状态'已预约',执行SQL:"+sql_updateStatus);
				
			} catch(SQLException e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			addActionMessage("成功提交预约信息，我们将尽快处理您的请求!");
		} else {
			logger.info("雇主【ID"+gzId+"】预约保姆【ID"+bmId+"】失败!");
		}
		
		db.close();
		return SUCCESS;
	}
	/*
	 * 工具类:检查验证码 
	 */
	public boolean check() {
		boolean flag = false;
		//接收session中securityCode；
		String sessionSecurityCode = (String)session.get("SESSION_SECURITY_CODE");
		securityCode = bookBm.getSecurityCode();
		if(!securityCode.equals(sessionSecurityCode)) {
		    addFieldError("bookBm.securityCode","亲，验证码不对哦!!");
		} else {
		    flag = true;
		}
		return flag;
	}
	/*
	 * 工具类:根据开始日期和时间段计算结束日期
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
		//将开始时间赋给日历实例  
		Calendar sCalendar = Calendar.getInstance();
		sCalendar.setTime(sDate);
		//计算结束时间  
        sCalendar.add(Calendar.MONTH, Integer.valueOf(period));
        endDate = format.format(sCalendar.getTime());
		return endDate;
	}
	/*
	 * 工具类:根据用户名查询ID
	 */
	public int queryID (String surname,String type) {
		int ID = 0;
		String sql_query = "select id from " + type + " where name='" + surname + "'";
		ResultSet rs = db.executeQuery(sql_query);
		logger.info("查询ID,执行SQL: "+sql_query);
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
	 * 工具类:查询当前雇主是否能够预约当前家政阿姨
	 */
	public boolean canBook(int bmId, int gzId) {
		boolean flag = false;
		//家政阿姨的状态
		String status = null;
		//预约节点
		String node = null;
		//家政阿姨是否选择
		String isSuccess = null;
		//根据保姆ID查询保姆状态
		String sql_queryBmStatus = "select status from tb_employee where id=" + bmId;
		//在预约表中查询雇主是否能够预约
		String sql_isBooked = "select node, isSuccess  from tb_book where gzId =" + gzId + " and isOutdated ='0'";
		ResultSet rs1 = db.executeQuery(sql_queryBmStatus);
		logger.info("执行SQL: "+sql_queryBmStatus);
		ResultSet rs2 = db.executeQuery(sql_isBooked);
		logger.info("执行SQL: "+sql_isBooked);
		try {
			if(rs1.next()) {
				status = rs1.getString("status");
			}
			rs1.close();
			if(status.equals("2")) {
				addActionMessage("家政阿姨已在岗,请您重新预约其他阿姨,对此造成不便我们深表歉意!");
				flag = false; 
			} else {
			    while(rs2.next()) {
				    node = rs2.getString("node");
				    isSuccess = rs2.getString("isSuccess");
				    if(node.equals("gz")) {
					    addActionMessage("亲，您已经预约过了，我们正在火速处理中！!");
					    flag = false;
					    break;
				    } else if(isSuccess.equals("1")) {
					    addActionMessage("您已预约了家政阿姨,一个账号只能预约一位家政阿姨!");
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
	 * 工具类:根据前台长ID截取原始用户ID
	 */
	public static int cutID(int id) {
		int ok_id = 0;
		String temp = null;
		temp = String.valueOf(id).substring(4);
		ok_id = Integer.parseInt(temp);
		return ok_id;
	}

}
