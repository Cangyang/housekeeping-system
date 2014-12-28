<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.ytf.core.ConnDB" %>
<%@include file="head.jsp" %>
<table class="MenuWrap">
  <tr><td>
  <div class="nav_menu" >
  <ul class="lavaLamp" id="lavaLamp">
  <li style="background-image: url(images/bgtab.gif)"><a href="index.jsp" >首页</a></li>
  <li><a href="employee.jsp" >家政员</a></li>
  <li><a href="info.jsp" >供求信息</a></li>
  <li><a href="company.jsp" >家政公司</a></li>
  <li><a href="usercenter.jsp" >用户中心</a></li>
  </ul>
 </div></td></tr>
 <tr><td>
<!--  可放图片banner -->
<!--    <div class="nav_Ad"><img /></div> -->
 </td></tr>
</table>
</div>
<script type="text/javascript">
<!--
$(document).ready(function() {
	//异步加载服务器端新闻信息
	$(function() {
		$.ajax({
			url:"showNews",
			type:"post",
			data:{},
			dataType:"json",
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
			success:function(data) {
				if(data==""||data==null) {
					$("#newsList").append("<li>对不起,暂无新闻信息!</li>")
				} else {
					for (var i in data) {
						var content= "<li><a target='_blank' href='shownews.jsp?newsID="+data[i].id+"'>"+data[i].title+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].issuedate+"</a></li>";
						$("#newsList").append(content);
					}
				}
			}
		});
	});
	   
});
//-->
</script>


<div class="content" >  
<div id="flipbook">
	<div class="slide">
		<img src="images/1.jpg" alt="" /> 
		<div class="content"><a href="shownews.jsp?newsID=1">家政行业乱象频生亟待规范</a></div> 
	</div>
	<div class="slide">
		<img src="images/2.jpg" alt="" /> 
		<div class="content"><a href="shownews.jsp?newsID=5">云家政：家政行业的“洋气”玩法</a></div> 
	</div>
	<div class="slide">
		<img src="images/3.jpg" alt="" /> 
		<div class="content"><a href="shownews.jsp?newsID=3">高三女难伺候月换仨保姆</a></div> 
	</div>
	<div class="slide">
		<img src="images/4.jpg" alt="" /> 
		<div class="content"><a href="shownews.jsp?newsID=6">日本一2岁儿童死亡 或因遭保姆遗弃</a></div>
	</div>
</div>
<table class="innerContent"><tr>
    <td><div class="news">
    <p>行业新闻</p><br>
    <ul id="newsList"></ul>
    </div>
    </td>
    <td><div class="board">
      <p>网站公告</p><br>
	  <marquee onMouseOver=this.stop()  onMouseOut=this.start() scrollamout=2 scrolldelay=90 direction=up>
      <span style="font-size: 14px"><br>
	  请各位用户在使用本网站时注意以下事项：<br>
	  <br>
	 （一）本网站会员分为雇主、家政员和企业用户三种类型；<br>
	 （二）普通用户需在注册成为会员后才可登录进入用户中心；<br>
	 （三）为保证公平和效率，每位雇主用户只能预约一位家政阿姨，只有家政阿姨未同意预约后，雇主才可预约下一位；<br>
	 （四）会员用户在发布供求信息时，应本着实事求是的态度，语言尽量简洁方便其他人浏览；<br>
	 （五）会员用户发布完一条供求信息后，再发布另一条将会覆盖掉之前发布的那一条信息，请注意；<br>
	 （六）本网站版权归家家乐家政所有；<br>
	  </span>
	  </marquee>
	</div></td> 
</tr></table>

</div>

<%@include file="bottom.jsp" %>