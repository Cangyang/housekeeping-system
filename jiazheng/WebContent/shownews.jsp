<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type=text/javascript src="/jiazheng/JS/jquery.min.js"></script>
<script type=text/javascript src="/jiazheng/JS/purl.js"></script>
<script type="text/javascript">
//更具url参数异步加载新闻信息
$(document).ready(function() {
	$(function() {
		$.ajax({
			url:"showNewsDetail",
			type:"post",
			data:{
				newsID:$.url().param('newsID')
			},
			dataType:"json",
			error:function(XMLHttpRequest ,textStatus, errorThrown) {
				alert(XMLHttpRequest.Status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
			success:function(data) {
                $("#newsTitle").html(data[0].title);
                $("#content").html(data[0].content);
                $("#issuedate").html(data[0].issuedate);
                $("#type").html(data[0].type);
			}
		});
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家政新闻</title>
</head>
<link href="/jiazheng/CSS/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.news{
    margin: 5px auto;
	padding: 5px ;
	width: 940px;
	border: 1px solid #ccc;
	background-color: #F9F9F9;
}
.news h3 {
    text-align: center;
    margin: 10px auto;
}
.news #content {
    width: 900px;
    margin: 2px auto;
    font-size: 16px;
}
.news p {
    margin: 5px 2px;
    text-align: right;
    font-weight: bold;
    font-style: italic;
}
</style>
<body>
<div class="BodyWrap">
<div class="BodyInner" >
<div class="HeaderWrap">
  <table class=HeaderTxt>
  <tr>
    <td>
    <div style="float: left;">
      <!-- JiaThis Button BEGIN -->
      <div class="jiathis_style">
	  <a class="jiathis_button_qzone"></a>
	  <a class="jiathis_button_tsina"></a>
	  <a class="jiathis_button_tqq"></a>
	  <a class="jiathis_button_weixin"></a>
	  <a class="jiathis_button_renren"></a>
	  <a href="http://www.jiathis.com/share?uid=1904612" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
	  <a class="jiathis_counter_style"></a>
      </div>
      <script type="text/javascript">
      var jiathis_config = {data_track_clickback:'true'};
      </script>
      <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1393995504756822" charset="utf-8"></script>
      <!-- JiaThis Button END -->
     </div></td>
     <td>
     <div style="float: right; line-height: line-height: 25px;">
       <a class="aRed" href="#" target=_blank>加入收藏</a>
       <a class="aRed" href="#" target=_blank>关于我们</a>
       <a class="aRed" href="#" target=_blank>服务协议</a>
     </div></td>
  </tr>
  </table>
  <table class="LogoWrap">
  <tr>
     <td>
       <div class="LogoWrapLeft">
        <a href="index.jsp"><img width="200" height="180" src="/jiazheng/images/jiajiale.gif" alt="家家乐家政" /></a> 
       </div>
     </td>
     <td><table class="LogoWrapRight">
       <tr>
         <td><div class="LogoRightAd">
         <ul><li><img src="/jiazheng/images/banner.gif" border="0" alt="家家乐家政"/></li></ul>
         </div></td>
       </tr>
       <tr>
         <td><div class="wrapRightTxt">
         <span class="middleTitle">家政新闻</span>
         </div></td>
       </tr>            
     </table></td>
  </tr>   
  </table>
  </div>
  <div class="news">
  <h3 id="newsTitle"></h3>
  <div id="content"></div>
  <p>发布日期：<span id="issuedate"></span></p>
  <p>新闻类别：<span id="type"></span></p>
  </div>
  <%@include file="bottom.jsp" %>
       
