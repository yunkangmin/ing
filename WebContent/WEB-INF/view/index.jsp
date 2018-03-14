<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div align="center">
		<c:if test="${!empty logonId }">

			<a href="${pageContext.servletContext.contextPath }/chatroom"
				style="text-decoration: none;">채팅방 개설</a>

		</c:if>
		<hr />
		<div align="right" style="padding-right: 20px;">
			<c:if test="${empty logonId }">
				<a href="${pageContext.servletContext.contextPath }/login"><span>Sign
						in</span></a>
				<span>or</span>
			</c:if>
			<span style="font-weight: bold">Sign up</span>
		</div>
		<hr />
		
		<div style="width: 980px;">
			<h2>Spring Project</h2>
			<small>${ment }</small> <br /> <a
				href="${pageContext.servletContext.contextPath }/join"
				style="text-decoration: none;">회원가입</a>
			<hr />
			


		</div>
		<hr/>
	<div class="alert alert-warning alert-dismissible" id="warn" style="display: none">
			<a href="javascript:location.reload();" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>경고!</strong> 다른 윈도우 혹은 탭에서 상태가 변경되었습니다.
		</div>
		<hr />
		<div>
			<!--b와 strong은 같은 효과  -->
			<p>
			<div class="alert alert-info">
				<b>현재접속자수:<span id="cnt"></span></b> / <strong>서버알림</strong><span
					id="info">-</span>
			</div>
			</p>

		</div>
	</div>
	<!--id는 접속한 순서대로 나온다.
	아이피주소로 적을것.
	클라이언트는 localhost자기 아이피여서 안된다.
	다른 브라우저로 해야지만 다른 클라이언트접속이된다.
	  -->
	${pageContext.request.serverName }
	<script>
		var ws = new WebSocket(
				"ws://${pageContext.request.serverName }/chap05/alert");

		//연결이 됬을때..
		ws.onopen = function() {
			console.log("opened");
			console.log(this);

		}

		//메시지가 들어올때..
		ws.onmessage = function(resp) {
			console.log(resp);
			var obj=JSON.parse(resp.data);
			window.alert(obj);
			$("#warn").show();

			//console.log(resp);
			//window.alert(obj.data);
			//$("#cnt").html(obj.cnt);
			// $("#info").html(obj.info);
		}

		//웹서버가 해제되었을대 접속 종료
		ws.onclose = function() {
			window.alert("연결이 해제되었습니다.");
		}
	</script>

</body>
</html>