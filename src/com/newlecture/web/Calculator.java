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
		//request ��û�� get���� post���� req.getMethod�� ������ �� �ִµ� calculator.html���� get���� ������
		//req.getMethod()������ �빮�ڷ� ���Ƿ� GET���� ó���ؾ� �Ѵ�.
		if(req.getMethod().equals("GET")) {
			System.out.println("GET ��û�� �Խ��ϴ�.");
		}
		else if(req.getMethod().equals("POST")) {
			System.out.println("POST ��û�� �Խ��ϴ�.");
		}
		
		//super.service�� ����� �������̵��� service �޼ҵ带 �����Ű�� �ǵ�
		//service �Ʒ��� request��û�� get���� ������ post�� ������ �Ǻ��Ѵ� �Լ��� �ִ�.
		//�װ� doget�޼ҵ�� dopost�޼ҵ� �̴�.
		//�� �� �޼ҵ尡 ���ǵǾ� �־�� get�Ǵ� post��û�� �͵� ������ �߻����� �ʴ´�.
		super.service(req, resp);
	}*/
	// ���� doGet, doPost�� ��������� �� ���� �ڵ���� ���� �ʿ䰡 ��������.
	// ���� get�� post�� �� �� ȣ���ؼ� ����ؾ� �ϸ� super.service�� ����ϵ��� �Ѵ�.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		//����ڰ� �����ϴ� ���� exp�� , expression���� ����� �̰� calcpage�� �Ѱ���� �Ѵ�.
		
		String exp = "";
		if(cookies != null) //��Ű�� null �̴ϸ� �Ʒ� �ڵ带 �����ϰ� �Ѵ�.
			for(Cookie c : cookies)  //��Ű�� ����� ���� exp�� �ִ´�.
				if(c.getName().equals("exp")) {
					exp = c.getValue(); //���� exp ���� �Ʒ��� ȭ�鿡 ǥ�ø� ���ش�.
					break;
				}
		
		//�� '='�� ��� ����� ��� ����� ��������Ѵ�.
		if(operator != null && operator.contentEquals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));//����� object�� �����ֱ� ������ String���� �ٲ���� �Ѵ�.
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.contentEquals("C")) {
			exp = "";
		}
		else {
			exp += (value == null)?"":value;//null�� �ƴ� �͵��� �״�� exp �迭�� �����ϰ� �����.
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		
		
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.contentEquals("C")) {
			expCookie.setMaxAge(0);
		}
		expCookie.setPath("/calculator"); //�̷��� ��Ű�� �ٸ� url�� ���� �ȵȴ�.
		response.addCookie(expCookie);
		response.sendRedirect("calculator");
	}
}
