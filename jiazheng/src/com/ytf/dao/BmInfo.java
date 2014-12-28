package com.ytf.dao;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger; 
import com.ytf.actionSuper.*;
import com.ytf.actionForm.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import com.ytf.core.*;
public class BmInfo extends MySuperAction{
	//private UserInfoForm bmInfo = new UserInfoForm();
	///**
	// * @return the bmInfo
	// */
	//public UserInfoForm getBmInfo() {
//		return bmInfo;
	//}
	///**
	// * @param bmInfo the bmInfo to set
	// */
	//public void setBmInfo(UserInfoForm bmInfo) {
//		this.bmInfo = bmInfo;
	//}




	private static final long serialVersionUID = 1L;
	int userId;
	String surname;
	String birthdate;
	String hometown;
	String uptime;
	/* 
	 * 0:δѡ��,Ĭ�ϲ�ѯ����
	 * 1:�յ��ʱ
	 * 2:����
	 * 3:ȫ��
	 */
	private String workTime;	
	/*
	 * 0:δѡ��,Ĭ�ϲ�ѯ����
	 * 1:����/��ס
	 * 2:����/����ס
	 * 3:������/��ס
	 * 4:������/����ס
	 */
	private String accomm;
	/*
	 * 0:δѡ��,Ĭ�ϲ�ѯ����
	 * 1:��������
	 * 2:����ɩ
	 * 3:��Ӥʦ
	 * 4:��ɩĸӤ
	 * 5:�ҽ��׽�
	 * 6:�ܼ�˾��
	 */
	private String workType;
	/*
	 * 0:δѡ��,Ĭ�ϲ�ѯ����
	 * cert_jiankang:����֤
	 * cert_yuesao:��ɩ֤
	 * cert_hushi:��ʿ֤
	 * cert_yingyangshi:Ӫ��ʦ֤
	 * cert_zaojiao:���֤
	 * cert_jiashi:��ʻ֤
	 * cert_chushi:��ʦ֤
	 */
	private String bmCert;
	/*
	 * 0:δѡ��,Ĭ�ϲ�ѯ����
	 * skill_computer:���Բ���
	 * skill_ironing:�����·�
	 * skill_handwork:�ֹ���֯
	 * skill_waiyu:����
	 * skill_driving:����
	 * skill_nutriology:Ӫ��ѧ֪ʶ
	 */
	private String bmSkill;
	/*
	 * 0:δѡ��,Ĭ�ϲ�ѯ����
	 * lang_local:���ط���
	 * lang_normal:��ͨ��
	 * lang_sichuan:�Ĵ���
	 * lang_dongbei:������
	 * lang_guangdong:�㶫��
	 * lang_kejia:�ͼһ�
	 * lang_minnan:������
	 */
	private String bmLang;
	/*
	 * 0:δѡ��,Ĭ�ϲ�ѯ����
	 * flavor_local:���ز�
	 * flavor_xican:����
	 * flavor_chuancai:����
	 * flavor_yuecai:����
	 * flavor_lucai:³��
	 * flavor_sucai:�ղ�
	 * flavor_zhecai:���
	 * flavor_xiangcai:���
	 * flavor_mincai:����
	 * flavor_huicai:�ղ�
	 */
	private String bmFlavor;
	private String bmHomeCity;
	private static Logger logger = Logger.getLogger(BmInfo.class);	
	private ConnDB db = new ConnDB();

