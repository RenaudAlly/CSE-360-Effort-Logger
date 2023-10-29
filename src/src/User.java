package src;

public class User {
	private int userID;
	
	public User() {
		userID = 0;
	}
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int newUserID) { 
		userID = newUserID;
	}
	
	public String toString() {
		return "User [userID =]" + userID + "]";
	}

}
