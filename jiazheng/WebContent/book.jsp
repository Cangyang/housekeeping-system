<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type=text/javascript src="/jiazheng/JS/jquery.min.js"></script>
<script type=text/javascript src="/jiazheng/JS/purl.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>在线预约家政保姆</title>
</head>
<script type="text/javascript">
/*
 * 功能:初始化信息
 */
  $(document).ready(function() {
	  $(function(){
          $("input[name=bookBm.bmName]").attr('value',$.url().param("bmName"));
          $("input[name=bookBm.bmId]").attr('value',$.url().param("bmId"));
//        $("input[name=bookBm.bmName]").attr('disabled','disabled');
//        $("input[name=bookBm.bmId]").attr('disabled','disabled');
//        alert($.url().param('bmName'));
	  });
      /*
       *功能:验证输入信息
       */
       $("form").submit(function(){
    	  if($("input[name=bookBm.gzName]").val()=="") {
    		  alert("我们还不知道该怎么称呼您呢!");
    		  $("input[name=bookBm.gzName]").focus();
    		  return false;
    	  }
    	  if($("input[name=bookBm.gzPhone]").val()=="") {
    		  alert("哎呀，您怎么忘了您的联系方式啊!");
    		  $("input[name=bookBm.gzPhone]").focus();
    		  return false;
    	  }
    	  if($("input[name=bookBm.gzPay]").val()=="") {
    		  alert("做人要大度，请输入您提供给家政阿姨的薪资!");
    		  $("input[name=bookBm.gzPay]").focus();
    		  return false;
    	  }
    	  if($("input[name=bookBm.startDate]").val()=="") {
    		  alert("即蓝预约了阿姨，就赶紧定下一个开工的日子吧!");
    		  $("input[name=bookBm.startDate]").focus();
    		  return false;
    	  }
    	  if($("input[name=bookBm.wkPeriod]").val()=="") {
    		  alert("您打算一辈纸养着阿姨了么?");
    		  $("input[name=bookBm.wkPeriod]").focus();
    		  return false;
    	  }
    	  if($("textarea[name=bookBm.gzbkContent]").val()=="") {
    		  alert("即蓝预约了人家阿姨，您多少也得透露点您家的情况!");
    		  $("textarea[name=bookBm.gzbkContent]").focus();
    		  return false;
    	  }
    	  if($("input[name=bookBm.securityCode]").val()=="") {
    		  alert("啊呀呀，您不会是僵尸程序吧？");
    		  $("input[name=bookBm.securityCode]").focus();
    		  return false;
    	  }
    	  if(isNaN($("input[name=bookBm.wkPeriod]").val())) {
    		  alert("工作周期输入整数月份哦");
    		  $("input[name=bookBm.wkPeriod]").focus();
    		  return false;
    	  }
          var str = $("input[name=bookBm.startDate]").val();
    	  if(str!=str.match(/\d{4}-\d{2}-\d{2}/ig)) {
    		  alert("请输入日期格式为:yyyy-MM-dd！");
    		  $("input[name=bookBm.startDate]").focus();
    		  return false;
    	  }
//           if($("input[name=bookBm.securityCode]").val()!=$("input[name=sessionSecurityCode]").val()) {
//         	  alert("亲，验证码不对哦!");
//         	  $("input[name=bookBm.securityCode]").focus();
//         	  return false;
//           }
//        });
       });
     //点击图片更换验证码
       $(function() {
           $("#Verify").click(function(){
               $(this).attr("src","Security/ValidateImage?timestamp="+new Date().getTime());
           });
       });  
  });
</script>
<style type="text/css">
h4 {
    text-align: center;
    color: red;
}
.bookBm {
    border: 1px solid silver;
    font-size: 16px;  
}
.bookBm td{
    border: 1px solid silver;
    padding: 2px;
}
.bookBm th {
    background: #CCE8F7;
    border: 1px solid silver;
    width: 100px; 
}
.bookBm span {
    color: red;
} 
</style>
<body>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>
<h4>在线预约家政阿姨</h4>
<s:form name="bookOnline" type="post"  theme="simple" namespace="/" action="BookBm">
    <table cellpadding="0" cellspacing="0" border="0" class="bookBm">
        <tr><th>预约阿姨</th><td>姓名<s:textfield name="bookBm.bmName" /><br>编号<s:textfield name="bookBm.bmId" /></td></tr>
        <tr><th>您的姓名</th><td><s:textfield name="bookBm.gzName" /><span>&nbsp;*&nbsp;请输入您的真实姓名</span></td></tr>
        <tr><th>联系方式</th><td><s:textfield name="bookBm.gzPhone" /><span>&nbsp;*&nbsp;请输入有效联系方式</span></td></tr>
        <tr><th>提供薪资</th><td><s:textfield name="bookBm.gzPay" /><span>/月</span></td></tr>
        <tr><th>开始时间</th><td><s:textfield name="bookBm.startDate" /><span>&nbsp;*&nbsp;格式:2000-01-01</span></td></tr>
        <tr><th>工作周期</th><td><s:textfield name="bookBm.wkPeriod" /><span>个月&nbsp;*&nbsp;请填写数字</span></td></tr>
        <tr><th>详细说明</th><td><s:textarea name="bookBm.gzbkContent" style=" width:200px; height:90px;"/><span>&nbsp;&nbsp;*<br>
        请填写：工作地址/家庭成员/面积/主要工作<br>
        如：三口之家，市中阳光小区，150平方，<br>主要与我起来照顾6个月男宝宝</span></td></tr>
        <tr><th>验证码</th><td><s:textfield name="bookBm.securityCode" /><img src="Security/ValidateImage" id="Verify" style="cursor:hand;" alt="看不清，换一张" /><span>&nbsp;*&nbsp;<s:fielderror fieldName="bookBm.securityCode" cssStyle="text-align:left; color: red; list-style-type:none;"/></span></td></tr>
        <tr><th>写好了</th><td><s:submit value="马上预约"/></td></tr>
    </table>
    <input type="hidden" name="bookBm.bkDate" value='<%=upTime %>' />
    <s:actionmessage cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/>   
</s:form>
</body>
</html>