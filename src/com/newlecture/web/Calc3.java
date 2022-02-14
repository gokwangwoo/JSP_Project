package com.newlecture.web;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		//사용자가 전달하는 값을 exp즉 , expression으로 만들고 이걸 calcpage에 넘겨줘야 한다.
		
		String exp = "";
		if(cookies != null) //쿠키가 null 이니면 아래 코드를 실행하게 한다.
			for(Cookie c : cookies)  //쿠키에 저장된 값을 exp에 넣는다.
				if(c.getName().equals("exp")) {
					exp = c.getValue(); //이제 exp 값을 아래에 화면에 표시를 해준다.
					break;
				}
		
		//단 '='은 계산 결과는 계산 결과를 보여줘야한다.
		if(operator != null && operator.contentEquals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));//결과를 object로 돌려주기 때문에 String으로 바꿔줘야 한다.
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.contentEquals("C")) {
			exp = "";
		}
		else {
			exp += (value == null)?"":value;//null이 아닌 것들을 그대로 exp 배열에 누적하게 만든다.
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		
		
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.contentEquals("C")) {
			expCookie.setMaxAge(0);
		}
		expCookie.setPath("/");
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");//서로 같은 경로'/'에 있어서 이름만 넣음
			
		
	}

}
