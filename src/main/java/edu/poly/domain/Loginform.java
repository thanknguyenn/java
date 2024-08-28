package edu.poly.domain;

public class Loginform {
private String userID , password;
public boolean role;
private boolean  remember;

public Loginform() {
	
}
public Loginform(String userID, String password, boolean remember ,boolean role) {
this.role=role;
	this.userID = userID;
	this.password = password;
	this.remember = remember;
}
public String getuserID() {
	return userID;
}
public void setuserID(String userID) {
	this.userID = userID;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isRemember() {
	return remember;
}
public void setRemember(boolean remember) {
	this.remember = remember;
}
public boolean isRole() {
	return role;
}
public void setRole(boolean role) {
	this.role = role;
}

}
