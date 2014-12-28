<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ytf.core.ConnDB" %>
<%@ page import="java.sql.*" %>
<% 
       String user = null;
       user = (String)session.getAttribute("userType");
       
       if(user!=null&&user.equals("admin"))
       {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type=text/javascript src="/jiazheng/JS/jquery.min.js"></script>
<script type=text/javascript src="/jiazheng/JS/purl.js"></script>
<script type="text/javascript">

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>合同信息</title>
</head>
<link href="/jiazheng/CSS/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.service{
    margin: 5px auto;
	padding: 10px 20px;
	width: 920px;
	border: 1px solid #ccc;
	background-color: #F9F9F9;
}
.service h2 {
    margin: 10px 2px;
    text-align: center;
}

.service table {
    border-collapse: collapse;
}
.service table tr {
    margin: 8px 2px;
}
.service table td {
    font-size: 16px;
    text-align: center;
    
}
.service table th {
    font-size: 14px;
    font-style: italic;
    margin: 4px 2px;
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
         <span class="middleTitle">查看服务评价</span>
         </div></td>
       </tr>            
     </table></td>
  </tr>   
  </table>
 </div>
<div class="service">
  <h2 id="service"><span>所有服务评价</span></h2>
  <table>
    <tr><th>评价人编号</th><th>服务项目</th><th>服务态度评价</th><th>服务质量评价</th><th>服务价格评价</th><th>推荐指数</th><th>总体</th><th>评价日期</th></tr>
  
  <%
  String bmID = request.getParameter("bmID");
  int i =0;
  ConnDB db = new ConnDB();
  String sql = "select gzID, servType, svAttitude, svQuality, svPrice, svRecommend, svContent, svDate from tb_assess where bmID="+bmID;
  ResultSet rs = db.executeQuery(sql);
  try {
	  while(rs.next()) {
		  ++i;
		  int gzID = rs.getInt("gzID");
		  String servType = rs.getString("servType");
		  int svAttitude = rs.getInt("svAttitude");
		  int svQuality = rs.getInt("svQuality");
		  int svPrice = rs.getInt("svPrice");
		  int svRecommend = rs.getInt("svRecommend");
		  int svPoint = (svAttitude + svQuality + svPrice + svRecommend)/4;
		  String svContent = rs.getString("svContent");
		  String svDate = rs.getString("svDate");
  %>
  <tr>
    <td><%=i %>、GZ2000<%=gzID %></td>
    <td><%=servType %></td>
    <td><%=(svAttitude==1 ? "差评" : (svAttitude==2 ? "中评" : (svAttitude==3 ? "好评" : "暂无数据")))%></td>
    <td><%=(svQuality==1 ? "差评" : (svQuality==2 ? "中评" : (svQuality==3 ? "好评" : "暂无数据")))%></td>
    <td><%=(svPrice==1 ? "偏高" : (svPrice==2 ? "一般" : (svPrice==3 ? "满意" : "暂无数据")))%></td>
    <td><%=(svRecommend==1 ? "不推荐" : (svRecommend==2 ? "不确定" : (svRecommend==3 ? "推荐" : "暂无数据")))%></td>
    <td><%=(svPoint==1 ? "差评" : (svPoint==2 ? "中评" : (svPoint==3 ? "好评" : "暂无数据")))%></td>
    <td><%=(svDate==null ? "暂无数据" : svDate) %> </td>
  </tr>
  <% 		  
	  }
  } catch(SQLException e) {
	  e.printStackTrace();
  } finally {
	  db.close();
  }
%>
</table>
</div>

 <table class="FriendLinks" >
<tr><td>
<h3>欢迎申请友情链接     QQ:1345183572</h3>
</td></tr>
<tr><td>
<ul class="TxtLinks">
<li><a target=_blank href="http://www.yangtengfei.net">苍羊博客</a></li>
<li><a target=_blank href="http://plus.google.com">Google+ </a></li>
<li><a target=_blank href="http://www.twitter.com">Twitter</a></li>
<li><a target=_blank href="http://www.facebook.com">Facebook</a></li>
<li><a target=_blank href="http://www.youtube.com">Youtube</a></li>
<li><a target=_blank href="http://instagram.com/">Instagram</a></li>
<li><a target=_blank href="http://www.zhihu.com/">知乎</a></li>
<li><a target=_blank href="http://www.guokr.com/">果壳</a></li>
<li><a target=_blank href="http://wufazhuce.com/">ONE·一个</a></li>
</ul></td>
</tr>
</table>
</div>
<div class="FooterWrap">
<div class="CopyRight">
 <p>家家乐家政是专业的家政服务平台，提供找保姆、找阿姨、月嫂、家教、家政等细致服务</p>
 <br>
 版权所有  &copy; 2014   杨腾飞 <br>
</div>
</div>
</div>
<%
       }else {
%>
<div class="error">
<p>本站禁止非法登陆后台！</p>
</div>
<%     }%>