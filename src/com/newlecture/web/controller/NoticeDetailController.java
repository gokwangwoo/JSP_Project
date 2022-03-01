package com.newlecture.web.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/detail")

public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE ID=?"; //특정 ID의 값만 가져오게 WHERE ID=? 을 붙인다.

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC","1234");
			PreparedStatement st = con.prepareStatement(sql); //prepared Statement를 이용해서 ? 쿼리문에 값을 넣어주자
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); //위에서 prepared를 이용해 미리 준비한 sql문을 사용하므로 rs의 쿼리문은 빼준다.

			//이제 JSP MVC model1구조에 맞게 Model + Controller를 여기 코드 상위에 만들어주게 Model을 만들자
			rs.next();

			//이게 바로 model 변수를 만든 것이다.
			String title = rs.getString("TITLE");
			Date regdate = rs.getDate("REGDATE");
			String writerId = rs.getString("WRITER_ID");
			String hit = rs.getString("HIT");
			String files = rs.getString("FILES");
			String content = rs.getString("CONTENT");
			
			//이제 위에서 받아온 string, Date값들을  우리가 com.newlec.web.entity에서 만든 Notice 객체에 넘겨줘야 합니다.
			//new로 만들 생성자는 기본 생성자가 아니고 아래에 위의 title, regdate등을 채울 생성자 public Notice(int id, String title ...) 이렇게 된 생성자
			Notice notice = new Notice(
					id,
					title,
					regdate,
					writerId,
					hit,
					files,
					content
					);//순서 잘 들어가야 한다.
			
			request.setAttribute("n", notice);//<--이제 n 을 이용해서 화면에 출력해 줄 수 있다. detail.jsp에서 사용
			
			/*
			request.setAttribute("title", title);
			request.setAttribute("regdate", regdate);
			request.setAttribute("writerId", writerId);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			*/
			
			
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//이렇게 분리한 것들을 View인 detail.jsp에 넘겨줘야 하는데
		//serverlet에서 servlet으로 넘겨주는 방법은 2가지 redirect와 forward
		//redirect는 아예다른 페이지로 보내버리는 것(ex. 로그인 실패시 로그인실패화면 보여주기
		//forward는 여기서 작업 한 내용을 보내주는 것
		//detail jsp 보다 이 NoticeDetailController가 먼저 실행 되어야 한다.
		
		
		//forwar는 dispatcher가 사용된다.
		request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
		//notice아래에 있는 detail.jsp를 요청하고 request와 response를 공유 한다.
		//request 부분은 위의 try catch 부분에서 확인 가능
		
		
	}
}
