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
    text-align: left;
}
-->
</style>
<script type="text/javascript">
<!--
$(document).ready(function() {
	$("a.review").click(function() {
		if(confirm("确认通过？")) {
			$.ajax({
				url:"reviewGYInfo",
				type:"post",
				data:{
					gyInfoID:$(this).attr("id")
				},
				success:function(data) {
					if(data==1) {
						alert("成功审核！");
						window.location.reload();
					} else {
						alert("审核失败！");
					}
				}
			});
		}
		
	});
});
//-->
</script>
<h2 style="text-align: center">审核供应信息</h2>
<table>
  <tr>
    <th>信息编号</th>
    <th>信息标题</th>
    <th>发布时间</th>
    <th>操作</th>   
  </tr>
  <%
    ConnDB db = new ConnDB();
    String sql = "select user_id, headline, posttime from tb_info where type ='1' and isLock = '0'";
    ResultSet rs = db.executeQuery(sql);
    try {
    	while(rs.next()) {
    		int gyInfoID = rs.getInt("user_id");
    		String headline = rs.getString("headline");
    		String posttime = rs.getString("posttime");
  %>
  <tr>
    <td>GY5000<%=gyInfoID %></td>
    <td><a href="/jiazheng/showinfo.jsp?type=1&infoID=<%=gyInfoID %>" target="_blank"><%=(headline==null ? "暂无数据" : headline) %></a></td>
    <td><%=(posttime==null ? "暂无数据" : posttime) %></td>
    <td><a href="#" id="<%=gyInfoID%>" class="review">通过</a></td>
  </tr>
  <%
    	}
    } catch (SQLException e) {
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