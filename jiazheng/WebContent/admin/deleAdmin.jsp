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
	$("a.deleAdmin").click(function() {
		if(confirm("操作执行后数据不可恢复，请谨慎删除！")){
			$.ajax({
				url:"deleAdmin",
				type:"post",
				data:{
					adminID:$(this).attr("id")
				},
				success:function(data) {
					if(data==1) {
						alert("删除成功！");
						window.location.reload();
					} else {
						alert("执行操作失败");
					}
				}
			});
		}
	});
});
</script>
<h2 style="text-align: center">用户管理</h2>
<table>
  <tr>
    <th>用户编号</th>
    <th>用户账号</th>
    <th>用户密码</th>
    <th>操作</th>   
  </tr>
  <%
    ConnDB db =new ConnDB();
    String sql = "select id, name, password from tb_manager where type ='0'";
    ResultSet rs = db.executeQuery(sql);
    try {
    	while(rs.next()) {
    		int adminID = rs.getInt("id");
    		String adminAccount = rs.getString("name");
    		String adminPass = rs.getString("password");
  %>
  <tr>
    <td>GL4000<%=adminID %></td>
    <td><%=adminAccount %></td>
    <td><%=adminPass %></td>
    <td><a href="#" id="<%=adminID%>" class="deleAdmin">删除</a></td>   
  </tr>
  <% 
    	}
    } catch(SQLException e) {
    	e.printStackTrace();
    	e.getMessage();
    }
    db.close();
  %>
  </table>
  <%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>