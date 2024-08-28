package edu.poly.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report {

	@Id
	String tittle;
	Long likeCount;
	Date newest;
	Date oldest;
	
	
	public Report() {
		
	}
	public Report(String tittle, Long likeCount, Date newest, Date oldest) {
		
		this.tittle = tittle;
		this.likeCount = likeCount;
		this.newest = newest;
		this.oldest = oldest;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	public Date getNewest() {
		return newest;
	}
	public void setNewest(Date newest) {
		this.newest = newest;
	}
	public Date getOldest() {
		return oldest;
	}
	public void setOldest(Date oldest) {
		this.oldest = oldest;
	}
	
	
	
	
	
	
}
