<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>SPRING - by git</title>
 <!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
jQuery library
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
Latest compiled JavaScript
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head> 
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">${logon },LOGON</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">개인정보</a></li>
				<li><a href="#">로그아웃</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">친구관리 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">친구목록</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/friend/search">친구추가</a></li>
						<li><a href="#">친구관리</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	
	<script>
		var onws = new WebSocket("ws://${pageContext.request.serverName}/chap05/handle");
		
		onws.onmessage = function(rst) {
			var obj = JSON.parse(rst.data);
			switch(obj.mode) {
			case "fr_req":
				window.alert(obj.from+" 님으로부터 친구 요청이 발생하였습니다.");
				break;
			}
		}
	</script>
</body>
</html>




