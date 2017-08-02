package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {
	private Connection conn; //데이터 베이스에 접근하기 위한 객체
	
	private ResultSet rs; //어떠한 정보를 담을 수 있는 객체
	
	//실제 데이터베이스에 접근해서 데이터를 가져오거나 전달하는 class이다.
	public BbsDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs";
			String dbID = "root";
			String dbPassword = "root"; //보안상 위험해 보이는데
			//Class.forName("com.mysql.jdbc.Drvier");//mysql drvier를 연결하는 매게체
			Class.forName("com.mysql.jdbc.Driver"); //이건 내가 만든것
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//글쓰기를 위해서는 3개의 함수가 필요하다.
	//현재 시간을 가져오는 함수
	public String getDate() {
		String SQL = "SELECT NOW()"; //현재 시간을 가저오는 sql query문
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); //연결된 객체를 통해 SQL문을 실행 준비단계.
			rs = pstmt.executeQuery(); //실제 실행시키는 결과를 가져오게 하고
			if(rs.next()) {
				return rs.getString(1); //실행 결과를 가져온다.
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return ""; //데이터 베이스 오류
	}
	
	public int getNext() {
		String SQL = "SELECT bbsID FROM bbs ORDER BY bbsID DESC"; //가장 마지막에 쓰인 번호를 가져오는 것
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); //연결된 객체를 통해 SQL문을 실행 준비단계.
			rs = pstmt.executeQuery(); //실제 실행시키는 결과를 가져오게 하고
			if(rs.next()) {
				return rs.getInt(1) + 1; //실행 결과를 가져온다.
			}
			return 1; //첫 번째 게시물인 경우
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1; //데이터 베이스 오류
	}
	
	//실제 입력한 값을 데이터 베이스에 넣어주는 함수
	public int write(String bbsTitle, String userID, String bbsContent) {
		//나는 BBS가 아니라 bbs로 만들었다.
		String SQL = "INSERT INTO bbs VALUES(?, ?, ?, ?, ?, ?)"; //가장 마지막에 쓰인 번호를 가져오는 것
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); //연결된 객체를 통해 SQL문을 실행 준비단계.
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);//Available이므로 청믐 작성할때는 1
			//INSERT는 executeUpdate로 작동하는 것이다.
			return pstmt.executeUpdate(); //성공적으로 실행했다면 0이상 값 반환
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1; //데이터 베이스 오류
	}
}
