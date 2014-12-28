<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>
<table class="MenuWrap">
  <tr><td>
  <div class="nav_menu" >
  <ul class="lavaLamp" id="lavaLamp">
  <li><a href="index.jsp" >首页</a></li>
  <li><a href="employee.jsp" >家政员</a></li>
  <li style="background-image: url(images/bgtab.gif)"><a href="info.jsp" >供求信息</a></li>
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
<div class="infoList"></div>
<script type="text/javascript">
$(document).ready(function(){
	//异步加载供求信息
	$(function() {
		$.ajax({
			url:"showGQInfo.action",
			type:"post",
			data:{},
			dataType:"json",
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
			success:function(data) {
				for(var i in data)
// 				alert(data[i].headLine);
				if(data[i].type==1) {
					var c1 = "<li><a target='_blank' href='showinfo.jsp?type=1&infoID="+data[i].userId+"' onclick=window.open(this.href,'hehe','width=640,height=460,top=0,left=0');>"+data[i].headLine+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].postTime+"</a></li>";
					$("#supplyInfo").append(c1);
				} else {
					var c2 = "<li><a target='_blank' href='showinfo.jsp?type=2&infoID="+data[i].userId+"' onclick=window.open(this.href,'hehe','width=640,height=460,top=0,left=0');>"+data[i].headLine+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].postTime+"</a></li>";
					$("#demandInfo").append(c2);
				}
			}
		});
	});
});
</script>
<div class="gqInfo">
<div class="supplyInfo">
<h3>供应信息</h3>
<ul id="supplyInfo"></ul>
</div>
<div class="demandInfo">
<h3>需求信息</h3>
<ul id="demandInfo"></ul>
</div>
</div>


<%@include file="bottom.jsp" %>