
<%-- 이제 실질적으로 loginAction을 처리하는 것이다. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="user.UserDAO" %> <%-- 이제우리가 만든 클래스를 사용하자 --%>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %><%-- 입력으로 들어오는 걸 UTF-8로 받음 --%>
<%-- User라는 클래스를 이용해 java beans를 사요한ㄴ다. --%>
<jsp:useBean id="user" class="user.User" scope="page" /><%-- scope=page는 현재 page에서만 사용가능하다. --%>
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- Connector가 필요한데 java connector 를 다운바당서 WEB-INF->lib에 jar파일을 설치한다.--%>
<title>JSP 게시판 웹사이트</title>
</head>
<body>
	<%
		//이제 로그인한 유저는 로그인 화면이나 회원가입 화면에 들어가지 못하게 제작
		String userID = null;
		if(session.getAttribute("userID")!= null){
			//userID가 null이 아니다 즉 로그인에 성공한 계정인 경우
			//session으로 ID값을 넘겨준다.
			userID = (String)session.getAttribute("userID");
		}
		if(userID != null){
			PrintWriter script = response.getWriter();
			script.println("<script>"); //유동적으로 script가 되게 한다.
			script.println("alert('이미 로그인 되어있습니다.')");
			script.println("location.href = 'main.jsp'");//main.jsp로 돌려보내는 것이다.
			script.println("</script>");
		}//이미 로그인한 사람은 다시 로그인 할 수 없게 만들고
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		if(result == 1){
			//로그인 성공에 대한 session 관리를 해야 한다.
			session.setAttribute("userID", user.getUserID());
			//해당 회원의 ID값을 넣어준다.
			PrintWriter script = response.getWriter();
			script.println("<script>"); //유동적으로 script가 되게 한다.
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		else if (result == 0){
			//비밀번호가 틀린경우
			PrintWriter script = response.getWriter();
			script.println("<script>"); //유동적으로 script가 되게 한다.
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");//이전 페이지로 사용자를 돌려 보내는 것
			script.println("</script>");
		}
		else if (result == -1){
			//비밀번호가 틀린경우
			PrintWriter script = response.getWriter();
			script.println("<script>"); //유동적으로 script가 되게 한다.
			script.println("alert('존재하지 않는 아이디 입니다.')");
			script.println("history.back()");//이전 페이지로 사용자를 돌려 보내는 것
			script.println("</script>");
		}
		else if (result == -2){
			//비밀번호가 틀린경우
			PrintWriter script = response.getWriter();
			script.println("<script>"); //유동적으로 script가 되게 한다.
			script.println("alert('데이터 베이스 오류가 발생했습니다.')");
			script.println("history.back()");//이전 페이지로 사용자를 돌려 보내는 것
			script.println("</script>");
		}
	%>
	
</body>
</html>