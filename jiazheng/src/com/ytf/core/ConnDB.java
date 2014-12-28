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
			//��Properties�ļ���ȡ��InputStream������
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in);
			dbClassName = prop.getProperty("DB_CLASS_NAME");
			//��ȡ���ӵ�url
			dbUrl = prop.getProperty("DB_URL");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * ���ܣ��������ݿ����Ӳ�����쳣
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
				"����:DbConnectionManager.getConnection() ������ݿ�����ʧ�ܡ�\r\n\r\n��������:"+
		        dbClassName + "\r\n����λ��:" + dbUrl);  //�ڿ���̨�������ʾ��Ϣ
	}
		return conn;
  }
    /*
     * ���ܣ�ִ�в�ѯ���
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
      * ���ܣ�ִ�и��²���
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
				System.out.println("doPstm()��������");
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
 * ����:�ر����ݿ������
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
