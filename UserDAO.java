package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//DAO를 통해 회원 정보를 넣고자 할 때 사용된다.
//Ctrl + Shift + O를 누르면 sql connection을 할 수가 있다.
//외부 라이브러리를 부를 수 있다.

public class UserDAO {

	private Connection conn; //데이터 베이스에 접근하기 위한 객체
	private PreparedStatement pstmt; 
	private ResultSet rs; //어떠한 정보를 담을 수 있는 객체
	
	//실제 데이터베이스에 접근해서 데이터를 가져오거나 전달하는 class이다.
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "root"; //보안상 위험해 보이는데
			//Class.forName("com.mysql.jdbc.Drvier");//mysql drvier를 연결하는 매게체
			Class.forName("com.mysql.jdbc.Driver"); //이건 내가 만든것
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		//SQL injection 공격을 방어하기 위해 PreparedStatement이런걸 만들어두고
		// 미리 SQL .... = ?와 같음 문을 준비해서 pstmt.setString(1, userID)와 같이 처리해 주는 것이다.
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //결과를 담을 수 있는 객체에 결과를 담는다.
			
			//결과가 있으면 rs.next를 실행
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) { //아이디가 존재하고 password가 일치하는 경우
					return 1; //로그인 성공
				}
				else
					return 0; //비밀번호 불일치
			}
			//결과가 업으면 -1 즉 아이디가 없다 의미
			return -1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			//위에서 병시한 SQL구문을 입력하도록 한다.
			pstmt = conn.prepareStatement(SQL);
			//이제 각각 ?에 무엇이 들어가는지 적어준다.
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
			//-1이 아니면 성공적인 회원가입이 된것이다.
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터 베이스 오류
	}
	
}
