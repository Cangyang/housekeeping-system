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
.contract{
    margin: 5px auto;
	padding: 10px 20px;
	width: 920px;
	border: 1px solid #ccc;
	background-color: #F9F9F9;
}
#contractTitle {
    text-align: center;
}
.data {
    text-decoration: underline;
}
.table {
    border-collapse: collapse;
    border: 1px solid;
    width: 300px;
}
.table tr {
    border: 1px solid;
}
.table tr td {
    border: 1px solid;
    height: 20px;
    text-align: center;
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
         <span class="middleTitle">合同信息</span>
         </div></td>
       </tr>            
     </table></td>
  </tr>   
  </table>
  </div>
  <%
    String contID = request.getParameter("contID");
    ConnDB db = new ConnDB();
    String sql = "select gzName, bmName, servType, servDailyTime, isAccomm, beginDate, endDate, gzPay, gzSignature, bmSignature, gzSigDate, bmSigDate, sigDate from tb_contract where id="+contID;
    ResultSet rs = db.executeQuery(sql);
    try {
    	if(rs.next()) {
        	String gzName = rs.getString("gzName");
        	String bmName = rs.getString("bmName");
        	String servType = rs.getString("servType");
        	String servDailyTime = rs.getString("servDailyTime");
        	String isAccomm = rs.getString("isAccomm");
        	String beginDate = rs.getString("beginDate");
        	String endDate = rs.getString("endDate");
        	String gzPay = rs.getString("gzPay");
        	String gzSignature = rs.getString("gzSignature");
        	String bmSignature = rs.getString("bmSignature");
        	String gzSigDate = rs.getString("gzSigDate");
        	String bmSigDate = rs.getString("bmSigDate");
        	String sigDate = rs.getString("sigDate");
      
%>
  <div class="contract">
  <h2 id="contractTitle">家政服务协议书</h2>
<p>甲方（雇主）：<span class="data" id="gzName"><%=gzName %></span></p>
<p>乙方（家政员）：<span class="data" id="bmName"><%=bmName %></span></p>
<p>甲乙双方根据平等自愿的原则，经协商签订如下协议，愿意共同遵守。</p>
<p>一、乙方愿意为甲方提供<span class="data" id="servType"><%=servType %></span>为内容的家政服务工作。</p>
<table class="table">
<tr><td>食宿条件</td><td><span class="data" id="isAccomm"><%=isAccomm %></span></td></tr>
<tr><td>工作时间</td><td><span class="data" id="workTime"><%=servDailyTime %></span></td></tr>
<tr><td>服务期限</td><td><span class="data" id="beginDate"><%=beginDate %></span>至<span class="data" id="endDate"><%=endDate %></span></td></tr>
</table>
<p>&nbsp;&nbsp;&nbsp;&nbsp;未经乙方同意，甲方不能擅自增加上述规定以外的服务内容。</p>
<p>二、甲方应尊重乙方的人格和劳动，平等待人，若乙方不能按协议要求完成工作，甲方有权辞退乙方。双方应<br>&nbsp;&nbsp;&nbsp;&nbsp;各自遵守《家政服务员的职业道德》及《雇主应履行的义务》。</p>
<p>三、乙方在合同期内患病，甲方应允许其外出看病，并与乙方协商应病误工的解决办法。</p>
<p>四、甲方在每月应按时向乙方支付上月的工资<span class="data" id="gzPay"><%=gzPay %></span>元。</p>
<p>五、乙方在服务过程中，造成本人或他人的意外事故，甲方应立即通知乙方家属及有关部门，积极处理好善后<br>&nbsp;&nbsp;&nbsp;&nbsp;事宜，并按法律承担相应的责任。</p>
<p>六、乙方在服务过程中，因工作失误给甲方造成损失的，甲方可追究乙方责任，并有权要求乙方承担相应负责<br>&nbsp;&nbsp;&nbsp;&nbsp;人的经济赔偿。在任何情况下，甲方都不得采取搜身、扣押钱财以及殴打、威逼等侵权行为。</p>
<p>七、乙方有权拒绝为他人服务或被带往外省市服务。</p>
<p>八、甲乙双方如发生纠葛、争议和其它事故，可先由见证机构进行调解，如无效，可向具有管辖权的法院提起<br>&nbsp;&nbsp;&nbsp;&nbsp;民事诉讼。</p>
<p>九、一方要求提前解除本协议，应提前7天以口头或书面形式通知另一方。</p>
<p>十、本协议未尽事宜，双方共同协商。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;甲方：<span class="data" id="gzSignature"><%=(gzSignature==null ? "暂无数据" : gzSignature) %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乙方：<span class="data" id="bmSignature"><%=(bmSignature==null ? "暂无数据" : bmSignature) %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见证机构：<span class="data">家家乐家政服务网</span></p>
<p><br>&nbsp;&nbsp;&nbsp;&nbsp;日期：<span class="data" id="gzSigDate"><%=(gzSigDate==null ? "暂无数据" : gzSigDate) %></span>&nbsp;&nbsp;日期：<span class="data" id="bmSigDate"><%=(bmSigDate==null ? "暂无数据" : bmSigDate) %></span>&nbsp;&nbsp;日期：<span class="data" id="sigDate"><%=(sigDate==null ? "暂无数据" : sigDate) %></span></p>
  </div>
  <%
    	  }
    } catch(SQLException e) {
    	e.printStackTrace();
    } finally {
    	 db.close();
    }
   
  %>
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
</body>
</html>

<%     }%>