package com.newlecture.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/hi")
public class Nana extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException
	{
		PrintWriter out = resp.getWriter();
		out.println("Hello ~~~ ");
	}
}
