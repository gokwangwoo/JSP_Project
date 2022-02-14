package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet{
	/*protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//request 요청이 get인지 post인지 req.getMethod로 구분할 수 있는데 calculator.html에서 get으로 보내도
		//req.getMethod()에서는 대문자로 오므로 GET으로 처리해야 한다.
		if(req.getMethod().equals("GET")) {
			System.out.println("GET 요청이 왔습니다.");
		}
		else if(req.getMethod().equals("POST")) {
			System.out.println("POST 요청이 왔습니다.");
		}
		
		//super.service의 기능은 오버라이드한 service 메소드를 실행시키는 건데
		//service 아래에 request요청이 get으로 오는지 post로 오는지 판별한는 함수가 있다.
		//그게 doget메소드와 dopost메소드 이다.
		//이 두 메소드가 정의되어 있어야 get또는 post요청이 와도 오류가 발생하지 않는다.
		super.service(req, resp);
	}*/
	// 이제 doGet, doPost가 만들어지면 이 위의 코드들은 이제 필요가 없어진다.
	// 만약 get과 post를 둘 다 호출해서 사용해야 하면 super.service를 사용하도록 한다.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String exp = "0"; //쿠키를 가져오게 하고
		Cookie[] cookies = request.getCookies();//쿠키를 배열로 받아오게 하고
		if(cookies != null) //쿠키가 null 이니면 아래 코드를 실행하게 한다.
			for(Cookie c : cookies)  //쿠키에 저장된 값을 exp에 넣는다.
				if(c.getName().equals("exp")) {
					exp = c.getValue(); //이제 exp 값을 아래에 화면에 표시를 해준다.
					break;
				}
		
		
		response.setCharacterEncoding("UTF-8"); //<-- 이건 쓰기가 UTF-8이다.
		response.setContentType("text/html; charset=UTF-8");
		//위의 response.setContentType은 서버가 클라이언트(브라우저)에게 보낼 때 내가 어떤 타입의 문서와 글자 set으로 보내는지 알려주는 것이다.
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("		<meta charset=\"UTF-8\">");
		out.write("		<title>Insert title here</title>");
		out.write("		<style>");
		out.write("			input{");
		out.write("				width:50px;");
		out.write("				height:50px;");
		out.write("			}");
		out.write("		.output{");
		out.write("			height:50px;");
		out.write("			background: #e9e9e9;");
		out.write("			font-size:24px;");
		out.write("			font-weight: bold;");
		out.write("			text-align: right;");
		out.write("			padding: 0px 5px;");
		out.write("		}");
		out.write("		</style>");
		out.write("		</head>");
		out.write("		<body>");
		out.write("			<div>");
		out.write("				<form method=\"post\">");
		out.write("					<table>");
		out.write("						<tr>");
		out.printf("							<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("						</tr>");
		out.write("						<tr>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"CE\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"C\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"BS\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"/\" /></td>");
		out.write("						</tr>");
		out.write("						<tr>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"7\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"8\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"9\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"*\" /></td>");
		out.write("						</tr>");
		out.write("						<tr>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"4\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"5\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"6\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"-\" /></td>");
		out.write("						</tr>");
		out.write("						<tr>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"1\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"2\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"3\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"+\" /></td>");
		out.write("						</tr>");
		out.write("						<tr>");
		out.write("							<td></td>");
		out.write("							<td><input type=\"submit\" name=\"value\" value=\"0\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"dot\" value=\".\" /></td>");
		out.write("							<td><input type=\"submit\" name=\"operator\" value=\"=\" /></td>");
		out.write("						</tr>");
		out.write("					</table>");
		out.write("				</form>");
		out.write("			</div>");
		out.write("		</body>");
		out.write("		</html>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		//사용자가 전달하는 값을 exp즉 , expression으로 만들고 이걸 calcpage에 넘겨줘야 한다.
		
		String exp = "";
		if(cookies != null) //쿠키가 null 이니면 아래 코드를 실행하게 한다.
			for(Cookie c : cookies)  //쿠키에 저장된 값을 exp에 넣는다.
				if(c.getName().equals("exp")) {
					exp = c.getValue(); //이제 exp 값을 아래에 화면에 표시를 해준다.
					break;
				}
		
		//단 '='은 계산 결과는 계산 결과를 보여줘야한다.
		if(operator != null && operator.contentEquals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));//결과를 object로 돌려주기 때문에 String으로 바꿔줘야 한다.
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.contentEquals("C")) {
			exp = "";
		}
		else {
			exp += (value == null)?"":value;//null이 아닌 것들을 그대로 exp 배열에 누적하게 만든다.
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		
		
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.contentEquals("C")) {
			expCookie.setMaxAge(0);
		}
		expCookie.setPath("/calculator"); //이러면 쿠키가 다른 url로 저달 안된다.
		response.addCookie(expCookie);
		response.sendRedirect("calculator");
	}
}
