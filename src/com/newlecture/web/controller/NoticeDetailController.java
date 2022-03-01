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
		String sql = "SELECT * FROM NOTICE WHERE ID=?"; //Ư�� ID�� ���� �������� WHERE ID=? �� ���δ�.

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC","1234");
			PreparedStatement st = con.prepareStatement(sql); //prepared Statement�� �̿��ؼ� ? �������� ���� �־�����
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); //������ prepared�� �̿��� �̸� �غ��� sql���� ����ϹǷ� rs�� �������� ���ش�.

			//���� JSP MVC model1������ �°� Model + Controller�� ���� �ڵ� ������ ������ְ� Model�� ������
			rs.next();

			//�̰� �ٷ� model ������ ���� ���̴�.
			String title = rs.getString("TITLE");
			Date regdate = rs.getDate("REGDATE");
			String writerId = rs.getString("WRITER_ID");
			String hit = rs.getString("HIT");
			String files = rs.getString("FILES");
			String content = rs.getString("CONTENT");
			
			//���� ������ �޾ƿ� string, Date������  �츮�� com.newlec.web.entity���� ���� Notice ��ü�� �Ѱ���� �մϴ�.
			//new�� ���� �����ڴ� �⺻ �����ڰ� �ƴϰ� �Ʒ��� ���� title, regdate���� ä�� ������ public Notice(int id, String title ...) �̷��� �� ������
			Notice notice = new Notice(
					id,
					title,
					regdate,
					writerId,
					hit,
					files,
					content
					);//���� �� ���� �Ѵ�.
			
			request.setAttribute("n", notice);//<--���� n �� �̿��ؼ� ȭ�鿡 ����� �� �� �ִ�. detail.jsp���� ���
			
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
		//�̷��� �и��� �͵��� View�� detail.jsp�� �Ѱ���� �ϴµ�
		//serverlet���� servlet���� �Ѱ��ִ� ����� 2���� redirect�� forward
		//redirect�� �ƿ��ٸ� �������� ���������� ��(ex. �α��� ���н� �α��ν���ȭ�� �����ֱ�
		//forward�� ���⼭ �۾� �� ������ �����ִ� ��
		//detail jsp ���� �� NoticeDetailController�� ���� ���� �Ǿ�� �Ѵ�.
		
		
		//forwar�� dispatcher�� ���ȴ�.
		request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
		//notice�Ʒ��� �ִ� detail.jsp�� ��û�ϰ� request�� response�� ���� �Ѵ�.
		//request �κ��� ���� try catch �κп��� Ȯ�� ����
		
		
	}
}
