package edu.webapde.bean;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user")
public class User {
	@Id
	private String username;
	@Column
	private char[] password;
	@Column
	private String description;
	
	public User() {
		// TODO Auto-generated constructor stub
		this.username = null;
		this.password = null;
		this.description = "";
	}
	
	public User(String username, char[] password) {
		// TODO Auto-generated constructor stub
		this();
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password) {
		// TODO Auto-generated constructor stub
		this(username, password.toCharArray());
	}
	
	public User(String username, char[] password, String description) {
		// TODO Auto-generated constructor stub
		this(username, password);
		this.description = description;
	}
	
	public User(String username, String password, String description) {
		// TODO Auto-generated constructor stub
		this(username, password.toCharArray(), description);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isPasswordEqual(char[] password) {
		if(this.password.length == password.length) {
			for(int i = 0; i < password.length; i++) {
				if(this.password[i] != password[i])
					return false;
				return true;
			}
		}
		return false;
	}
	
	public boolean isPasswordEqual(String password) {
		return isPasswordEqual(password.toCharArray());
	}

	@Override
	public String toString() {
		return "{\"username\":\"" + username + "\", \"password\":\"" + Arrays.toString(password) + "\", \"description\":\"" + description + "\"}";
	}
	
}
