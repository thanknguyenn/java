package edu.poly.domain;

import java.util.Date;

public class FavouriteUserReport {

	private String userID;
	private String fullname;
	private String email;
	private Date likeDate;
	
	
	public FavouriteUserReport() {
		
	}
	public FavouriteUserReport(String userID, String fullname, String email, Date likeDate) {
	
		this.userID = userID;
		this.fullname = fullname;
		this.email = email;
		this.likeDate = likeDate;
	}
	public String getuserID() {
		return userID;
	}
	public void setuserID(String userID) {
		this.userID = userID;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	
	
	
}
