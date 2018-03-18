<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<%!int day = 3;%>
<%!int fontSize;%>
<!-- jsp声明 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试jsp</title>
</head>
<body>
	<fieldset>
		<legend>JSP对象测试</legend>
		<a href="scope1.jsp">JSP对象范围测试</a>
	</fieldset>
	<fieldset>
		<legend>if-else测试</legend>
		<%
			if (day == 1 | day == 7) {
		%>
		<p>Today is weekend</p>
		<%
			} else {
		%>
		<p>Today is not weekend</p>
		<%
			}
		%>
	</fieldset>
	<fieldset>
		<legend>日期测试</legend>
		<p>
			今天是:<%=(new java.util.Date()).toLocaleString()%>
		</p>
	</fieldset>
	<fieldset>
		<legend>while循环测试</legend>
		<%
			while (fontSize <= 5) {
		%>
		<font color="green" size="<%=fontSize%>"> JSP Tutorial </font></br>
		<%
			fontSize++;
		%>
		<%
			}
		%>
	</fieldset>
</body>
</html>