package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/add")
public class add extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		response.setCharacterEncoding("UTF-8"); //<-- 이건 쓰기가 UTF-8이다.
		response.setContentType("text/html; charset=UTF-8");
		//위의 response.setContentType은 서버가 클라이언트(브라우저)에게 보낼 때 내가 어떤 타입의 문서와 글자 set으로 보내는지 알려주는 것이다.
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
