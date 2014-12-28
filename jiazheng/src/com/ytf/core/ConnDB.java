package com.ytf.core;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class ConnDB {
	
	private java.sql.PreparedStatement pstm;
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	private static String propFileName = "/com/connDB.properties";
	private static Properties prop = new Properties();
	private static String dbClassName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/db_jiazheng?user=root&password=ytf4060807&useUnicode=true";
	public ConnDB(){
		try {
			//将Properties文件读取到InputStream对象中
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in);
			dbClassName = prop.getProperty("DB_CLASS_NAME");
			//获取链接的url
			dbUrl = prop.getProperty("DB_URL");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 功能：建立数据库连接并输出异常
	 */
public static Connection getConnection() {
	Connection conn = null;
	try {
		Class.forName(dbClassName).newInstance();
		conn = DriverManager.getConnection(dbUrl);
	}
	catch (Exception ce) {
		ce.printStackTrace();
	}
	if(conn == null) {
		System.err.println(
				"警告:DbConnectionManager.getConnection() 获得数据库连接失败。\r\n\r\n链接类型:"+
		        dbClassName + "\r\n链接位置:" + dbUrl);  //在控制台上输出提示信息
	}
		return conn;
  }
    /*
     * 功能：执行查询语句
     */
public ResultSet executeQuery(String sql) {
	try {
		conn = getConnection();
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery(sql);
	}
	catch (SQLException ex) {
		System.err.println(ex.getMessage());
	}
	return rs;
}

     /*
      * 功能：执行更新操作
      */
public int executeUpdate(String sql) {
	int result = 0;
	try {
		conn = getConnection();
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		result = stmt.executeUpdate(sql);
	} catch (SQLException ex) {
		result = 0;
	}
	return result;
}
    

public void doPstm(String sql,Object[] params){
	if(sql!=null&&!sql.equals("")){
		if(params==null)
			params=new Object[0];
		
		getConnection();
		if(conn!=null){
			try{		
				System.out.println(sql);
				pstm=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1,params[i]);
				}
				pstm.execute();
			}catch(SQLException e){
				System.out.println("doPstm()方法出错！");
				e.printStackTrace();
			}				
		}			
	}
}
public ResultSet getRs() throws SQLException{
	return pstm.getResultSet();		
}
public int getCount() throws SQLException{
	return pstm.getUpdateCount();		
}
/*
 * 功能:关闭数据库的连接
*/
public void close() {
try {
	if(pstm!=null){
		pstm.close();
	}
	if(rs!=null){
		rs.close();
	}
	if(stmt!=null){
		stmt.close();
	}
	if(conn!=null){
		conn.close();
	}
} catch(Exception e) {
	e.printStackTrace(System.err);
}
}


}
