<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ytf.core.ConnDB" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*;" %>
<% 
       String user = null;
       user = (String)session.getAttribute("userType");
       
       if(user!=null&&user.equals("admin"))
       {
%>
<style>
<!--
#result {
    color: red;
    padding-left: 10px;
}
-->
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('a.dele').click(function() {
		if(confirm("谨慎操作，删除后不可恢复")) {
			$.ajax({
				url:"deleNews",
				type:"post",
				data:{
					newsID:$(this).attr('id')
				},
			    success:function() {
			    	alert("删除成功！");
			    	window.location.reload();
			    }
			});
		}
	});
});
</script>
<h2 style="text-align: center;">新闻管理</h2>
<table>
  <tr>
    <th>新闻标题</th>
    <th>发布日期</th>
    <th>操作</th>
  </tr>
 
<% 
  ConnDB db=new ConnDB();
  String sql = "select * from tb_news order by issuedate asc";
  ResultSet rs = db.executeQuery(sql);
  try {
	  while(rs.next()) {
		  int id = rs.getInt("id");
		  String title = rs.getString("title");
	      String issuedate = rs.getString("issuedate");
  
%>
 <tr>
    <td><a href="/jiazheng/shownews.jsp?newsID=<%=id %>" target="_blank"><%=title %></a></td><td><%=issuedate %></td><td><a id="<%=id%>" class="dele" href="#">删除</a></td>
 </tr>   
<%   
	  }
   } catch (SQLException e) {
	    e.printStackTrace();
   }
   db.close();
%>    
  
</table>
<p id="result"></p>
<%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>