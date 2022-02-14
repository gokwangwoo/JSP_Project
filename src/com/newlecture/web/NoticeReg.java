package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		response.setCharacterEncoding("UTF-8"); //<-- 이건 쓰기가 UTF-8이다.
		response.setContentType("text/html; charset=UTF-8");
		//request.setCharacterEncoding("UTF-8"); <--CharacterFilter에 구현되어서 이제 필요없다.
		
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
	}

}
