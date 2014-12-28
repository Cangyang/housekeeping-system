
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ytf.core.ConnDB" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*;" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + ":/" +"/"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
        <sx:head />
		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <script>
//功能，显示实时时间
  function realTime(){
	  var now = new Date();
	  var year = now.getFullYear();
	  var month = now.getMonth();
	  var date = now.getDate();
	  var day = now.getDay();
	  var hour = now.getHours();
	  var minu = now.getMinutes();
	  var sec = now.getSeconds();
	  month = month + 1;
	  var arr_week = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
	  var week = arr_week[day];
	  if (hour < 10) hour = "0" + hour;
	  if (minu < 10) minu = "0"+ minu;
	  if (sec < 10) sec = "0" + sec;
	  var time = "今天是" + year + "年" + month + "月" + date + "日" + " " + week + " " + "系统时间: " + hour + ":" + minu + ":" + sec;
	  document.getElementById('clock').innerHTML = time;
	  window.setTimeout("realTime();",1000);
}
window.onload = realTime;
      
</script>
 
	</head>

	<body>
		<div id="clock" style="font-size: 14px; color:#000;"></div>
	    <sx:datetimepicker name="" displayFormat="yyyy-MM-dd"/>
        <%
        
       //String test = request.getSession().getServletContext().getRealPath(WebContent);
        String test = application.getRealPath("/")+ "images/defImg.jpg";
        String imgPath = basePath + "images/defImg.jpg";
        System.out.println(test);
        
        
        %>
      
    <img alt="" src="image.jsp?userId=1">
	</body>
</html>