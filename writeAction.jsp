
<%-- 이제 실질적으로 loginAction을 처리하는 것이다. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="bbs.BbsDAO" %> <%-- 이제우리가 만든 클래스를 사용하자 --%>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %><%-- 입력으로 들어오는 걸 UTF-8로 받음 --%>
<%-- User라는 클래스를 이용해 java beans를 사용한다. --%>
<%-- bbs데이터베이스의 테이블을 가져와야 한다. --%>
<jsp:useBean id="bbs" class="bbs.Bbs" scope="page" /><%-- scope=page는 현재 page에서만 사용가능하다. --%>
<%-- join.jsp에서 보내는 5가지 인자를 모두 받아야 한다. 이메일, 이름, 성멸 등등 --%>
<jsp:setProperty name="bbs" property="bbsTitle" />
<jsp:setProperty name="bbs" property="bbsContent" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- Connector가 필요한데 java connector 를 다운바당서 WEB-INF->lib에 jar파일을 설치한다.--%>
<title>JSP 게시판 웹사이트</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID")!= null){
				//userID가 null이 아니다 즉 로그인에 성공한 계정인 경우
				//session으로 ID값을 넘겨준다.
				userID = (String)session.getAttribute("userID");
			}
			//글쓰기는 로그인이 되어 있어야 작성이 가능하므로 userID == null이다.
			if(userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>"); //유동적으로 script가 되게 한다.
			script.println("alert('로그인을 하세요')");
			script.println("location.href = 'login.jsp'");//main.jsp location하이퍼 링크를 통해 보낸다.
			script.println("</script>");
		} else{ //로그인한 사용자는 여기서 작업을 하도록
			if(bbs.getBbsTitle() == null || bbs.getBbsContent()==null){
				PrintWriter script = response.getWriter();
				script.println("<script>"); //유동적으로 script가 되게 한다.
				script.println("alert('입력이 안된사항이 있습니다.')");
				script.println("history.back()");//이전 페이지로 사용자를 돌려 보내는 것
				script.println("</script>");
			}else{
				BbsDAO bbsDAO = new BbsDAO();
				int result = bbsDAO.write(bbs.getBbsTitle(), userID, bbs.getBbsContent());
				
				if (result == -1){
					//데이터 베이스에 오류가 생긴경우(글쓰기에 실패한 경우)
					PrintWriter script = response.getWriter();
					script.println("<script>"); //유동적으로 script가 되게 한다.
					script.println("alert('글쓰기에 실패하였습니다.')");
					script.println("history.back()");//이전 페이지로 사용자를 돌려 보내는 것
					script.println("</script>");
				}else{
					PrintWriter script = response.getWriter();
					script.println("<script>"); //유동적으로 script가 되게 한다.
					script.println("location.href = 'bbs.jsp'");//이전 페이지로 사용자를 돌려 보내는 것
					script.println("</script>");
				}
		}
		//여기서 제대론 된 이자들 ID나 password등이 입력되지 안으면 null이 오므로
		//if문으로 처리해 준다.
		
			
		}
		
	%>
</body>
</html>