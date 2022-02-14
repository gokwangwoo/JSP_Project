package com.newlecture.web;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
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
		expCookie.setPath("/");
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");//���� ���� ���'/'�� �־ �̸��� ����
			
		
	}

}
