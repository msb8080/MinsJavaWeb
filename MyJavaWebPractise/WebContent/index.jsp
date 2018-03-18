<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
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
			var url = this.href+" h2 a";
			var args ={"time":new Date()}
			$("#details").load(url,args);
			return false;
		})
	})
</script>
</head>
<body>	
	<h1>WEBSITE</h1>
	<ul>
		<li><a href="files/test1.html">百度</a></li>
		<li><a href="files/test2.html">ecipse</a></li>
		<li><a href="files/test3.html">NODE.JS</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>