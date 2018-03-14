<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath }/friend" method="post">

<input type="text" name="search"/>

<button type="submit">검색</button>

</form>
</body>
</html>