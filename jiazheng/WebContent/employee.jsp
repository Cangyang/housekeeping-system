<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<table class="MenuWrap">
  <tr><td>
  <div class="nav_menu" >
  <ul class="lavaLamp" id="lavaLamp">
  <li><a href="index.jsp" >首页</a></li>
  <li style="background-image: url(images/bgtab.gif)"><a href="employee.jsp" >家政员</a></li>
  <li><a href="info.jsp" >供求信息</a></li>
  <li><a href="company.jsp" >家政公司</a></li>
  <li><a href="usercenter.jsp" >用户中心</a></li>
  </ul>
 </div></td></tr>
 <tr><td>
<!--  可放图片banner -->
<!--    <div class="nav_Ad"><img /></div> -->
 </td></tr>
  </table>
  </div>
<script type="text/javascript">
$(function(){
	$("#query").click(function() {
// 		$(".bm").remove(li);
	  $.ajax({
		  url:"queryBm.action",
		  type:"post",
		  data:{
			  workTime: $("#workTime option:selected").val(),
			  accomm: $("#accomm option:selected").val(),
			  workType: $("#workType option:selected").val(),
			  bmCert: $("#bmCert option:selected").val(),
			  bmSkill: $("#bmSkill option:selected").val(),
			  bmLang: $("#bmLang option:selected").val(),
			  bmFlavor: $("#bmFlavor option:selected").val(),
			  bmHomeCity: $("#bmHomeCity option:selected").val()
		  },
		  dataType:"json",
		  success:function(data){
			 if(data==""||data==null) {
				 $(".sel").val("0");
				 alert("灰常抱歉，木有查询到相关信息的阿姨，请重新选择条件后再查询!");
			 } else {
			     var content=""; 
			     for(var i in data){
			         content = content+ "<li><span><a  target='_blank' href='showBmInfo.jsp?id="+data[i].id+"' onclick=window.open(this.href,'hehe','width=700,height=800,top=0,left=0');><img src='image.jsp?userId="+data[i].id+"' /></a></span><br>姓名:"+data[i].surname+"<br>年龄:"+data[i].birthDate+"岁<br>籍贯:"+data[i].hometown+"<br>更新时间:"+data[i].uptime+"</li>";
// 			         onclick=window.open(this.href,'hehe','width=700,height=800,top=0,left=0');
			     }
                 $(".bm").html(content);
			 }
          }  
	  });
});
});
/*
 * 功能:单击链接打开新窗口 a[rel=external] 
 */
 $(document).ready(function() {
	 var sFeatures = "width=700,height=800,top=0,left=0";
	 $('a[class="hehe"]').click(function() {
		 window.open($(this).attr('href'),'hehe',sFeatures);
		 return false;
//          alert("hehe");
	 });
 });
</script>
<s:head />
<div class="BmNav">
<form action="showBm" method="post">
<p id="hehe">您可通过选择查询条件,我们将为你筛选出符合条件的家政阿姨</p>
<table cellpadding="5" cellsapcing="9px"><tr>
<td class="colmnHead">工时:</td>
<td class="colmnOp" ><select id="workTime" class="sel">
  <option value="0">--不限--</option>
  <option value="1">终点计时</option>
  <option value="2">半天</option>
  <option value="3">全天</option>
</select><span class="tips">&nbsp;*&nbsp;通过工作时间来查询阿姨</span></td> 
</tr><tr>
<td class="colmnHead">食宿:</td>
<td class="colmnOp" ><select id="accomm" class="sel">
   <option value="0">--不限--</option>
   <option value="1">包吃/包住</option>
   <option value="2">包吃/不包住</option>
   <option value="3">不包吃/包住</option>
   <option value="4">不包吃/不包住</option>
</select><span class="tips">&nbsp;*&nbsp;通过是否提供食宿来查询阿姨</span></td>
</tr><tr>
<td class="colmnHead">服务:</td>
<td class="colmnOp"><select id="workType" class="sel">
   <option value="0">--不限--</option>
   <option value="1">做饭保洁</option>
   <option value="2">育儿嫂</option>
   <option value="3">育婴师</option>
   <option value="4">月嫂母婴</option>
   <option value="5">家教幼教</option>
   <option value="6">管家司机</option>
