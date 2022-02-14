package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/calcpage")
public class CalcPage extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		
		
		String exp = "0"; //��Ű�� �������� �ϰ�
		Cookie[] cookies = request.getCookies();//��Ű�� �迭�� �޾ƿ��� �ϰ�
		if(cookies != null) //��Ű�� null �̴ϸ� �Ʒ� �ڵ带 �����ϰ� �Ѵ�.
			for(Cookie c : cookies)  //��Ű�� ����� ���� exp�� �ִ´�.
				if(c.getName().equals("exp")) {
					exp = c.getValue(); //���� exp ���� �Ʒ��� ȭ�鿡 ǥ�ø� ���ش�.
					break;
				}
		
		
		response.setCharacterEncoding("UTF-8"); //<-- �̰� ���Ⱑ UTF-8�̴�.
		response.setContentType("text/html; charset=UTF-8");
		//���� response.setContentType�� ������ Ŭ���̾�Ʈ(������)���� ���� �� ���� � Ÿ���� ������ ���� set���� �������� �˷��ִ� ���̴�.
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
