<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@include file="/head.jsp" %>

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

<div class="userCenter">
<!-- .userOption li a:hover {background:#AA7F00;} -->
<!-- .userOption li a:link {background:#7F0000;} -->
<div class="userOption"><ul>
  <li>
    <a href="employee/psinfo.jsp" style="background:#AA7F00;">个人信息</a>
  </li>
  <li>
    <a href="employee/addinfo.jsp">发布信息</a>
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
<s:form name="userInfo" method="post" theme="simple" align="center" enctype="multipart/form-data" action="addDetail" namespace="/">
<table cellpadding= "0" cellspacing= "0" border="1" class="mainInfo" >
<thead><tr><td colspan="2">更新个人信息</td></tr></thead>
<tr><th>姓名:</th><td><s:textfield name="userInfo.surname" />&nbsp;*&nbsp;请填写您的真实姓名</td></tr>
<tr><th>籍贯:</th><td><s:textfield name="userInfo.hometown" />&nbsp;*&nbsp;格式:山东济南</td></tr>
<tr><th>性别:</th><td><s:radio name="userInfo.sex" list="%{#{'1':'男','0':'女'}}" theme="simple" value="0"/></td></tr>
<tr><th>出生年月:</th><td><sx:datetimepicker name="userInfo.birthDate"  displayFormat="yyyy-MM-dd" language="UTF-8" value="%{'2000-01-01'}" />&nbsp;*&nbsp;格式：2000-01-01</td></tr>
<tr><th>联系电话:</th><td><s:textfield name="userInfo.phone" /></td></tr>
<tr><th>婚姻状况:</th><td><s:select name="userInfo.marriage" headerValue="----" headerKey="-1" list="#{1:'未婚',2:'已婚',3:'离异'}"/></td></tr>
<tr><th>文化程度:</th><td><s:select  name="userInfo.education"  headerKey="-1" headerValue="----" list="#{1:'小学',2:'初中',3:'高中',4:'中专',5:'大专',6:'大学',7:'硕士' }"/></td></tr>
<tr><th>居住地址:</th><td><s:textfield name="userInfo.address" size="40" /></td></tr>
<tr><th>证书:</th><td>
<input type="checkbox" name="userInfo.cert" value="0" />健康证
<input type="checkbox" name="userInfo.cert" value="1" />月嫂证
<input type="checkbox" name="userInfo.cert" value="2" />护士证
<input type="checkbox" name="userInfo.cert" value="3" />营养师证<br>
<input type="checkbox" name="userInfo.cert" value="4" />早教证
<input type="checkbox" name="userInfo.cert" value="5" />驾驶证
<input type="checkbox" name="userInfo.cert" value="6" />厨师证
</td></tr> 
<tr><th>技能:</th><td>
<input type="checkbox" name="userInfo.skills" value="0" />电脑操作
<input type="checkbox" name="userInfo.skills" value="1" />熨烫衣服
<input type="checkbox" name="userInfo.skills" value="2" />手工编织<br>
<input type="checkbox" name="userInfo.skills" value="3" />外语
<input type="checkbox" name="userInfo.skills" value="4" />开车
<input type="checkbox" name="userInfo.skills" value="5" />营养学知识
</td></tr>
<tr><th>语言:</th><td>
<input type="checkbox" name="userInfo.lang" value="0" />本地方言
<input type="checkbox" name="userInfo.lang" value="1" />普通话
<input type="checkbox" name="userInfo.lang" value="2" />四川话
<input type="checkbox" name="userInfo.lang" value="3" />东北话<br>
<input type="checkbox" name="userInfo.lang" value="4" />广东话
<input type="checkbox" name="userInfo.lang" value="5" />客家话
<input type="checkbox" name="userInfo.lang" value="6" />闽南语
</td></tr>
<tr><th>口味:</th><td>
<input type="checkbox" name="userInfo.flavor" value="0" />本地菜
<input type="checkbox" name="userInfo.flavor" value="1" />西餐
<input type="checkbox" name="userInfo.flavor" value="2" />川菜
<input type="checkbox" name="userInfo.flavor" value="3" />粤菜
<input type="checkbox" name="userInfo.flavor" value="4" />鲁菜<br>
<input type="checkbox" name="userInfo.flavor" value="5" />苏菜
<input type="checkbox" name="userInfo.flavor" value="6" />浙菜
<input type="checkbox" name="userInfo.flavor" value="7" />湘菜
<input type="checkbox" name="userInfo.flavor" value="8" />闽菜
<input type="checkbox" name="userInfo.flavor" value="9" />徽菜
</td></tr>
<tr><th>头像:</th><td><input type="file" name="userInfo.avatar">&nbsp;*&nbsp;请上传个人真实头像</td></tr>
<tr><th>个人简介:</th><td><s:textarea name="userInfo.profile"/>&nbsp;*&nbsp;不超过500字</td></tr>
<tr><td><input type="hidden" name="userInfo.uptime" value='<%=upTime %>'></td></tr>
</table>
<tr>
   <td><s:reset value="重置" />
   <s:submit value="更新"/></td>
</tr>
<tr><td><s:actionmessage cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
<s:fielderror cssStyle="font-size: 20px; color:red;"/>
</s:form>
</div>

</div>


<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>
<%} %>
<%@include file="/bottom.jsp" %>