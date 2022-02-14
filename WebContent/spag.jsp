<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result", "hello");
//EL 저장객체는 우선순위가 있다.
//ex. ${cnt}를 해서 값을 가져오면
//제일 먼저 page 부터 뒤지고 값이 없으면 request -> session -> application 순으로 값을 가져온다
//따라서 저장객체간 우선순위가 있어서 같이 써도 Error가 발생하지 않는다.
//page ${cnt}값과 request ${cnt}값이 다르므로
//따라서 특정 객체에 있는 값을 가져오고 싶으면 ${sessionScope.cnt} 이런식으로 가져와야 session의 cnt 값을 가져올 수 있다.
//sessionScope는 한정사라서 Session의 값을 전부 가져오고 사용하거나 그럴 순 없다.
%>
<body>
	<%=request.getAttribute("result") %>입니다.
	${resultScope.result}<!-- 여기 result값은 resultScoper.result는 Spag.java에서 setting 한 result값을 통해서 홀/짝을 표시할 것이다. -->
	${names[0]}<br ><!-- 이것이 바로 View에서 사용하느 EL(Expression Language) 위의 java 코드같이 복잡한 코드 대신 EL을 쓰자 -->
	${names[1]}<br >
	${notice.title}<br>
	${result} <!-- 여기 result 값은 page 객체에 있는 result 값을 표시하기 때문에 hello 값을 가져온다. -->
	${param.n/2}<br><!-- Spag.java에서 n값을 가져오는 것 -->
	${header.accept}
	<!-- View이다. -->
</body>
</html>