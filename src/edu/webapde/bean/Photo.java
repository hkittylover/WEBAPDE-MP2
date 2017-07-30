package edu.webapde.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name = "photo")
//@SecondaryTables({ @SecondaryTable(name = "userphoto", foreignKey = @ForeignKey(name = "photoId")) })
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int photoId;
	@Column//(table = "userphoto")
	private String username;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String filepath;
	@Column
	private String privacy;
	@Column
	private String date;
	@ElementCollection
	@CollectionTable(name="tagphoto", joinColumns = @JoinColumn(name = "photoId"))
	@Column(name = "tag")
	private List<String> tags;
	@ElementCollection
	@CollectionTable(name = "allowedusers", joinColumns = @JoinColumn(name = "photoId"))
	@Column(name = "username")
	private List<String> allowedUsers;

	public Photo() {
		// TODO Auto-generated constructor stub
		this.title = "";
		this.description = "";
		this.filepath = "";
		this.privacy = "";
		this.tags = new ArrayList<>();
		this.allowedUsers = new ArrayList<>();
	}

	public Photo(String username, String title, String filepath, String privacy, String date) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.title = title;
		this.description = "";
		this.filepath = filepath;
		this.privacy = privacy;
		this.date = date;
		this.tags = new ArrayList<>();
		this.allowedUsers = new ArrayList<>();
	}
	
	public Photo(String username, String title, String description, String filepath, String privacy, String date) {
		// TODO Auto-generated constructor stub
		this(username, title, filepath, privacy, date);
		this.description = description;
	}
	
	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getAllowedUsers() {
		return allowedUsers;
	}

	public void setAllowedUsers(List<String> allowedUsers) {
		this.allowedUsers = allowedUsers;
	}
	
	@Override
	public String toString() {
		return "{'photoId':" + photoId + ", 'username':'" + username + "', 'title':'" + title + "', 'description':'"
				+ description + "', 'filepath':'" + filepath + "', 'privacy':'" + privacy + "', 'date': new Date('" + date + "'), 'tags':" + toStringTags()
				+ ", 'allowedUsers':" + toStringAllowedUsers() + "}";
	}
	
	private String toStringTags() {
		String str = "[";
		for(int i = 0; i < tags.size(); i++) {
			str += "'" + tags.get(i) + "'";
			if(i + 1 < tags.size())
				str += ", ";
		}
		str += "]";
		return str;
	}
	
	private String toStringAllowedUsers() {
		String str = "[";
		for(int i = 0; i < allowedUsers.size(); i++) {
			str += "'" + allowedUsers.get(i) + "'";
			if(i + 1 < allowedUsers.size())
				str += ", ";
		}
		str += "]";
		return str;
	}

	public boolean addTag(String tag) {
		if(!containInList(tags, tag)) {
			tags.add(tag);
			return true;
		}
		return false;
	}
	
	public boolean addAllowedUser(String username) {
		if(!containInList(allowedUsers, username) && !this.username.equalsIgnoreCase(username)) {
			allowedUsers.add(username);
			return true;
		}
		return false;
	}
	
	private boolean containInList(List<String> list, String str) {
		for(String s : list) {
			if(s.equalsIgnoreCase(str))
				return true;
		}
		return false;
	}
}
