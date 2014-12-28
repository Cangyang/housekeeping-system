<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&user.equals("company"))
       {
%>


<div class="userOption"><ul>
  <li>
    <a href="company/mybusicard.jsp" style="background:#AA7F00;">家政名片</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li>
</ul></div>  
<div class="userInfo">
  <s:form method="post" theme="simple" align="center" name="publishCard" action="publishCard" namespace="/">
    <table class="busCard">
      <thead><tr><td>发布家政名片</td></tr></thead>
      <tr><td><span>公司名称</span>:<s:textfield name="company.name"/></td></tr>
      <tr><td><span>公司主营</span>:<s:textfield name="company.desc"/></td></tr>
      <tr><td><span>公司联系人</span>:<s:textfield name="company.linkman"/></td></tr>
      <tr><td><span>公司联系方式</span>:<s:textfield name="company.contacts"/></td></tr>
      <tr><td><input type="hidden" name="company.upTime" value='<%=upTime %>'></td></tr>
      <tr><td><s:submit value="写好了,发布"/></td></tr>
      <tr><td><s:actionmessage cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
    </table>
  </s:form>
</div>

<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>
<%} %>
<%@include file="/bottom.jsp" %>