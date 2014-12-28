<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&user.equals("employee"))
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
			url:"bmSignAgree",
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
					$("#gzSignature").text(data[0].gzSignature);
					$("#gzSigDate").text(data[0].gzSigDate);
					if(data[0].bmSignature==""||data[0].bmSignature==null) {
						$("#message").text("请您输入您的姓名后点击确认");
// 						alert($("#signTime").val());
					} else {
						$("#bmSignature").text(data[0].bmSignature);
						$("#bmSigDate").text(data[0].bmSigDate);
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
 				url:"bmSignAgree",
 				type:"post",
 				data:{
 					bmSig:$("input[name=mySignature]").val(),
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
    <a href="employee/psinfo.jsp" >个人信息</a>
  </li>
  <li>
    <a href="employee/addinfo.jsp">发布信息</a>
  </li>
  <li>
    <a href="employee/mybook.jsp">预约管理</a>
  </li>
  <li>
    <a href="employee/mycontract.jsp" style="background:#AA7F00;">服务协议</a>
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