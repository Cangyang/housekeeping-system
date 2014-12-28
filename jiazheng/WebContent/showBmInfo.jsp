<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type=text/javascript src="/jiazheng/JS/jquery.min.js"></script>
<script type=text/javascript src="/jiazheng/JS/purl.js"></script>
<script type="text/javascript">
 /*
  *功能:获取当前页面的url参数
  */
//    (function($){
// 	   $.getUrlParam = function(name)
// 	   {
// 		   var reg = new RegExp("(^|&)"+name +"=([^&]*)(&|$)");
// 		   var r = window.location.search.substr(1).match(reg);
// 		   if (r != null) return unescape(r[2]); return null;
// 	   };
//    })(jQuery);
   /*
    *功能:预约条件判断
    */
   $(function() {
	    $("#BmRes").click(function() {
	    	var type = $("#userType").val();
		    if(type==0) {
		    	alert("您尚未登录,请登录后再作此操作!");
		    } else if(type==2){
		    	var sFeatures = "width=470,height=500,top=0,left=0";
		    	var para_name = $("#BmName").text();
		    	var para_id = $("#BmNum").text();
		    	var url = "book.jsp?bmName="+para_name+"&bmId="+para_id;
		    	window.open(url,'预约',sFeatures);
		   		return false;
//                 alert(encodeURI(url));
		    } else {
		    	alert("请您以【雇主】角色登录系统后再作此操作!");
		    }
//             alert($("#userType").val());
	    });
	});  
   /*
    * 功能:单击链接打开新窗口 
    */
//     $(function() {
//    	 var sFeatures = "width=400,height=400,top=0,left=0";
//    	 $("#BmRes").click(function() {
//    		 window.open('http://baidu.com','预约',sFeatures);
//    		 return false;
//    	 });
//     });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家政阿姨个人信息</title>
</head>
<style type="text/css">
.showBmInfo {
    width: 600px;
    height: 350px;
    padding: 6px;
    display: block;
}
.showBmInfo ul {
    display: inline;
}
.bmInfo {
    float:left;
    width: 300px;
}
.bmInfo li {
    padding: 5px;
    font-size:16px;
    list-style:none;
}
.bmInfo li span {
    color:blue;
}
.bmInfo li #BmStatus {
    color: red;
}
.bmImage {
    float:right
}
.bmImage li {
    padding:6px;
    list-style:none;
}
.bmImage img {
    width:200px;
    height:200px;
}
.bmImage span {
    font-size:40px;
    background:green;
}
.showDetail {
    width:650px;
    margin-left:5px;
    display:inline-block;
}
.showDetail ul {
    display: inline;
}
.supply {
    float:left
}
.supply li {
    padding: 5px;
    font-size:16px;
    list-style:none;
}
.supply li span {
    color:red;
}
.attach {
    float:right;
}
.attach li {
    padding: 5px;
    font-size:16px;
    list-style:none;
}
.attach li span {
    color:red;
    padding-right:2px;
}
</style>
<body>
<script type="text/javascript">
/*
 * 功能:异步加载保姆信息
 */
