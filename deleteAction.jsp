
<%-- writeAction을 그대로 가져와서 사용한다. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="bbs.BbsDAO" %> <%-- 이제우리가 만든 클래스를 사용하자 --%>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %><%-- 입력으로 들어오는 걸 UTF-8로 받음 --%>


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
			} 
			int bbsID = 0;
			if(request.getParameter("bbsID") != null){
				bbsID = Integer.parseInt(request.getParameter("bbsID"));
			}
			if(bbsID == 0){
				//writeAction에서 긁어 왔다
				PrintWriter script = response.getWriter();
				script.println("<script>"); //유동적으로 script가 되게 한다.
				script.println("alert('유효하지 않은 글 입니다.')");
				script.println("location.href = 'bbs.jsp'");//main.jsp location하이퍼 링크를 통해 보낸다.
				script.println("</script>");
			}
			//넘어온 bbsID값을 세션으로 관리해줄 필요가 있습니다.
			Bbs bbs = new BbsDAO().getBbs(bbsID);
			//ID값이 동일하지 않으면
			if(!userID.equals(bbs.getUserID())){
				PrintWriter script = response.getWriter();
				script.println("<script>"); //유동적으로 script가 되게 한다.
				script.println("alert('권한이 없습니다.')");
				script.println("location.href = 'bbs.jsp'");//main.jsp location하이퍼 링크를 통해 보낸다.
				script.println("</script>");
				
			}else{ 
				BbsDAO bbsDAO = new BbsDAO();
				int result = bbsDAO.delete(bbsID);
				if (result == -1){
					//데이터 베이스에 오류가 생긴경우(글쓰기에 실패한 경우)
					PrintWriter script = response.getWriter();
					script.println("<script>"); //유동적으로 script가 되게 한다.
					script.println("alert('글 삭제에 실패하였습니다.')");
					script.println("history.back()");//이전 페이지로 사용자를 돌려 보내는 것
					script.println("</script>");
				}else{
					PrintWriter script = response.getWriter();
					script.println("<script>"); //유동적으로 script가 되게 한다.
					script.println("location.href = 'bbs.jsp'");//이전 페이지로 사용자를 돌려 보내는 것
					script.println("</script>");
				}
		
		//여기서 제대론 된 이자들 ID나 password등이 입력되지 안으면 null이 오므로
		//if문으로 처리해 준다.
		
			
		}
		
	%>
</body>
</html>