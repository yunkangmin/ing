<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
<title>Spring - managed by GIT</title>
</head>
<style>
a {
	text-decoration: none;
}
input {
	padding: 3px; width: 100px;
}
</style>
<body>

	<div align="center">
		<div style="width: 980px;">
			<div>
				<h1>Spring Project</h1>
				<small>- ${ment }-</small>
			</div>
			<hr />
			<div align="right" style="padding-right: 20px;">
			<c:if test="${empty logonId }">
				<a href="/login"><span>Sign in</span></a> <span>or</span></c:if> <span
					style="font-weight: bold">Sign up</span>
			</div>
			<hr />
			<div style="font-size: 17pt; margin-top: 50px;">
				Create your personal account<br /> <span style="font-size: 11pt;">회원가입시 모든 요소는
					필수기입 항목입니다.</span>
			</div>
			<div>
			<c:if test="${!empty err }">
					<p style="color:red">${err }</p>
				</c:if>
					
				<form action="${pageContext.servletContext.contextPath }/join"
					method="post" style="width: 330px; text-align: left;"
					autocomplete="off">
					<div style="margin-top: 20px;">
						<b>ID(*)</b> <small id="checkrst"></small><br /> <input
							type="text" name="id" id="id" pattern="[a-zA-Z]+" value="${data.id }">
					</div>
					<div style="margin-top: 20px;">
						<b>PASS(*)</b><br /> <input type="password" name="pass" value="${data.pass}">
					</div>
					<div style="margin-top: 20px;">
						<b>EMAIL(*)</b><br /> <input type="email" name="email" value="${data.email }">
					</div>
					<div style="margin-top: 20px;">
						<button id="sbt" type="submit" style="width: 100%; height: 30px;"
							>가 입 신 청</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	
	
	
	
	</script>
</body>
</html>