// Sawyers Risk Prototype
// Name: Sawyer Kesti
// ASU ID: 12191187987
// Class Time: Tuesday 9:00 - 10:15 AM
package application;

public class User { // secondary user class with an ID attribute
	private int userID;
	
	public User() { // constructor
		userID = 0;
	}
	
	public int getUserID() { // getter method
		return userID;
	}
	
	public void setUserID(int userID) { // setter method
		this.userID = userID;
	}
	
	public String toString() { // to string method
		return "UserID: " + userID + "\n";
	}
}
