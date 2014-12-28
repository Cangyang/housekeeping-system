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
	$("a.deleQy").click(function() {
		if(confirm("执行操作后，用户所有相关信息都将删除，请谨慎删除！")){
			$.ajax({
				url:"deleQy",
				type:"post",
				data:{
					qyID:$(this).attr("id")
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
    <th>企业编号</th>
    <th>企业账号</th>
    <th>企业邮箱</th>
    <th>企业名称</th>
    <th>企业简介</th>
    <th>企业联系人</th>
    <th>企业联系方式</th>
    <th>操作</th>   
  </tr>
  <%
    ConnDB db =new ConnDB();
    String sql = "select id, name, email, companyName, companyDesc, companyLinkman, companyContacts from tb_company";
    ResultSet rs = db.executeQuery(sql);
    try {
    	while(rs.next()) {
    		int qyID = rs.getInt("id");
    		String qyAccount = rs.getString("name");
    		String qyEmail = rs.getString("email");
    		String qyName = rs.getString("companyName");
    		String qyDesc = rs.getString("companyDesc");
    		String qyLinkman = rs.getString("companyLinkman");
    		String qyContacts = rs.getString("companyContacts");
  %>
  <tr>
    <td>QY3000<%=qyID %></td>
    <td><%=qyAccount %></td>
    <td><%=qyEmail %></td>
    <td><%=qyName %></td>
    <td><%=qyDesc %></td>
    <td><%=qyLinkman %></td>
    <td><%=qyContacts %></td>
    <td><a href="#" id="<%=qyID%>" class="deleQy">删除</a></td>   
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