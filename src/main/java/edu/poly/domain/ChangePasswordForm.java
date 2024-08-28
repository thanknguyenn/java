package edu.poly.domain;

public class ChangePasswordForm {
private String userID;
private String password;
private String confirmPassword;
private String currentPassword;





public ChangePasswordForm() {
	
}
public ChangePasswordForm(String userID, String password, String confirmPassword, String currentPassword) {

	this.userID = userID;
	this.password = password;
	this.confirmPassword = confirmPassword;
	this.currentPassword = currentPassword;
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
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
public String getCurrentPassword() {
	return currentPassword;
}
public void setCurrentPassword(String currentPassword) {
	this.currentPassword = currentPassword;
}




}
