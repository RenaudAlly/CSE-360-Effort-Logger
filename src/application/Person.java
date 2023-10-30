package application;

public class Person {
	private String firstName;
	private String lastName;
	private int hours;
	
	public Person() {
		this.firstName = "";
		this.lastName = "";
		this.hours = 0;
	}
	
	public Person(String firstName, String lastName, int hours) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.hours = hours;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	
}