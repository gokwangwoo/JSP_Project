package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	//이제 데이터 베이스에서 글의 목록을 가져오는 코드를 짜야 한다.
	public ArrayList<Bbs> getList(int pageNumber){
		//ctrl + shift + o 를 눌러서 외부 라이브러리로 가져오게 할 수 있다.
		//불필요한 import 선언등을 다 없애준다.
		// 위의 get next에석 긁어 왔다.
		// bbsID가 특정 번호보다 작은 값을 가져오고 삭제 되지 않아 bbsAvailable=1인 게시글을 ORDER BY 내림차순으로 정리되고 desc limit 10 즉 10개를 보여준다.
		String SQL = "SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); //연결된 객체를 통해 SQL문을 실행 준비단계.
			//이제 ?에 들어갈 값을 찾아야 하는데
			//getNext는 다음에 작성될 글 번호를 의미
			//게시글이 5이라면 getNext는 6이 될것이다. pageNumber는 1 글래서  *10 전까지의 값이 6이 담긴다.
			//즉 6보다 작은 값을 다가녀와서 모든 게시글이 보이는 것이다/
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery(); //실제 실행시키는 결과를 가져오게 하고
			while(rs.next()) {
				//위의 select문이 모든 속성을 가져오므로 아래는 모든 속성을 모아 주어야 한다.
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs); //list에 해당 인스턴스를 담아서 가져온다.
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list; //뽑아온 정보를 보여준다.
	}
	
	//이제 하나의 함수가 더 필요하다.
	//페이지를 10개씩 표시하는데 게시물이 10개 밖에 없다면 다음 화면이 존재하지 않을 것이다.
	//그걸 알려줘야한다.
	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); 
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery(); //실제 실행시키는 결과를 가져오게 하고
			if(rs.next()) {
				//다음 페이지가 존재하면 다음 페이지로 넘어갈 수 있다는 걸 표현
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false; //뽑아온 정보를 보여준다.
	}
	
	//이제 하나의 게시판 글을 가져오는 코드를 만들어보자
	public Bbs getBbs(int bbsID) {
		//위에서 긁어 왔음
		String SQL = "SELECT * FROM BBS WHERE BBSID = ? "; //특정 ID와 같으면 값을 뽑아오게 만들려고
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); 
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				//위에서 긁어 왔음
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null; //해당 글이 존재하지 않으면 null을 반환 
	
	}
	//이제 수정하는 기능을 넣어 보겠습니다.
	//매개 변수로 들어온 변수를 바꿔치기 해주겠다는 의미
	//write와 비슷하지 위의 write의 기능을 가져오자
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?"; //특정한 ID에 해당하는 제목과 내용을 바꿔준다는 의미
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); //연결된 객체를 통해 SQL문을 실행 준비단계.
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate(); //성공적으로 실행했다면 0이상 값 반환
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1; 
	}
	//이제 삭제를 만들어 보자
	public int delete(int bbsID) {
		//위에걸 그대로 복사 후 이용
		String SQL = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?"; //bbsAvailable을 0으로 바꿔줘서 글을 삭제한다.
		//삭제 되도 글의 내뇽은 남음
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); //연결된 객체를 통해 SQL문을 실행 준비단계.
			pstmt.setInt(1, bbsID); //avaiable을 0으로 바꾼다는 의미
			return pstmt.executeUpdate(); //성공적으로 실행했다면 0이상 값 반환
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1; 
	}
}
