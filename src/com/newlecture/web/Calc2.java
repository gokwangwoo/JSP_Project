package com.newlecture.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8"); //<-- 이건 쓰기가 UTF-8이다.
		response.setContentType("text/html; charset=UTF-8");
		//위의 response.setContentType은 서버가 클라이언트(브라우저)에게 보낼 때 내가 어떤 타입의 문서와 글자 set으로 보내는지 알려주는 것이다.
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		//계산
		if(op.equals("=")) {
			/*먼저 값을 입력하고 + 나 - operator 누르면 입력된 v값은 else 구문을 통해서 ServletContext application에 저장된다.
			 * 다시 값을 입력하고 다음 operator를 = 을 입력하면 여기 if 구문안으로 들어와서 x는 이전에 입력된 값을 저장한 ServletContext에서 가져와
			 * (ServletContext가 application이라는 이름으로 되어 있으므로) 그 값을 x에 넣는다.
			 * 그 이후 +든 -든 계산을 한다.
			 *여기서 operator가 + 이면 더하고 - 이면 뺀다
			 	*/
			//int x = (Integer)application.getAttribute("value");
			
			//int x = (Integer)session.getAttribute("value");
			int x = 0;
			for(Cookie c : cookies) 
			if(c.getName().equals("value")) {
				x = Integer.parseInt(c.getValue());
				break;
			}
			
			int y = v;
			//String operator = (String)application.getAttribute("op");
			
			//String operator = (String)session.getAttribute("op");
			
			String operator = "";
			for(Cookie c : cookies) 
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			
			
			int result = 0;
			
			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;
			
			response.getWriter().printf("result is %d\n", result);
		}
		//값을 저장
		else {
			
			//이제 ServletContext application에 v_ 값과 operator를 저장하려고 한다.
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			//Cookie로 전달될 값은 무조건 URL에 사용가능한 문자열로 보내야 한다. 그래서 String.valueOf(v) 사용
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");// path를 "/"로 지정하면 어떤 서블릿을 요청하든 value 값의 cookie가 웹서버로 전달된다.
			opCookie.setPath("/calc2"); // 즉 path를 calc2로 요청해야 제대로 Cookie가 전달되게 된다.
			valueCookie.setMaxAge(24*60*60); //24시간 후에 만료, 즉 브라우져를 다시 시작해도 쿠키가 사라지지 않는다.
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			//이러면 response에 Cookie 값이 들어가서 보내진다.
			
			response.sendRedirect("calc2.html");//이래야 값 입력하고 백지가 안보이고 다시 calc2 화면보여주게 된다.
			
		}
		
		
		
		
	}

}
