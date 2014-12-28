<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<sx:head extraLocales="UTF-8" />
<base href="<%=basePath%>" />
<link rel="shortcut icon" type="images/x-icon" href="/jiazheng/images/favicon.ico" />
<title>家家乐家政系统</title>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes" />
<link href="/jiazheng/CSS/style.css" rel="stylesheet" type="text/css" />
<script type=text/javascript src="/jiazheng/JS/jquery.min.js"></script>
<script type=text/javascript src="/jiazheng/JS/lavalamp.min.js"></script>
<script type=text/javascript src="/jiazheng/JS/xixi.js"></script>
<script type="text/javascript">try{Typekit.load();}catch(e){}</script>
<script src="JS/modernizr.js"></script>
<script src="JS/jquery.js"></script>
<script src="JS/jquery.pictureflip.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	$('#flipbook').pageFlip({});

});
</script>
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
	  var time = "今天是" + year + "年" + month + "月" + date + "日" + " " + week + " " +"  系统时间:  " + hour + ":" + minu + ":" + sec;
	  var hehe = document.getElementById('clock');
	  if(hehe) {
		  hehe.innerHTML = time;
	  }
	  window.setTimeout("realTime();",1000);
}

  window.onload = realTime;    
</script>
</head>
<body>
<div class="BodyWrap">
<div class="BodyInner" >
<div class="HeaderWrap">
  <table class=HeaderTxt>
  <tr>
    <td>
    <div style="float: left;">
      <!-- JiaThis Button BEGIN -->
      <div class="jiathis_style">
	  <a class="jiathis_button_qzone"></a>
	  <a class="jiathis_button_tsina"></a>
	  <a class="jiathis_button_tqq"></a>
	  <a class="jiathis_button_weixin"></a>
	  <a class="jiathis_button_renren"></a>
	  <a href="http://www.jiathis.com/share?uid=1904612" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
	  <a class="jiathis_counter_style"></a>
      </div>
      <script type="text/javascript">
      var jiathis_config = {data_track_clickback:'true'};
      </script>
      <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1393995504756822" charset="utf-8"></script>
      <!-- JiaThis Button END -->
     </div></td>
     <td>
     <div style="float: right; line-height: line-height: 25px;">
       <a class="aRed" href="#" target=_blank>加入收藏</a>
       <a class="aRed" href="#" target=_blank>关于我们</a>
       <a class="aRed" href="#" target=_blank>服务协议</a>
     </div></td>
  </tr>
  </table>
  <table class="LogoWrap">
  <tr>
     <td>
       <div class="LogoWrapLeft">
        <a href="index.jsp"><img width="200" height="180" src="/jiazheng/images/jiajiale.gif" alt="家家乐家政" /></a> 
       </div>
     </td>
     <td><table class="LogoWrapRight">
       <tr>
         <td><div class="LogoRightAd">
         <ul><li><img src="/jiazheng/images/banner.gif" border="0" alt="家家乐家政"/></li></ul>
         </div></td>
       </tr>
<% 
       String user = null;
       user = (String)session.getAttribute("username");
       if(user==null||user.equals(""))
       {
%>
       <tr>
         <td><div class="LogoWrapRightTxt">
         <span class="TxtRightBottom"><a class="aBlue"  href="Login">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="aBlue"  href="Register">注册</a></span>
         </div></td>
       </tr>
       
<%     }else { %>
       <tr>
         <td>
         <div class="LogoWrapRightTxt">
         <span class="TxtRightBottom"><%=user %>,您好！&nbsp;&nbsp;<span style="color:#cc0000;" id="clock"></span>&nbsp;&nbsp;<a class="aBlue" href="Logout">注销</a></span>
         </div></td>
       </tr>    
<% } %>             
     </table></td>
  </tr>   
  </table>
   
 