$(document).ready(function (){
// 	var id = $.getUrlParam("id");
// 	alert($.getUrlParam("id"));
	  $.ajax({
		  url:"detail.action",
		  type:"post",
		  data:{
// 			  id: $.getUrlParam("id")
			  id: $.url().param('id')
			  },
		  dataType:'json',	  
		  success:function(detail){
			  if(detail[0].status==0) {
				  $("#BmStatus").html("待岗");
			  } else if(detail[0].status==1) {
				  $("#BmStatus").html("已预约");
			  } else {
				  $("#BmStatus").html("在岗");
			  }
			  $("#BmName").html(detail[0].surname);
			  $("#BmNum").html(detail[0].id);
			  if(detail[0].sex==0) {
				  $("#BmSex").html("女"); 
			  } else {
				  $("#BmSex").html("男");
			  }
			  $("#BmAge").html(detail[0].birthDate);
			  $("#BmHomeCity").html(detail[0].hometown);
			  if(detail[0].education==1) {
				  $("#BmEdu").html("小学");
			  } else if(detail[0].education==2) {
				  $("#BmEdu").html("初中");
			  } else if(detail[0].education==3) {
				  $("#BmEdu").html("高中");
			  } else if(detail[0].education==4) {
				  $("#BmEdu").html("中专");
			  } else if(detail[0].education==5) {
				  $("#BmEdu").html("大专");
			  } else if(detail[0].education==6) {
				  $("#BmEdu").html("大学");
			  } else if(detail[0].education==7) {
				  $("#BmEdu").html("硕士");
			  } else {
				  $("#BmEdu").html("未知");
			  }
			  if(detail[0].marriage==1) {
				  $("#BmMarriage").html("未婚");
			  } else if(detail[0].marriage==2) {
				  $("#BmMarriage").html("已婚");
			  } else if(detail[0].marriage==3) {
				  $("#BmMarriage").html("离异");
			  } else {
				  $("#BmMarriage").html("未知");
			  }
			  $("#BmAddress").html(detail[0].address);
			  $("#BmPhone").html(detail[0].phone);
			  $("#BmProfile").html(detail[0].profile);
			  $("#BmUptime").html(detail[0].uptime);
			  $("#BmImg").attr("src","image.jsp?userId="+$.url().param("id"));
			  if(detail[0].worktype==1) {
				  $("#BmWorkType").html("做饭保洁");
			  } else if (detail[0].worktype==2) {
				  $("#BmWorkType").html("育儿嫂");
			  } else if (detail[0].worktype==3) {
				  $("#BmWorkType").html("育婴师");
			  } else if (detail[0].worktype==4) {
				  $("#BmWorkType").html("月嫂母婴");
			  } else if (detail[0].worktype==5) {
				  $("#BmWorkType").html("家教幼教");
			  } else if (detail[0].worktype==6) {
				  $("#BmWorkType").html("管家司机");
			  }  else {
				  $("#BmWorkType").html("未知");
			  }
			  if(detail[0].worktime==1) {
				  $("#BmWorkTime").html("终点计时");
			  } else if (detail[0].worktime==2) {
				  $("#BmWorkTime").html("半天");
			  } else if (detail[0].worktime==3) {
				  $("#BmWorkTime").html("全天");
			  } else {
				  $("#BmWorkTime").html("未知");
			  }
			  if(detail[0].accomm==1) {
				  $("#BmAccomm").html("包吃/包住");
			  } else if (detail[0].accomm==2) {
				  $("#BmAccomm").html("包吃/不包住");
			  } else if (detail[0].accomm==3) {
				  $("#BmAccomm").html("不包吃/包住");
			  } else if (detail[0].accomm==4) {
				  $("#BmAccomm").html("不包吃/不包住");
			  } else {
				  $("#BmAccomm").html("未知");
			  }
			  $("input[name=bmLang]").each(function(){
				  if(detail[0].lang[$(this).attr('value')]==1) {
					  $(this).attr("checked",true);
				  }
				  $(this).attr("disabled","disabled");
			  });
			  $("input[name=bmFlavor]").each(function(){
			      if(detail[0].flavor[$(this).attr('value')]==1) {
			    	  $(this).attr("checked","checked");
			      }
			      $(this).attr("disabled","disabled");
			  });
			  $("input[name=bmCert]").each(function(){
				  if(detail[0].cert[$(this).attr('value')]==1) {
					  $(this).attr("checked","checked");
				  }
				  $(this).attr("disabled","disabled");
			  });
			  $("input[name=bmSkill]").each(function(){
				  if(detail[0].skills[$(this).attr('value')]==1) {
					  $(this).attr("checked","checked");
				  }
				  $(this).attr("disabled","disabled");
			  });
// 			  alert("您可在此预约符合您条件的家政阿姨!");
		  }
	  });
  });
