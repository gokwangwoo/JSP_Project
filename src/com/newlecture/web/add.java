package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/add")
public class add extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		response.setCharacterEncoding("UTF-8"); //<-- �̰� ���Ⱑ UTF-8�̴�.
		response.setContentType("text/html; charset=UTF-8");
		//���� response.setContentType�� ������ Ŭ���̾�Ʈ(������)���� ���� �� ���� � Ÿ���� ������ ���� set���� �������� �˷��ִ� ���̴�.
		PrintWriter out = response.getWriter();
		
		String temp = request.getParameter("x");
		String temp2 = request.getParameter("y");
		
		int x, y = 0;
		if(temp!= null && !temp.equals("") && temp2!=null && !temp2.equals("")) {
			x = Integer.parseInt(temp);
			y = Integer.parseInt(temp2);
			
			int result =  x+y;
			
			out.printf("result is %d\n", result);
		}
		
		
		
	}

}
