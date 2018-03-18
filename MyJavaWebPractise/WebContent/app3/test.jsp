<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.blockUI.js"></script>

<script type="text/javascript">
	$(document).on('click', '#myButton', function() {
	   $.blockUI();
	});
</script>
</head>
<body>
<input type="button" value="点击" id="myButton" /> 
</body>
</html>