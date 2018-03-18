<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
	/* 	var jsAjax = function(){
	 var testStr = "alert('hello eval')";
	 eval(testStr);
	 document.getElementsByTagName("a")[0].onclick = function () {		
	 var request = new XMLHttpRequest();
	 var url = this.href+"?time="+new Date();
	 var method = "POST";
	 request.open(method,url);	
	 request.setRequestHeader("ContentType","application/x-www-form-urlencoded")
	 request.send(null);
	 request.onreadystatechange = function(){
	 alert(request.readyState);
	 if(request.readyState == 4){ //响应完成
	 if(request.status == 200 || ruquest.status ==304){//响应结果可用
	 alert(request.reponseText);
	 }
	 }
	
	 }
	 return false;			
	 }
	 } */
	$(function(){
		$("a").click(function(){
			var url = this.href;
			var args ={ "time":new Date() };
			$.post(url,args,function(data){
				var name = $(data).find("name").text();			
				var website = $(data).find("website").text();		
				var introduction = $(data).find("introduction").text();				
				$("#details").empty().append("<h2>"+name+"</h2>")
									.append("<h3><a href='"+website+"'>"+website+"</h3>")
									.append("<h4>"+introduction+"</h4>");
			});
			return false;
		})
	})
</script>
</head>
<body>	
	<h1>WEBSITE</h1>
	<ul>
		<li><a href="files/test1.xml">百度</a></li>
		<li><a href="files/test2.xml">ecipse</a></li>
		<li><a href="files/test3.xml">NODE.JS</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>