<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %> <%--java.io.PrintWriter를 넣으면 script를 쓸 수 있다. --%> 
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>JSP 게시판 웹사이트</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
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
		//bbsID가 0이 아니라면 
		Bbs bbs = new BbsDAO().getBbs(bbsID);
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<%-- main페이지니까 active로 지정해주고 --%>
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">게시판</a></li>
			</ul>
			<%
				//아래의 접속하기는 userID가 null인 경우에 작동하도록 합니다.
				if(userID == null){
					
				
			
			%>
			<%--로그인 되지 않은 애들은 로그인을 하도록 --%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="carrot"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				} else{
			%>
			<%--이제 여기는 로그인 한 사람이 확인을 할 수 있는 화면 --%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="carrot"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<% 
				}
			%>
			
			
		</div>
	</nav>
	<div class="container">
			<div class="row">
			<%-- form 태그 지웠는데 이제는 우리에게 보여주기만 하면 되니까 --%>
				<table class = "table table-striped" style="text-align: cneter; border: 1px solid #dddddd">
					<thead> 
						<tr> 
							<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기 양식</th>
							
						</tr>
					</thead>
					<tbody>
						<tr> <%-- 한줄 씩만 나오게 tr테그로 감싸준다. --%>
							<%-- 20%는 비고이다. --%>
							<td style="width: 20%;">글 제목</td>
							<td colspan="2"><%= bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
							
						</tr>
						<tr> 
							<td>글 제목</td>
							<td colspan="2"><%= bbs.getUserID() %></td>
						</tr>
						<tr> 
							<td>작성일자</td> <%--bbs 페이지에서 가져온다. --%>
							<td colspan="2"><%= bbs.getBbsDate().substring(0, 11) + bbs.getBbsDate().substring(11, 13) + "시" + bbs.getBbsDate().substring(14, 16) + "분" %></td>
						</tr>
						<tr> 
							<td>내용</td>
							<%-- 공백이나 기타 특수문자 처리를 위해 replaceAll이라는 함수를 쓴다. &nbps나 &lt같은 건 html 문자이다. --%>
							<td colspan="2" style="min-height: 200px; text-align: left;"><%= bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
						</tr>
					</tbody>
				</table>
				<a href="bbs.jsp" class="btn btn-primary">목록</a>
				<%-- userID와 지금 접속한 ID가 같으면 글을 고칠 수 있다. --%>
				<%
						
					if(userID != null && userID.equals(bbs.getUserID())){
				%>
					<%-- bbsID=<%= bbsID %>을 통해 bbsID를 그대로 가져간다. --%>
					<a href="update.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">수정</a>
					<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="deleteAction.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">삭제</a>
				<%
					}
				%>
			<%--<input type="submit" href="write.jsp" class="btn btn-primary pull-right" value="글쓰기"></a> --%>
		</div>
	
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>