<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>
<table class="MenuWrap">
  <tr><td>
  <div class="nav_menu" >
  <ul class="lavaLamp" id="lavaLamp">
  <li><a href="index.jsp" >首页</a></li>
  <li><a href="employee.jsp" >家政员</a></li>
  <li><a href="info.jsp" >供求信息</a></li>
  <li style="background-image: url(images/bgtab.gif)"><a href="company.jsp" >家政公司</a></li>
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
$(document).ready(function() {
	//加载家政名片
	$(function() {
		$.ajax({
			url:"showCompanyCard",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data) {
				for (var i in data) {
					var card = "<div class='card'><div class='title'>"+data[i].name+"</div><span class='desc'>"+data[i].desc+"</span><br>联系人:"+data[i].linkman+"<br>服务热线:"+data[i].contacts+"</div>";
					$(".cardWrap").append(card);
//                     alert("hehe");
//                        $(".cardWrap").html(data[0].name);
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
</script>
<div class="cardWrap">
<h3>您申请注册为企业用户后，可在此发布免费家政名片</h3>
</div>


<%@include file="bottom.jsp" %>