	/*
	 * ����:����������ѯ��ķ
	 * @param workTime
	 * @param accomm
	 * @param workType
	 * @param bmCert
	 * @param bmSkill
	 * @param bmLang
	 * @param bmFlavor
	 * @param bmHomeCity
	 * @return bmInfo
	 */
	public String queryBm() throws IOException {
		List<UserInfoForm> list = new LinkedList<UserInfoForm>();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =  response.getWriter();
		
		workTime = request.getParameter("workTime");
		logger.info("��ȡ��ʱ:"+workTime);
		accomm = request.getParameter("accomm");
		logger.info("��ȡʳ��:"+accomm);
		workType = request.getParameter("workType");
		logger.info("��ȡ��������:"+workType);
		bmCert = request.getParameter("bmCert");
		logger.info("��ȡ��ķ֤��:"+bmCert);
		bmSkill = request.getParameter("bmSkill");
		logger.info("��ȡ��ķ����:"+bmSkill);
		bmLang = request.getParameter("bmLang");
		logger.info("��ȡ��ķ����:"+bmLang);
		bmFlavor = request.getParameter("bmFlavor");
		logger.info("��ȡ��ķ��ζ:"+bmFlavor);
		bmHomeCity = request.getParameter("bmHomeCity");
		logger.info("��ȡ��ķ����:"+bmHomeCity);
		
		String sql_workTime = workTime.equals("0") || workTime==null ? "select user_id from tb_info t1 where " : "select user_id from tb_info t1 where workTime = '"+workTime+"' and ";
		String sql_accomm = accomm.equals("0") || accomm==null ? "select user_id from tb_info t2 where " : "select user_id from tb_info t2 where accomm = '"+accomm+"' and ";
		String sql_workType = workType.equals("0") || workType==null ? "select user_id from tb_info t3 where " : "select user_id from tb_info t3 where workType = '"+workType+"' and ";
		String sql_cert = bmCert.equals("0") || bmCert==null ? "select user_id from tb_cert t4 where " : "select user_id from tb_cert t4 where "+bmCert+"=1 and ";
		String sql_skill = bmSkill.equals("0") || bmSkill==null ? "select user_id from tb_skills t5 where " : "select user_id from tb_skills t5 where "+bmSkill+" =1 and ";
		String sql_lang = bmLang.equals("0") || bmLang==null ? "select user_id from tb_lang t6 where " : "select user_id from tb_lang t6 where "+bmLang+" =1 and ";
		String sql_flavor = bmFlavor.equals("0") || bmFlavor==null ? "select user_id from tb_flavor t7 where " : "select user_id from tb_flavor t7 where "+bmFlavor+" =1 and ";
		String sql_homeCity = bmHomeCity.equals("0") || bmHomeCity==null ? "select id from tb_employee t8 where " : "select id from tb_employee t8 where hometown like '%"+bmHomeCity+"%' and ";
		String sql_result = "select id, surname, birthdate, hometown, uptime from tb_employee t where exists (" + 
		    sql_workTime+"t.id=t1.user_id) and exists (" + 
				sql_accomm+"t.id=t2.user_id) and exists (" + 
		            sql_workType+"t.id=t3.user_id) and exists (" + 
				        sql_cert+"t.id=t4.user_id) and exists (" +
		                    sql_skill+"t.id=t5.user_id) and exists (" +
				                sql_lang+"t.id=t6.user_id) and exists (" +
		                            sql_flavor+"t.id=t7.user_id) and exists (" +
				                        sql_homeCity+"t.id=t8.id) and uptime is not null order by rank desc";
		try{
			db.executeQuery("set names gbk");
			ResultSet rs = db.executeQuery(sql_result);
			logger.info("ִ��SQL: "+sql_result);
			if(rs==null){
				logger.info("û�в�ѯ�������Ϣ�İ���");
			}else{
			    while(rs.next()){
			    UserInfoForm bmInfo = new UserInfoForm();	
				userId = rs.getInt("id");
				surname = rs.getString("surname");
				birthdate = rs.getString("birthdate");
				hometown = rs.getString("hometown");
				uptime = rs.getString("uptime");
				
				logger.info("��ѯ���:�û����"+userId);
				logger.info("��ѯ���:�û�����"+surname);
//				logger.info("��ѯ���:�û�����"+birthdate);
//				logger.info("��ѯ���:�û�����"+hometown);
//				logger.info("��ѯ���:�û�����ʱ��"+uptime);
				bmInfo.setId(userId);
				bmInfo.setSurname(surname);
				bmInfo.setBirthDate(calAge(birthdate));
				bmInfo.setHometown(hometown);
				bmInfo.setUptime(uptime);
				list.add(bmInfo);
			    }
			    
			    JSONArray bm = JSONArray.fromObject(list);
			    out.print(bm);
			
			out.flush();
			out.close();

//			System.out.println(list.size());
			}	

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			db.close();
			
		}
		
		return "query";
	}
	/*
	 * Ĭ����ʾ���б�ķ�Ļ�����Ϣ
	 */
	public String showAll() throws IOException {
//		    List<UserInfoForm> bmList = new LinkedList<UserInfoForm>();
		    response.setContentType("application/json;charset=utf-8");
		    response.setCharacterEncoding("utf-8");
		    PrintWriter out =  response.getWriter();
//		    //Ĭ�ϲ�ѯ���м�����Ա��Ϣ
//			String sql_bm = "select id, surname, birthdate, hometown, uptime from tb_employee";
//			ResultSet rs = db.executeQuery(sql_bm);
//			logger.info("ִ��SQL:"+sql_bm);
//			try{
//				while(rs.next()) {
//				    UserInfoForm bmInfo = new UserInfoForm();	
//					userId = rs.getInt("id");
//					surname = rs.getString("surname");
//					birthdate = rs.getString("birthdate");
//					hometown = rs.getString("hometown");
//					uptime = rs.getString("uptime");
//					
//					bmInfo.setId(userId);
//					bmInfo.setSurname(surname);
//					bmInfo.setBirthDate(birthdate);
//					bmInfo.setHometown(hometown);
//					bmInfo.setUptime(uptime);
//					bmList.add(bmInfo);
//					System.out.println(bmInfo.getSurname());
//				}
//				JSONArray all = JSONArray.fromObject(bmList);
//			    out.print("hello");
//			    out.flush();
//			} catch(SQLException e){
//				logger.info(e.getMessage());
//				e.printStackTrace();
//			}  finally {
//				db.close();
//				out.close();
//			}
			out.write("hello");
			out.flush();
			return "ready";
	       }
	/*
	 * ��ʾ��ķ����ϸ��Ϣ
	 */
	@SuppressWarnings({ })
	public String detail() throws IOException {
		List<UserInfoForm> list1 = new LinkedList<UserInfoForm>();
		response.setContentType("application/json;charset=utf-8");
	    response.setCharacterEncoding("utf-8");
	    PrintWriter ot1 =  response.getWriter();
		String bmId = request.getParameter("id");
		logger.info("��ȡ��ķID:"+bmId);
		//����ID��ѯ��ķ������Ϣ
		String sql_employee = "select status, id, surname, hometown, sex, birthdate, phone, marriage, education, address, profile, uptime from tb_employee where id="+bmId;
		ResultSet rs_employee = db.executeQuery(sql_employee);
		logger.info("ִ��SQL:"+sql_employee);
		//����ID��ѯ֤��
		String sql_cert = "select * from tb_cert where user_id="+bmId;
		ResultSet rs_cert = db.executeQuery(sql_cert);
		logger.info("ִ��SQL:"+sql_cert);
		//����ID��ѯ����
		String sql_skill = "select * from tb_skills where user_id="+bmId;
		ResultSet rs_skill = db.executeQuery(sql_skill);
		logger.info("ִ��SQL:"+sql_skill);
		//����ID��ѯ����
		String sql_lang = "select * from tb_lang where user_id="+bmId;
		ResultSet rs_lang = db.executeQuery(sql_lang);
		logger.info("ִ��SQL:"+sql_lang);
		//����ID��ѯ��ζ
		String sql_flavor = "select * from tb_flavor where user_id="+bmId;
		ResultSet rs_flavor = db.executeQuery(sql_flavor);
		logger.info("ִ��SQL:"+sql_flavor);
		//����ID��ѯ��Ӧ��Ϣ
		String sql_supply = "select worktime, accomm, workType from tb_info where user_id="+bmId +" and type='1'";
		ResultSet rs_supply = db.executeQuery(sql_supply);
		logger.info("ִ��SQL:"+sql_supply);
		try {
			UserInfoForm bmDetail = new UserInfoForm();
			while(rs_employee.next()) {
				bmDetail.setStatus(rs_employee.getString("status"));
				bmDetail.setSurname(rs_employee.getString("surname"));
				bmDetail.setId(genId(rs_employee.getInt("id")));
				bmDetail.setSex(rs_employee.getInt("sex"));
				bmDetail.setBirthDate(calAge(rs_employee.getString("birthdate")));
				bmDetail.setHometown(rs_employee.getString("hometown"));
				bmDetail.setEducation(rs_employee.getInt("education"));
				bmDetail.setMarriage(rs_employee.getInt("marriage"));
				bmDetail.setAddress(rs_employee.getString("address"));
				bmDetail.setPhone(rs_employee.getString("phone"));
				bmDetail.setProfile(rs_employee.getString("profile"));
				bmDetail.setUptime(rs_employee.getString("uptime"));
				
			}
			while(rs_cert.next()) {
				String [] cert = {
						String.valueOf(rs_cert.getInt("cert_jiankang")),
						String.valueOf(rs_cert.getInt("cert_yuesao")),
						String.valueOf(rs_cert.getInt("cert_hushi")),
						String.valueOf(rs_cert.getInt("cert_yingyangshi")),
						String.valueOf(rs_cert.getInt("cert_zaojiao")),
						String.valueOf(rs_cert.getInt("cert_jiashi")),
						String.valueOf(rs_cert.getInt("cert_chushi"))
						};
				bmDetail.setCert(cert);
			}
			while(rs_skill.next()) {
				String [] skill = {
						String.valueOf(rs_skill.getInt("skill_computer")),
						String.valueOf(rs_skill.getInt("skill_ironing")),
						String.valueOf(rs_skill.getInt("skill_handwork")),
						String.valueOf(rs_skill.getInt("skill_waiyu")),
						String.valueOf(rs_skill.getInt("skill_driving")),
						String.valueOf(rs_skill.getInt("skill_nutriology"))
				};
				bmDetail.setSkills(skill);
			}
			while(rs_lang.next()) {
				String [] lang = {
						String.valueOf(rs_lang.getInt("lang_local")),
						String.valueOf(rs_lang.getInt("lang_normal")),
						String.valueOf(rs_lang.getInt("lang_sichuan")),
						String.valueOf(rs_lang.getInt("lang_dongbei")),
						String.valueOf(rs_lang.getInt("lang_guangdong")),
						String.valueOf(rs_lang.getInt("lang_kejia")),
						String.valueOf(rs_lang.getInt("lang_minnan"))
				};
				bmDetail.setLang(lang);
			}
			while(rs_flavor.next()) {
				String [] flavor = {
						String.valueOf(rs_flavor.getInt("flavor_local")),
						String.valueOf(rs_flavor.getInt("flavor_xican")),
						String.valueOf(rs_flavor.getInt("flavor_chuancai")),
						String.valueOf(rs_flavor.getInt("flavor_yuecai")),
						String.valueOf(rs_flavor.getInt("flavor_lucai")),
						String.valueOf(rs_flavor.getInt("flavor_sucai")),
						String.valueOf(rs_flavor.getInt("flavor_zhecai")),
						String.valueOf(rs_flavor.getInt("flavor_xiangcai")),
						String.valueOf(rs_flavor.getInt("flavor_mincai")),
						String.valueOf(rs_flavor.getInt("flavor_huicai")),
				};
				bmDetail.setFlavor(flavor);
			}
			while(rs_supply.next()) {
				bmDetail.setWorktime(rs_supply.getInt("worktime"));
				bmDetail.setAccomm(rs_supply.getInt("accomm"));
				bmDetail.setWorktype(rs_supply.getInt("workType"));
			}
			list1.add(bmDetail);
			JSONArray bmdetail = JSONArray.fromObject(list1);
			System.out.println(bmdetail);
			ot1.print(bmdetail);
			ot1.flush();
		} catch(SQLException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			db.close();
			ot1.close();
		}
//		OUT.PRINT("HELLO");
//		OUT.FLUSH();
		return "detail";
	}

	/*
	 * ������:�����û�����ĳ������ڼ�������
	 * @param birthdate
	 * @return age
	 */
	public static String calAge(String birthdate) {
		Date now = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String str1 = dt.format(now).replace('-', '/');
//		logger.info("��ǰʱ��:"+str1);
		String str2 = birthdate.replace('-', '/');
//		logger.info("��ķ����:"+str2);
		Date dt1 = new Date(str1);
		Date dt2 = new Date(str2);
		long i = (dt1.getYear() - dt2.getYear());
//		logger.info("����:"+i);
		String age = String.valueOf(i);
		return age;
	}
	/*
	 * ������:�����û��������ǰ̨���
	 */
	public static int genId (int id) {
		String temp = null;
		int ok_id = 0;
		temp = "1000" + String.valueOf(id);
		ok_id = Integer.parseInt(temp); 
		return ok_id;
	}


}
