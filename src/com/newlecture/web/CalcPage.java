package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/calcpage")
public class CalcPage extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		
		
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
		out.write("				<form action=\"calc3\" method=\"post\">");
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

}
