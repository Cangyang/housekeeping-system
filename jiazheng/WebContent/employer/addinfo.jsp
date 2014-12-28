<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&user.equals("employer"))
       {
%>

<div class="userOption"><ul>
  <li>
    <a href="employer/addinfo.jsp" style="background:#AA7F00;">发布信息</a>
  </li>
  <li>
    <a href="employer/mybook.jsp">我的预约</a>
  </li>
  <li>
    <a href="employer/mycontract.jsp">服务协议</a>
  </li>
  <li>
    <a href="employer/mylikes.jsp">服务评价</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>

<div class="userInfo">
<s:form name="addInfo" method="post" theme="simple" align="center" action="gzAddInfo" namespace="/">
<table cellpadding = "0" cellspacing = "10" border = "0" class="addInfo">
<thead><tr><th>发布需求信息</th></tr></thead>
<tr><td><span>服务:</span><s:select name="gzInfo.worktype" headerValue="做饭保洁" headerKey="1" list="#{2:'育儿嫂',3:'育婴师',4:'月嫂母婴',5:'教教幼教',6:'管家司机'}" />&nbsp;&nbsp;&nbsp;&nbsp;<span>工时:</span><s:select name="gzInfo.worktime" headerValue="钟点计时" headerKey="1" list="#{2:'半天',3:'全天' }"/>&nbsp;&nbsp;&nbsp;&nbsp;<span>食宿:</span><s:select name="gzInfo.accomm" headerValue="包吃/住家" headerKey="1" list="#{2:'不包吃/住家',3:'包吃/不住家',4:'不包吃/不住家' }"></s:select></td></tr>
<tr><td><span>标题:</span><s:textfield name="gzInfo.headLine"  size="40"/></td></tr>
<tr><td><span>内容:</span><s:textarea name="gzInfo.content" style=" width:440px; height:160px;" label="内容"/></td></tr>
<tr><td><span>联系方式:</span><s:textfield name="gzInfo.phone"  size="40"/></td></tr>
<tr><td><span>提供薪资:</span><s:textfield name="gzInfo.pay"  size="40"/></td></tr>
<tr><td><input type="hidden" name="gzInfo.postTime" value='<%=upTime %>'></td></tr>
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