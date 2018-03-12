<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<c:if test="${!empty logonId }">

		<a href="${pageContext.servletContext.contextPath }/chatroom" style="text-decoration: none;">채팅방 개설</a>

	</c:if>
<hr />
			<div align="right" style="padding-right: 20px;">
			<c:if test="${empty logonId }">
				<a href="${pageContext.servletContext.contextPath }/login"><span>Sign in</span></a> <span>or</span></c:if> <span
					style="font-weight: bold">Sign up</span>
			</div>
			<hr />
		<div style="width: 980px;">
			<h2>Spring Project</h2>
			<small>${ment }</small> <br/>
			<a href="${pageContext.servletContext.contextPath }/join" style="text-decoration: none;">회원가입</a>
			<hr />


		</div>
	</div>

</body>
</html>