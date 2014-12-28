<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&user.equals("employer"))
       {
%>
<script type="text/javascript">
$(document).ready(function () {
	//加载预约信息
	$(function() {
		$.ajax({
			url:"showbmBook.action",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data) {
				if(data==null||data=="") {
					$("#nicai").html("您当前没有预约！");
				} else {
					for (var i in data) {
						var workType ,workTime, accomm, result;
		            	if (data[i].servType==1){
		            		workType = "做饭保洁";
		            	} else if (data[i].servType==2){
		            		workType = "育儿嫂";
		            	} else if (data[i].servType==3){
		            		workType = "育婴师";
		            	} else if (data[i].servType==4){
		            		workType = "月嫂母婴";
		            	} else if (data[i].servType==5){
		            		workType ="家教幼教";
		            	} else if (data[i].servType==6){
		            		workType ="管家司机";
		            	} else {
		            		workType ="";
		            	}
		            	if (data[i].servDailyTime==1){
		            		workTime = "终点计时";
		            	} else if (data[i].servDailyTime==2){
		            		workTime = "半天";
		            	} else if (data[i].servDailyTime==3){
		            		workTime = "全天";
		            	} else {
		            		workTime = "";
		            	}
		            	if (data[i].isAccomm==1) {
		            		accomm = "包吃/包住";
		            	} else if (data[i].isAccomm==2) {
		            		accomm = "包吃/不包住";
		            	} else if (data[i].isAccomm==3) {
		            		accomm = "不包吃/包住";
		            	} else if (data[i].isAccomm==4) {
		            		accomm = "不包吃/不包住";
		            	} else {
		            		accomm = "";
		            	}
		            	if (data[i].node=='gz') {
// 		            		alert("heh");
		            		result = "处理中";
		            	} else {
		            		if (data[i].isSuccess==0) {
		            			result = "预约失败";
		            		} else {
		            			result = "预约成功";
		            		}
		            	}
		            	var newrow = '<tr><td>' + data[i].bmId + '</td><td>' +
		            	    data[i].bmName + '</td><td>' + workType + '</td><td>' +
		            	    workTime + '</td><td>' + accomm + '</td><td>' +
		            	    data[i].gzPay + '</td><td>' + data[i].startDate + '</td><td>' +
		            	    data[i].wkPeriod + '</td><td>' + data[i].bkDate + '</td><td>' +
		            	    result + '</td></tr>';
		            	    $("#tableHead").after(newrow);
					}
				}
// 				$("#hehe").html(data[0].node);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
		    	alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
		    }
		});
	});
});
</script>
<%
  Date dt = new Date();
  SimpleDateFormat up = new SimpleDateFormat("yyyy-MM-dd");
  String upTime = up.format(dt);
%>


<div class="userOption"><ul>
  <li>
    <a href="employer/addinfo.jsp">发布信息</a>
  </li>
  <li>
    <a href="employer/mybook.jsp" style="background:#AA7F00;">我的预约</a>
  </li>
  <li>
    <a href="employer/mycontract.jsp">服务协议</a>
  </li>
  <li>
    <a href="employer/mylikes.jsp">服务评价</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>
<div class="userInfo">
  <table class="mybook">
  <thead><tr><td colspan="10">预约查看</td></tr></thead>
  <tr id="tableHead"><th>阿姨编号</th><th>阿姨姓名</th><th>预约服务</th><th>每日工时</th><th>提供食宿条件</th><th>提供薪资/月</th><th>开始日期</th><th>工作周期</th><th>预约日期</th><th>预约结果</th></tr>
  </table>
  <p id="nicai"></p>
  <p id="note">注：如果您当前预约失败，则可以重新发起预约；如果当前预约成功，则无法再发起预约，需要到“服务协议”中签订服务协议</p>
</div>

<%
       }else {
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>
<%} %>
<%@include file="/bottom.jsp" %>