package edu.webapde.bean;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="photo")
public class Photo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int photoId;
	@Column
	private int userId;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String filepath;
	@Column
	private String privacy;
	private ArrayList<String> tags;
	private ArrayList<Integer> allowedUsers;
	
	public Photo() {
		// TODO Auto-generated constructor stub
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public ArrayList<Integer> getAllowedUsers() {
		return allowedUsers;
	}

	public void setAllowedUsers(ArrayList<Integer> allowedUsers) {
		this.allowedUsers = allowedUsers;
	}
	
	
	
}
