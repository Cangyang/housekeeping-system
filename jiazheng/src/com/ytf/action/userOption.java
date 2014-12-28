package com.ytf.action;

import net.sf.json.JSONArray;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger; 
import com.ytf.actionSuper.MySuperAction;
import com.ytf.actionForm.BookBmForm;
import com.ytf.actionForm.CompanyCardForm;
import com.ytf.actionForm.ContractForm;
import com.ytf.actionForm.UserInfoForm;
import com.ytf.actionForm.AssessForm;
import com.ytf.actionForm.MessageForm;
import com.ytf.core.ConnDB;
import com.ytf.tool.calDate;
import com.ytf.action.BookBm;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 用户提交个人信息Action
 * @version 1.0
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class userOption extends MySuperAction {
	

	private String selectedGz;
	/**
	 * @return the selectedGz
	 */
	public String getSelectedGz() {
		return selectedGz;
	}
	/**
	 * @param selectedGz the selectedGz to set
	 */
	public void setSelectedGz(String selectedGz) {
		this.selectedGz = selectedGz;
	}

	private static Logger logger = Logger.getLogger(userOption.class);	
	//保姆Info
	private UserInfoForm userInfo;
	public UserInfoForm getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoForm userInfo) {
		this.userInfo = userInfo;
	}
	//雇主Info
	private UserInfoForm gzInfo;
	/**
	 * @return the gzInfo
	 */
	public UserInfoForm getGzInfo() {
		return gzInfo;
	}
	/**
	 * @param gzInfo the gzInfo to set
	 */
	public void setGzInfo(UserInfoForm gzInfo) {
		this.gzInfo = gzInfo;
	}
	//家政公司
	private CompanyCardForm company;
	/**
	 * @return the company
	 */
	public CompanyCardForm getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(CompanyCardForm company) {
		this.company = company;
	}

	ConnDB mydb=new ConnDB();
	Connection conn = ConnDB.getConnection();
	ResultSet rs = null;
	PreparedStatement ps = null;
	//数据库查询个人信息显示为默认值
	
	public String execute()  {	
		
		return SUCCESS;
		
           
		}
		
	
	/*
	 * 功能:获取所有checkBox的值包括未勾选项
	 * @param get 包含checkBox选中值的数组, length 数组长度
	 * @return num 包含checkBox所有中的数组
	 */
	public int [] getAllValues(String [] get,int length) {
		int num [] =new int[length];
		for (int i=0;i<length;i++) {
			num[i] = 0;
		}
		boolean flag = get==null||get.equals("");
		if(!flag){
		for (int i=0;i<length;i++) {
			for(int j=0;j<get.length;j++) {
				if(Integer.parseInt(get[j])==i) {
					num[i] = 1;
				}
			}
		} 
	}	
		return num;
	}
	
	/*
	 * 功能:保姆填写个人基本信息并更新到数据库
	 */
	public String update() throws IOException  {	
	    //获取用户输入的姓名，如果为空则null
		String surname = userInfo.getSurname();
		surname = (surname==null||surname.equals("")) ? null : surname;
		//获取用户输入的籍贯，如果为空则null		
		String hometown = userInfo.getHometown();
		hometown = (hometown==null||hometown.equals("")) ? null : hometown;
		//获取用户输入的性别		
		int sex = userInfo.getSex();
		//获取用户输入的出生日期，如果为空则为2000-01-01	
		String birthdate = userInfo.getBirthDate();
		birthdate = (birthdate==null||birthdate.equals("")) ? "2000-01-01" : birthdate.substring(0,birthdate.indexOf('T'));
		//获取用户输入的联系方式，如果为空则null		
		String phone = userInfo.getPhone();
		phone = (phone==null||phone.equals("")) ? null : phone;
		//获取用户输入的婚姻状况,为空则-1		
		int marriage = userInfo.getMarriage();
		//获取用户输入的学历,为空则-1
		int education = userInfo.getEducation();
		//获取用户输入的住址，如果为空则null		
		String address = userInfo.getAddress();
		address = (address==null||address.equals("")) ? null : address;
		//获取用户输入的证书信息，保存所有值到数组		
		String [] cert = userInfo.getCert();
		int result_cert [] = getAllValues(cert,7);
		//获取用户输入的技能信息，保存所有值到数组		
		String [] skills = userInfo.getSkills();
		int result_skills [] = getAllValues(skills,6);
		//获取用户输入的语言信息，保存所有值到数组		
		String [] lang = userInfo.getLang();
		int result_lang [] = getAllValues(lang,7);
		//获取用户输入的口味信息，保存所有值到数组				
		String [] flavor = userInfo.getFlavor();
		int result_flavor [] = getAllValues(flavor,10);
		//获取用户上传的头像，如果为空则使用系统默认头像	
		File  avatar = userInfo.getAvatar();		
		String imgPath = request.getSession().getServletContext().getRealPath("/")+ "images/defImg.jpg";
		avatar = avatar==null||avatar.equals("") ? new File(imgPath) : avatar;
		int length = 0;
		FileInputStream fis = null;		
		fis = new FileInputStream(avatar);
		//获取用户输入的个人简介，如果为空则使用默认信息	
		String profile = userInfo.getProfile();
		profile = (profile==null||profile.equals("")) ? "这个人很懒，什么也没留下!" : profile;
		//获取用户上传信息的时间		
		String uptime = userInfo.getUptime();		
		try {
		//查询当前用户Id
		String user = null;
	    user = (String)session.get("username");
	    int userId = 0 ;
	    String sql = "select id from tb_employee where name = "+"'"+user+"'";
	    rs = mydb.executeQuery(sql);
	    if(rs.next()) {
	        userId = rs.getInt("id");
	    } 
	    //执行数据更新操作
	    String sql_employee = "update tb_employee set surname =?,hometown=?,sex=?,birthdate=?,phone=?,marriage=?,education=?,address=?,profile=?,uptime=?,avatar=? where id =?";
	    String sql_cert = "update tb_cert set cert_jiankang=?,cert_yuesao=?,cert_hushi=?,cert_yingyangshi=?,cert_zaojiao=?,cert_jiashi=?,cert_chushi=? where user_id=?";
	    String sql_skills = "update tb_skills set skill_computer=?,skill_ironing=?,skill_handwork=?,skill_waiyu=?,skill_driving=?,skill_nutriology=? where user_id=?";
	    String sql_lang = "update tb_lang set lang_local=?,lang_normal=?,lang_sichuan=?,lang_dongbei=?,lang_guangdong=?,lang_kejia=?,lang_minnan=? where user_id=?";
	    String sql_flavor = "update tb_flavor set flavor_local=?,flavor_xican=?,flavor_chuancai=?,flavor_yuecai=?,flavor_lucai=?,flavor_sucai=?,flavor_zhecai=?,flavor_xiangcai=?,flavor_mincai=?,flavor_huicai=? where user_id=?";
	    PreparedStatement ps =conn.prepareStatement(sql_employee);
	    PreparedStatement ps1 = conn.prepareStatement(sql_cert);
	    PreparedStatement ps2 = conn.prepareStatement(sql_skills);
	    PreparedStatement ps3 = conn.prepareStatement(sql_lang);
	    PreparedStatement ps4 = conn.prepareStatement(sql_flavor);
	    //更新用户证书表
	    ps1.setInt(1,result_cert[0]);
	    ps1.setInt(2,result_cert[1]);
	    ps1.setInt(3,result_cert[2]);
	    ps1.setInt(4,result_cert[3]);
	    ps1.setInt(5,result_cert[4]);
	    ps1.setInt(6,result_cert[5]);
	    ps1.setInt(7,result_cert[6]);
	    ps1.setInt(8,userId);
	    ps1.executeUpdate();
	    ps1.close();
	    logger.info("执行SQL:"+sql_cert);
	    //更新用户技能表
	    ps2.setInt(1, result_skills[0]);
	    ps2.setInt(2, result_skills[1]);
	    ps2.setInt(3, result_skills[2]);
	    ps2.setInt(4, result_skills[3]);
	    ps2.setInt(5, result_skills[4]);
	    ps2.setInt(6, result_skills[5]);
	    ps2.setInt(7, userId);
	    ps2.executeUpdate();
	    ps2.close();
	    logger.info("执行SQL:"+sql_skills);
	    //更新用户语言表
	    ps3.setInt(1, result_lang[0]);
	    ps3.setInt(2, result_lang[1]);
	    ps3.setInt(3, result_lang[2]);
	    ps3.setInt(4, result_lang[3]);
	    ps3.setInt(5, result_lang[4]);
	    ps3.setInt(6, result_lang[5]);
	    ps3.setInt(7, result_lang[6]);
	    ps3.setInt(8, userId);
	    ps3.executeUpdate();
	    ps3.close();
	    logger.info("执行SQL:"+sql_lang);
	    //更新用户口味表
	    ps4.setInt(1, result_flavor[0]);
	    ps4.setInt(2, result_flavor[1]);
	    ps4.setInt(3, result_flavor[2]);
	    ps4.setInt(4, result_flavor[3]);
	    ps4.setInt(5, result_flavor[4]);
	    ps4.setInt(6, result_flavor[5]);
	    ps4.setInt(7, result_flavor[6]);
	    ps4.setInt(8, result_flavor[7]);
	    ps4.setInt(9, result_flavor[8]);
	    ps4.setInt(10, result_flavor[9]);
	    ps4.setInt(11, userId);
	    ps4.executeUpdate();
	    ps4.close();
	    logger.info("执行SQL:"+sql_flavor);
	    //更新用户姓名
	    ps.setString(1, surname);
	    //更新用户籍贯
	    ps.setString(2, hometown);
	    //更新用户性别
	    ps.setInt(3, sex);
	    //更新用户出生年月
	    ps.setString(4, birthdate);
	    //更新用户联系方式
	    ps.setString(5, phone);
	    //更新用户婚姻状况
	    ps.setInt(6, marriage);
	    //更新用户学历
	    ps.setInt(7, education);
	    //更新用户居住地址
	    ps.setString(8, address);
	    //更新用户个人简介
	    ps.setString(9, profile);
	    //更新更新时间
	    ps.setString(10, uptime);
	    //更新用户头像
	    ps.setBinaryStream(11, fis,length);
		ps.setBinaryStream(11, fis, fis.available());
		//当前用户Id为查询对象
		ps.setInt(12, userId);
		ps.executeUpdate();
		ps.close();
		logger.info("执行SQL:"+sql_employee);
    	fis.close();
    	rs.close();
		conn.close();
    	addActionMessage("信息更新成功!");
    	logger.info("用户"+user+"更新信息成功!");
    	return SUCCESS;
		} catch (SQLException e) {
		    e.printStackTrace();
	    	addActionMessage("更新信息出现错误,请检查您当前网络连接!");
	    	logger.info(e.getMessage());
	    	return ERROR;
		}
	   
	}
	/*
	 * 功能:保姆发布供应信息
	 */
	public String addInfo() {
		//获取保姆选择的工作类型
		int workType = userInfo.getWorktype();
		//获取保姆选择的工时
		int workTime = userInfo.getWorktime();
		//获取保姆选择的食宿要求
		int accomm = userInfo.getAccomm();
		//获取保姆输入的供应信息标题
		String title = userInfo.getHeadLine();
		//获取保姆输入的供应信息内容
		String content = userInfo.getContent();
		//获取保姆发布供应信息时间
		String postTime = userInfo.getPostTime();
		//获取保姆联系方式
		String phone = userInfo.getPhone();
		//获取保姆薪资
		String pay = userInfo.getPay();
		//执行数据库更新操作
		try {
			//查询当前用户Id
			String user = null;
		    user = (String)session.get("username");
		    int userId = 0 ;
		    String sql = "select id from tb_employee where name = "+"'"+user+"'";
		    rs = mydb.executeQuery(sql);
		    if(rs.next()) {
		        userId = rs.getInt("id");
		    }
			String sql_supply = "update tb_info set worktime =?, accomm =?, workType =?, headline =?, content =?, pay=?, phone=?,  posttime =? where user_id =? and type=?";
			PreparedStatement ps_supply = conn.prepareStatement(sql_supply);
			ps_supply.setInt(1, workTime);
			ps_supply.setInt(2, accomm);
			ps_supply.setInt(3, workType);
			ps_supply.setString(4, title);
			ps_supply.setString(5, content);
			ps_supply.setString(6, pay);
			ps_supply.setString(7, phone);
			ps_supply.setString(8, postTime);
			ps_supply.setInt(9, userId);
			ps_supply.setString(10, "1");
			ps_supply.executeUpdate();
			ps_supply.close();
			logger.info("执行SQL:"+sql_supply);
			rs.close();
			conn.close();
			addActionMessage("发布信息成功!");
			logger.info("用户"+user+"发布信息成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
			addActionMessage("发布信息失败,请检查您当前网络连接!");
		}
		return SUCCESS;
	}
	/*
	 * 功能:显示雇主预约信息
	 */
	public String showgzBook() throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =  response.getWriter();
		String bmRegName = (String)session.get("username");
		BookBm bm = new BookBm();
		int bmId = bm.queryID(bmRegName, "tb_employee");
		List<BookBmForm> list = new LinkedList<BookBmForm>();
		try {
			String sql_queryBook = "select gzId, gzName, gzPhone, gzPay, servType, servDailyTime, isAccomm, startDate, wkPeriod, bookTime, gzbkContent, isSuccess from tb_book where bmId=" + bmId + " and isOutdated='0'";
			ResultSet rs = mydb.executeQuery(sql_queryBook);
			logger.info("查询预约信息,执行SQL:"+sql_queryBook);
			
			while(rs.next()) {
				BookBmForm bookBm = new BookBmForm();
				bookBm.setGzId(rs.getInt("gzId"));
				bookBm.setGzName(rs.getString("gzName"));
				bookBm.setGzPhone(rs.getString("gzPhone"));
				bookBm.setGzPay(rs.getString("gzPay"));
				bookBm.setServType(rs.getInt("servType"));
				bookBm.setServDailyTime(rs.getInt("servDailyTime"));
				bookBm.setIsAccomm(rs.getInt("isAccomm"));
				bookBm.setStartDate(rs.getString("startDate"));
				bookBm.setWkPeriod(rs.getString("wkPeriod"));
				bookBm.setBkDate(rs.getString("bookTime"));
				bookBm.setGzbkContent(rs.getString("gzbkContent"));
				bookBm.setIsSuccess(rs.getString("isSuccess"));
//				System.out.println(bookBm.getGzName());
				list.add(bookBm);
			}
			JSONArray gzBook = JSONArray.fromObject(list);
//			System.out.println(gzBook);
			out.print(gzBook);
			out.flush();
			out.close();
			rs.close();
			mydb.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "gzBook";
	}
	/*
	 * 功能:家政阿姨选择已预约的雇主并生成服务协议
	 */
	public String bmOption() {
		//定义合同信息
		String bmName = null, gzName = null, servType = null, isAccomm = null, servDailyTime = null, beginDate = null, endDate = null, gzPay = null; 
		//查询当前用户Id
		String bmRegName = null;
		bmRegName = (String)session.get("username");
		BookBm bookBm = new BookBm();
	    int bmId = bookBm.queryID(bmRegName, "tb_employee");
		int selGzId = Integer.parseInt(request.getParameter("selectedGz"));
		//更新预约表结果
		String sql_updateOp = "update tb_book set isSuccess ='1' where bmID=" + bmId + " and gzID=" + selGzId;
		//更新预约表节点
		String sql_updateNode = "update tb_book set node ='bm' where bmID="+bmId;
		//更新阿姨工作状态
		String sql_updateBmStatus = "update tb_employee set status = '2' where id ="+bmId;
		//查询预约详细信息
		String sql_queryBook = "select bmName, gzName, servType, servDailyTime, isAccomm, startDate, endDate, gzPay from tb_book where bmID ="+bmId+" and gzID ="+selGzId+" and isSuccess ='1'";
		//更新评价表
		String sql_insertAssess = "insert into tb_assess (bmID,gzID,bmName,servType) values (?,?,?,?)";
		//更新合同表
		String sql_insertContract = "insert into tb_contract (bmID,bmName,gzID,gzName,servType,isAccomm,servDailyTime,beginDate,endDate,gzPay) values (?,?,?,?,?,?,?,?,?,?)";
		mydb.executeUpdate(sql_updateOp);
		logger.info("更新预约结果,执行SQL: "+sql_updateOp);
		mydb.executeUpdate(sql_updateNode);
		logger.info("更新预约节点,执行SQL: "+sql_updateNode);
		mydb.executeUpdate(sql_updateBmStatus);
		logger.info("更新保姆状态'在岗',执行SQL: "+sql_updateBmStatus);
//		System.out.println(selGzId);
		ResultSet rs_queryBook = mydb.executeQuery(sql_queryBook);
		logger.info("查询合同信息,执行SQL:"+sql_queryBook);
		try {
			int servTypeTemp = 0 , servDailyTimeTemp = 0, isAccommTemp = 0;
			if(rs_queryBook.next()) {
				bmName = rs_queryBook.getString("bmName");
				gzName = rs_queryBook.getString("gzName");
				servTypeTemp = rs_queryBook.getInt("servType");
				servDailyTimeTemp = rs_queryBook.getInt("servDailyTime");
				isAccommTemp = rs_queryBook.getInt("isAccomm");
				beginDate = rs_queryBook.getString("startDate");
				endDate = rs_queryBook.getString("endDate");
				gzPay = rs_queryBook.getString("gzPay");
			
			    if(servTypeTemp==1) {
				    servType = "做饭保洁";
			    }else if(servTypeTemp==2) {
				    servType = "育儿嫂";
			    }else if(servTypeTemp==3) {
				    servType = "育婴师";
			    }else if(servTypeTemp==4) {
				    servType = "月嫂母婴";
			    }else if(servTypeTemp==5) {
				    servType = "家教幼教";
			    }else {
				    servType = "管家司机";
			    }
			    if(servDailyTimeTemp==1) {
				    servDailyTime  = "终点计时";
			    }else if(servDailyTimeTemp==2) {
				    servDailyTime = "半天";
			    }else {
				    servDailyTime = "全天";
			    }
			    if(isAccommTemp==1) {
				    isAccomm = "住家包吃";
			    }else if(isAccommTemp==2) {
				    isAccomm = "不住家包吃";
			    }else if(isAccommTemp==3) {
				    isAccomm = "住家不包吃";
			    }else {
				    isAccomm = "不住家不包吃";
			    }
			    PreparedStatement ps_insertContract = conn.prepareStatement(sql_insertContract);
			    ps_insertContract.setInt(1, bmId);
			    ps_insertContract.setString(2, bmName);
			    ps_insertContract.setInt(3, selGzId);
			    ps_insertContract.setString(4, gzName);
			    ps_insertContract.setString(5, servType);
			    ps_insertContract.setString(6, isAccomm);
			    ps_insertContract.setString(7, servDailyTime);
			    ps_insertContract.setString(8, beginDate);
			    ps_insertContract.setString(9, endDate);
			    ps_insertContract.setString(10, gzPay);
			    ps_insertContract.executeUpdate();
			    ps_insertContract.close();
			    logger.info("更新合同信息,执行SQL:"+sql_insertContract);
			    PreparedStatement ps_insertAssess = conn.prepareStatement(sql_insertAssess);
			    ps_insertAssess.setInt(1, bmId);
			    ps_insertAssess.setInt(2, selGzId);
			    ps_insertAssess.setString(3, bmName);
			    ps_insertAssess.setString(4, servType);
			    ps_insertAssess.executeUpdate();
			    ps_insertAssess.close();
			    logger.info("更新评价表,执行SQL:"+sql_insertAssess);
			    conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		mydb.close();
		return SUCCESS;
	}
	/*
	 * 功能:雇主发布需求信息
	 */
	public String gzAddInfo() {
		//获取雇主选择的工作类型
		int workType = gzInfo.getWorktype();
//		System.out.println(workType);
		//获取雇主选择的工时
		int workTime = gzInfo.getWorktime();
//		System.out.println(workTime);
		//获取雇主选择的食宿要求
		int accomm = gzInfo.getAccomm();
//		System.out.println(accomm);
		//获取雇主输入的供应信息标题
		String title = gzInfo.getHeadLine();
		//获取雇主输入的供应信息内容
		String content = gzInfo.getContent();
		//获取雇主发布供应信息时间
		String postTime = gzInfo.getPostTime();
		//获取雇主联系方式
		String phone = gzInfo.getPhone();
		//获取雇主薪资
		String pay = gzInfo.getPay();
		//获取雇主用户名
		String gzSurname = (String)session.get("username");
		//查询用户ID
		BookBm bookBm = new BookBm();
		int gzID = bookBm.queryID(gzSurname, "tb_employer");
		
		try {
		    String sql_demand = "update tb_info set worktime =?, accomm =?, workType =?, headline =?, content =?, pay=?, phone=?,  posttime =? where user_id =? and type=?"; 
			PreparedStatement ps_demand = conn.prepareStatement(sql_demand);
			ps_demand.setInt(1, workTime);
			ps_demand.setInt(2, accomm);
			ps_demand.setInt(3, workType);
			ps_demand.setString(4, title);
			ps_demand.setString(5, content);
			ps_demand.setString(6, pay);
			ps_demand.setString(7, phone);
			ps_demand.setString(8, postTime);
			ps_demand.setInt(9, gzID);
			ps_demand.setString(10, "2");
			ps_demand.executeUpdate();
			logger.info("执行SQL:"+sql_demand);
			ps_demand.close();
			addActionMessage("成功发布信息，请等待审核");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return SUCCESS;
	}
	/*
	 * 功能：雇主界面显示预约结果
	 */
	public String showbmBook() throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter outer = response.getWriter();
		String gzRegName = (String)session.get("username");
		BookBm bookBm = new BookBm();
		int gzID = bookBm.queryID(gzRegName, "tb_employer");
		List<BookBmForm> list = new LinkedList<BookBmForm>();
		try {
			String sql_queryBmBook = "select bmID, bmName, startDate, wkPeriod, servType, servDailyTime, isAccomm, gzPay, node, bookTime, isSuccess from tb_book where gzID =" + gzID + " and isOutdated ='0'";
			ResultSet rs_queryBm = mydb.executeQuery(sql_queryBmBook);
			logger.info("执行SQL："+sql_queryBmBook);
			while(rs_queryBm.next()) {
				BookBmForm gzBookBm = new BookBmForm();
				gzBookBm.setBmId(rs_queryBm.getInt("bmID"));
				gzBookBm.setBmName(rs_queryBm.getString("bmName"));
				gzBookBm.setStartDate(rs_queryBm.getString("startDate"));
				gzBookBm.setWkPeriod(rs_queryBm.getString("wkPeriod"));
				gzBookBm.setServType(rs_queryBm.getInt("servType"));
				gzBookBm.setServDailyTime(rs_queryBm.getInt("servDailyTime"));
				gzBookBm.setIsAccomm(rs_queryBm.getInt("isAccomm"));
				gzBookBm.setGzPay(rs_queryBm.getString("gzPay"));
				gzBookBm.setNode(rs_queryBm.getString("node"));
				gzBookBm.setBkDate(rs_queryBm.getString("bookTime"));
				gzBookBm.setIsSuccess(rs_queryBm.getString("isSuccess"));
				list.add(gzBookBm);	
			}
			mydb.close();
			JSONArray bmBook = JSONArray.fromObject(list);
			outer.print(bmBook);
			outer.flush();
			outer.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return "bmBook";
	}
	/*
	 * 功能:雇主签订服务协议
	 */
	public String gzSignAgree() throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =  response.getWriter();
		String regName = (String)session.get("username");
		BookBm bookBm = new BookBm();
		int gzID = bookBm.queryID(regName, "tb_employer");
		String sql_queryContract = "select bmName, gzName, servType, isAccomm, servDailyTime, DATE_FORMAT(beginDate,'%Y年%m月%d日') as beginDate, DATE_FORMAT(endDate,'%Y年%m月%d日') as endDate, gzPay, gzSignature, DATE_FORMAT(gzSigDate,'%Y年%m月%d日') as gzSigDate, bmSignature, DATE_FORMAT(bmSigDate,'%Y年%m月%d日') as bmSigDate, DATE_FORMAT(sigDate,'%Y年%m月%d日') as sigDate from tb_contract where gzID=" + gzID + " and isOutDated ='0'";
		ResultSet rs_queryContract = mydb.executeQuery(sql_queryContract);
		logger.info("查询合同信息,执行SQL:"+sql_queryContract);
		List<ContractForm> list = new LinkedList<ContractForm>();
		try {
			if(rs_queryContract.next()) {
				ContractForm cm = new ContractForm();
				cm.setBmName(rs_queryContract.getString("bmName"));
				cm.setGzName(rs_queryContract.getString("gzName"));
				cm.setServType(rs_queryContract.getString("servType"));
				cm.setIsAccomm(rs_queryContract.getString("isAccomm"));
				cm.setServDailyTime(rs_queryContract.getString("servDailyTime"));
				cm.setBeginDate(rs_queryContract.getString("beginDate"));
				cm.setEndDate(rs_queryContract.getString("endDate"));
				cm.setGzPay(rs_queryContract.getString("gzPay"));
				cm.setGzSignature(rs_queryContract.getString("gzSignature"));
				cm.setGzSigDate(rs_queryContract.getString("gzSigDate"));
				cm.setBmSignature(rs_queryContract.getString("bmSignature"));
				cm.setBmSigDate(rs_queryContract.getString("bmSigDate"));
				cm.setSigDate(rs_queryContract.getString("sigDate"));
				list.add(cm);
			}
			JSONArray contract = JSONArray.fromObject(list);
			out.print(contract);
			out.flush();
			out.close();
			mydb.close();
		} catch(SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		String gzSignature = null, gzSigDate = null;
		gzSignature = request.getParameter("gzSig");
		gzSigDate = request.getParameter("sigDate");
		if(gzSignature!=null){
		    String sql_updateContract = "update tb_contract set gzSignature ='" +gzSignature+ "', gzSigDate ='" +gzSigDate+ "' where gzID =" +gzID+ " and isOutdated ='0'";
		    mydb.executeUpdate(sql_updateContract);
		    logger.info("雇主ID"+gzID+"签名,执行SQL:"+sql_updateContract);
		    mydb.close();
		}
		return "contract";
	}
	/*
	 * 功能:阿姨签订服务协议
	 */
	public String bmSignAgree() throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =  response.getWriter();
		String regName = (String)session.get("username");
		BookBm bookBm = new BookBm();
		int bmID = bookBm.queryID(regName, "tb_employee");
		String sql_queryContract = "select bmName, gzName, servType, isAccomm, servDailyTime, DATE_FORMAT(beginDate,'%Y年%m月%d日') as beginDate, DATE_FORMAT(endDate,'%Y年%m月%d日') as endDate, gzPay, gzSignature, DATE_FORMAT(gzSigDate,'%Y年%m月%d日') as gzSigDate, bmSignature, DATE_FORMAT(bmSigDate,'%Y年%m月%d日') as bmSigDate, DATE_FORMAT(sigDate,'%Y年%m月%d日') as sigDate from tb_contract where bmID=" + bmID + " and isOutDated ='0'";
		ResultSet rs_queryContract = mydb.executeQuery(sql_queryContract);
		logger.info("查询合同信息,执行SQL:"+sql_queryContract);
		List<ContractForm> list = new LinkedList<ContractForm>();
		try {
			if(rs_queryContract.next()) {
				ContractForm cm = new ContractForm();
				cm.setBmName(rs_queryContract.getString("bmName"));
				cm.setGzName(rs_queryContract.getString("gzName"));
				cm.setServType(rs_queryContract.getString("servType"));
				cm.setIsAccomm(rs_queryContract.getString("isAccomm"));
				cm.setServDailyTime(rs_queryContract.getString("servDailyTime"));
				cm.setBeginDate(rs_queryContract.getString("beginDate"));
				cm.setEndDate(rs_queryContract.getString("endDate"));
				cm.setGzPay(rs_queryContract.getString("gzPay"));
				cm.setGzSignature(rs_queryContract.getString("gzSignature"));
				cm.setGzSigDate(rs_queryContract.getString("gzSigDate"));
				cm.setBmSignature(rs_queryContract.getString("bmSignature"));
				cm.setBmSigDate(rs_queryContract.getString("bmSigDate"));
				cm.setSigDate(rs_queryContract.getString("sigDate"));
				list.add(cm);
			}
			JSONArray contract = JSONArray.fromObject(list);
			out.print(contract);
			out.flush();
			out.close();
			mydb.close();
		} catch(SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		String bmSignature = null, bmSigDate = null;
		bmSignature = request.getParameter("bmSig");
		bmSigDate = request.getParameter("sigDate");
		if(bmSignature!=null){
		    String sql_updateContract = "update tb_contract set bmSignature ='" +bmSignature+ "', bmSigDate ='" +bmSigDate+ "' where bmID =" +bmID+ " and isOutdated ='0'";
		    mydb.executeUpdate(sql_updateContract);
		    logger.info("阿姨ID"+bmID+"签名,执行SQL:"+sql_updateContract);
		    mydb.close();
		}
		return "contract";
	}
	/*
	 * 填写服务评价
	 */
	public String gzAssess() {
		String regName = (String)session.get("username");
		BookBm bookBm = new BookBm();
		int gzID = bookBm.queryID(regName, "tb_employer");
		String svAttitude = request.getParameter("svAttitude");
		String svQuality = request.getParameter("svQuality");
		String svPrice = request.getParameter("svPrice");
		String svRecommend = request.getParameter("svRecommend");
		String svContent = request.getParameter("svContent");
		String svDate = request.getParameter("svDate");
		String sql = "update tb_assess set svAttitude =?, svQuality=?, svPrice=?, svRecommend=?, svContent=?, svDate =? where isOutdated= '0' and gzID="+gzID;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, svAttitude);
			ps.setString(2, svQuality);
			ps.setString(3, svPrice);
			ps.setString(4, svRecommend);
			ps.setString(5, svContent);
			ps.setString(6, svDate);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
		return SUCCESS;
	}
	/*
	 * 查看服务评价
	 */
	public String gzIsAssess() throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		BookBm bookBm = new BookBm();
		String regName = (String)session.get("username");
		int gzID = bookBm.queryID(regName, "tb_employer");
//		System.out.println(gzID);
		String sqlGzAssess = "select bmID, bmName, servType, svAttitude, svQuality, svPrice, svRecommend, svContent, svDate from tb_assess where isOutdated = '0' and gzID="+gzID;
		ResultSet rs = mydb.executeQuery(sqlGzAssess);
		logger.info("查询评价信息,执行SQL:"+sqlGzAssess);
		List<AssessForm> list = new LinkedList<AssessForm>();
		try {
			if(rs.next()) {
				AssessForm assess = new AssessForm();
				assess.setBmID(rs.getInt("bmID"));
				assess.setBmName(rs.getString("bmName"));
				assess.setServType(rs.getString("servType"));
				assess.setSvAttitude(rs.getInt("svAttitude"));
				assess.setSvQuality(rs.getInt("svQuality"));
				assess.setSvPrice(rs.getInt("svPrice"));
				assess.setSvRecommend(rs.getInt("svRecommend"));
				assess.setSvContent(rs.getString("svContent"));
				assess.setSvDate(rs.getString("svDate"));
				list.add(assess);
			}
			JSONArray assess = JSONArray.fromObject(list);
			out.print(assess);
			out.flush();
			out.close();
			mydb.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
		return "assess";
	}
	/*
	 * 功能:家政公司发布家政名片
	 */
	public String publishCard() {
		//获取家政公司名称
        String companyName = company.getName();
        //获取家政公司主营业务
        String companyDesc = company.getDesc();
        //获取家政公司联系人
        String companyLinkman = company.getLinkman();
        //获取家政公司联系方式
        String companyContacts = company.getContacts();
        //获取家政公司更细名片时间
        String companyUptime = company.getUpTime();
        //获取用户ID
        String regName = (String)session.get("username");
        BookBm bookBm = new BookBm();
        int compID = bookBm.queryID(regName, "tb_company");
        String sql_updateCompany = "update tb_company set companyName =?, companyDesc =?, companyLinkman =?, companyContacts =?, companyUpTime =? where id =?";
        try {
			PreparedStatement ps_updateCompany = conn.prepareStatement(sql_updateCompany);
			ps_updateCompany.setString(1, companyName);
			ps_updateCompany.setString(2, companyDesc);
			ps_updateCompany.setString(3, companyLinkman);
			ps_updateCompany.setString(4, companyContacts);
			ps_updateCompany.setString(5, companyUptime);
			ps_updateCompany.setInt(6, compID);
			ps_updateCompany.executeUpdate();
			conn.close();
			logger.info("更新家政名片,执行SQL:"+sql_updateCompany);
			addActionMessage("成功更新家政名片");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
			addActionMessage("更新家政名片失败");
		}
		return SUCCESS;
	}
	/*
	 * 功能：用户中心消息提醒
	 */
	public String newTips() throws IOException  {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<MessageForm> list = new LinkedList<MessageForm>();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String type = (String)session.get("userType");
		String user = (String)session.get("username");
		//家政阿姨消息提醒
		if(type!=null&&type.equals("employee")) {
			//家政阿姨ID
			BookBm bookBm = new BookBm();
			int bmID = bookBm.queryID(user, "tb_employee");
			try {
				String sql_employee = "select uptime from tb_employee where id ="+bmID;
				ResultSet rs_employee = mydb.executeQuery(sql_employee);
				if(rs_employee.next()) {
					String uptime = rs_employee.getString("uptime");
					if(uptime==null||uptime.equals("")) {
						MessageForm message = new MessageForm();
						message.setMessageForm("请您尽快到【个人信息】中完成基本信息的更新！");
						list.add(message);
					}
				}
				String sql_book = "select node from tb_book where isOutdated = '0' and bmID ="+bmID;
				ResultSet rs_book = mydb.executeQuery(sql_book);
				if(rs_book.next()) {
					String node = rs_book.getString("node");
					if(node.equals("gz")) {
						MessageForm message = new MessageForm();
						message.setMessageForm("当前已经有雇主预约您，请您尽快到【预约管理】中查看和选择！");
						list.add(message);
					}
				}
				String sql_contract = "select bmSigDate, endDate from tb_contract where isOutdated = '0' and bmID="+bmID;
				ResultSet rs_contract = mydb.executeQuery(sql_contract);
				if(rs_contract.next()) {
					String bmSigDate = rs_contract.getString("bmSigDate");
					if(bmSigDate==null||bmSigDate.equals("")) {
						MessageForm message = new MessageForm();
						message.setMessageForm("您已成功完成预约流程，请尽快到【服务协议】中确认信息并签署服务协议！");
						list.add(message);
					}
					String endDate = rs_contract.getString("endDate");
					Date sDate = dt.parse(dt.format(new Date()));
					Date eDate = dt.parse(endDate);
					int interval = calDate.calInterval(sDate,eDate,"D");
					MessageForm message = new MessageForm();
					message.setMessageForm("您的合同还有"+interval+"天到期，请您注意！");
					list.add(message);
					//更新过期信息标识
					if(interval<=0) {
						String sqlBook = "update tb_book set isOutdated = '1' where bmID="+bmID;
						mydb.executeUpdate(sqlBook);
						logger.info("预约表过期,执行SQL:"+sqlBook);
						String sqlContract = "update tb_contract set isOutdated = '1' where bmID="+bmID;
						mydb.executeUpdate(sqlContract);
						logger.info("合同表过期,执行SQL:"+sqlContract);
						String sqlAssess = "update tb_assess set isOutdated = '1' where bmID="+bmID;
						mydb.executeUpdate(sqlAssess);
						logger.info("评价表过期,执行SQL:"+sqlAssess);
						String sqlEmployee = "update tb_employee set status = '0' where id ="+bmID;
						mydb.executeUpdate(sqlEmployee);
						logger.info("更新阿姨状态'在岗',执行SQL:"+sqlEmployee);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
		//雇主消息提醒
		if(type!=null&&type.equals("employer")){
			BookBm bookBm = new BookBm();
			int gzID = bookBm.queryID(user, "tb_employer");
			try {
				String sql_contract = "select gzSigDate, endDate from tb_contract where isOutdated = '0' and gzID="+gzID;
				ResultSet rs_contract = mydb.executeQuery(sql_contract);
				if(rs_contract.next()) {
					String gzSigDate = rs_contract.getString("gzSigDate");
					if(gzSigDate==null||gzSigDate.equals("")) {
						MessageForm message = new MessageForm();
						message.setMessageForm("您已成功完成预约流程，请尽快到【服务协议】中确认信息并签署服务协议！");
						list.add(message);
					}
					String endDate = rs_contract.getString("endDate");
					Date sDate = dt.parse(dt.format(new Date()));
					Date eDate = dt.parse(endDate);
					int interval = calDate.calInterval(sDate,eDate,"D");
					MessageForm message = new MessageForm();
					message.setMessageForm("您的合同还有"+interval+"天到期，请您注意！");
					list.add(message);
					//更新过期信息标识
//					if(interval<=0) {
//						String sqlBook = "update tb_book set isOutdated = '1' where gzID ="+gzID;
//						mydb.executeUpdate(sqlBook);
//						logger.info("预约表过期,执行SQL:"+sqlBook);
//						String sqlContract = "update tb_contract set isOutdated = '1' where gzID ="+gzID;
//						mydb.executeUpdate(sqlContract);
//						logger.info("合同表过期,执行SQL:"+sqlContract);
//						String sqlAssess = "update tb_assess set isOutdated = '1' where gzID ="+gzID;
//						mydb.executeUpdate(sqlAssess);
//						logger.info("评价表过期,执行SQL:"+sqlAssess);
//					}
				}
				String sql_assess = "select svDate from tb_assess where isOutdated = '0' and gzID ="+gzID;
				ResultSet rs_assess = mydb.executeQuery(sql_assess);
				if(rs_assess.next()) {
					String svDate = rs_assess.getString("svDate");
					if(svDate==null||svDate.equals("")) {
						MessageForm message = new MessageForm();
						message.setMessageForm("您有尚未完成的服务评价，请及时到【服务评价】中查看并完成！");
						list.add(message);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
		//企业用户消息提醒
		if(type!=null&&type.equals("company")) {
			BookBm bookBm = new BookBm();
			int companyID = bookBm.queryID(user, "tb_company");
			try {
				String sqlCompany = "select companyUpTime from tb_company where id ="+companyID;
				ResultSet rsCompany = mydb.executeQuery(sqlCompany);
				if(rsCompany.next()) {
					String companyUpTime = rsCompany.getString("companyUpTime");
					if(companyUpTime==null||companyUpTime.equals("")) {
						MessageForm message = new MessageForm();
						message.setMessageForm("欢迎您注册使用本网站，现在您可以到【家政名片】中发布免费的企业名片了，它将会在我们的前台页面展示！");
						list.add(message);
					} 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
		JSONArray message = JSONArray.fromObject(list);
		out.print(message);
		out.flush();
		out.close();
		mydb.close();
		return "message";
	}

}