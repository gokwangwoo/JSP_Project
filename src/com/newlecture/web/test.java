package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/test")
public class test extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		response.setCharacterEncoding("UTF-8"); //<-- �̰� ���Ⱑ UTF-8�̴�.
		response.setContentType("text/html; charset=UTF-8");
		//���� response.setContentType�� ������ Ŭ���̾�Ʈ(������)���� ���� �� ���� � Ÿ���� ������ ���� set���� �������� �˷��ִ� ���̴�.
		PrintWriter out = response.getWriter();
		
		String cnt_ = request.getParameter("cnt");
		int cnt = 100;
		if(cnt_ != null && !cnt_.equals(""))
			cnt = Integer.parseInt(cnt_);
		
		for(int i=0; i<cnt; i++)
		{
			out.println((i+1)+": �ȳ� Servlet!!<br />");
		}
		
	}

}
