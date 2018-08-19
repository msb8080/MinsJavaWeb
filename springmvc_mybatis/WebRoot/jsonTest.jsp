<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>json测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
function requestJson(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath}/requestJson.action',
		contentType:'application/json;charset=utf-8',
		data:'{"name":"手机","price":900}',
		success:function(data){
			alert(data.name);
		}
	})
}
function responseJson(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath}/responseJson.action',
		data:'name=手机&price=900',
		success:function(data){
			alert(data.name);
		}
	})
}

	

</script>
</head>
<body>
<input type="button"  onclick="requestJson()" value="请求json字符串，接受json字符串">
<input type="button"  onclick="responseJson()" value="请求key/value，接受json字符串">
</body>
</html>