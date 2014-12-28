<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
       String user = null;
       user = (String)session.getAttribute("userType");
       
       if(user!=null&&user.equals("admin"))
       {
%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	$("#button").click(function() {
		function confirm() {
			if($("input[name=news.title]").val()=="") {
				alert("请输入新闻标题！");
				$("input[name=news.title]").focus();
				return false;
			}
			if($("textarea[name=news.content]").val()=="") {
				alert("请输入新闻内容！");
				$("textarea[name=news.content]").focus();
				return false;
			}
			if($("input[name=news.type]").val()=="") {
		        alert("");
		        $("input[name=news.type]").focus();
		        return false;
	        }
			return true;
		};
		if(confirm()) {
			$.ajax({
				url:"addNews",
				type:"post",
				data:{
					newsTitle:$("input[name=news.title]").val(),
					newsContent:$("textarea[name=news.content]").val(),
					newsType:$("input[name=news.type]").val(),
					newsIssuedate:$("input[name=news.issuedate]").val()
				},
				success:function() {
					alert("成功添加新闻！");
					window.location.reload();
				}
			});
		}
		
	});
});
//-->
</script>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>
<style type="text/css">
<!--

-->
</style>
<h2 style="text-align: center">添加网站新闻</h2>

<table>
<tr><td><span>新闻标题</span><input type="text" name="news.title"/></td></tr>
<tr><td><span>新闻内容</span><textarea class="ckeditor" name="news.content" style="width: 700px; height: 300px;" ></textarea></td></tr>
<tr><td><span>新闻类别</span><input type="text" name="news.type"/></td></tr>
<tr><td><input type="hidden" name="news.issuedate" value='<%=upTime %>'></td></tr>
<tr><td><input type="button" value="写好了,发布" id="button"/></td></tr>
<tr><td><p id="newsOk"></p></td></tr>
</table>
<%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>
