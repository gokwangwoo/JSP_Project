package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		response.setCharacterEncoding("UTF-8"); //<-- �̰� ���Ⱑ UTF-8�̴�.
		response.setContentType("text/html; charset=UTF-8");
		//request.setCharacterEncoding("UTF-8"); <--CharacterFilter�� �����Ǿ ���� �ʿ����.
		
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
	}

}
