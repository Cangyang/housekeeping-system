<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type=text/javascript src="/jiazheng/JS/jquery.min.js"></script>
<script type=text/javascript src="/jiazheng/JS/purl.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//根据url参数加载具体供求信息
	$(function() {
		$.ajax({
			url:"showGQDetail",
			type:"post",
			dataType:"json",
			data:{
				type: $.url().param('type'),
				infoID: $.url().param('infoID')
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.Status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
			success:function(data) {
				var wkType, isAccomm, wkTime;
				$("#headLine").html(data[0].headLine);
				$("#postTime").html(data[0].postTime);
				if(data[0].workType==1) {
					$("#workType").html("做饭保洁");
				} else if (data[0].workType==2) {
					$("#workType").html("育儿嫂");
				} else if (data[0].workType==3) {
					$("#workType").html("育婴师");
				} else if (data[0].workType==4) {
				    $("#workType").html("月嫂母婴");
				} else if (data[0].workType==5) {
					$("#workType").html("家教幼教");
				} else if (data[0].workType==6) {
					$("#workType").html("管家司机");
				}  else {
				    $("#workType").html("未知");
				}
				if(data[0].isAccomm==1) {
					$("#accomm").html("包吃/包住");
				} else if(data[0].isAccomm==2) {
					$("#accomm").html("包吃/不包住");
				} else if(data[0].isAccomm==3) {
					$("#accomm").html("不包吃/包住");
				} else if(data[0].isAccomm==4){
					$("#accomm").html("不包吃/不包住");
				} else {
					$("#accomm").html("未知");
				}
				if(data[0].workTime==1) {
					$("#workTime").html("终点计时");
				} else if(data[0].workTime==2) {
					$("#workTime").html("半天");
				} else if(data[0].workTime==3) {
					$("#workTime").html("全天");
				} else {
					$("#workTime").html("未知");
				}
				$("#pay").html(data[0].pay);
				$("#phone").html(data[0].phone);
				$("#content").html(data[0].content);
			}
		});
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>供求信息</title>
</head>
<style type="text/css">
.head {
    width: 600px;
    height: auto;
    border: 1px dotted #ccc;
    padding: 2px;
}
.head h3 {
    text-align: center;
}
.head #headLine {
    color: red;
}
.head p {
    margin: 2px;
    font-size: 14px;
}
.bodyWrap {
    width: 600px;
    height: 400px;
    border: 1px dashed blueviolet;
    margin: 4px 0;
}
.bodyWrap #content {
    width: 400px;
    margin: 6px auto;
}
</style>
<body>
<div class="head">
<h3>标题：<span id="headLine"></span></h3>
<p>发布时间：<span id="postTime"></span></p>
</div>
<div class="bodyWrap">
<ul>
<li>服务项目:<span id="workType"></span></li>
<li>食宿条件:<span id="accomm"></span></li>
<li>工作时间:<span id="workTime"></span></li>
<li>薪资要求:<span id="pay"></span></li>
<li>联系方式:<span id="phone"></span></li>
</ul>
<span>详细：</span><div id="content"></div>
</div>
</body>
</html>