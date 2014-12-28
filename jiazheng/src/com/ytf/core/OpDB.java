package com.ytf.core;
import java.sql.*;
import com.ytf.core.ConnDB;
public class OpDB {

	private ConnDB mydb;
	public OpDB(){
		mydb=new ConnDB();
	}
	
	public boolean LogOn(String table_name,String user, String pwd) {
		try {
			String sql = "select * from "+table_name+ " where name= " + "'"+user+"'" + " and password = "+ "'"+pwd+"'";
			ResultSet rs = mydb.executeQuery(sql);
			boolean flag = (rs==null||!rs.next()?false:true);
			rs.close();
			return flag;
		} catch (SQLException e ){
			System.out.println("µÇÂ¼Ê§°Ü");
			e.printStackTrace();
			return false;
		} finally {
			mydb.close();
		}
		
	}
	
	public boolean CheckUser(String username,String userType) {
		try {
			String sql = "select * from "+userType+ " where name like "+"'"+username+"'";
			ResultSet rs = mydb.executeQuery(sql);
			boolean flag = (rs==null||!rs.next()?false:true);
			rs.close();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			mydb.close();
		}
		
	}
	
	
	
  public void ToRegister(String username,String email,String password,String userType) {
	  try {
		  String sql = "insert into "+userType+" (name,email,password) values ("+"'"+username+"',"+"'"+email+"',"+"'"+password+"')";
		  mydb.executeUpdate(sql);
	  } catch (Exception ex) {
		 ex.printStackTrace();
	  } finally {
		  mydb.close();
		
	  }
  }
  
  public int getId (String username) {
	  
	return 0;
	  
  }
	
	
}
