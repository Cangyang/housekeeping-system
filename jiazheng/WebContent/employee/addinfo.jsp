<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	/*
	 *功能:验证输入信息
	 */
	 $("form").submit(function() {
		 if($("input[name=userInfo.headLine]").val()=="") {
			 alert("亲，请输入标题！");
			 $("input[name=userInfo.headLine]").focus();
			 return false;
		 }
		 if($("textarea[name=userInfo.content]").val()=="") {
			 alert("亲,请输入内容!");
			 $("textarea[name=userInfo.content]").focus();
			 return false;
		 }
		 if($("input[name=userInfo.phone]").val()=="") {
			 alert("亲,请输入联系方式!");
			 $("input[name=userInfo.phone]").focus();
			 return false;
		 }
		 if($("input[name=userInfo.pay]").val()=="") {
			 alert("亲,请输入希望的薪资!");
			 $("input[name=userInfo.pay]").focus();
			 return false;
		 }
	 });
});
</script>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&user.equals("employee"))
       {
%>


<div class="userOption"><ul>
  <li>
    <a href="employee/psinfo.jsp">个人信息</a>
  </li>
  <li>
    <a href="employee/addinfo.jsp" style="background:#AA7F00;">发布信息</a>
  </li>
  <li>
    <a href="employee/mybook.jsp">预约管理</a>
  </li>
  <li>
    <a href="employee/mycontract.jsp">服务协议</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>

<div class="userInfo">
<s:form name="addInfo" method="post" theme="simple" align="center" action="addInfo" namespace="/">
<table cellpadding = "0" cellspacing = "10" border = "0" class="addInfo">
<thead><tr><th>发布供应信息</th></tr></thead>
<tr><td><span>服务:</span><s:select name="userInfo.worktype" headerValue="做饭保洁" headerKey="1" list="#{2:'育儿嫂',3:'育婴师',4:'月嫂母婴',5:'教教幼教',6:'管家司机'}" />&nbsp;&nbsp;&nbsp;&nbsp;<span>工时:</span><s:select name="userInfo.worktime" headerValue="钟点计时" headerKey="1" list="#{2:'半天',3:'全天' }"/>&nbsp;&nbsp;&nbsp;&nbsp;<span>食宿:</span><s:select name="userInfo.accomm" headerValue="包吃/住家" headerKey="1" list="#{2:'不包吃/住家',3:'包吃/不住家',4:'不包吃/不住家' }"></s:select></td></tr>
<tr><td><span>标题:</span><s:textfield name="userInfo.headLine"  size="40" /></td></tr>
<tr><td><span>内容:</span><s:textarea name="userInfo.content" style=" width:440px; height:160px;" label="内容"/></td></tr>
<tr><td><span>联系方式:</span><s:textfield name="userInfo.phone"  size="40"/></td></tr>
<tr><td><span>希望薪资:</span><s:textfield name="userInfo.pay"  size="40"/></td></tr>
<tr><td><input type="hidden" name="userInfo.postTime" value='<%=upTime %>'></td></tr>
<tr><td><s:submit value="写好了,发布"/></td></tr>
<tr><td><s:actionmessage cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
</table>

</s:form>
</div>

<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>

<%} %>
<%@include file="/bottom.jsp" %>