</script>
<%
    String userType = null;
    userType = (String)session.getAttribute("userType");
    String type = "-1";
    if (userType==null||userType.equals("")) {
    	type = "0";
    } else if (userType.equals("employee")){
    	type = "1";
    } else if (userType.equals("employer")) {
    	type = "2";
    } else {
    	type = "3";
    }
%>
<input type="hidden" id="userType" value="<%=type%>"/>
<h2 style="text-align: center"><span style="color:red;">家政阿姨</span>的基本信息</h2>
<div class="showBmInfo">
  <ul class="bmInfo">
    <li>状态:<span id="BmStatus"></span></li>
    <li>姓名:<span id="BmName"></span></li>
    <li>编号:<span id="BmNum"></span></li>
    <li>性别:<span id="BmSex"></span></li>
    <li>年龄:<span id="BmAge"></span></li>
    <li>籍贯:<span id="BmHomeCity"></span></li>
    <li>学历:<span id="BmEdu"></span></li>
    <li>婚姻状况:<span id="BmMarriage"></span></li>
    <li>居住地址:<span id="BmAddress"></span></li>
    <li>联系方式:<span id="BmPhone"></span></li>
    <li>保姆简介:<span id="BmProfile"></span></li>
    <li>更新时间:<span id="BmUptime"></span></li>
  </ul>
  <ul class="bmImage">
      <li><img alt="" id="BmImg"></li>
      <li><span id="BmRes">点我预约</span></li>
  </ul>
</div>
<div class="showDetail">
  <ul class="supply">
    <li>服务项目:<span id="BmWorkType"></span></li>
    <li>工作时间:<span id="BmWorkTime"></span></li>
    <li>食宿要求:<span id="BmAccomm"></span></li>
  </ul>
  <ul class="attach">
    <li>
      <span>掌握语言:</span>
      <input type="checkbox" name="bmLang" value="0" />本地方言
      <input type="checkbox" name="bmLang" value="1" />普通话
      <input type="checkbox" name="bmLang" value="2" />四川话<br>
      <input type="checkbox" name="bmLang" value="3" />东北话
      <input type="checkbox" name="bmLang" value="4" />广东话
      <input type="checkbox" name="bmLang" value="5" />客家话
      <input type="checkbox" name="bmLang" value="6" />闽南语
    </li>
    <li>
      <span>做饭口味:</span>
      <input type="checkbox" name="bmFlavor" value="0" />本地菜
      <input type="checkbox" name="bmFlavor" value="1" />西餐
      <input type="checkbox" name="bmFlavor" value="2" />川菜
      <input type="checkbox" name="bmFlavor" value="3" />粤菜<br>
      <input type="checkbox" name="bmFlavor" value="4" />鲁菜
      <input type="checkbox" name="bmFlavor" value="5" />苏菜
      <input type="checkbox" name="bmFlavor" value="6" />浙菜
      <input type="checkbox" name="bmFlavor" value="7" />湘菜
      <input type="checkbox" name="bmFlavor" value="8" />闽菜
      <input type="checkbox" name="bmFlavor" value="9" />徽菜
    </li>
    <li>
      <span>所有证书:</span>
      <input type="checkbox" name="bmCert" value="0" />健康证
      <input type="checkbox" name="bmCert" value="1" />月嫂证
      <input type="checkbox" name="bmCert" value="2" />护士证<br>
      <input type="checkbox" name="bmCert" value="3" />营养师证
      <input type="checkbox" name="bmCert" value="4" />早教证
      <input type="checkbox" name="bmCert" value="5" />驾驶证
      <input type="checkbox" name="bmCert" value="6" />厨师证
    </li>
    <li>
      <span>掌握技能:</span>
      <input type="checkbox" name="bmSkill" value="0" />电脑操作
      <input type="checkbox" name="bmSkill" value="1" />熨烫衣服
      <input type="checkbox" name="bmSkill" value="2" />手工编织<br>
      <input type="checkbox" name="bmSkill" value="3" />外语
      <input type="checkbox" name="bmSkill" value="4" />开车
      <input type="checkbox" name="bmSkill" value="5" />营养学知识
    </li>  
  </ul>
</div>  
</body>
</html>