</select><span class="tips">&nbsp;*&nbsp;通过服务项目来查询阿姨</span></td>   
</tr><tr>
<td class="colmnHead">证书:</td>
<td class="colmnOp"><select id="bmCert" class="sel">
   <option value="0">--不限--</option>
   <option value="cert_jiankang">健康证</option>
   <option value="cert_yuesao">月嫂证</option>
   <option value="cert_hushi">护士证</option>
   <option value="cert_yingyangshi">营养师证</option>
   <option value="cert_zaojiao">早教证</option>
   <option value="cert_jiashi">驾驶证</option>
   <option value="cert_chushi">厨师证</option>
</select><span class="tips">&nbsp;*&nbsp;通过提供的证书来查询阿姨</span></td>
</tr><tr>
<td class="colmnHead">技能:</td>
<td class="colmnOP"><select id="bmSkill" class="sel">
    <option value="0">--不限--</option>
    <option value="skill_computer">电脑操作</option>
    <option value="skill_ironing">熨烫衣服</option>
    <option value="skill_handwork">手工编织</option>
    <option value="skill_waiyu">外语</option>
    <option value="skill_driving">开车</option>
    <option value="skill_nutriology">营养学知识</option>
</select><span class="tips">&nbsp;*&nbsp;通过所具备的技能来查询阿姨</span></td>    
</tr><tr>
<td class="colmnHead">语言:</td>
<td class="colmnOp"><select id="bmLang" class="sel">
   <option value="0">--不限--</option>
   <option value="lang_local">本地方言</option>
   <option value="lang_normal">普通话</option>
   <option value="lang_sichuan">四川话</option>
   <option value="lang_dongbei">东北话</option>
   <option value="lang_guangdong">广东话</option>
   <option value="lang_kejia">客家话</option>
   <option value="lang_minnan">闽南语</option>
</select><span class="tips">&nbsp;*&nbsp;通过所说语言来查询阿姨</span></td>   
</tr><tr>
<td class="colmnHead">口味:</td>
<td class="colmnOp"><select id="bmFlavor" class="sel">
    <option value="0">--不限--</option>
    <option value="flavor_local">本地菜</option>
    <option value="flavor_xican">西餐</option>
    <option value="flavor_chuancai">川菜</option>
    <option value="flavor_yuecai">粤菜</option>
    <option value="flavor_lucai">鲁菜</option>
    <option value="flavor_sucai">苏菜</option>
    <option value="flavor_zhecai">浙菜</option>
    <option value="flavor_xiangcai">湘菜</option>
    <option value="flavor_mincai">闽菜</option>
    <option value="flavor_huicai">徽菜</option>
</select><span class="tips">&nbsp;*&nbsp;通过做饭口味来查询阿姨</span></td>    
</tr><tr>
<td class="colmnHead">籍贯:</td>
<td class="colmnOp"><select id="bmHomeCity" class="sel">
    <option value="0">--不限--</option>
    <option value="北京">北京</option>
    <option value="上海">上海</option>
    <option value="天津">天津</option>
    <option value="重庆">重庆</option>
    <option value="安徽">安徽</option>
    <option value="福建">福建</option>
    <option value="甘肃">甘肃</option>
    <option value="广东">广东</option>
    <option value="广西">广西</option>
    <option value="贵州">贵州</option>
    <option value="海南">海南</option>
    <option value="河北">河北</option>
    <option value="河南">河南</option>
    <option value="黑龙江">黑龙江</option>
    <option value="湖北">湖北</option>
    <option value="湖南">湖南</option>
    <option value="吉林">吉林</option>
    <option value="江苏">江苏</option>
    <option value="江西">江西</option>
    <option value="辽宁">辽宁</option>
    <option value="内蒙古">内蒙古</option>
    <option value="宁夏">宁夏</option>
    <option value="青海">青海</option>
    <option value="山东">山东</option>
    <option value="山西">山西</option>
    <option value="陕西">陕西</option>
    <option value="四川">四川</option>
    <option value="西藏">西藏</option>
    <option value="新疆">新疆</option>
    <option value="云南">云南</option>
    <option value="浙江">浙江</option>
    <option value="香港">香港</option>
    <option value="澳门">澳门</option>
    <option value="台湾">台湾</option>
</select><span class="tips">&nbsp;*&nbsp;通过籍贯来查询阿姨</span></td>    
</tr>
<tr><td class="button" colspan="2" width="100%"><input id="query" type="button" value="查询" ></td></tr>
</table>
</form>
<!-- <a href="http://baidu.com" onclick="window.open(this.href,'hehe','width=500,height=900,top=0,left=0');" target="_blank">baidu</a> -->
</div>
<div id="info"> </div>
<div class="bmInfo">
<ul class="bm">

</ul>
</div>
<%@include file="bottom.jsp" %>



