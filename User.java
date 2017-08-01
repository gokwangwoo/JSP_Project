package user;
	//여기서 mysql 데이터를 가져올 수 있습니다.
	// 처음에 만들었던 DB형태와 똑같이 만드는게 좋습니다.
public class User {

	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;
	
	//빈곳 오른쪽 클릭 -> Source -> Generate Getters and Setters로 들어가서
	//Select All을 선택합니다.
	//그러면 아래와 같이 완성되고 DB자료를 java에서 쓸수 있게 되는데 이걸 Java beans라고 부릅니다.
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
