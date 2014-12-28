<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&user.equals("employer"))
       {
%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	//显示评价相关信息
	$(function() {
		$.ajax({
			url:"gzIsAssess",
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
					$(".assess").hide();
					$("#assessInfo").html("您暂时没有可进行的服务评价！");
					
				} else {
					if(data[0].svDate==""||data[0].svDate==null) {
						$("#bmID").text("BM1000"+data[0].bmID);
						$("#bmName").text(data[0].bmName);
						$("#servType").text(data[0].servType);
// 						$("form").submit(function() {
// 							if($("input[name=assess.svAttitude]").val()=="") {
// 								alert("亲，不要忘了选择服务态度！");
// 								$("input[name=assess.svAttitude]").focus();
// 								return false;
// 							}
// 						});
						$("#confirm").click(function() {
							$.ajax({
								url:"gzAssess",
								type:"post",
								data:{
									bmID:$("#bmID").text(),
									svAttitude:$("input[name='assess.svAttitude']:checked").val(),
									svQuality:$("input[name='assess.svQuality']:checked").val(),
									svPrice:$("input[name='assess.svPrice']:checked").val(),
									svRecommend:$("input[name='assess.svRecommend']:checked").val(),
									svContent:$("textarea[name='assess.svContent']").val(),
									svDate:$("input[name='assess.svDate']").val(),
								},
								success:function(){
									$("#confirm").hide();
									$("#assessInfo").html("您已成功提价服务评价");
								}
							});
						});
					} else {
						$("#confirm").hide();
						$("#assessInfo").html("您已成功完成服务评价");
						$("#bmID").text("BM1000"+data[0].bmID);
						$("#bmName").text(data[0].bmName);
						$("#servType").text(data[0].servType);
						$("input[name='assess.svAttitude'][value='"+data[0].svAttitude+"']").attr("checked",true);
						$("input[name='assess.svQuality'][value='"+data[0].svQuality+"']").attr("checked",true);
						$("input[name='assess.svPrice'][value='"+data[0].svPrice+"']").attr("checked",true);
						$("input[name='assess.svRecommend'][value='"+data[0].svRecommend+"']").attr("checked",true);
						$("textarea[name='assess.svContent']").text(data[0].svContent);
						$("input[type='radio']").attr("disabled","disabled");
						$("textarea[name='assess.svContent']").attr("disabled","disabled");
					}
				}
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
    <a href="employer/mylikes.jsp" style="background:#AA7F00;">服务评价</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>
<div class="userInfo">
<table class="assess">
<thead><tr><td colspan="10">服务评价</td></tr></thead>
<tr><td>阿姨编号:&nbsp;<span id="bmID"></span>&nbsp;&nbsp;&nbsp;&nbsp;阿姨姓名:&nbsp;<span id="bmName"></span>&nbsp;&nbsp;&nbsp;&nbsp;服务项目:&nbsp;<span id="servType"></span></td></tr>
<tr><td><span>服务态度</span><input type="radio" name="assess.svAttitude" value="1">差评<input type="radio" name="assess.svAttitude" value="2">中评<input type="radio" name="assess.svAttitude" value="3">好评</td></tr>
<tr><td><span>服务质量</span><input type="radio" name="assess.svQuality" value="1">差评<input type="radio" name="assess.svQuality" value="2">中评<input type="radio" name="assess.svQuality" value="3">好评</td></tr>
<tr><td><span>服务价格</span><input type="radio" name="assess.svPrice" value="1">偏高<input type="radio" name="assess.svPrice" value="2">一般<input type="radio" name="assess.svPrice" value="3">满意</td></tr>
<tr><td><span>推荐指数</span><input type="radio" name="assess.svRecommend" value="1">不推荐<input type="radio" name="assess.svRecommend" value="2">不确定<input type="radio" name="assess.svRecommend" value="3">推荐</td></tr>
<tr><td><span>评价内容</span><textarea name="assess.svContent" style="width: 360px;height: 120px;"></textarea></td></tr>
<tr><td><input type="hidden" name="assess.svDate" value='<%=upTime %>'></td></tr>
<tr><td><input type="button" id="confirm" value="提交" /></td></tr>
</table>
<div id="assessInfo"></div>
</div>

<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>
<%} %>
<%@include file="/bottom.jsp" %>