<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath }/friend/search" method="post">

<input type="text" name="search"/><br/>
  <%--for each  for( String s :cate) items는 배열이나 list(indexing처리된것만 의미가 있다.) 
    "aaa".getBytes(); <- v.bytes 
     --%>
    <c:forEach var="v" items="${list }" varStatus="vs">
    id: ${v.ID } /  email: ${v.EMAIL } / password: ${v.PASSWORD } / lv: ${v.LV } <br/>
    
    <%-- <c:choose>
    <c:when test="${vs.first }">
     <span style="color:blue"> ->${v } : ${v.bytes }     (${vs })</span>
    </c:when>
    <c:when test="${vs.last }">
     <span style="color:red">->${v } : ${v.bytes }     (${vs })</span>
    </c:when>
    <c:otherwise>
     <span style="color:green">->${v } : ${v.bytes }     (${vs })</span>
   </c:otherwise>
    </c:choose> --%>
    </c:forEach>


<button type="submit">검색</button>

</form>
</body>
</html>