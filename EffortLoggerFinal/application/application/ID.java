package application;

public class ID {

	private String user;
	private String pass;
	private int level;
	
	public ID() {
		
		this.user = "";
		this.pass = "";
		this.level = 0;
		
	}
	
	public String getUser() {
		
		return user;
		
	}
	
	public String getPass() {
		
		return pass;
		
	}
	
	public int getLevel() {
		
		return level;
		
	}
	
	public void setUser(String s) {
		
		this.user = s;
		
	}
	
	public void setPass(String s) {
		
		this.pass = s;
		
	}
	
	public void setLevel(int i) {
		
		
		this.level = i;
		
	}
	
}
