<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ytf.core.ConnDB" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.*" %>
<% 
       String user = null;
       user = (String)session.getAttribute("userType");
       
       if(user!=null&&user.equals("admin"))
       {
%>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>
<style>
<!--
.confirmCont td {
    text-align: center;
}
-->
</style>
<script type="text/javascript">
<!--
$(document).ready(function() {
	$("a.yes").click(function() {
		if(confirm("确认审核通过？")) {
			$.ajax({
				url:"confirmCont",
				type:"post",
				data:{
					contID:$(this).attr("id"),
			        confirmDate:$("input[name=sigDate]").val()
				},
				success:function(data) {
// 					alert(data);
					if(data==1) {
						alert("成功签署家政服务协议！");
						window.location.reload();
					}else {
						alert("执行操作失败");
					}
				}
			});
		}
	});
});
//-->
</script>
<h2 style="text-align: center;">审核合同</h2>
<table class="confirmCont">
  <tr>
    <th>合同编号</th>
    <th>雇主姓名</th>
    <th>家政员姓名</th>
    <th>雇主签订日期</th>
    <th>家政员签订日期</th>
    <th>操作</th>
  </tr>
  <%
    ConnDB db = new ConnDB();
    String sql = "select id, bmName, gzName, gzSigDate, bmSigDate from tb_contract where sigDate is NULL";
	ResultSet rs = db.executeQuery(sql);
    try {
  	  if(rs==null) {
 %>
   <tr>
     <td colspan="6"><span style="color: red; font-style: italic;">您暂无可签署的家政服务协议！</span></td>
   </tr>
 <%  		  
  	  } else {
  		
  		while(rs.next()) {
    		  int contID = rs.getInt("id");
    		  String gzName = rs.getString("gzName");
    		  String bmName = rs.getString("bmName");
    		  String gzSigDate = rs.getString("gzSigDate");
    		  String bmSigDate = rs.getString("bmSigDate");
  %>
  <tr>
    <td>HT<%=contID %></td>
    <td><%=gzName %></td>
    <td><%=bmName %></td>
    <td><%=(gzSigDate==null ? "暂无数据" : gzSigDate) %></td>
    <td><%=(bmSigDate==null ? "暂无数据" : bmSigDate) %></td>
    <td><a href="#" id="<%=contID %>" class="yes">确认</a></td>
  </tr>
  <%
  	  }
    } 
    } catch (SQLException e) {
    	System.out.println(e.getMessage());
    	e.printStackTrace();
    }
    db.close();
  %>
</table>
<input type="hidden" name="sigDate" value='<%=upTime %>'>
<%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>