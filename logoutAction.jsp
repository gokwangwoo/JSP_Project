
<%-- 이제 실질적으로 loginAction을 처리하는 것이다. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- Connector가 필요한데 java connector 를 다운바당서 WEB-INF->lib에 jar파일을 설치한다.--%>
<title>JSP 게시판 웹사이트</title>
</head>
<body>
	<%
		//로그아웃 하면 session을 빼앗기게 한다.
		session.invalidate();
	%>
	<script>
		location.href = "main.jsp"; //로그 아웃하면 main.jsp로 이동하도록
	</script>
	
</body>
</html>