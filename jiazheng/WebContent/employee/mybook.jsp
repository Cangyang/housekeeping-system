<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/head.jsp" %>
<% 
       String userType = null;
       user = (String)session.getAttribute("userType");
       if(user!=null&&user.equals("employee"))
       {
%>
<script type="text/javascript">
$(document).ready(function() {
// 	alert("hehe");
	//加载雇主预约信息
	$(function() {
		$.ajax({
			url:"showgzBook.action",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data) {
				if(data==null||data=="") {
					$("#select").replaceWith("<p id='select'>暂时还没有雇主预约您哦!</p>");
				} else {
					
	            for(var i in data) {
	            	var workType ,workTime, accomm;
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
	                var newrow = '<tr><td>'+data[i].gzName+'</td><td>' + 
	                    data[i].gzPhone+'</td><td>' + data[i].gzPay + '</td><td>' +
	                        workType + '</td><td>' + workTime +
	                           '</td><td>' + accomm + '</td><td>' + data[i].startDate +
	                               '</td><td>' + data[i].wkPeriod + '个月</td><td>' + data[i].bkDate +
	                                   '</td><td id="content">' + data[i].gzbkContent + '</td><td>' + '<input type=radio name="bmOption" value="' +
	                                       data[i].gzId +'" /></td></tr>';
	                    $("#tableHead").after(newrow); 
	                    
	            }
	            for(var j in data) {
	            	if(data[j].isSuccess==1) {
	            		$("input[name='bmOption'][value="+data[j].gzId+"]").attr("checked","checked");
            		    $("input[name='bmOption']").attr("disabled","disabled");
	                	$("#select").replaceWith("<p id='select'>您已成功选择了预约您的雇主!</p>");
            	    }
	            }
				}
// 				alert(data[0].gzName);
			},
		    error:function(XMLHttpRequest, textStatus, errorThrown) {
		    	alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
		    }
		});
	});
	//获取家政阿姨选择的雇主
		$("#select").click(function(){
			var selVal = $("input[name=bmOption]:checked").val();
			if(selVal==null) {
				alert("请选择一位预约雇主！");
			} else {
			    $.ajax({
				    url:"bmOption.action",
				    type:"post",
				    data:{
				    	selectedGz:selVal
				    },
				    success:function() {
					    $("#select").replaceWith("<p id='select'>提交成功</p>");
				}
			});
			}
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
    <a href="employee/psinfo.jsp">个人信息</a>
  </li>
  <li>
    <a href="employee/addinfo.jsp">发布信息</a>
  </li>
  <li>
    <a href="employee/mybook.jsp" style="background:#AA7F00;">预约管理</a>
  </li>
  <li>
    <a href="employee/mycontract.jsp">服务协议</a>
  </li>
  <li>
    <a href="Logout">退出系统</a>
  </li></ul>
</div>
<div class="userInfo">
  <table class="mybook">
    <thead><tr><td colspan="10">预约管理</td></tr></thead>
    <tr id="tableHead"><th>雇主称呼</th><th>雇主联系方式</th><th>提供薪资/月</th><th>预约服务</th><th>每日工时</th><th>食宿条件</th><th>开始日期</th><th>工作周期</th><th>预约日期</th><th>详细说明</th><th>选中</th></tr>
  </table>
  <input type="button" value="确认" id="select">
</div>

<%
       } else{
%>
<div class="error">
<p>对不起，您当前没有此权限，请登录后操作！</p>
</div>
<%} %>
<%@include file="/bottom.jsp" %>