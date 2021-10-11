<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>MVC2 패턴</title>
<link rel="stylesheet" href="css/myStyle.css" />
</head>
<body>
	<h1 align="center">MVC2 Web Project</h1>
	<c:import url="header.jsp" />
	<!—header.jsp를 index.jsp에 포함시킨다 -->
	<br>
	<br>
	<br>
	<h2 align="center">index page 입니다.</h2>
	<form action = "upload.do" method = "post" enctype = "multipart/form-data">
		글쓴이 : <input type="text" name="name" /><br /> 
		제 목 : <input type="text" name="title" /><br /> 
		첨부파일 : <input type="file" name="uploadFile" /><br />
		<input type="submit" value="전송" />
	</form>
</body>
</html>
