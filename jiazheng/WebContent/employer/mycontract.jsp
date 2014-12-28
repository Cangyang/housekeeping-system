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
$(document).ready(function() {
	//异步加载合同样本
 	$(function() {
		$("#contract").load("contract.jsp");
 	});
	//异步加载合同信息
	$(function() {
		$.ajax({
			url:"gzSignAgree",
			type:"post",
			data:{},
			dataType:"json",
			success:function (data){
				if(data==""||data==null) {
					$("#signInput").hide();
					$("#message").text("您暂时还木有可签订的服务协议");
				} else {
					$("#gzName").text(data[0].gzName);
					$("#bmName").text(data[0].bmName);
					$("#servType").text(data[0].servType);
					$("#isAccomm").text(data[0].isAccomm);
					$("#workTime").text(data[0].servDailyTime);
					$("#beginDate").text(data[0].beginDate);
					$("#endDate").text(data[0].endDate);
					$("#gzPay").text(data[0].gzPay);
					$("#bmSignature").text(data[0].bmSignature);
					$("#bmSigDate").text(data[0].bmSigDate);
					if(data[0].gzSignature==""||data[0].gzSignature==null) {
						$("#message").text("请您输入您的姓名后点击确认");
// 						alert($("#signTime").val());
					} else {
						$("#gzSignature").text(data[0].gzSignature);
						$("#gzSigDate").text(data[0].gzSigDate);
						$("#signInput").hide();
						$("#message").text("您已成功签署家政服务协议");
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
 	//提交签署信息
 	$(function() {
 		$("#signConfirm").click(function() {
 			$.ajax({
 				url:"gzSignAgree.action",
 				type:"post",
 				data:{
 					gzSig:$("input[name=mySignature]").val(),
 					sigDate:$("#signTime").val()
 				},
 				success:function(){
 					$("#signInput").hide();
 					$("#message").text("您已成功签署家政服务协议");
 				}
 			});
 		});
 	});
});
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
    <a href="employer/mycontract.jsp" style="background:#AA7F00;">服务协议</a>
  </li>
  <li>
    <a href="employer/mylikes.jsp">服务评价</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>
<div class="userInfo">
<div id="contract"></div>
<div id="signInput"><input type="text" name="mySignature"/><input type="button" id="signConfirm" value="确认"/></div>
<p id="message"></p>
<input type="hidden" id="signTime" value='<%=upTime %>'>
</div>

<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>
<%} %>
<%@include file="/bottom.jsp" %>