<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ytf.core.ConnDB" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*"%>
<% 
   String photo_no = request.getParameter("userId"); 
   Connection conn = ConnDB.getConnection();
   try {
	   Statement stmt = conn.createStatement(); 
	   String sql = " SELECT * FROM tb_employee WHERE id = "+ photo_no; 
	   ResultSet rs = stmt.executeQuery(sql); 
	   if (rs.next()) { 
	   Blob b = rs.getBlob("avatar"); 
	   long size = b.length(); 
	   //out.print(size); 
	   byte[] bs = b.getBytes(1, (int)size); 
	   response.setContentType("image/jpeg"); 
	   OutputStream outs = response.getOutputStream(); 
	   outs.write(bs); 
	   outs.flush(); 
	   rs.close(); 
	   out.clear();  
	   out = pageContext.pushBody(); 
	   }
   } catch (Exception e) {
	  e.printStackTrace();   
   }
   

%>