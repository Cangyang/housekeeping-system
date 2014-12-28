<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" type="images/x-icon" href="/jiazheng/images/favicon.ico" />
<link href="http://fonts.googleapis.com/css?family=Terminal+Dosis&subset=latin" rel="stylesheet" type="text/css" />
<style type="text/css">
body { margin: 0; padding: 0;}
.up { height: 120px; background-color: #2E8B57; padding: 0; margin: 0; border-bottom: solid black 4px;}
.up p { margin: 0; text-align: center; font-size: 80px;  color: white;font-family: "Terminal Dosis", sans-serif; text-shadow: 5px 5px 0 transparent, 2px 2px 0 #888eee; overflow: hidden;}
.middle { position: relative; border: 2px solid silver; height: 350px; width: 600px; margin: 0 auto; text-align: center;}
.middle tr th { text-align: center; font-size: 16px;}
.down {  position: fixed; left: 0; right: 0; bottom: 0;  margin: 0;  height: 120px; line-height: 40px; background-color: #2E8B57; border-top: solid black 4px;}
.down p { text-align: center;}
.clear { clear: both;}
</style>
<script type=text/javascript src="/jiazheng/JS/jquery.min.js"></script>
<script type="text/javascript" >
//** 使.down 的div 元素始终居于页面最下方* *// 
  $(document).ready(function(){
	  var screenWidth = $(window).width();  //获取屏幕可视区域宽度
	  $(".down").width(screenWidth);  //将宽度赋值给down元素使其贯穿整个屏幕
	  var screenHeight = $(window).height(); //获取屏幕可视高度
	  var divHeight = $(".down").height() + 1; 
	  
	  $(window).scroll(function(){
		  var scrollHeight = $(document).scrollTop();  //获取滚动条滚动的高度
		  if(!window.XMLHttpRequest){
			  $(".down").css("top",screenHeight + scrollHeight - divHeight);
			  
		  }
	  })
	  //使表格居于浏览器中部位置
	  var middleHeight = $(".middle").height();
	  var upHeight = $(".up").height();
	  var downHeight = $(".down").height();
	  $(".middle").css("top",(screenHeight - upHeight - downHeight - middleHeight)/2);
  
	  
  });
</script>
<script>
//点击更新图片
$(function () {  
    //点击图片更换验证码
    $("#Verify").click(function(){
        $(this).attr("src","Security/ValidateImage?timestamp="+new Date().getTime());
    });
});
//表单验证
$(function () {
	$("form").submit(function() {
		if($("select[name=user.type]").val()=="") {
			alert("亲爱的,您还没有选择用户类型呢！");
			$("select[name=user.type]").focus();
			return false;
		}
		if($("input[name=user.name]").val()=="") {
			alert("亲爱的,您还没有输入用户名呢！");
			$("input[name=user.name]").focus();
			return false;
		}
		if($("input[name=user.email]").val()=="") {
			alert("亲爱的,您还没有输入注册邮箱呢！");
			$("input[name=user.email]").focus();
			return false;
		}
		if($("input[name=user.pwd]").val()=="") {
			alert("亲爱的,您还没有输入密码呢！");
			$("input[name=user.pwd]").focus();
			return false;
		}
		if($("input[name=user.repwd]").val()=="") {
			alert("亲爱的,请您再次输入密码！");
			$("input[name=user.repwd]").focus();
			return false;
		}
		if($("input[name=user.pwd]").val()!=$("input[name=user.repwd]").val()) {
			alert("亲爱的,您两次输入的密码不一样哦！");
			return false;
		}
		if($("input[name=user.securityCode]").val()=="") {
			alert("亲爱的，您还没有输入验证码呢！");
			$("input[name=user.securityCode]").focus();
			return false;
		}
	});
});
</script>
<title>家家乐家政系统</title>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
</head>
<body>
<div class="up">
  <p>家家乐家政</p>
</div>
<s:form name="RegisterAction" action="RegisterAction" method="post" namespace="/" theme="simple"  align="center">
<table class="middle">
<tr><th>家家乐家政用户注册</th></tr>
<tr><td>注册类型:<s:select name="user.type" headerValue="--请选择注册用户类别--" headerKey="" list="#{'employee':'家政员','employer':'雇主','company':'家政公司'}" /><s:fielderror fieldName="user.type" cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
<tr><td>用户名:&nbsp;&nbsp;<s:textfield name="user.name" size="20" label="用户名" /><s:fielderror fieldName="user.name" cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
<tr><td>邮&nbsp;&nbsp;箱:&nbsp;&nbsp;<s:textfield name="user.email" size="20" label="邮箱" /><s:fielderror fieldName="user.email" cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
<tr><td>密&nbsp;&nbsp;码:&nbsp;&nbsp;<s:password name="user.pwd" size="20" label="密码" /><s:fielderror fieldName="user.pwd" cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
<tr><td>确认密码:<s:password size="20" name="user.repwd" label="确认密码" /><s:fielderror fieldName="user.repwd" cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
<tr>
  <td>验证码:&nbsp;&nbsp;<s:textfield name="user.securityCode" size="10" label="验证码"/>
  <img src="Security/ValidateImage" id="Verify" style="cursor:hand;" alt="看不清，换一张" /><s:fielderror fieldName="user.securityCode" cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)" /></td>
</tr>
<tr>
   <td><s:reset value="重置" />
   <s:submit value="注册"/></td>
</tr>
<tr><td><s:actionmessage cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/></td></tr>
</table>
</s:form>


<div class="down">
  <p>家家乐家政是专业的家政服务平台，提供找保姆、找阿姨、月嫂、家教、家政等细致服务</p>
  <p>版权所有  &copy; 2014   杨腾飞 </p> 
</div>
</body>
</html>