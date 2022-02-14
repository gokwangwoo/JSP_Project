package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8"); //<-- �̰� ���Ⱑ UTF-8�̴�.
		response.setContentType("text/html; charset=UTF-8");
		//���� response.setContentType�� ������ Ŭ���̾�Ʈ(������)���� ���� �� ���� � Ÿ���� ������ ���� set���� �������� �˷��ִ� ���̴�.
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		//���
		if(op.equals("=")) {
			/*���� ���� �Է��ϰ� + �� - operator ������ �Էµ� v���� else ������ ���ؼ� ServletContext application�� ����ȴ�.
			 * �ٽ� ���� �Է��ϰ� ���� operator�� = �� �Է��ϸ� ���� if ���������� ���ͼ� x�� ������ �Էµ� ���� ������ ServletContext���� ������
			 * (ServletContext�� application�̶�� �̸����� �Ǿ� �����Ƿ�) �� ���� x�� �ִ´�.
			 * �� ���� +�� -�� ����� �Ѵ�.
			 *���⼭ operator�� + �̸� ���ϰ� - �̸� ����
			 	*/
			//int x = (Integer)application.getAttribute("value");
			
			//int x = (Integer)session.getAttribute("value");
			int x = 0;
			for(Cookie c : cookies) 
			if(c.getName().equals("value")) {
				x = Integer.parseInt(c.getValue());
				break;
			}
			
			int y = v;
			//String operator = (String)application.getAttribute("op");
			
			//String operator = (String)session.getAttribute("op");
			
			String operator = "";
			for(Cookie c : cookies) 
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			
			
			int result = 0;
			
			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;
			
			response.getWriter().printf("result is %d\n", result);
		}
		//���� ����
		else {
			
			//���� ServletContext application�� v_ ���� operator�� �����Ϸ��� �Ѵ�.
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			//Cookie�� ���޵� ���� ������ URL�� ��밡���� ���ڿ��� ������ �Ѵ�. �׷��� String.valueOf(v) ���
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");// path�� "/"�� �����ϸ� � ������ ��û�ϵ� value ���� cookie�� �������� ���޵ȴ�.
			opCookie.setPath("/calc2"); // �� path�� calc2�� ��û�ؾ� ����� Cookie�� ���޵ǰ� �ȴ�.
			valueCookie.setMaxAge(24*60*60); //24�ð� �Ŀ� ����, �� �������� �ٽ� �����ص� ��Ű�� ������� �ʴ´�.
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			//�̷��� response�� Cookie ���� ���� ��������.
			
			response.sendRedirect("calc2.html");//�̷��� �� �Է��ϰ� ������ �Ⱥ��̰� �ٽ� calc2 ȭ�麸���ְ� �ȴ�.
			
		}
		
		
		
		
	}

}
