<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ytf.core.ConnDB" %>
<%@ page import="java.sql.*" %>
<% 
       String user = null;
       user = (String)session.getAttribute("userType");
       
       if(user!=null&&user.equals("admin"))
       {
%>
<style>
<!--
table td {
    text-align: center;
}
-->
</style>
<h2 style="text-align: center">查看合同</h2>
<table>
  <tr>
    <th>合同编号</th>
    <th>雇主姓名</th>
    <th>家政员姓名</th>
    <th>服务项目</th>
    <th>服务时间</th>
    <th>食宿条件</th>
    <th>薪资条件</th>
    <th>开始日期</th>
    <th>结束日期</th>
    <th>是否到期</th>
    <th>操作</th>   
  </tr>
  <%
  ConnDB db = new ConnDB();
  String sql = "select id, gzName, bmName, servType, servDailyTime, isAccomm, gzPay, beginDate, endDate, isOutdated from tb_contract";
  ResultSet rs = db.executeQuery(sql);
  try {
      if(rs==null) {
  %>
  <tr><td colspan="11"><span style="color: red; font-style: italic;">暂无可查看的家政服务协议！</span></td></tr>
  <%   
      } else {
	      while(rs.next()) {
		      int contID = rs.getInt("id");
		      String gzName = rs.getString("gzName");
		      String bmName = rs.getString("bmName");
		      String servType = rs.getString("servType");
		      String servDailyTime = rs.getString("servDailyTime");
		      String isAccomm = rs.getString("isAccomm");
		      String gzPay = rs.getString("gzPay");
		      String beginDate = rs.getString("beginDate");
		      String endDate = rs.getString("endDate");
		      String isOutdated = rs.getString("isOutdated");
		      String isOutDated = isOutdated.equals("1") ? "已到期" : "未到期";
	    
  %>
  <tr>
    <td>HT<%=contID %></td>
    <td><%=gzName %></td>
    <td><%=bmName %></td>
    <td><%=servType %></td>
    <td><%=servDailyTime %></td>
    <td><%=isAccomm %></td>
    <td><%=gzPay %>元/月</td>
    <td><%=beginDate %></td>
    <td><%=endDate %></td>
    <td><%=isOutDated %></td>
    <td><a target="_blank" href="showCont.jsp?contID=<%=contID%>">查看</a></td>
  </tr>
  <%
	      }
      }    
  } catch(SQLException e) {
	  e.printStackTrace();
  }
  db.close();
  %>
</table>
<%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>
