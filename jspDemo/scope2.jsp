<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>JSP对象范围</title>
</head>
<body>
<h2>JSP对象范围示例一：</h2>
    <b>Page Scope</b> ::<c:out value="${Pag}" /><br>
    <b>Request Scope</b> ::<c:out value="${Req}" /><br>
    <b>Session Scope</b> ::<c:out value="${Ses}" /><br>
    <b>Application Scope</b>::<c:out value="${App}" /><br>
    <a href="scope2.jsp">下一页Session,Application范围</a>
</body>
</html>