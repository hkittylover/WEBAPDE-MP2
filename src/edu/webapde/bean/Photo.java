package edu.webapde.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;

@Entity(name = "photo")
@SecondaryTables({ @SecondaryTable(name = "userphoto", foreignKey = @ForeignKey(name = "photoId")) })
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int photoId;
	@Column(table = "userphoto")
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
	@ElementCollection//(fetch=FetchType.EAGER)
	@CollectionTable(name="tagphoto", joinColumns = @JoinColumn(name = "photoId"))
	//
	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinTable(name = "tagphoto", joinColumns = {@JoinColumn(name = "photoId")})
	@Column(name = "tag")
	private List<String> tags;
	@ElementCollection//(fetch=FetchType.EAGER)
	@CollectionTable(name = "allowedusers", joinColumns = @JoinColumn(name = "photoId"))
	//
	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinTable(name = "allowedusers", joinColumns = {@JoinColumn(name = "photoId")})
	@Column(name = "allowedUser")
	private List<String> allowedUsers;

	public Photo() {
		// TODO Auto-generated constructor stub
		this.title = null;
		this.description = null;
		this.filepath = null;
		this.privacy = null;
		this.tags = new ArrayList<>();
		this.allowedUsers = new ArrayList<>();
	}

	public Photo(String username, String title, String filepath, String privacy, String date) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.title = title;
		this.description = null;
		this.filepath = filepath;
		this.privacy = privacy;
		this.date = date;
		this.tags = new ArrayList<>();
		this.allowedUsers = new ArrayList<>();
	}
	
	public Photo(String username, String title, String description, String filepath, String privacy, String date) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.title = title;
		this.description = description;
		this.filepath = filepath;
		this.privacy = privacy;
		this.date = date;
		this.tags = new ArrayList<>();
		this.allowedUsers = new ArrayList<>();
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
				+ description + "', 'filepath':'" + filepath + "', 'privacy':'" + privacy + "', 'date':'" + date + "', 'tags':" + toStringTags()
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

	public void addTag(String tag) {
		if(!tags.contains(tag))
			tags.add(tag);
	}
	
	public void addAllowedUser(String allowedUser) {
		if(!allowedUser.contains(allowedUser))
			allowedUsers.add(allowedUser);
	}
}
