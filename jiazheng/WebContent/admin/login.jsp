<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="JS/jquery-1.2.1.min.js"></script>
<link rel="shortcut icon" type="images/x-icon" href="/jiazheng/images/favicon.ico" />
<link href="http://fonts.googleapis.com/css?family=Terminal+Dosis&subset=latin" rel="stylesheet" type="text/css" />
<style type="text/css">
body .login {
    background: #fbfbfb;  
}
.login * {
    margin: 0;
    padding； 0；
}
#login {
    width: 360px;
    padding: 114px 0 0;
    margin: auto;
}
.login form {
    margin-left: 8px;
    padding: 26px 24px 46px;
    font-weight: 400;
    background: #fff;
    border: 1px solid #e5e5e5;
    box-shadow: rgba(200,200,200,.7) 0 4px 10px -1px;   
}
.login label {
    color: #777;
    font-size: 14px;
}
.login form .input {
    color: #555;
    font-weight: 200;
    font-size: 24px;
    line-height: 1;
    width: 100%;
    padding: 3px;
    margin: 2px 6px 16px;
    border: 1px solid #e5e5e5;
    background: #fbfbfb;
    box-shadow: inset 1px 1px 2px rgba(200,200,200,.2);
}
.login form .button {
    height: 30px;
    line-height: 28px;
    padding: 0 20px; 
    background-color: #21759b;
    background-image: linear-gradient(to bottom,#2a95c5,#21759b);
    border-color: #21759b;
    border_bottom-color: #1e6a8d;
    box-shadow: inset 0 1px 0 rgba(120,200,230,.5);
    text-decoration: none;
    text-shadow: 0 1px 0 rgba(0,0,0,.1);
    color: #fff;
}
</style>
<title>后台管理</title>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<script type="text/javascript">
$(document).ready(function () {
	//验证输入
	$("form").submit(function () {
		if($("input[name=user.username]").val()=="") {
			alert("请输入管理员账号！");
			$("input[name=user.username]").focus();
			return false;
		}
		if($("input[name=user.password]").val()=="") {
			alert("请输入管理员密码！");
			$("input[name=user.password]").focus();
			return false;
		}
	});
});
</script>
</head>
<body class="login">
<div id="login">
  <h1>家家乐家政后台管理</h1>
  <form action="adminLogin" method="post">
  <p><label for="user_login">用户名<br/><input type="text" name="user.username" size="20" class="input" /></label></p>
  <p><label for="user_pass">密码<br/><input type="password" name="user.password" size="20" class="input" /></label></p>
  <p class="submit"><input type="submit" name="" class="button" value="登录"/></p>
  </form>
  <s:actionmessage cssStyle=" margin-left: 160px; text-align:left; color: red; list-style-type:none; list-style-image: url(images/attention.png)"/>
</div>
</body>
</html>