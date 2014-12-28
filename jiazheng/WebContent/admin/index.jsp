<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="JS/jquery-1.2.1.min.js"></script>
<script type="text/javascript" src="/jiazheng/ckeditor/ckeditor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>家家乐家政后台管理</title>
<meta name="keywords" content=""/>
<% 
       String user = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&!user.equals("")) {
    	   
%>
<style type="text/css">
.adminTop {
  height: 64px;
  width: 100%;
  background-image: url(images/top-right.gif);
  background-repeat: repeat-x;
}
.topLeft {
  float: left;
}
.topRight {
  float: right;
  padding-right: 40px;
  height: 40px;
  line-height: 40px;
}
.topRight a {
  color: #fff;
  line-height: 25px;
  text-decoration: none;
}
#menu {
  float:left;
}
ul#menu, ul#menu ul {
  list-style-type:none;
  margin: 5;
  padding: 0;
  width: 15em;
}

ul#menu a {
  display: block;
  text-decoration: none;	
}

ul#menu li {
  margin-top: 1px;
}

ul#menu li a {
  background: #333;
  color: #fff;	
  padding: 0.5em;
}

ul#menu li a:hover {
  background: #000;
}

ul#menu li ul li a {
  background: #ccc;
  color: #000;
  padding-left: 20px;
}

ul#menu li ul li a:hover {
  background: #aaa;
  border-left: 5px #000 solid;
  padding-left: 15px;
}
#content {
  float: left;
  padding: 10px;
  margin: 5px 40px;
  border: 1px solid silver;
  width: 900px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	 $("#menu ul a").click(function(){
	        $("#content").load($(this).attr("id"));
	 });
});
</script>
<script src="JS/jquery-1.2.1.min.js" type="text/javascript"></script>
<script src="JS/menu.js" type="text/javascript"></script>

</head>
<body>
<div class="adminTop">
  <div class="topRight">
    <a href="/jiazheng/index.jsp" target="_blank">网站首页</a>
    <a href="/jiazheng/Logout">退出管理</a>
  </div>
  <div class="topLeft">
    <img src="images/adminlogo.png"></img>
  </div>
</div>
<ul id="menu">
  <li>
    <a href="#">新闻管理</a>
    <ul>
      <li><a href="#" id="addNews.jsp">添加新闻</a></li>
      <li><a href="#" id="adminNews.jsp">删除新闻</a></li>
    </ul>
  </li>
  <li>
    <a href="#" >合同管理</a>
    <ul>
      <li><a href="#" id="viewCont.jsp">查看合同</a></li>
      <li><a href="#" id="confirmCont.jsp">审核合同</a></li>
    </ul>
  </li>
  <li>
    <a href="#">家政员管理</a>
    <ul>
      <li><a href="#" id="rateBm.jsp">评价考核</a></li>
      <li><a href="#" id="deleBm.jsp">删除用户</a></li>
      <li><a href="#" id="reviewGYInfo.jsp">审核信息</a></li>
    </ul>
  </li>
  <li>
    <a href="#">客户管理</a>
    <ul>
      <li><a href="#" id="deleGz.jsp">删除用户</a></li>
      <li><a href="#" id="reviewXQInfo.jsp">审核信息</a></li>
    </ul>
  </li>
  <li>
    <a href="#">企业用户管理</a>
    <ul>
      <li><a href="#" id="deleQy.jsp">删除用户</a></li>
    </ul>
  </li>
  <li>
    <a href="#">管理员管理</a>
    <ul>
      <li><a href="#" id="addAdmin.jsp">添加管理员</a></li>
      <li><a href="#" id="deleAdmin.jsp">删除管理员</a></li>
    </ul>
  </li>
</ul>
<div id="content"></div>
<%
       }else {
%>
<script type="text/javascript">
$(document).ready(function() {
	$(function () {
		alert("本站后台禁止非法登录！");
		location.href = "login.jsp"
	});
});
</script>
<!-- <div class="error"> -->
<!-- <p>本站禁止非法登陆后台！</p> -->
<!-- </div> -->
<input type="hidden" value="<%=user%>" />
</body>
</html>


<%    }%>