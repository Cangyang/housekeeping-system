<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ytf.core.ConnDB" %>
<%@ page import="java.sql.*" %>
<% 
       String user = null;
       user = (String)session.getAttribute("userType");
       
       if(user!=null&&user.equals("admin"))
       {
%>
<style>
<!--
table td {
    text-align: center;
}
-->
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#confirm").click(function() {
		function hehe(){
			if($("input[name=adminAccount]").val()=="") {
				alert("请输入管理员账号！");
				$("input[name=adminAccount]").focus();
				return false;
			}
			if($("input[name=adminPass]").val()=="") {
				alert("请输入管理员密码！");
				$("input[name=adminPass]").focus();
				return false;
			}
			return true;
		};
		if(hehe()) {
			$.ajax({
				url:"addAdmin",
				type:"post",
				data:{
				    adminAccount:$("input[name=adminAccount]").val(),
				    adminPass:$("input[name=adminPass]").val()
				},
				success:function(data) {
					if(data==1) {
						alert("成功添加！");
						window.location.reload();
					} else {
						alert("添加失败！");
					}
				}
			});
		}
		
	});
});
</script>

<h2 style="text-align: center;">添加管理员</h2>
<table>
<tr><th>管理员账号</th><td><input type="text" name="adminAccount"/></td></tr>
<tr><th>管理员密码</th><td><input type="text" name="adminPass"/></td></tr>
<tr><td colspan="2"><input type="button" id="confirm" value="添加"/></td></tr>
</table>
<%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>