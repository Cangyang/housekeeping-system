<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null||user.equals("employee"))
       {
%>

<div class="userOption"><ul>
  <li>
    <a href="employee/psinfo.jsp" >个人信息</a>
  </li>
  <li>
    <a href="employee/addinfo.jsp">发布信息</a>
  </li>
  <li>
    <a href="employee/mybook.jsp">预约管理</a>
  </li>
  <li>
    <a href="employee/mycontract.jsp">服务协议管理</a>
  </li>
  <li>
    <a href="employee/mylikes.jsp" style="background:#AA7F00;">查看服务评价</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>
<div class="userInfo">
</div>

<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>
<%} %>
<%@include file="/bottom.jsp" %>