package com.newlecture.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;

@WebFilter("/*")
//@WebFilter("/*")을 하면 아제 모든 웹 페이지에 filter를 적용하라는 의미 이다.
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("before filter");
		//필터 거치기전 해야 할 일이 있으면 chain 이전에 하면 되고
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response); //여기서 다음 Servlet을 실행시킨다.
		//필터 거친 이후 해야 할 일이 있으면 chain 이후에 코드를 넣으면 된다.
		System.out.println("after filter");

	}

}
