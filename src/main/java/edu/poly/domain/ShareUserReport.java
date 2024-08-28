package edu.poly.domain;

import java.util.Date;

public class ShareUserReport {

	String userID;
	String videoID;
	String email;
	Date sharedate;
	public ShareUserReport(String userID, String videoID, String email, Date sharedate) {
		
		this.userID = userID;
		this.videoID = videoID;
		this.email = email;
		this.sharedate = sharedate;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getSharedate() {
		return sharedate;
	}
	public void setSharedate(Date sharedate) {
		this.sharedate = sharedate;
	}
	
	
}
