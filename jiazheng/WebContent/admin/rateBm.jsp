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
<!--
$(document).ready(function() {
	$("a.rateBm").click(function() {
// 		alert($("#select"+$(this).attr("id")+" option:selected").val());
		if(confirm("确认操作？")) {
			$.ajax({
				url:"rateBm",
				type:"post",
				data:{
					bmID:$(this).attr("id"),
					bmRank:$("#select"+$(this).attr("id")+" option:selected").val()
				},
			success:function(){
				alert("成功评级");
				window.location.reload();
			}
			});
		}
	});
});
//-->
</script>
<h2 style="text-align: center">家政员考核</h2>
<table>
  <tr>
    <th>家政员编号</th>
    <th>家政员姓名</th>
    <th>查看服务评价</th>
    <th>操作</th>
  </tr>
  <%
  ConnDB db = new ConnDB();
  String sql = "select id, surname from tb_employee";
  ResultSet rs = db.executeQuery(sql);
  try {
      while(rs.next()) {
    	  int bmID = rs.getInt("id");
    	  String bmSurname = rs.getString("surname");
      
  %>
  <tr>
    <td>BM1000<%=bmID %></td>
    <td><%=bmSurname %></td>
    <td><a target="_blank" href="showBmAssess.jsp?bmID=<%=bmID%>">服务评价</a></td>
    <td>给予<select id="select<%=bmID%>" >
      <option value="1">一星
      <option value="2">二星
      <option value="3">三星
      <option value="4">四星
      <option value="5">五星
    </select>评级/<a href="#" id="<%=bmID %>" class="rateBm">确认</a></td>
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