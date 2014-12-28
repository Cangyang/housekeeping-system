package com.ytf.action;

import org.apache.log4j.Logger; 
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ytf.actionSuper.MySuperAction;
import com.ytf.actionForm.RegisterForm;
import com.ytf.core.ConnDB;
import com.ytf.core.OpDB;
import com.ytf.action.BookBm;
/**
 * 用户注册Action
 * @version 1.0
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
public class RegisterAction extends MySuperAction {
	private static Logger logger = Logger.getLogger(RegisterAction.class);	
	//实现RegisterForm
	private RegisterForm user;
	public RegisterForm getUser() {
		return user;
	}
	public void setUser(RegisterForm user) {
		this.user = user;
	}
	
	
	public String execute() throws IOException {
		String type=user.getType();
		String name=user.getName();
		String email=user.getEmail();
		String password=user.getPwd();
		String repass=user.getRepwd();
		String securityCode=user.getSecurityCode();
		String userType = "tb_"+type;
	   if(!isEmpty()) {
		   return INPUT;
	   } else if(!isValid()){
		   return INPUT;
	   } else if(!Register()) {
		   addActionMessage("用户注册失败!");
		   return INPUT;
		   
	   } else if(!IniEmployeeInfo(name, type)){
		   addActionMessage("初始化用户信息失败!");
		   return INPUT;
	   }
	   else {
		
		
		return SUCCESS;
	   }
	}
	
	/**
	 * 验证用户是否输入为空
	 * @return boolean
	 */
	public boolean isEmpty() {
		String type=user.getType();
		String name=user.getName();
		String email=user.getEmail();
		String password=user.getPwd();
		String repass=user.getRepwd();
		String securityCode=user.getSecurityCode();
		String userType = "tb_"+type;
		if(type==null||type.equals("")){
			addFieldError("user.type","请选择用户类型!");
			return false;
		}
		else if(name==null||name.equals("")){
			addFieldError("user.name","请输入用户名");
			return false;
		}
		else if(email==null||email.equals("")){
			addFieldError("user.email","请输入邮箱");
			return false;
		}
		else if(password==null||password.equals("")){
			addFieldError("user.pwd","请输入密码!");
			return false;
		}
		else if(repass==null||repass.equals("")){
			addFieldError("user.repwd","请再次输入密码");
			return false;
		}
		else if(securityCode==null||securityCode.equals("")){
			addFieldError("user.securityCode","请输入验证码!");
			return false;
		}
		else {
			return true;
		}
	}
	
   /**
    * 验证用户的输入是否有效
    * @return boolean
    */
	public boolean isValid() {
		String type=user.getType();
		String name=user.getName();
		String email=user.getEmail();
		String password=user.getPwd();
		String repass=user.getRepwd();
		String securityCode=user.getSecurityCode().toUpperCase();
		String userType = "tb_"+type;
		OpDB myOp = new OpDB();
		//接收客户端传来的验证码
		String serverCode = (String)session.get("SESSION_SECURITY_CODE");
        if(!serverCode.equals(securityCode)) {
			addActionMessage("验证码不正确，请仔细看看再填入！");
			return false;
        }
        else if(myOp.CheckUser(name, userType)) {
            addActionMessage("用户名已存在!");
            return false;
        }
        else return true;
	}
	
	/**
	 * 用户注册
	 * @return String
	 * @throws IOException 
	 */
	public boolean Register() throws IOException {
		String type=user.getType();
		String name=user.getName();
		String email=user.getEmail();
		String password=user.getPwd();
		String userType = "tb_"+type;
		Connection conn = ConnDB.getConnection();
		//注册用户插入数据库	
		try {
			String sql = "insert into " + userType + " (name,email,password) values (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.executeUpdate();
			ps.close();
			logger.info("插入注册用户信息,执行SQL:"+sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			e.printStackTrace();
			return false;
		}				
		return true;
	}
	
	/** 功能：初始化注册用户信息 
	 * @throws IOException */
	public boolean IniEmployeeInfo(String username, String userType) throws IOException {	
		Connection conn = ConnDB.getConnection();
		if(userType.equals("employee")) {
			int userId=0;
			String sql_queryID = "select id from tb_employee where name= ?";
			String sql_insertCert = "insert into tb_cert (user_id,cert_jiankang,cert_yuesao,cert_hushi,cert_yingyangshi,cert_zaojiao,cert_jiashi,cert_chushi) values (?,?,?,?,?,?,?,?)";
			String sql_insertSkills = "insert into tb_skills (user_id,skill_computer,skill_ironing,skill_handwork,skill_waiyu,skill_driving,skill_nutriology) values (?,?,?,?,?,?,?)";
			String sql_insertLang = "insert into tb_lang (user_id,lang_local,lang_normal,lang_sichuan,lang_dongbei,lang_guangdong,lang_kejia,lang_minnan) values (?,?,?,?,?,?,?,?)";
			String sql_insertFlavor = "insert into tb_flavor (user_id,flavor_local,flavor_xican,flavor_chuancai,flavor_yuecai,flavor_lucai,flavor_sucai,flavor_zhecai,flavor_xiangcai,flavor_mincai,flavor_huicai) values (?,?,?,?,?,?,?,?,?,?,?)";
			String sql_insertSupply = "insert into tb_info (user_id,worktime,accomm,workType,type) values (?,?,?,?,?)";
		    try {
		    	PreparedStatement ps =conn.prepareStatement(sql_queryID);
				PreparedStatement ps1 =conn.prepareStatement(sql_insertCert);
				PreparedStatement ps2 =conn.prepareStatement(sql_insertSkills);
				PreparedStatement ps3 =conn.prepareStatement(sql_insertLang);
				PreparedStatement ps4 =conn.prepareStatement(sql_insertFlavor);
				PreparedStatement ps5 =conn.prepareStatement(sql_insertSupply);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();	
				if(rs.next()) {
				    userId = rs.getInt("id");
				}
			    rs.close();
			    ps.close();
			    logger.info("初始化注册用户ID,执行SQL:"+sql_queryID);
			    ps1.setInt(1, userId);
			    ps1.setInt(2, 0);
			    ps1.setInt(3, 0);
			    ps1.setInt(4, 0);
			    ps1.setInt(5, 0);
			    ps1.setInt(6, 0);
			    ps1.setInt(7, 0);
			    ps1.setInt(8, 0);
			    ps1.executeUpdate();
			    ps1.close();
			    logger.info("初始化注册用户的证书表,执行SQL:"+sql_insertCert);
			    ps2.setInt(1, userId);
			    ps2.setInt(2, 0);
			    ps2.setInt(3, 0);
			    ps2.setInt(4, 0);
			    ps2.setInt(5, 0);
			    ps2.setInt(6, 0);
			    ps2.setInt(7, 0);
			    ps2.executeUpdate();
			    ps2.close();
			    logger.info("初始化注册用户的技能表,执行SQL:"+sql_insertSkills);
			    ps3.setInt(1, userId);
			    ps3.setInt(2, 0);
			    ps3.setInt(3, 0);
			    ps3.setInt(4, 0);
			    ps3.setInt(5, 0);
			    ps3.setInt(6, 0);
			    ps3.setInt(7, 0);
			    ps3.setInt(8, 0);
			    ps3.executeUpdate();
			    ps3.close();
			    logger.info("初始化注册用户的语言表,执行SQL:"+sql_insertLang);
			    ps4.setInt(1, userId);
			    ps4.setInt(2, 0);
			    ps4.setInt(3, 0);
			    ps4.setInt(4, 0);
			    ps4.setInt(5, 0);
			    ps4.setInt(6, 0);
			    ps4.setInt(7, 0);
			    ps4.setInt(8, 0);
			    ps4.setInt(9, 0);
			    ps4.setInt(10, 0);
			    ps4.setInt(11, 0);
			    ps4.executeUpdate();
			    ps4.close();
			    logger.info("初始化注册用户的口味表,执行SQL:"+sql_insertFlavor);
			    ps5.setInt(1, userId);
				ps5.setInt(2, 1);
				ps5.setInt(3, 1);
				ps5.setInt(4, 1);
				ps5.setString(5, "1");
				ps5.executeUpdate();
				ps5.close();
				logger.info("初始化注册用户的供应信息表,执行SQL:"+sql_insertSupply);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}  
		}
		//初始化雇主信息
		if(userType.equals("employer")) {
			BookBm bookBm = new BookBm();
			int gzID = bookBm.queryID(username, "tb_employer");
			try{
			    String sql_insertDemand = "insert into tb_info (user_id,type) values (?,?)";
			    PreparedStatement ps_demand = conn.prepareStatement(sql_insertDemand);
			    ps_demand.setInt(1, gzID);
			    ps_demand.setString(2, "2");
			    ps_demand.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
				logger.info(e.getMessage());
				return false;
			}
		}
		return true;
	}
	
	
	
    
}
