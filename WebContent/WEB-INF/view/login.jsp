<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<div align="center" style="width:200px">
<form action="${pageContext.servletContext.contextPath }/loginp" method="post"
		style="width: 100%; text-align: left;" autocomplete="off">
		<div style="margin-top: 15px;">
			<span>LOGIN ID or EMAIL(*)</span> <span></span><br /> <input type="text"
				name="idmail" id="id" placeholder="아이디나 이메일 입력">
		</div>
		<div style="margin-top: 15px;">
			<span>LOGIN PASS(*)</span><br /> <input type="password" name="pass"
				placeholder="비밀번호">
		</div>
		<div style="margin-top: 15px;">

			<button type="submit" style="width: 100%; height: 30px;">로 그
				인</button>
		</div>
	</form>
	</div>
</body>
</html>