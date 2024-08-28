package edu.poly.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Favourite database table.
 * 
 */
@Entity
@NamedQuery(name="Favourite.findAll", query="SELECT f FROM Favourite f")
public class Favourite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FavouriteID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String favouriteID;
	
	
	@Column(name="LikeDate")
	private Date likeDate;

	//bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name="VideoID")
	private Video video;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;

	public Favourite() {
	}

	public String getFavouriteID() {
		return this.favouriteID;
	}

	public void setFavouriteID(String favouriteID) {
		this.favouriteID = favouriteID;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

}