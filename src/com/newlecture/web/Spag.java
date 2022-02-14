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
	//�� �κ��� Controller�̴�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);
		
		String result;
		
		if(num%2 != 0)
			result = "Ȧ��";
		else
			result = "¦��";
		
		request.setAttribute("result", result); //result�� ���� ���� spag�� ������.
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL�� ���ƿ�");
		request.setAttribute("notice", notice);
		
		
		//�̰� spag.jsp�� �Ѱ��ִ� forwarding ���ִ� ����Ұ� �ʿ��ϴ�.
		//forward�� ���� �ϰ� �ִ� �۾��� �̾�� ��, redirect�� ���� �۾����� ���� ������°�
		//dispatcher�� ���� �� ������ spag.jsp�� �Ѱ��ش�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
		
		
		
		//tip
		/* 
		 * 	�ϳ��� ���� �������� ���� ����Ҵ� pageContext
		  	�ϳ��� Web application�� ���� ����Ҵ� ServletContext(���� ������ ����� ����)
			forward ���迡�� ���Ǵ� ����Ҵ� Request
			Ư�� Session�� ���� ����Ҵ� Session
			Client�� �����ϴ� ����Ҵ� Cookie
		 */
		

	}
	

}
