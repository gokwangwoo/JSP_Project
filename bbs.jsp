<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %> <%--java.io.PrintWriter를 넣으면 script를 쓸 수 있다. --%>
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
		//로그인을 한 사람이면 session값이 있을 것이고
		//로그인 하지 않은 사람은 null값이 담길 것이다.
	
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
			<table class = "table table-striped" style="text-align: cneter; border: 1px solid #dddddd">
				<thead> <%-- 테이블의 제목같은 부분 thead는 --%>
					<tr> <%-- tr은 테이블의 한줄 같은 것이다. --%>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<tr><%-- 예시 데이터를 넣어준다. --%>
						<td>1</td>
						<td>안녕하세요.</td>
						<td>홍길동</td>
						<td>2017-08-01</td>
					</tr>
				</tbody>
			</table>
			<%-- write.jsp는 글을 작성할 수 있는 페이지로 보내는 것 --%>
			<%-- 글 쓰기 버튼이다. --%>
			<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>