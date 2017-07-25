package edu.webapde.bean;

import java.util.ArrayList;

public class Photo {
	private int photoId;
	private int userId;
	private String title;
	private String description;
	private String filepath;
	private String privacy;
	private ArrayList<String> tags;
	private ArrayList<Integer> allowedUsers;
}
