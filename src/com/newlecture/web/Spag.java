package com.newlecture.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	//이 부분이 Controller이다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);
		
		String result;
		
		if(num%2 != 0)
			result = "홀수";
		else
			result = "짝수";
		
		request.setAttribute("result", result); //result에 담은 값을 spag로 보낸다.
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice);
		
		
		//이걸 spag.jsp로 넘겨주는 forwarding 해주는 저장소가 필요하다.
		//forward는 현재 하고 있는 작업을 이어가는 것, redirect는 현재 작업관느 전혀 상관없는것
		//dispatcher를 통해 이 내용을 spag.jsp로 넘겨준다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
		
		
		
		//tip
		/* 
		 * 	하나의 서블릿 페이지에 대한 저장소는 pageContext
		  	하나의 Web application에 대한 저장소는 ServletContext(전역 변수와 비슷한 느낌)
			forward 관계에서 사용되는 저장소는 Request
			특정 Session에 대한 저장소는 Session
			Client에 저장하는 저장소는 Cookie
		 */
		

	}
	

}
