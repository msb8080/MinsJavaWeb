<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax验证用户名</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.5.1.min.js"></script>
</head>
<body>
	<form action="" method="post">
		用户名：<input type="text" name="username" /></br>
		<div id="message"></div></br>
		<input type="submit" value="提交"/>
	</form>
</body>
<script type="text/javascript">
$(function () {
	$(":input[name='username']").change(function() {
		var val = $(this).val();
		val = $.trim(val);
		if(val !=""){
			var url = "${pageContext.request.contextPath}/validateUserName";
			var args = {"username":val,"time":new Date()};
			$.post(url,args,function(data){
				if(data != null){
					$("#message").html(data);
				}
			});
		}
	})
})

</script>
</html>