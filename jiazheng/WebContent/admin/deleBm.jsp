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
	$("a.deleBm").click(function() {
		if(confirm("执行操作后，用户所有相关信息都将删除，请谨慎删除！")){
			$.ajax({
				url:"deleBm",
				type:"post",
				data:{
					bmID:$(this).attr("id")
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
    <th>用户邮箱</th>
    <th>操作</th>   
  </tr>
  <%
    ConnDB db =new ConnDB();
    String sql = "select id, name, email from tb_employee";
    ResultSet rs = db.executeQuery(sql);
    try {
    	while(rs.next()) {
    		int bmID = rs.getInt("id");
    		String bmAccount = rs.getString("name");
    		String bmEmail = rs.getString("email");
  %>
  <tr>
    <td>BM1000<%=bmID %></td>
    <td><%=bmAccount %></td>
    <td><%=bmEmail %></td>
    <td><a href="#" id="<%=bmID%>" class="deleBm">删除</a></td>   
  </tr>
  <% 
    	}
    } catch(SQLException e) {
    	e.printStackTrace();
    	e.getMessage();
    } finally {
    	db.close();
    }
    
  %>
  </table>
  <%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>