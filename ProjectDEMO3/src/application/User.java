package application;

public class User {
	
	public String firstname;
	public String lastname;
	public int userid;
	public String username;
	
	public User(int userid, String firstname, String lastname, String username) {
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
