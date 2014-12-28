<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="head.jsp" %>
<table class="MenuWrap">
  <tr><td>
  <div class="nav_menu" >
  <ul class="lavaLamp" id="lavaLamp">
  <li><a href="index.jsp" >首页</a></li>
  <li><a href="employee.jsp" >家政员</a></li>
  <li><a href="info.jsp" >供求信息</a></li>
  <li><a href="company.jsp" >家政公司</a></li>
  <li style="background-image: url(images/bgtab.gif)"><a href="usercenter.jsp" >用户中心</a></li>
  </ul>
 </div></td></tr>
 <tr><td>
<!--  可放图片banner -->
<!--    <div class="nav_Ad"><img /></div> -->
 </td></tr>
  </table>
  </div>
<% 
       String userType = null;
       userType = (String)session.getAttribute("userType");
       if(userType!=null&&!userType.equals("admin"))
       {
%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	$(function() {
		$.ajax({
			url:"newTips",
			type:"post",
			data:{
				
			},
			dataType:"json",
			success:function(data) {
				if(data==null||data=="") {
					$("#messList").append("<li>您暂时没有消息！</li>");
				} else {
					for(var i in data){
						$("#messList").append("<li>"+i+"、"+data[i].messageForm+"</li>");
					}
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				
			}
		});
	});
});
//-->
</script>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>


<div class="userCenter">
<%
      if(userType.equals("employee")) {
%>

<div class="userOption"><ul>
  <li>
    <a href="employee/psinfo.jsp">个人信息</a>
  </li>
  <li>
    <a href="employee/addinfo.jsp">发布信息</a>
  </li>
  <li>
    <a href="employee/mybook.jsp">预约管理</a>
  </li>
  <li>
    <a href="employee/mycontract.jsp">服务协议</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>
<%
      }else if(userType.equals("employer")){
%>

<div class="userOption"><ul>
  <li>
    <a href="employer/addinfo.jsp">发布信息</a>
  </li>
  <li>
    <a href="employer/mybook.jsp">我的预约</a>
  </li>
  <li>
    <a href="employer/mycontract.jsp">服务协议</a>
  </li>
  <li>
    <a href="employer/mylikes.jsp">服务评价</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>
<%
      } else {
%>

<div class="userOption"><ul>
  <li>
    <a href="company/mybusicard.jsp">家政名片</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>

<%} %>

<div class="userInfo">
<BUNNYHERO PET START />
<div style="width: 250px; padding: 0; margin: 0; text-align: center; float: left">
<embed src="http://petswf.bunnyherolabs.com/adopt/swf/guineapig" width="250" height="300" quality="high" bgcolor="ffffff" pluginspage="http://www.macromedia.com/go/getflashplayer" flashvars="cn=kimmy&an=jiajiale&clr=0xcc9933" type="application/x-shockwave-flash">
</embed><br />
<small><a href="http://bunnyherolabs.com/adopt/">adopt your own virtual pet!</a></small>
</div>
<BUNNYHERO PET END />
<div id="myMessage">
<p>我的消息</p>
<ul id="messList"></ul>
</div>
</div>
<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限！</p>
<div>还没有账号？<a href="Register">马上注册</a><br>
 已经有账号了？<a href="Login">现在登录</a></div>
</div>

</div>
<%} %>
<%@include file="bottom.jsp" %>